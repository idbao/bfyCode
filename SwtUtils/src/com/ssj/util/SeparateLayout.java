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
 * 分割父容器为常用
 * 顶：一行
 * 底：充满
 * @author ssj234
 *
 */
public class SeparateLayout {//此方法把传入的容器分为两部分 上和下两部分
    public Composite topComp,bottomComp;

	public Composite getButtomComp() {
		return bottomComp;
	}

	

	public Composite getTopComp() {
		return topComp;
	}
/**
 * 
 * @param parent 父容器
 */
   public  SeparateLayout(Composite parent){//传入父容器
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//父容器设置布局
	   
	   topComp=new Composite(parent,SWT.NONE);//top容器
	   RowLayout row= new RowLayout();
	   row.marginTop=5;
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top容器设置为row布局
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top容器水平充满
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom容器
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom充满
	   bottomComp.setLayout(new FillLayout());
	   
   }
   /**
    * 
    * @param parent 父容器
    * @param data 顶容器layoutdata的格式
    */
   public  SeparateLayout(Composite parent,int data){//传入父容器，和grid的格式
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//父容器设置布局
	   
	   topComp=new Composite(parent,SWT.NONE);//top容器
	   RowLayout row= new RowLayout();
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top容器设置为row布局
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL|data));//top容器水平充满
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom容器
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom充满
	   
	   bottomComp.setLayout(new FillLayout());
	   
   }
   public  SeparateLayout(Composite parent,boolean flag){//传入父容器
	   if(flag){
		   GridLayout gl=new GridLayout();
		   gl.marginWidth=0;
		   gl.marginHeight=0;
		   parent.setLayout(gl);//父容器设置布局
		   Composite  topCompx=new Composite(parent,SWT.NONE);//top容器
		  GridLayout gdl= new GridLayout();
		   topCompx.setLayout(gdl);//top容器设置为row布局
		   topCompx.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top容器水平充满
		   
		   topComp=new Composite(topCompx,SWT.NONE);
		   
		   new Label(topCompx,SWT.SEPARATOR|SWT.HORIZONTAL).setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		   bottomComp=new Composite(parent,SWT.NONE);//buttom容器
		   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom充满
		   bottomComp.setLayout(new FillLayout());
	   }else{
	   GridLayout gl=new GridLayout();
	   gl.marginWidth=0;
	   gl.marginHeight=0;
	   parent.setLayout(gl);//父容器设置布局
	   topComp=new Composite(parent,SWT.NONE);//top容器
	   RowLayout row= new RowLayout();
	   row.marginTop=5;
//	   row.marginHeight=5;
//	   row.marginWidth=5;
	   topComp.setLayout(row);//top容器设置为row布局
	   topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));//top容器水平充满
	   
	   bottomComp=new Composite(parent,SWT.NONE);//buttom容器
	   bottomComp.setLayoutData(new GridData(GridData.FILL_BOTH));//buttom充满
	   bottomComp.setLayout(new FillLayout());
	   }
   }
	
}
