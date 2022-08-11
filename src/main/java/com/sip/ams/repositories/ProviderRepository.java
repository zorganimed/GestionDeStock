package com.sip.ams.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
	public Provider findProviderByAdress(String adr);

	@Query("select a from Article a where a.provider.id=:x ")
	List<Article>findArticlesByProvider(@Param("x") Long id);
	
	@Query("select p from Provider p where p.adress like %?1")
	List<Provider> findProvidersByAdress(String adr);
	
}
