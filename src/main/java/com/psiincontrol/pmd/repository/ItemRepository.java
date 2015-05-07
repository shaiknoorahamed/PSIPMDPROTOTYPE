package com.psiincontrol.pmd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.psiincontrol.pmd.entity.Blog;
import com.psiincontrol.pmd.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findByBlog(Blog blog, Pageable pageable);

}
