package com.tao.util;

public class CharFiltForMysql {
	// 过滤非法字符
	public static String filtRiskChar(String s) {
		// "+,',--,%,^,&,?,(,),<,>,[,],{,},/,\,;,:," & Chr(34) & "," & Chr(0) &
		// ""
		String str = "";
		str = s.replace("—", "-");
		str = s.replace("'", "''");
		str = str.replace("〇", "零");
		str = str.replace("·", ".");
		str = str.replace("㎡", "m^2");
		str = str.replace("", " ");
		return str;
	}

}
