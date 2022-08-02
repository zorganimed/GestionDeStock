package com.sip.ams.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Provider;

@Controller
@RequestMapping("/provider")
public class ProvidersController {
	
		
	@RequestMapping("/list")
	//@ResponseBody
	public String providersList(Model model) {
		
		ArrayList<String> providers = new ArrayList<>();
		providers.add("Samsung");
		providers.add("Dell");
		providers.add("Acer");
		providers.add("Asus");
		String provider = "Lenovo";
		
		ArrayList<Provider> objs = new ArrayList<>();
		objs.add(new Provider("fujitsu", "Japan","fujitsu@gmail.com"));
		objs.add(new Provider("Dell", "USA","Dell@gmail.com"));
		objs.add(new Provider("Toshiba", "Korea","Toshiba@gmail.com"));
		model.addAttribute("pr", provider);
		model.addAttribute("providers", providers);
		model.addAttribute("objs", objs);
		return "provider/listProviders";
	}
	
	@GetMapping("/add")
	public String addProviderGet() {
		
		return "provider/addProvider";
	}
	
	@PostMapping("/add")
	//@ResponseBody
	public String addProviderPost() {
		
		return "pageInsertion";
	}

}
