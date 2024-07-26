package com.garbuz.web.model;

import java.util.Objects;

public class ControlPanelPage {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControlPanelPage other = (ControlPanelPage) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "ControlPanelPage [title=" + title + "]";
	}	
}
