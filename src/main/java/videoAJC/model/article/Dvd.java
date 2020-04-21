package videoAJC.model.article;

public class Dvd extends Article{
    private Boolean bonus;

    public Dvd(Boolean bonus) {
        super();
        this.bonus = bonus;

    }

    public Dvd() {
    }

    public Dvd(Integer id, Integer nbDisques, Boolean bonus) {
        super(id, nbDisques);
        this.bonus = bonus;
    }

}
