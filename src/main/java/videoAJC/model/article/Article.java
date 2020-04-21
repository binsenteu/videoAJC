package videoAJC.model.article;

import videoAJC.model.Film;
import videoAJC.model.adherent.Adherent;

public abstract class Article {
	private Integer id;
	private Integer nbDisques;
	
	public Article(Integer id,Integer nbDisques) {
		this.id = id;
		this.nbDisques = nbDisques;
	}
	
	public Article(Integer id) {
		this.id = id;

	}
	
	public Article() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNbDisques() {
		return nbDisques;
	}
	public void setNbDisques(Integer nbDisques) {
		this.nbDisques = nbDisques;
	}
	
	public Adherent getEmprunteur(Adherent adherent) {
		return adherent;
	}
	public void setEmprunteur(Adherent emprunteur ) {
		
	}
	
	public Film getFilm(Film film) {
		return film;
	}
	public void setFilm(Film film) {
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nbDisques == null) ? 0 : nbDisques.hashCode());
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDisques == null) {
			if (other.nbDisques != null)
				return false;
		} else if (!nbDisques.equals(other.nbDisques))
			return false;
		return true;
	}
	
	
	

}
