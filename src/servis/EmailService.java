package servis;

import domen.Dogadjaj;
import domen.Ucesce;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import konfiguracija.Konfiguracija;

public class EmailService {

    public void posaljiObavestenjeODogadjaju(Dogadjaj dogadjaj, List<Ucesce> ucesca) throws Exception {
        List<String> adrese = prikupiAdreseZaDogadjaj(dogadjaj, ucesca);
        if (adrese.isEmpty()) {
            return;
        }

        String host = Konfiguracija.getInstanca().getKonfiguracija("smtp.host");
        String port = Konfiguracija.getInstanca().getKonfiguracija("smtp.port");
        String username = Konfiguracija.getInstanca().getKonfiguracija("smtp.username");
        String password = Konfiguracija.getInstanca().getKonfiguracija("smtp.password");
        String from = Konfiguracija.getInstanca().getKonfiguracija("smtp.from");
        String auth = Konfiguracija.getInstanca().getKonfiguracija("smtp.auth");
        String starttls = Konfiguracija.getInstanca().getKonfiguracija("smtp.starttls");

        if (host == null || host.trim().isEmpty() || from == null || from.trim().isEmpty()) {
            throw new IllegalStateException("SMTP konfiguracija nije podesena.");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", host.trim());
        props.put("mail.smtp.port", (port == null || port.trim().isEmpty()) ? "587" : port.trim());
        props.put("mail.smtp.auth", String.valueOf(Boolean.parseBoolean(auth)));
        props.put("mail.smtp.starttls.enable", String.valueOf(Boolean.parseBoolean(starttls)));

        Session session;
        if (Boolean.parseBoolean(auth) && username != null && !username.trim().isEmpty()) {
            session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username.trim(), password == null ? "" : password);
                }
            });
        } else {
            session = Session.getInstance(props);
        }

        String subject = napraviNaslov(dogadjaj);
        String body = napraviTelo(dogadjaj);

        for (String adresa : adrese) {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from.trim()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(adresa));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            Transport.send(message);
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

    private String napraviNaslov(Dogadjaj dogadjaj) {
        String naziv = (dogadjaj == null || dogadjaj.getNaziv() == null || dogadjaj.getNaziv().trim().isEmpty())
                ? "Događaj" : dogadjaj.getNaziv().trim();
        return "Obaveštenje o događaju: " + naziv;
    }

    private String napraviTelo(Dogadjaj dogadjaj) {
        StringBuilder sb = new StringBuilder();
        sb.append("Poštovani,\n\n");
        sb.append("Kreiran je novi događaj za ansambl.\n\n");
        sb.append("Naziv: ").append(dogadjaj == null || dogadjaj.getNaziv() == null ? "" : dogadjaj.getNaziv()).append("\n");
        sb.append("Datum: ").append(dogadjaj == null || dogadjaj.getDatum() == null ? "" : dogadjaj.getDatum()).append("\n");
        sb.append("Mesto: ").append(dogadjaj == null || dogadjaj.getMesto() == null ? "" : dogadjaj.getMesto().getNaziv()).append("\n");
        sb.append("Ansambl: ").append(dogadjaj == null || dogadjaj.getAnsambl() == null ? "" : dogadjaj.getAnsambl().getImeAnsambla()).append("\n\n");
        sb.append("Pozdrav.");
        return sb.toString();
    }
}
