package repository.db.impl;

import domen.ApstraktniDomenskiObjekat;
import domen.Ansambl;
import domen.Dogadjaj;
import domen.Ucesce;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;

public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        
        // Za Ansambl, dodaj LEFT JOIN sa zanr tabelom
        if (param instanceof Ansambl) {
            upit = "SELECT ansambl.*, zanr.naziv as zanrNaziv FROM ansambl LEFT JOIN zanr ON ansambl.zanr = zanr.zanrID";
        }
        // Za Dogadjaj, dodaj LEFT JOIN sa mesto i ansambl tabelom
        else if (param instanceof Dogadjaj) {
            upit = "SELECT dogadjaj.*, mesto.naziv as mestoNaziv, ansambl.imeAnsambla FROM dogadjaj " +
                   "LEFT JOIN mesto ON dogadjaj.mesto = mesto.mestoID " +
                   "LEFT JOIN ansambl ON dogadjaj.ansambl = ansambl.ansamblID";
        }
        // Za Ucesce, dodaj LEFT JOIN sa clan, ansambl i uloga tabelom
        else if (param instanceof Ucesce) {
            upit = "SELECT ucesce.clan, ucesce.ansambl, ucesce.uloga as ulogaID, clandrustva.clanIme, ansambl.imeAnsambla, uloga.naziv as ulogaNaziv " +
                   "FROM ucesce " +
                   "LEFT JOIN clandrustva ON ucesce.clan = clandrustva.clanID " +
                   "LEFT JOIN ansambl ON ucesce.ansambl = ansambl.ansamblID " +
                   "LEFT JOIN uloga ON ucesce.uloga = uloga.ulogaID";
        }
        
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " ( " + param.vratiKoloneZaUbacivanje() + " ) VALUES ( " + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu()
                + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
