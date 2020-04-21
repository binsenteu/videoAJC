package videoAJC.dao.realisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import videoAJC.context.Context;
import videoAJC.dao.realisation.DaoRealisation;
import videoAJC.dao.realisation.DaoRealisationFactory;
import videoAJC.model.Realisateur;
import videoAJC.model.Realisation;

public class DaoRealisateurJdbcImpl implements DaoRealisateur {

    @Override
    public void insert(Realisateur obj) {

        // Insertion dans la table realisateurs du réalisateur
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

        // Ajout dans la table realisation lien id_realisateur id_film
        DaoRealisation daoRealisation = DaoRealisationFactory.getInstance();
        for (Realisation real : obj.getRealisation()) {
            daoRealisation.insert(real.getId());
        }

        Context.destroy();
    }

    @Override
    public Realisateur update(Realisateur obj) {
        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("UPDATE realisateurs SET prenom=?, nom=? WHERE id_realisateur=?")) {

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

        // si on supprime un realisateur, on supprime les films associés dans la table
        // realisations
        DaoRealisation daoRealisation = DaoRealisationFactory.getInstance();
        daoRealisation.deleteFromRealisateur(key);

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
        List<Realisateur> listeRealisateur = new ArrayList<Realisateur>();

        try (Statement st = Context.getInstance().getConnection().createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM realisateurs");
            while (rs.next()) {
                // java.sql.Date derive de java.util.date, pas besoin donc de la transformer
                listeRealisateur
                        .add(new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();
        return listeRealisateur;
    }

    @Override
    public List<Realisateur> findByNom(String nom) {
        List<Realisateur> listeRealisateur = new ArrayList<Realisateur>();

        Realisateur r = null;

        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("SELECT * FROM realisateurs WHERE nom=?")) {
            ps.setString(1, nom);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom"));
                listeRealisateur.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();

        return listeRealisateur;
    }

    @Override
    public List<Realisateur> findByPrenom(String prenom) {
        List<Realisateur> listeRealisateur = new ArrayList<Realisateur>();

        Realisateur r = null;

        try (PreparedStatement ps = Context.getInstance().getConnection()
                .prepareStatement("SELECT * FROM realisateurs WHERE prenom=?")) {
            ps.setString(1, prenom);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom"));
                listeRealisateur.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Context.destroy();

        return listeRealisateur;
    }

}
