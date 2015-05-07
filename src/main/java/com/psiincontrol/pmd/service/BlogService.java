package com.psiincontrol.pmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psiincontrol.pmd.entity.Blog;
import com.psiincontrol.pmd.entity.User;
import com.psiincontrol.pmd.repository.BlogRepository;
import com.psiincontrol.pmd.repository.UserRepository;


@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	public void save(Blog blog, String name) {
		
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		
		
	}

}
