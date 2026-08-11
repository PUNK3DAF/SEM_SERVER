package operacije.dogadjaj;

import domen.Dogadjaj;
import domen.Mesto;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class KreirajDogadjajTest {

    private KreirajDogadjaj operacija;
    private Dogadjaj dogadjaj;

    @Before
    public void setUp() {
        operacija = new KreirajDogadjaj();
        dogadjaj = new Dogadjaj();
        dogadjaj.setNaziv("Koncert");
        dogadjaj.setDatum(LocalDate.of(2026, 8, 11));

        Mesto mesto = new Mesto();
        mesto.setMestoID(3);
        dogadjaj.setMesto(mesto);
    }

    @Test
    public void predusloviIspravnogDogadjaja() throws Exception {
        operacija.preduslovi(dogadjaj);
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanTipParametra() throws Exception {
        operacija.preduslovi(new Object());
    }

    @Test(expected = Exception.class)
    public void predusloviPrazanNaziv() throws Exception {
        dogadjaj.setNaziv(" ");
        operacija.preduslovi(dogadjaj);
    }

    @Test(expected = Exception.class)
    public void predusloviNedostajeDatum() throws Exception {
        dogadjaj.setDatum(null);
        operacija.preduslovi(dogadjaj);
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravnoMesto() throws Exception {
        Mesto mesto = new Mesto();
        mesto.setMestoID(0);
        dogadjaj.setMesto(mesto);
        operacija.preduslovi(dogadjaj);
    }
}
