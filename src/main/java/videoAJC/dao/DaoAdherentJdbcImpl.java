package videoAJC.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import videoAJC.context.Context;
import videoAJC.model.Adherent;
import videoAJC.model.Adresse;
import videoAJC.model.Article;
import videoAJC.model.Bluray;
import videoAJC.model.Civilite;
import videoAJC.model.Dvd;

public class DaoAdherentJdbcImpl implements DaoAdherent {

	@Override
	public void insert(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into adherents (id_adherents, prenom, nom, civilite, numero_rue, adresse_rue, code_postal, ville) values (nextval('seq_adherent'),?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			if (obj.getAdresse() != null) {
				ps.setInt(4, obj.getAdresse().getNumero());
				ps.setString(5, obj.getAdresse().getRue());
				ps.setString(6, obj.getAdresse().getCodePostal());
				ps.setString(7, obj.getAdresse().getVille());
			} else {
				ps.setNull(4, Types.INTEGER);
				ps.setNull(5, Types.VARCHAR);
				ps.setNull(6, Types.VARCHAR);
				ps.setNull(7, Types.VARCHAR);
			}

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
				"update adherents set prenom = ?, nom = ?, civilite = ?, numero_rue = ?, adresse_rue = ?, code_postal = ?, ville = ? where id_adherents = ?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			if (obj.getAdresse() != null) {
				ps.setInt(4, obj.getAdresse().getNumero());
				ps.setString(5, obj.getAdresse().getRue());
				ps.setString(6, obj.getAdresse().getCodePostal());
				ps.setString(7, obj.getAdresse().getVille());
			} else {
				ps.setNull(4, Types.INTEGER);
				ps.setNull(5, Types.VARCHAR);
				ps.setNull(6, Types.VARCHAR);
				ps.setNull(7, Types.VARCHAR);
			}
			
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
				.prepareStatement("delete from adherents where id_adherents = ?")) {
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
				prepareStatement("select id_adherents, prenom, nom, civilite, numero_rue, adresse_rue, code_postal, ville from adherents where id_adherents = ?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				adresse = new Adresse(rs.getInt("numero_rue"), rs.getString("adresse_rue"), rs.getString("code_postal"), rs.getString("ville"));
				adherent = new Adherent(rs.getInt("id_adherents"), rs.getString("prenom"), rs.getString("nom"), adresse);
				if (rs.getString("civilite") != null) {
					adherent.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
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
				adherent = new Adherent(rs.getInt("id_adherents"), rs.getString("prenom"), rs.getString("nom"), adresse);
				if (rs.getString("civilite") != null) {
					adherent.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
				adherents.add(adherent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return adherents;
	}

	@Override
	public List<Article> findArticles(Adherent obj) {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement("select * from articles where id_emprunteur = ?")) {
			ps.setInt(1, obj.getId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				if (rs.getString("dvd_ou_bluray").equals("b")) {
					article = new Bluray(rs.getInt("id_article"), rs.getInt("nb_disques"), rs.getBoolean("trois_d"));
				} else if (rs.getString("dvd_ou_bluray").equals("d")) {
					article = new Dvd(rs.getInt("id_article"), rs.getInt("nb_disques"), rs.getBoolean("bonus"));
				}
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return articles;
	}

	@Override
	public void updateArticles(Adherent obj, List<Article> articles) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement("update articles set id_emprunteur = ? where id_article = ?")) {
			for (Article article : articles) {
				ps.setInt(1, obj.getId());
				ps.setInt(2, article.getId());
				ps.executeUpdate();
			}
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
