package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams.entities.Role;
import com.sip.ams.repositories.RoleRepository;

@Controller
@RequestMapping("/role")
public class RolesController {
	
	public final RoleRepository roleRepository;

	@Autowired
	public RolesController(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	
	@GetMapping("/list")
	public String listRoles(Model model) {
		List<Role> roles = (List<Role>) roleRepository.findAll();
		long nbr = roleRepository.count();
		if(roles.isEmpty()) 
			roles = null;
		model.addAttribute("roles", roles);
		model.addAttribute("nbr", nbr);
		
		return "role/listRoles";
	}

	@GetMapping("/add")
	public String showAddRoleForm(Model model) {
		 
		return "role/addRole";
	}
	
	@PostMapping("/add")
	public String addRole(@RequestParam("role") String role) {
		
		 Role r = new Role(role);
		
		roleRepository.save(r);
		return "redirect:list";
	}
	
	

}
