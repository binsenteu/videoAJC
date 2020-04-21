package videoAJC.model.article;

public class Bluray extends Article{
	private Boolean troisD;

	public Bluray(Boolean troisD) {
		this.troisD = troisD;

	}
	public Bluray() {
		this.troisD = troisD;

	}
	
	
	public Boolean getTroisD() {
		return troisD;
	}

	public void setTroisD(Boolean troisD) {
		this.troisD = troisD;
	}

	
}
