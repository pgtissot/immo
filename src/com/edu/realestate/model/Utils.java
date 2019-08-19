package com.edu.realestate.model;

import java.io.UnsupportedEncodingException;

public class Utils {
	
	public static String toUTF8 (String s) throws UnsupportedEncodingException {
		return new String(s.getBytes(), "UTF-8");
	}

}
