package videoAJC.model;

import java.util.ArrayList;
import java.util.List;

import videoAJC.dao.realisation.DaoRealisation;
import videoAJC.dao.realisation.DaoRealisationFactory;

public class Realisateur {
    private Integer id;
    private String prenom;
    private String nom;
    private List<Realisation> realisation;

    public Realisateur(Integer id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public Realisateur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Realisateur() {
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

    public List<Realisation> getRealisation() {

        // On vide la liste de réalisation présente au cas où
        this.realisation = new ArrayList<Realisation>();
        DaoRealisation daoRealisation = DaoRealisationFactory.getInstance();
        this.realisation = daoRealisation.findAllFilm(this);

        return this.realisation;
    }

    public void setRealisation(List<Realisation> realisation) {
        this.realisation = realisation;
        DaoRealisation daoRealisation = DaoRealisationFactory.getInstance();
        for (Realisation real : realisation) {
            daoRealisation.insert(real.getId());
        }
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
        Realisateur other = (Realisateur) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
