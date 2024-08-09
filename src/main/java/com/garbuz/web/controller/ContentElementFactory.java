package com.garbuz.web.controller;

import com.garbuz.web.entity.ContentElement;

public class ContentElementFactory {
	private ContentElementFactory(){};

	public static final ContentElement create(String name, String path, String parentPath, boolean published, String preview, String text) {
		return new ContentElement(name, path, parentPath, published, preview, text);
	}
	
}
