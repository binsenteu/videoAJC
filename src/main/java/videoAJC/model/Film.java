package videoAJC.model;

import java.util.Date;

public class Film {
		private Integer id;
		private String titre;
		private Date dateDeSortie;
		
		public Film(Integer id, String titre, Date dateDeSortie) {
			this.id = id;
			this.titre = titre;
			this.dateDeSortie = dateDeSortie;
		}

		public Film(String titre, Date dateDeSortie) {
			this.titre = titre;
			this.dateDeSortie = dateDeSortie;
		}

		public Film(String titre) {
			this.titre = titre;
		}
		
		public Film() {
			
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public Date getDateDeSortie() {
			return dateDeSortie;
		}

		public void setDateDeSortie(Date dateDeSortie) {
			this.dateDeSortie = dateDeSortie;
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
			Film other = (Film) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
		
}
