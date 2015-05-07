package com.psiincontrol.pmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psiincontrol.pmd.entity.Blog;
import com.psiincontrol.pmd.entity.User;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByUser(User user);

}

