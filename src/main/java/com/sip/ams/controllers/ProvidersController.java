package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/provider")
public class ProvidersController {
	
	private final ProviderRepository providerRepository;

	@Autowired
	public ProvidersController(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;

	}

	@GetMapping("/list")
	public String listProviders(Model model)
			{
		List<Provider> ls = (List<Provider>) providerRepository.findAll();
		
		if (ls.isEmpty())
			ls = null;
		model.addAttribute("providers", ls);
		
		return "provider/listProviders";
	}
	
	@PostMapping("/searchAdress")
	public String findProviderByAdress (Model model, @RequestParam(name = "adress", required = false) String adress) {
		//List<Provider> ls = (List<Provider>) providerRepository.findAll();
		List<Provider> ls = (List<Provider>) providerRepository.findProvidersByAdress(adress);
		if (ls.isEmpty())
			ls = null;
		model.addAttribute("adress", adress);
		model.addAttribute("providers", ls);
		return "provider/listProviders";
	}

	@GetMapping("/add")
	// @ResponseBody
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("/add")
	public String addProvider(@Valid Provider provider, BindingResult bindingResult) {
		System.out.println("binding result ");
		System.out.println("binding has errors " + bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			return "provider/addProvider";
		}
		providerRepository.save(provider);
		return "redirect:list";

	}

	@GetMapping("/edit/{id}")
	public String showUpdateProviderForm(@PathVariable("id") Long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider id" + id));
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}

	@PostMapping("/update")
	public String updateProvider(@Valid Provider provider, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "provider/updateProvider";
		}
		providerRepository.save(provider);
		return "redirect:list";

	}

	@GetMapping("/delete/{id}")
	public String deleteProvider(@PathVariable("id") Long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider id" + id));
		providerRepository.delete(provider);
		return "redirect:../list";
	}

	@GetMapping("/show/{id}")
	public String showArticles(@PathVariable("id") Long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider id " + id));
		List<Article> listArticles = (List<Article>) providerRepository.findArticlesByProvider(id);
		model.addAttribute("articles", listArticles);
		model.addAttribute("provider", provider);
		return "provider/showArticles";
	}
	/*
	
	@GetMapping("/list")
	public String listProviders(Model model)
			{
		 
		
		return "provider/listProviders";
	}*/

}
