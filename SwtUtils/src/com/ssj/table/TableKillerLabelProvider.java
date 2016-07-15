/*
 *@(#)TableKillerLabelProvider.java  2009-8-20
 *
 *Copyright 2009 Ssj234,All rights reserved.
 */
package com.ssj.table;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
/**
 * 标签器
 * @author Administrator
 *
 */
public class TableKillerLabelProvider implements ITableLabelProvider {
	/*
	 * 此方法决定数据记录在表格的每一列是显示什么文字 @param element 实体类对象 @param col 列号 0是第一列 @return
	 * 返回值一定要避免null值，否则出错
	 */
	private int index;

	private Class pack;

	private String formatType;

	private String trueName = "男", falseName = "女";

	private String[] colName;

	private Method midmethod;

	// 构造方法 进行赋值
	public TableKillerLabelProvider(Class obj, String[] colName,
			String formatType, String trueName, String falseName) {
		this.pack = obj;
		this.colName = colName;
		index = 0;
		this.formatType = formatType;
		this.trueName = trueName;
		this.falseName = falseName;
	}

	public Image getColumnImage(Object arg0, int arg1) {
	
		return null;
	}

	public String getColumnText(Object element, int col) {
		Method getMethod;
		String getMethodName;
		
		try {
			Class cla = pack;// 得到pack的类
			Object o = cla.newInstance();// 新建一个pack类的实例
			o = element;
			// 将element赋给o，即将element转为pack类的类型，o为element转换类型后的引用名

			Class type = o.getClass();// 得到o的类型
			Method ms[] = type.getDeclaredMethods();// 得到所有方法

			if (index == colName.length)
				index = 0;
			for (col = index; col < colName.length; col++) {
				index++;
				String attrName = colName[col];// 得到属性名称
//				System.out.println(attrName);
				if(attrName.equals("YHSOFT")){
					return "";
				}else{
				if (attrName.indexOf(".") < 0) {
//					System.out.println("未找到.");
					String newName = getUpp(attrName);
//					System.out.println("新的字符串-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					} catch (NoSuchMethodException e) {// 捕获异常，方法改为is
						newName = getUpper(attrName);// 属性对应的方法名称
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					}
					o = midmethod.invoke(o, new String[] {});
					
					Class returnType = (Class) midmethod.getGenericReturnType();
					if (returnType.getName().equals("boolean")) {// 如果返回boolean型
						if (o.toString().equals("true")) {
							return trueName;
						} else {
							return falseName;
						}
					} else if (returnType.getName().equals("java.util.Date")) {// 如果返回date型
						
							SimpleDateFormat sd = new SimpleDateFormat(formatType);
							if(o!=null){
							return sd.format(o).toString();
							}else{
								return "";
							}
						
						
					
					} else {

						return o.toString();// 返回得到的值
					}
					
				} else {// 找到
				// System.out.println("传入的字符串包含.");
					String[] eles = attrName.split("\\.");
					int count = eles.length, k = 0;
					for (int j = 0; j < count; j++) {
						// System.out.println("拆开结果"+eles[j]);
						String name = eles[j];
						String newxx = getUpp(name);
						// System.out.println("得到最新"+newxx);
						k++;
						try {
							midmethod = cla.getDeclaredMethod(newxx, null);
						} catch (NoSuchMethodException e) {
							newxx = getUpper(attrName);// 属性对应的方法名称
							midmethod = cla.getDeclaredMethod(newxx, null);// 获得方法
						}
						// System.out.println("第"+k+"个方法："+midmethod.getName());
						cla = midmethod.getReturnType();
						o = midmethod.invoke(o, new String[] {});
						// System.out.println(o);
						// if(k==count){
						// lastmethod=midmethod;
						// System.out.println("最后的方法"+lastmethod.getName());
						// }
					}
					
				}
				}
				Class returnType = (Class) midmethod.getGenericReturnType();
				if (returnType.getName().equals("boolean")) {// 如果返回boolean型
					if (o.toString().equals("true")) {
						return trueName;
					} else {
						return falseName;
					}
				} else if (returnType.getName().equals("java.util.Date")) {// 如果返回date型

					SimpleDateFormat sd = new SimpleDateFormat(formatType);
					return sd.format(o).toString();
				} else {

					return o.toString();// 返回得到的值
				}
              
			}
		} catch (NullPointerException e) {
			return "";
//         System.out.println("TableKiller发生异常："+e.getMessage());
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (InstantiationException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}

	public void addListener(ILabelProviderListener arg0) {
		

	}

	public void dispose() {
		

	}

	public boolean isLabelProperty(Object arg0, String arg1) {
		
		return false;
	}

	public void removeListener(ILabelProviderListener arg0) {
		

	}

	public String getUpp(String element) {
		String first = element.substring(0, 1);

		String upper = first.toUpperCase();

		String newName = "get" + upper + element.substring(1);
		return newName;
	}

	public String getUpper(String element) {
		String first = element.substring(0, 1);

		String upper = first.toUpperCase();

		String newName = "is" + upper + element.substring(1);
		return newName;
	}

}
