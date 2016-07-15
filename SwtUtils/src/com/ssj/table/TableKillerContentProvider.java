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
 * 内容器
 * @author Administrator
 *
 */
public class TableKillerContentProvider implements IStructuredContentProvider {
	/*
	  * 此方法接收输入到表格的数据集合，然后转换为数组放回，每个数组元素就是一个
	  * 实体类对象，也即一条记录。参数element就是通过setInput(getPeoples())输入的
	  * 对象，因为getPeoples()返回的是一个List，所以参数element的类型也是List
	  */
	public Object[] getElements(Object element) {
		if(element instanceof Collection)//加一个类型判断
			return ((Collection)element).toArray();//将List转化为数组
		else 
			return new Object[0];//若不是List则返回一个空数组
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

}
