package videoAJC.model.adherent;

public enum Civilite {
	M("monsieur"), MME("madame"), MLLE("mademoiselle");
	
	private String label;
	
	private Civilite(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
