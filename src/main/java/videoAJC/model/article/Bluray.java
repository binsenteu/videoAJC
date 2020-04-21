package videoAJC.model.article;

public class Bluray extends Article{
    private Boolean troisD;

    public Bluray(Boolean troisD) {
        super();
        this.troisD = troisD;
    }

    public Bluray() {
    }

    public Bluray(Integer id, Integer nbDisques, Boolean troisD) {
        super(id, nbDisques);
        this.troisD = troisD;
    }

    public Boolean getTroisD() {
        return troisD;
    }

    public void setTroisD(Boolean troisD) {
        this.troisD = troisD;
    }
}
