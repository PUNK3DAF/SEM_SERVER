package operacije.clanovi;

import domen.ClanDrustva;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IzmeniClanaDrustvaTest {

    private IzmeniClanaDrustva operacija;
    private ClanDrustva clan;

    @Before
    public void setUp() {
        operacija = new IzmeniClanaDrustva();
        clan = new ClanDrustva();
        clan.setClanID(12);
        clan.setClanIme("Ana Jovanovic");
        clan.setClanPol("Z");
        clan.setClanGod(30);
        clan.setClanBrTel("0619876543");
        clan.setClanEmail("ana.jovanovic@gmail.com");
    }

    @Test
    public void predusloviIspravneIzmene() throws Exception {
        operacija.preduslovi(clan);
        assertEquals("ana.jovanovic@gmail.com", clan.getClanEmail());
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanTipParametra() throws Exception {
        operacija.preduslovi(new Object());
    }

    @Test(expected = Exception.class)
    public void predusloviPrazanEmail() throws Exception {
        clan.setClanEmail(null);
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviNeispravanEmail() throws Exception {
        clan.setClanEmail("ana.jovanovic@provider");
        operacija.preduslovi(clan);
    }

    @Test(expected = Exception.class)
    public void predusloviPrazanTelefon() throws Exception {
        clan.setClanBrTel("   ");
        operacija.preduslovi(clan);
    }
}
