/*
 *@(#)TableKillerViewerSorter.java  2009-8-20
 *排序器
 *Copyright 2009 Ssj234,All rights reserved.
 */
package com.ssj.table;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
/**
 * 排序器
 * @author Administrator
 *
 */
public class TableKillerViewerSorter extends ViewerSorter {

	private int sort;

	private Class pack;// 实体类的全名

	private String[] attName;// 属性数组
	private Method midmethod;

	public TableKillerViewerSorter(int sort, Class pack, String[] attName) {
		this.sort = sort;
		this.attName = attName;
		this.pack = pack;
	}

	public int compare(Viewer viewer, Object obj1, Object obj2) {
		Class cla;
		Method getMethod;
		String getMethodName;
		try {
			cla = pack;// 得到pack的类型

			Object o1 = cla.newInstance();
			o1 = obj1;

			Object o2 = cla.newInstance();
			o2 = obj2;
			// obj1和obj2转为pack的类型

			Class type = o1.getClass();

			if (sort > 0) {
				sort = sort;
				String att = attName[sort - 1];
			/*	String firstLetter = att.substring(0, 1).toUpperCase();
				getMethodName = "get" + firstLetter + att.substring(1);
				try{
				 getMethod = type
						.getMethod(getMethodName, new Class[] {});
				}catch(NoSuchMethodException e){
					getMethodName = "is" + firstLetter + att.substring(1);
					getMethod = type
					.getMethod(getMethodName, new Class[] {});
				}

				// System.out.println("大于0的时候" + getMethod.getName());
*/
				
				if (att.indexOf(".") < 0) {
//					System.out.println("未找到.");
					String newName = getUpp(att);
//					System.out.println("新的字符串-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					} catch (NoSuchMethodException e) {// 捕获异常，方法改为is
						newName = getUpper(att);// 属性对应的方法名称
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					}
				} else {// 找到
//				 System.out.println("传入的字符串包含.");
					String[] eles = att.split("\\.");
					int count = eles.length, k = 0;
					for (int j = 0; j < count; j++) {
//						 System.out.println("拆开结果"+eles[j]);
						String name = eles[j];
						String newxx = getUpp(name);
//						 System.out.println("得到最新"+newxx);
						k++;
					
						try {
							midmethod = cla.getDeclaredMethod(newxx, null);
						} catch (NoSuchMethodException e) {
							newxx = getUpper(att);// 属性对应的方法名称
							midmethod = cla.getDeclaredMethod(newxx, null);// 获得方法
						}
						cla = midmethod.getReturnType();
						if(j!=count-1){
							
						o1 = midmethod.invoke(o1, new String[] {});
						o2 = midmethod.invoke(o2, new String[] {});
						}
					}
					
				}
				Class returnType = (Class) midmethod.getGenericReturnType();
				if (!returnType.toString().equals("boolean")&&!returnType.toString().equals("int")) {
					Method compare = returnType.getDeclaredMethod("compareTo",
							new Class[] { returnType });
//					System.out.println("最后的方法"+midmethod.toString());
					Object return1 = midmethod.invoke(o1, new Object[] {});
					Object return2 = midmethod.invoke(o2, new Object[] {});
					Integer res = (Integer) compare.invoke(return1,
							new Object[] { return2 });
					return res.intValue();
				} else if(returnType.toString().equals("boolean")) {
					Boolean return1 = (Boolean) midmethod.invoke(obj1,
							new Object[] {});
					Boolean return2 = (Boolean) midmethod.invoke(obj2,
							new Object[] {});

					return return1.compareTo(return2);
				}else if(returnType.toString().equals("int")){
					Integer return1 = (Integer) midmethod.invoke(obj1,
							new Object[] {});
					Integer return2 = (Integer) midmethod.invoke(obj2,
							new Object[] {});

					return return1.compareTo(return2);
				}

				// return getMethod.invoke(o1, new Object[] {});
			}
			if (sort < 0) {

				String att = attName[-sort - 1];
				if (att.indexOf(".") < 0) {
//					System.out.println("未找到.");
					String newName = getUpp(att);
//					System.out.println("新的字符串-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					} catch (NoSuchMethodException e) {// 捕获异常，方法改为is
						newName = getUpper(att);// 属性对应的方法名称
						midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
					}
				} else {// 找到
				// System.out.println("传入的字符串包含.");
					String[] eles = att.split("\\.");
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
							newxx = getUpper(att);// 属性对应的方法名称
							midmethod = cla.getDeclaredMethod(newxx, null);// 获得方法
						}
						cla = midmethod.getReturnType();
						if(j!=count-1){
						o1 = midmethod.invoke(o1, new String[] {});
						o2 = midmethod.invoke(o2, new String[] {});
						}
					}
					
				}
				/*
				String firstLetter = att.substring(0, 1).toUpperCase();
				 getMethodName = "get" + firstLetter + att.substring(1);
				 try{
				 getMethod = type
						.getMethod(getMethodName, new Class[] {});
				 }catch(NoSuchMethodException e){
						getMethodName = "is" + firstLetter + att.substring(1);
						getMethod = type
						.getMethod(getMethodName, new Class[] {});
					}

				// System.out.println("小于0的时候" + getMethod.getName());
*/
				Class returnType = (Class) midmethod.getGenericReturnType();
				if (!returnType.toString().equals("boolean")&&!returnType.toString().equals("int")) {
					Method compare = returnType.getDeclaredMethod("compareTo",
							new Class[] { returnType });
					Object return1 = midmethod.invoke(o1, new Object[] {});
					Object return2 = midmethod.invoke(o2, new Object[] {});
					Integer res = (Integer) compare.invoke(return2,
							new Object[] { return1 });
					return res.intValue();
				} else if(returnType.toString().equals("boolean")){
					Boolean return1 = (Boolean) midmethod.invoke(obj1,
							new Object[] {});
					Boolean return2 = (Boolean) midmethod.invoke(obj2,
							new Object[] {});

					return return2.compareTo(return1);
				}else if(returnType.toString().equals("int")){
					Integer return1 = (Integer) midmethod.invoke(obj1,
							new Object[] {});
					Integer return2 = (Integer) midmethod.invoke(obj2,
							new Object[] {});

					return return2.compareTo(return1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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
