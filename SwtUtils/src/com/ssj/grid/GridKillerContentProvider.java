package com.ssj.grid;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * 内容器
 * @author ssj234
 *
 */

public class GridKillerContentProvider implements IStructuredContentProvider {
    public Object[] getElements(Object inputElement) {
    	if(inputElement instanceof Collection)//加一个类型判断
			return ((Collection)inputElement).toArray();//将List转化为数组
		else 
			return new Object[0];//若不是List则返回一个空数组
      }

      public void dispose() {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      }

  	

	
}
