package videoAJC.dao.adherent;

import java.util.List;

import videoAJC.dao.DaoGeneric;
import videoAJC.model.adherent.Adherent;
import videoAJC.model.article.Article;

public interface DaoAdherent extends DaoGeneric<Adherent, Integer> {
	List<Article> findArticles(Adherent obj);
	
	void updateArticles(Adherent obj, List<Article> articles);
	
}
