package videoAJC.dao.realisation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import videoAJC.context.Context;
import videoAJC.model.Film;
import videoAJC.model.Realisateur;
import videoAJC.model.Realisation;
import videoAJC.model.RealisationKey;

public class DaoRealisationJdbcImpl implements DaoRealisation {

    public void insert(RealisationKey obj) {

        try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
                "INSERT INTO realisations(id_realisateur, id_film) VALUES(?, ?)")) {
            System.out.println("generated key : " + obj.getRealisateur().getId());
            System.out.println("film key : " + obj.getFilm().getId());
            ps.setInt(1, obj.getRealisateur().getId());
            ps.setInt(2, obj.getFilm().getId());
            ps.executeUpdate();
            Context.getInstance().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Context.getInstance().getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        Context.destroy();

    }

    public void deleteFromFilm(Integer key) {
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("DELETE FROM realisations where id_film=?")) {
            ps.setInt(1, key);

            ps.executeUpdate();

            Context.getInstance().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Context.getInstance().getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        Context.destroy();
    }

    public void deleteFromRealisateur(Integer key) {
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("DELETE FROM realisations where id_realisateur=?")) {
            ps.setInt(1, key);

            ps.executeUpdate();

            Context.getInstance().getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Context.getInstance().getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        Context.destroy();
    }

    public List<Realisation> findAll(Realisateur realisateur) {
        List<Realisation> listeRealisation = new ArrayList();
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement(
                        "SELECT * FROM films f JOIN realisation r on f.id_film=r.id_film  WHERE r.id_realisateur=?")) {
            ps.setInt(1, realisateur.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Film f = new Film(rs.getInt("id_film"), rs.getString("titre"), rs.getDate("date_de_sortie"));
                RealisationKey realKey = new RealisationKey(realisateur, f);
                Realisation realisation = new Realisation(realKey);
                listeRealisation.add(realisation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();
        return listeRealisation;
    }
}
