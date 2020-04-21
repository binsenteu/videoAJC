package videoAJC.dao.realisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import videoAJC.context.Context;
import videoAJC.model.Realisateur;

public class DaoRealisateurJdbcImpl implements DaoRealisateur {

    @Override
    public void insert(Realisateur obj) {
        try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
                "INSERT INTO realisateurs(id_realisateur, prenom, nom) VALUES (nextval('seq_realisateur'), ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, obj.getPrenom());
            ps.setString(2, obj.getNom());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getInt(1));
            }
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

    @Override
    public Realisateur update(Realisateur obj) {
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("UPDATE realisateur SET prenom=?, nom=? WHERE id_realisateur=?")) {

            ps.setString(1, obj.getPrenom());
            ps.setString(2, obj.getNom());

            ps.setInt(3, obj.getId());

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
        return obj;
    }

    @Override
    public void delete(Realisateur obj) {
        this.deleteById(obj.getId());

    }

    @Override
    public void deleteById(Integer key) {
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("DELETE FROM realisateurs where id_realisateur=?")) {
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

    @Override
    public Optional<Realisateur> findByKey(Integer key) {
        Realisateur r = null;
        
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("SELECT * FROM realisateurs WHERE id_realisateur=?")) {
            ps.setInt(1, key);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();

        return Optional.ofNullable(r);
    }

    @Override
    public List<Realisateur> findAll() {
        List<Realisateur> listeFormation = new ArrayList<Realisateur>();

        try (Statement st = Context.getInstance().getConnection().createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM realisateurs");
            while (rs.next()) {
                // java.sql.Date derive de java.util.date, pas besoin donc de la transformer
                listeFormation
                        .add(new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();
        return listeFormation;
    }

    @Override
    public List<Realisateur> findByName(String nom) {
        // TODO Auto-generated method stub
        return null;
    }

}
