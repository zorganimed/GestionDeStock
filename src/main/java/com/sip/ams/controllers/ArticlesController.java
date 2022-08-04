package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

@Controller
@RequestMapping("/article")
public class ArticlesController {

	private final ProviderRepository providerRepository;
	private final ArticleRepository articleRepository;

	public ArticlesController(ProviderRepository providerRepository, ArticleRepository articleRepository) {
		super();
		this.providerRepository = providerRepository;
		this.articleRepository = articleRepository;
	}

	@GetMapping("/list")
	public String listArticles(Model model) {

		List<Article> ls = (List<Article>) articleRepository.findAll();
		if (ls.isEmpty())
			ls = null;
		model.addAttribute("articles", ls);
		return "article/listArticles";
	}
	
	@GetMapping("/add")
	public String showAddArticleForm(Model model) {
		model.addAttribute("providers",  providerRepository.findAll());
		Article article = new Article();
		model.addAttribute("article", article);
		return "article/addArticle";
	}
	
	@PostMapping("/add")
	public String addArticle(Article article,BindingResult bindingResult,@RequestParam(name = "providerId",required = false) Long providerId) {

		Provider provider = providerRepository.findById(providerId).orElseThrow(()->new IllegalArgumentException("Invalid provider id"+providerId));
		if(bindingResult.hasErrors()) 
			return "article/addArticle";
		
		article.setProvider(provider);
		articleRepository.save(article);
		return "redirect:list";
	}
	
	
	@GetMapping("edit/{id}")
	public String showUpdateArticleForm(Model model,@PathVariable( name = "id") Long id) {
		Article article = articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid article id"+id));
		model.addAttribute("article", article);
		model.addAttribute("providers", providerRepository.findAll());
		model.addAttribute("idProvider", article.getProvider().getId());
		return "article/updateArticle";
	}
	
	
	@PostMapping("update")
	public String updateArticle(Model model, @Valid Article article, BindingResult bindingResult,@RequestParam(name = "providerId", required = false)Long idProvider) {
		Provider provider = providerRepository.findById(idProvider).orElseThrow(()->new IllegalArgumentException("Invalid provider id"+idProvider));
		if(bindingResult.hasErrors()) {
			return "article/updateArticle"; 
		}
		article.setProvider(provider);
		articleRepository.save(article);
 		return"redirect:list";
	}
	
	@GetMapping("delete/{id}")
	public String deleteArticle(@PathVariable( name = "id") Long id) {
		Article article = articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid article id"+id));
		articleRepository.delete(article);
		return "redirect:../list";
	}
	
	
	

}
