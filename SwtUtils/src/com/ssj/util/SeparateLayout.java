/*
 *@(#)SeparateLayout.java  2009-8-28
 *
 *Copyright 2009 YHSOFT,All rights reserved.
 */
package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
/**
 * �ָ����Ϊ����
 * ����һ��
 * �ף�����
 * @author ssj234
 *
 */
public class SeparateLayout {//�˷����Ѵ����������Ϊ������ �Ϻ���������
    public Composite topComp,bottomComp;

	public Composite getButtomComp() {
		return bottomComp;
	}

	

	public Composite getTopComp() {
		return topComp;
	}
/**
 * 
 * @param parent ������
 */
   public  SeparateLayout(Composite parent){//���븸����
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//���������ò���
	   
	   topComp=new Composite(parent,SWT.NONE);//top����
	   RowLayout row= new RowLayout();
	   row.marginTop=5;
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top��������Ϊrow����
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top����ˮƽ����
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom����
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom����
	   bottomComp.setLayout(new FillLayout());
	   
   }
   /**
    * 
    * @param parent ������
    * @param data ������layoutdata�ĸ�ʽ
    */
   public  SeparateLayout(Composite parent,int data){//���븸��������grid�ĸ�ʽ
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//���������ò���
	   
	   topComp=new Composite(parent,SWT.NONE);//top����
	   RowLayout row= new RowLayout();
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top��������Ϊrow����
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL|data));//top����ˮƽ����
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom����
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom����
	   
	   bottomComp.setLayout(new FillLayout());
	   
   }
   public  SeparateLayout(Composite parent,boolean flag){//���븸����
	   if(flag){
		   GridLayout gl=new GridLayout();
		   gl.marginWidth=0;
		   gl.marginHeight=0;
		   parent.setLayout(gl);//���������ò���
		   Composite  topCompx=new Composite(parent,SWT.NONE);//top����
		  GridLayout gdl= new GridLayout();
		   topCompx.setLayout(gdl);//top��������Ϊrow����
		   topCompx.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top����ˮƽ����
		   
		   topComp=new Composite(topCompx,SWT.NONE);
		   
		   new Label(topCompx,SWT.SEPARATOR|SWT.HORIZONTAL).setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		   bottomComp=new Composite(parent,SWT.NONE);//buttom����
		   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom����
		   bottomComp.setLayout(new FillLayout());
	   }else{
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//���������ò���
	   topComp=new Composite(parent,SWT.NONE);//top����
	   RowLayout row= new RowLayout();
	   row.marginTop=5;
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top��������Ϊrow����
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top����ˮƽ����
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom����
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom����
	   bottomComp.setLayout(new FillLayout());
	   }
   }
	
}
