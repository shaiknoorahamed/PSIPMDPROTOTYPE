package com.psiincontrol.pmd.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;





















import com.psiincontrol.pmd.entity.Blog;
import com.psiincontrol.pmd.entity.Item;
import com.psiincontrol.pmd.entity.Role;
import com.psiincontrol.pmd.entity.User;
import com.psiincontrol.pmd.repository.BlogRepository;
import com.psiincontrol.pmd.repository.ItemRepository;
import com.psiincontrol.pmd.repository.RoleRepository;
import com.psiincontrol.pmd.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll() {
	
		return userRepository.findAll();
		
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	
	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs){
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.ASC, "publishedDate"));
			blog.setItems(items);
		}
		
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
		
	}

	public User findOne(String username) {
		
		return userRepository.findByName(username);
	}



		

}
