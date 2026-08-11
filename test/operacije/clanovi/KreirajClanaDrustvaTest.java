package operacije.clanovi;

import domen.ClanDrustva;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KreirajClanaDrustvaTest {

    private KreirajClanaDrustva operacija;
    private ClanDrustva clan;

    @Before
    public void setUp() {
        operacija = new KreirajClanaDrustva();
        clan = new ClanDrustva();
        clan.setClanIme("Milan Petrovic");
        clan.setClanPol("m");
        clan.setClanGod(25);
        clan.setClanBrTel("0601234567");
        clan.setClanEmail("Milan.Petrovic@gmail.com");
    }

    @Test
    public void predusloviIspravnogClana() throws Exception {
        operacija.preduslovi(clan);
        assertEquals("milan.petrovic@gmail.com", clan.getClanEmail());
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanTipParametra() throws Exception {
        operacija.preduslovi(new Object());
    }

    @Test(expected = Exception.class)
    public void predusloviPraznoIme() throws Exception {
        clan.setClanIme("");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviSamoImeBezPrezimena() throws Exception {
        clan.setClanIme("Milan");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanPol() throws Exception {
        clan.setClanPol("X");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviGodineVanOpsega() throws Exception {
        clan.setClanGod(121);
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviPrazanTelefon() throws Exception {
        clan.setClanBrTel("");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviPrazanEmail() throws Exception {
        clan.setClanEmail("");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanEmail() throws Exception {
        clan.setClanEmail("milan.petrovic");
        operacija.preduslovi(clan);
    }
}
