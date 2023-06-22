package com.jonathan.java.demo.articles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	List<Article> findByTitleContainingIgnoreCase(String searchTitle);
}
