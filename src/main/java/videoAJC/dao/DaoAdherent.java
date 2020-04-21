package videoAJC.dao;

import java.util.List;

import videoAJC.model.Adherent;
import videoAJC.model.Adresse;
import videoAJC.model.Article;

public interface DaoAdherent extends DaoGeneric<Adherent, Integer> {
	List<Article> findArticles(Adherent obj);
	
	void updateArticles(Adherent obj, List<Article> articles);

	Adresse findAdresse(Adherent obj);

	void updateAdresse(Adherent obj, Adresse adresse);
	
}
