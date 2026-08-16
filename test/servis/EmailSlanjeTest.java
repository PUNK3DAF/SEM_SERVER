package servis;

import domen.Ansambl;
import domen.Dogadjaj;
import domen.Mesto;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EmailSlanjeTest {

    @Test
    public void napraviTeloZaAzuriranDogadjajSadrziAzuriranje() {
        EmailSlanje emailSlanje = new EmailSlanje();

        Dogadjaj dogadjaj = new Dogadjaj();
        dogadjaj.setNaziv("Koncert");

        Mesto mesto = new Mesto();
        mesto.setNaziv("Beograd");
        dogadjaj.setMesto(mesto);

        Ansambl ansambl = new Ansambl();
        ansambl.setImeAnsambla("Simfonijski orkestar");
        dogadjaj.setAnsambl(ansambl);

        String telo = emailSlanje.napraviTelo(dogadjaj, true);

        assertTrue(telo.contains("ažuriran"));
        assertTrue(telo.contains("Koncert"));
    }
}
