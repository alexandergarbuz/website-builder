package com.garbuz.web.model.page;

import java.util.ArrayList;
import java.util.List;

import com.garbuz.web.entity.ContentElement;

public class ContentManagerPage extends HomePage {
	
	private List<ContentElement> contentElements = new ArrayList<>();

	public List<ContentElement> getContentElements() {
		return contentElements;
	}

	public void setContentElements(List<ContentElement> contentElements) {
		this.contentElements = contentElements;
	}
}
