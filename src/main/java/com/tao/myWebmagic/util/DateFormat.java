package com.tao.myWebmagic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import com.tao.util.MyRegex;

public class DateFormat {
	private static SimpleDateFormat dateFormatSecend = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	private static SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy年MM月dd日");

	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static void main(String[] args) {
		getCurMonth(1);
	}
	
	/**
	 * 定义日期格式
	 * @param model
	 * @return
	 */
	public static SimpleDateFormat me(String model){
		return new SimpleDateFormat(model);
	}

	/**
	 * 正则提取年、月、日
	 * @param temp
	 * @return
	 */
	public static String regexDate(String temp){
		String dateTemp=null, year, month, day, matStr;
		int nums [] = new int[3];
		matStr = "(20\\d{2})[-年]([0-1]?\\d)[-月]([0-3]?\\d)";
		Matcher mat = MyRegex.getMat(temp, matStr);
		while(mat.find()){
		
		year = mat.group(1);
		month = mat.group(2);
		day = mat.group(3);
		//补全month
		if(month.length()<2){
			month = "0" + month;
		}
		//补全day
		if(day.length()<2){
			day = "0" + day;
		}	
		dateTemp = year + "-" + month + "-" + day;
		}
		return dateTemp;
	}

	/**
	 * 获取year增益后的系统日期
	 * @param add
	 * @return
	 */
	public static Date getCurYear(int add){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, add);
	    Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 获取month增益后的系统日期
	 * @param add
	 * @return
	 */
	public static Date getCurMonth(int add){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, add);
	    Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 获取day增益后的系统日期
	 * @param add
	 * @return
	 */
	public static Date getCurDate(int add){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, add);
	    Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 返回String形式
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static String getString(SimpleDateFormat dateFormat, Date date){
		return dateFormat.format(date);
	}
	
	/**
	 * 返回Date形式
	 * @param dateFormat
	 * @param strDate
	 * @return
	 */
	public static Date getDate(SimpleDateFormat dateFormat, String strDate){
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	//返回String形式yyyy年MM月dd日  HH时mm分ss秒
	public static String getFormatSecend(Date date){
		return dateFormatSecend.format(date);
	} 
	//返回String形式yyyy年MM月dd日
	public static String getFormatDay(Date date){
		return dateFormatDay.format(date);
	}
	//返回String形式yyyy-MM-dd HH:mm
	public static String getFormatMinute(Date date){
		return dateFormat3.format(date);
	}
	//返回Date
	public static Date getFormatDate(String strDate){
		Date date = null;
		try {
			date = dateFormatSecend.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	//返回Date
		public static Date getDateDay(String strDate){
			Date date = null;
			try {
				date = dateFormat2.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}
	//返回String形式yyyy-MM-dd
	public static String getFormatDate(Date date){
		return dateFormat2.format(date);
	}
	
	//20\d{2}[-年][0-1]?\d[-月][0-3]?\d
	public static int date2Num(String temp){
		String year, month, day, matStr;
		int num = 0;
		matStr = "(20\\d{2})[-年]([0-1]?\\d)[-月]([0-3]?\\d)";
		Matcher mat = MyRegex.getMat(temp, matStr);
		while(mat.find()){
		
		year = mat.group(1);
		month = mat.group(2);
		day = mat.group(3);
				
		num = Integer.parseInt(year) * 365 + Integer.parseInt(month) * 30 + Integer.parseInt(day);
		
		}
		return num;
	}
	
}
