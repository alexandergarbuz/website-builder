package com.garbuz.web.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

public class HtmlSanitizer {

	/**
	 * Removed all HTML tags from the given string.
	 * @param htmlText to sanitize.
	 * @return plain text verison.
	 */
	public String removeHtml(final String htmlText) {
		
		return (StringUtils.isNotEmpty(htmlText)) ? Jsoup.parse(htmlText).text() : StringUtils.EMPTY;
		
	}
	
}
