package com.garbuz.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class FileUtils {
	public static final String readFile(final String path) throws IOException {
    	try (final InputStream is = FileUtils.class.getResourceAsStream(path)){
            if (is == null) {
                throw new IOException("File not found: " + path);
            }
            String output = IOUtils.toString(is, Charset.defaultCharset());
            
            return output;
    	}
	}
}
