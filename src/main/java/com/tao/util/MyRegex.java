package com.tao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegex {

	/**
	 * 正则匹配，Matcher工厂 * @param content
	 * 
	 * @param regex
	 * @return
	 */
	public static Matcher getMat(String content, String regex) {

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		return m;
	}
}
