package servis;

import domen.Dogadjaj;
import domen.Ucesce;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import konfiguracija.Konfiguracija;

public class EmailSlanje {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public void posaljiObavestenjeODogadjaju(Dogadjaj dogadjaj, List<Ucesce> ucesca) throws Exception {
        posaljiObavestenjeODogadjaju(dogadjaj, ucesca, false);
    }

    public void posaljiObavestenjeODogadjaju(Dogadjaj dogadjaj, List<Ucesce> ucesca, boolean azuriran) throws Exception {
        List<String> adrese = prikupiAdreseZaDogadjaj(dogadjaj, ucesca);
        System.out.println("[MAIL] Događaj=" + (dogadjaj == null ? "null" : dogadjaj.getNaziv())
                + ", ansamblId=" + (dogadjaj == null || dogadjaj.getAnsambl() == null ? "null" : dogadjaj.getAnsambl().getAnsamblID())
                + ", azuriran=" + azuriran
                + ", broj_primalaca=" + adrese.size());
        if (adrese.isEmpty()) {
            System.out.println("[MAIL] Nema primalaca za slanje. Proveri učešća i članEmail.");
            return;
        }

        String host = Konfiguracija.getInstanca().getKonfiguracija("smtp.host");
        String port = Konfiguracija.getInstanca().getKonfiguracija("smtp.port");
        String username = Konfiguracija.getInstanca().getKonfiguracija("smtp.username");
        String password = Konfiguracija.getInstanca().getKonfiguracija("smtp.password");
        String from = Konfiguracija.getInstanca().getKonfiguracija("smtp.from");
        String auth = Konfiguracija.getInstanca().getKonfiguracija("smtp.auth");
        String starttls = Konfiguracija.getInstanca().getKonfiguracija("smtp.starttls");
        String debug = Konfiguracija.getInstanca().getKonfiguracija("smtp.debug");

        if (host == null || host.trim().isEmpty() || from == null || from.trim().isEmpty()) {
            throw new IllegalStateException("SMTP konfiguracija nije podešena.");
        }

        if (Boolean.parseBoolean(auth) && (username == null || username.trim().isEmpty())) {
            throw new IllegalStateException("SMTP auth je uključen, ali smtp.username nije unet.");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", host.trim());
        props.put("mail.smtp.port", (port == null || port.trim().isEmpty()) ? "587" : port.trim());
        props.put("mail.smtp.auth", String.valueOf(Boolean.parseBoolean(auth)));
        props.put("mail.smtp.starttls.enable", String.valueOf(Boolean.parseBoolean(starttls)));
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");

        final String normalizedPassword = password == null ? "" : password.replaceAll("\\s+", "");

        Session session;
        if (Boolean.parseBoolean(auth) && username != null && !username.trim().isEmpty()) {
            session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username.trim(), normalizedPassword);
                }
            });
        } else {
            session = Session.getInstance(props);
        }
        session.setDebug(Boolean.parseBoolean(debug));

        System.out.println("[MAIL] SMTP host=" + host + ", port=" + props.getProperty("mail.smtp.port")
                + ", auth=" + props.getProperty("mail.smtp.auth")
                + ", starttls=" + props.getProperty("mail.smtp.starttls.enable")
                + ", from=" + from);

        String subject = napraviNaslov(dogadjaj, azuriran);
        String body = napraviTelo(dogadjaj, azuriran);

        for (String adresa : adrese) {
            if (!EMAIL_PATTERN.matcher(adresa).matches()) {
                System.err.println("[MAIL] Preskačem neispravnu email adresu: " + adresa);
                continue;
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from.trim()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(adresa));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            System.out.println("[MAIL] Pokušaj slanja na: " + adresa);
            Transport.send(message);
            System.out.println("[MAIL] Uspešno poslato na: " + adresa);
        }
    }

    private List<String> prikupiAdreseZaDogadjaj(Dogadjaj dogadjaj, List<Ucesce> ucesca) {
        Set<String> adrese = new LinkedHashSet<>();
        if (dogadjaj == null || dogadjaj.getAnsambl() == null || ucesca == null) {
            return new ArrayList<>();
        }

        int ansamblId = dogadjaj.getAnsambl().getAnsamblID();
        for (Ucesce u : ucesca) {
            if (u == null || u.getAnsambl() == null || u.getAnsambl().getAnsamblID() != ansamblId) {
                continue;
            }
            if (u.getClan() == null || u.getClan().getClanEmail() == null) {
                continue;
            }
            String email = u.getClan().getClanEmail().trim();
            if (!email.isEmpty()) {
                adrese.add(email);
            }
        }

        return new ArrayList<>(adrese);
    }

    private String napraviNaslov(Dogadjaj dogadjaj, boolean azuriran) {
        String naziv = (dogadjaj == null || dogadjaj.getNaziv() == null || dogadjaj.getNaziv().trim().isEmpty())
                ? "Događaj" : dogadjaj.getNaziv().trim();
        return "Obaveštenje o događaju: " + naziv;
    }

    String napraviTelo(Dogadjaj dogadjaj, boolean azuriran) {
        StringBuilder sb = new StringBuilder();
        sb.append("Poštovani,\n\n");
        if (azuriran) {
            sb.append("Događaj je ažuriran za ansambl.\n\n");
        } else {
            sb.append("Kreiran je novi događaj za ansambl.\n\n");
        }
        sb.append("Naziv: ").append(dogadjaj == null || dogadjaj.getNaziv() == null ? "" : dogadjaj.getNaziv()).append("\n");
        sb.append("Datum: ").append(dogadjaj == null || dogadjaj.getDatum() == null ? "" : dogadjaj.getDatum()).append("\n");
        sb.append("Mesto: ").append(dogadjaj == null || dogadjaj.getMesto() == null ? "" : dogadjaj.getMesto().getNaziv()).append("\n");
        sb.append("Ansambl: ").append(dogadjaj == null || dogadjaj.getAnsambl() == null ? "" : dogadjaj.getAnsambl().getImeAnsambla()).append("\n\n");
        sb.append("Molimo vas da proverite promene u kalendaru.\n\n");
        sb.append("Pozdrav.");
        return sb.toString();
    }

    private String napraviTelo(Dogadjaj dogadjaj) {
        return napraviTelo(dogadjaj, false);
    }
}
