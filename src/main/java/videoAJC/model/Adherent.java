package videoAJC.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import videoAJC.context.Context;

public class Adherent {
	private Integer id;
	private String prenom;
	private String nom;
	private Civilite civilite;
	private Adresse adresse;
	
	public Adherent(Integer id, String prenom, String nom, Civilite civilite, Adresse adresse) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.civilite = civilite;
		this.adresse = adresse;
	}
	
	public Adherent(Integer id, String prenom, String nom, Adresse adresse) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
	public List<Article> getArticles() {
		
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement("select * from articles where id = ?")) {
			ps.setInt(1, this.id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				article = new Article(rs.getInt("id_article"), rs.getInt("nb_disques"));
				articles.add(article);
				//TODO - need gestion dvd bluray
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return articles;
	}

	
	public void setArticles(List<Article> articles) {
		//TODO
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adherent other = (Adherent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
