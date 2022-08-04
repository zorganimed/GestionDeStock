package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

import java.util.List;

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

	@GetMapping("list")
	public String listProviders(Model model) {
		List<Provider> ls = (List<Provider>) providerRepository.findAll();
		if (ls.isEmpty())
			ls = null;
		model.addAttribute("providers", ls);
		return "provider/listProviders";
	}

	@GetMapping("add")
	// @ResponseBody
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("/add")
	public String addProvider(Provider provider, BindingResult bindingResult) {
		System.out.println("binding result ");
		System.out.println("binding has errors " + bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			return "provider/addProvider";
		}
		providerRepository.save(provider);
		return "redirect:list";

	}

	@GetMapping("edit/{id}")
	public String showUpdateProviderForm(@PathVariable("id") Long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider id" + id));
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}

	@PostMapping("update")
	public String updateProvider(Provider provider, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "provide/addProvider";
		}
		providerRepository.save(provider);
		return "redirect:list";

	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") Long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider id" + id));
		providerRepository.delete(provider);
		return "redirect:../list";
	}

}
