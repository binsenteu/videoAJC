package videoAJC.model;

public class Realisation {
    public RealisationKey id;

    public Realisation() {
    }

    public Realisation(RealisationKey id) {
        this.id = id;
    }

    public RealisationKey getId() {
        return id;
    }

    public void setId(RealisationKey id) {
        this.id = id;
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
        Realisation other = (Realisation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
