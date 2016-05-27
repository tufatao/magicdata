package com.tao.util;

import java.util.regex.Matcher;

public class MyString {

	/**
	 * 
	 * @param pre
	 * @param arr
	 * @return
	 */
	public static String array2String(String pre, String arr[]){
		StringBuffer sb = new StringBuffer("");
		for (String str : arr) {
			sb.append(pre + str + "}");
		}
		return sb.toString();
	}
	
	/**
	 * 以指定分隔符cha 拆分字符串
	 * @param str 待拆分字符串
	 * @param cha 分隔符
	 * @return
	 */
	public static String[] getArray(String str, String cha){
		
		String strs[] = str.split(cha);
		return strs;
	}
	
	/**
	 * 判断是否为空
	 * @param temp
	 * @return boolean
	 */
	public static boolean isNullEmpty(String temp){
		if(null != temp && !"".equals(temp)){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * 包含数组parts中任意字符串
	 * @param str
	 * @param parts
	 * @return
	 */
	public boolean containsAny(String str, String parts[] ){
		for (String st : parts) {
			if(str.contains(st)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 包含数组parts中所有字符串
	 * @param str
	 * @param parts
	 * @return
	 */
	public boolean containsAll(String str, String parts[] ){
		for (String st : parts) {
			if(!str.contains(st)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 正则匹配数组中的regex, 匹配任意一个返回true, 否则返回false
	 * @param str
	 * @param parts
	 * @return
	 */
	public static boolean regexAny(String str, String parts[] ){
		Matcher m;
		for (String st : parts) {
			m = MyRegex.getMat(str, st);
			if(m.find()){
				return true;
			}
		}
		return false;
		
	}

/**
 * 正则匹配数组中的regex, 全部匹配返回true, 否则返回false
 * @param str
 * @param parts
 * @return
 */
	public static boolean regexAll(String str, String parts[] ){
		Matcher m;
		for (String st : parts) {
			m = MyRegex.getMat(str, st);
			if(!m.find()){
				return false;
			}
		}
		return true;
		
	}
}
