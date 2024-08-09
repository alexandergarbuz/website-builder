package com.garbuz.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.entity.ContentElement;
import com.garbuz.web.model.page.ContentManagerPage;
import com.garbuz.web.repository.ContentElementRepository;

@Controller
@RequestMapping("/admin/content")
public class ContentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private ContentElementRepository contentRepoRepository;

	@GetMapping("/view")
	public ModelAndView viewAll() {
		final ModelAndView mv = new ModelAndView();
		if(contentRepoRepository.count() < 5) {
			for(int i = 0; i < 5; i++) {
				final ContentElement entry = ContentElementFactory.create("Content Element", "path", "/root-path", true, "General descrption", "Description text...");
				this.contentRepoRepository.save(entry);
				LOG.debug("Saved {} element", entry);
			}

		}
		
		final List<ContentElement> allEntries = contentRepoRepository.findAll();
		final ContentManagerPage contentManagerPage = new ContentManagerPage();
		contentManagerPage.setTitle("Content Manager");
		contentManagerPage.setContentElements(allEntries);
		mv.setViewName("adminPage");
		mv.getModel().put(NavigationUtils.BODY_FRAGMENT_KEY, "contentManagerBodyFragment");
		mv.addObject(NavigationUtils.PAGE_OBJECT_KEY, contentManagerPage);		
		LOG.debug("Returning {} page", contentManagerPage);
		return mv;
	}
	
}
