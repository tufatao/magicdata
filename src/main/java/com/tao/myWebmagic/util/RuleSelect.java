package com.tao.myWebmagic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tao.util.MyRegex;
import com.tao.util.MyString;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 选择器引擎
 * @author fantom
 *
 */
public class RuleSelect {

	
	/**
	 * 匹配返回true, 否则返回false
	 * @param content
	 * @param rule
	 * @return
	 */
	public static boolean veriRule(String content, String rule){
		boolean flag = false;
		String regex[] = {"Any\\{.*?\\}", "All\\{.*?\\}", "AnyN\\{.*?\\}"
				, "AllN\\{.*?\\}"};
		//正则解析rule
		Matcher m;
		if(MyString.isNullEmpty(content)){
			return true;
		}
		if(MyString.isNullEmpty(rule)){
			return false;
		}
		//Any{.*?}匹配任意一个正则
			m = MyRegex.getMat(rule, regex[0]);
			if(null == m){
				return flag;
			}
			if(m.matches()){
				while(m.find()){
					String t = m.group();
					String tt[] = MyString.getArray(t, ",");
						if(MyString.regexAny(content, tt)){
							flag = true;
							return flag;
						}
					
				}
			}
			
			//AnyN{.*?}不匹配任意一个(都不包含)
			m = MyRegex.getMat(rule, regex[2]);
			if(m.matches()){
				int size = m.groupCount();
				while(m.find()){
					String t = m.group();
					String tt[] = MyString.getArray(t, ",");
						if(!MyString.regexAny(content, tt)){
							flag = true;
							return flag;
						}
					
				}
			}
			
			//All{.*?}匹配所有
			m = MyRegex.getMat(rule, regex[1]);
			if(m.matches()){
				int size = m.groupCount();
				while(m.find()){
					String t = m.group();
					String tt[] = MyString.getArray(t, ",");
						if(MyString.regexAll(content, tt)){
							flag = true;
							return flag;
						}
					
				}
			}

			//AllN{.*?}不匹配全部(不匹配或匹配部分)
			m = MyRegex.getMat(rule, regex[3]);
			if(m.matches()){
				int size = m.groupCount();
				while(m.find()){
					String t = m.group();
					String tt[] = MyString.getArray(t, ",");
						if(!MyString.regexAll(content, tt)){
							flag = true;
							return flag;
						}
				}
			}
		
		return flag;
	}
	
	/**
	 * 
	 * @param html
	 * @param temp
	 * @return null, htmlNode, PlainText;
	 */
	public static Selectable processRule(Selectable html, String temp){
		Selectable select = null;
			
		//选择器类型 正则、CSS、xpath;
		String style = temp.substring(0, 2);
		//选择规则
		String rule = temp.substring(2);
		if ("$X".equals(style)) {
			select = html.xpath(rule);
		} else if ("$R".equals(style)) {

			select = html.regex(rule);
		} else if ("$C".equals(style)) {

			select = html.$(rule);
		}
		else{
			System.out.println("无法识别的规则");
		}
		return select;
	}
	
	/**
	 * 生成提取规则
	 * @param style
	 * @param temp
	 * @return
	 */
	public static String generateRule(String style, String temp){
		String pre = "";
		//选择器类型 正则、CSS、xpath;
		if ("1".equals(style)) {
			pre = "$R";
		} else if ("2".equals(style)) {
			pre = "$X";
		} else if ("3".equals(style)) {
			pre = "$C";
		} 
		return pre + temp;
	}
}
