/*
 *@(#)TableKillerViewerSorter.java  2009-8-20
 *������
 *Copyright 2009 Ssj234,All rights reserved.
 */
package com.ssj.table;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
/**
 * ������
 * @author Administrator
 *
 */
public class TableKillerViewerSorter extends ViewerSorter {

	private int sort;

	private Class pack;// ʵ�����ȫ��

	private String[] attName;// ��������
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
			cla = pack;// �õ�pack������

			Object o1 = cla.newInstance();
			o1 = obj1;

			Object o2 = cla.newInstance();
			o2 = obj2;
			// obj1��obj2תΪpack������

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

				// System.out.println("����0��ʱ��" + getMethod.getName());
*/
				
				if (att.indexOf(".") < 0) {
//					System.out.println("δ�ҵ�.");
					String newName = getUpp(att);
//					System.out.println("�µ��ַ���-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					} catch (NoSuchMethodException e) {// �����쳣��������Ϊis
						newName = getUpper(att);// ���Զ�Ӧ�ķ�������
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					}
				} else {// �ҵ�
//				 System.out.println("������ַ�������.");
					String[] eles = att.split("\\.");
					int count = eles.length, k = 0;
					for (int j = 0; j < count; j++) {
//						 System.out.println("�𿪽��"+eles[j]);
						String name = eles[j];
						String newxx = getUpp(name);
//						 System.out.println("�õ�����"+newxx);
						k++;
					
						try {
							midmethod = cla.getDeclaredMethod(newxx, null);
						} catch (NoSuchMethodException e) {
							newxx = getUpper(att);// ���Զ�Ӧ�ķ�������
							midmethod = cla.getDeclaredMethod(newxx, null);// ��÷���
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
//					System.out.println("���ķ���"+midmethod.toString());
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
//					System.out.println("δ�ҵ�.");
					String newName = getUpp(att);
//					System.out.println("�µ��ַ���-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					} catch (NoSuchMethodException e) {// �����쳣��������Ϊis
						newName = getUpper(att);// ���Զ�Ӧ�ķ�������
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					}
				} else {// �ҵ�
				// System.out.println("������ַ�������.");
					String[] eles = att.split("\\.");
					int count = eles.length, k = 0;
					for (int j = 0; j < count; j++) {
						// System.out.println("�𿪽��"+eles[j]);
						String name = eles[j];
						String newxx = getUpp(name);
						// System.out.println("�õ�����"+newxx);
						k++;
						try {
							midmethod = cla.getDeclaredMethod(newxx, null);
						} catch (NoSuchMethodException e) {
							newxx = getUpper(att);// ���Զ�Ӧ�ķ�������
							midmethod = cla.getDeclaredMethod(newxx, null);// ��÷���
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

				// System.out.println("С��0��ʱ��" + getMethod.getName());
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
