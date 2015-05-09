package com.psiincontrol.pmd.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.psiincontrol.pmd.entity.Item;
import com.psiincontrol.pmd.exception.RssException;

public class RssServiceTest {
	
	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("test-rss/psiincontrol.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals("JAXB: Access Restriction Warning", firstItem.getTitle());
		assertEquals("16 06 2014 20:59:43", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
	}

}
