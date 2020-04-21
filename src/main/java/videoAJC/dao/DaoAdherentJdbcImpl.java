package videoAJC.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import videoAJC.context.Context;
import videoAJC.model.Adherent;
import videoAJC.model.Adresse;
import videoAJC.model.Article;
import videoAJC.model.Civilite;

public class DaoAdherentJdbcImpl implements DaoAdherent {

	@Override
	public void insert(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into adherents (id, prenom, nom, civilite, numero_rue, adresse_rue, code_postal, ville) values (nextval('seq_adherent'),?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getCivilite().toString());
			ps.setInt(4, obj.getAdresse().getNumero());
			ps.setString(5, obj.getAdresse().getRue());
			ps.setString(6, obj.getAdresse().getCodePostal());
			ps.setString(7, obj.getAdresse().getVille());

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
	public Adherent update(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"update adherents set prenom = ?, nom = ?, civilite = ?, numero_rue = ?, adresse_rue = ?, code_postal = ?, ville = ? where id = ?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getCivilite().toString());
			ps.setInt(4, obj.getAdresse().getNumero());
			ps.setString(5, obj.getAdresse().getRue());
			ps.setString(6, obj.getAdresse().getCodePostal());
			ps.setString(7, obj.getAdresse().getVille());
			ps.setInt(8, obj.getId());

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
	public void delete(Adherent obj) {
		deleteById(obj.getId());

	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from adherents where id = ?")) {
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
	public Optional<Adherent> findByKey(Integer key) {
		Adherent adherent = null;
		Adresse adresse = null;
		
		try (PreparedStatement ps = Context.getInstance().getConnection().
				prepareStatement("select id, prenom, nom, civilite, numero_rue, adresse_rue, code_postal, ville from adherents where id = ?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				adresse = new Adresse(rs.getInt("numero_rue"), rs.getString("adresse_rue"), rs.getString("code_postal"), rs.getString("ville"));
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"), Civilite.valueOf(rs.getString("civilite")), adresse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(adherent);
	}

	@Override
	public List<Adherent> findAll() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		Adherent adherent = null;
		Adresse adresse = null;
		
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			
			ResultSet rs = st.executeQuery("select * from adherents");
			
			while (rs.next()) {
				adresse = new Adresse(rs.getInt("numero_rue"), rs.getString("adresse_rue"), rs.getString("code_postal"), rs.getString("ville"));
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"), Civilite.valueOf(rs.getString("civilite")), adresse);
				adherents.add(adherent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return adherents;
	}



}
