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
 * ��ǩ��
 * @author Administrator
 *
 */
public class TableKillerLabelProvider implements ITableLabelProvider {
	/*
	 * �˷����������ݼ�¼�ڱ���ÿһ������ʾʲô���� @param element ʵ������� @param col �к� 0�ǵ�һ�� @return
	 * ����ֵһ��Ҫ����nullֵ���������
	 */
	private int index;

	private Class pack;

	private String formatType;

	private String trueName = "��", falseName = "Ů";

	private String[] colName;

	private Method midmethod;

	// ���췽�� ���и�ֵ
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
			Class cla = pack;// �õ�pack����
			Object o = cla.newInstance();// �½�һ��pack���ʵ��
			o = element;
			// ��element����o������elementתΪpack������ͣ�oΪelementת�����ͺ��������

			Class type = o.getClass();// �õ�o������
			Method ms[] = type.getDeclaredMethods();// �õ����з���

			if (index == colName.length)
				index = 0;
			for (col = index; col < colName.length; col++) {
				index++;
				String attrName = colName[col];// �õ���������
//				System.out.println(attrName);
				if(attrName.equals("YHSOFT")){
					return "";
				}else{
				if (attrName.indexOf(".") < 0) {
//					System.out.println("δ�ҵ�.");
					String newName = getUpp(attrName);
//					System.out.println("�µ��ַ���-->" + newName);
					try {
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					} catch (NoSuchMethodException e) {// �����쳣��������Ϊis
						newName = getUpper(attrName);// ���Զ�Ӧ�ķ�������
						midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
					}
					o = midmethod.invoke(o, new String[] {});
					
					Class returnType = (Class) midmethod.getGenericReturnType();
					if (returnType.getName().equals("boolean")) {// �������boolean��
						if (o.toString().equals("true")) {
							return trueName;
						} else {
							return falseName;
						}
					} else if (returnType.getName().equals("java.util.Date")) {// �������date��
						
							SimpleDateFormat sd = new SimpleDateFormat(formatType);
							if(o!=null){
							return sd.format(o).toString();
							}else{
								return "";
							}
						
						
					
					} else {

						return o.toString();// ���صõ���ֵ
					}
					
				} else {// �ҵ�
				// System.out.println("������ַ�������.");
					String[] eles = attrName.split("\\.");
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
							newxx = getUpper(attrName);// ���Զ�Ӧ�ķ�������
							midmethod = cla.getDeclaredMethod(newxx, null);// ��÷���
						}
						// System.out.println("��"+k+"��������"+midmethod.getName());
						cla = midmethod.getReturnType();
						o = midmethod.invoke(o, new String[] {});
						// System.out.println(o);
						// if(k==count){
						// lastmethod=midmethod;
						// System.out.println("���ķ���"+lastmethod.getName());
						// }
					}
					
				}
				}
				Class returnType = (Class) midmethod.getGenericReturnType();
				if (returnType.getName().equals("boolean")) {// �������boolean��
					if (o.toString().equals("true")) {
						return trueName;
					} else {
						return falseName;
					}
				} else if (returnType.getName().equals("java.util.Date")) {// �������date��

					SimpleDateFormat sd = new SimpleDateFormat(formatType);
					return sd.format(o).toString();
				} else {

					return o.toString();// ���صõ���ֵ
				}
              
			}
		} catch (NullPointerException e) {
			return "";
//         System.out.println("TableKiller�����쳣��"+e.getMessage());
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
