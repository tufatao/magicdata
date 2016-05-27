package com.tao.myWebmagic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjSerialize {
	
	/**
	 * ClassName:对象序列化类
	 * Description:用于对象的序列化与反序列化
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	
	/*
	 * MethodName:对象序列化方法
	 * Description:用于序列化对象
	 */
	public static void serializeObj(Object obj, String filePath) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
		oos.writeObject(obj);
		System.out.println("对象序列化成功");
		oos.close();
	}
	
	
	/*
	 * MethodName:对象反序列化方法
	 * Description:用于反序列化对象
	 */
	public static Object deSerializeObj(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)));
		Object obj = ois.readObject();
		return obj;
		
	}
}
