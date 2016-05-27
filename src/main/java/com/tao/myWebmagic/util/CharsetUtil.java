package com.tao.myWebmagic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CharsetUtil {

	//获取指定编码的字符串
//	public static String getUrlCode(String key, String charsetName) {
//		String keyWord = null;
//		try {
//			keyWord = URLEncoder.encode(new String(key.getBytes(charsetName)));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return keyWord;
//	}
	
	//获取指定编码的字符串
		public static String getUrlCode(String key, String charsetName) {
			String keyWord = null;
			try {
				keyWord = URLEncoder.encode(key, charsetName);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return keyWord;
		}
		
		//按指定编码解码字符串
			public static String UrlDecode(String key, String charsetName) {
				String keyWord = null;
				try {
					keyWord = URLDecoder.decode(key, charsetName);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return keyWord;
			}
}
