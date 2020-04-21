package videoAJC.model;

public class RealisationKey {
    private Realisateur realisateur;
    private Film film;

    public RealisationKey(Realisateur realisateur, Film film) {
        this.realisateur = realisateur;
        this.film = film;
    }

    public RealisationKey() {
    }

    public Realisateur getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((film == null) ? 0 : film.hashCode());
        result = prime * result + ((realisateur == null) ? 0 : realisateur.hashCode());
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
        RealisationKey other = (RealisationKey) obj;
        if (film == null) {
            if (other.film != null)
                return false;
        } else if (!film.equals(other.film))
            return false;
        if (realisateur == null) {
            if (other.realisateur != null)
                return false;
        } else if (!realisateur.equals(other.realisateur))
            return false;
        return true;
    }

}
