package com.github.djroush.demo.backend.util;

public class StringUtil {
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
}