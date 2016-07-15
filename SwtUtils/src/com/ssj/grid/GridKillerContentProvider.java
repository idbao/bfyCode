package com.ssj.grid;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * ������
 * @author ssj234
 *
 */

public class GridKillerContentProvider implements IStructuredContentProvider {
    public Object[] getElements(Object inputElement) {
    	if(inputElement instanceof Collection)//��һ�������ж�
			return ((Collection)inputElement).toArray();//��Listת��Ϊ����
		else 
			return new Object[0];//������List�򷵻�һ��������
      }

      public void dispose() {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      }

  	

	
}
