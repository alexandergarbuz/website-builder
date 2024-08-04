package com.garbuz.web.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HtmlSanitizerTest {

	@Test
	public void test() throws Exception {
		
		Assertions.assertEquals("test text", HtmlSanitizer.removeHtml("<h>test <a href='#test'>text</a></h1>"));
		
	}
	
}
