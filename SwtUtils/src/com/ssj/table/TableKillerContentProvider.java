/*
 *@(#)TableKillerContentProvider.java  2009-8-20
 *
 *Copyright 2009 Ssj234,All rights reserved.
 */
package com.ssj.table;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
/**
 * ������
 * @author Administrator
 *
 */
public class TableKillerContentProvider implements IStructuredContentProvider {
	/*
	  * �˷����������뵽�������ݼ��ϣ�Ȼ��ת��Ϊ����Żأ�ÿ������Ԫ�ؾ���һ��
	  * ʵ�������Ҳ��һ����¼������element����ͨ��setInput(getPeoples())�����
	  * ������ΪgetPeoples()���ص���һ��List�����Բ���element������Ҳ��List
	  */
	public Object[] getElements(Object element) {
		if(element instanceof Collection)//��һ�������ж�
			return ((Collection)element).toArray();//��Listת��Ϊ����
		else 
			return new Object[0];//������List�򷵻�һ��������
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

}
