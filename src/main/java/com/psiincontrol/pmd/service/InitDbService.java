package com.psiincontrol.pmd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;





import com.psiincontrol.pmd.entity.Blog;
import com.psiincontrol.pmd.entity.Item;
import com.psiincontrol.pmd.entity.Role;
import com.psiincontrol.pmd.entity.User;
import com.psiincontrol.pmd.repository.BlogRepository;
import com.psiincontrol.pmd.repository.ItemRepository;
import com.psiincontrol.pmd.repository.RoleRepository;
import com.psiincontrol.pmd.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PostConstruct
	public void init(){
		
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
	
		Blog blogPsiincontrol = new Blog();
		blogPsiincontrol.setName("Psiincontrol");
		blogPsiincontrol.setUrl("view-source:http://www.psi-incontrol.com/v2/.xml");
		blogPsiincontrol.setUser(userAdmin);
		blogRepository.save(blogPsiincontrol);
		
		/*Item item1 = new Item();
		item1.setBlog(blogPsiincontrol);
		item1.setTitle("first");
		item1.setLink("http://www.psi-incontrol.com");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogPsiincontrol);
		item2.setTitle("second");
		item2.setLink("http://www.psi-incontrol.com");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);*/
	}
	
}
