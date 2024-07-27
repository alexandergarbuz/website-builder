package com.garbuz.web.model.page;

public class HomePage {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [title=" + title + "]";
	}	
}
