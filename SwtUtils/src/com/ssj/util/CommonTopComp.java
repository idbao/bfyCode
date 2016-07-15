/*
 *@(#)CommonTopComp.java  2009-8-31
 *
 *Copyright 2009 YHSOFT,All rights reserved.
 */
package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * 类的Top容器的分隔符，Text和搜索，打印按钮的生成
 * 
 * @author  ssj
 */
public class CommonTopComp {
	public static Text text;
	public static Label label;
	public static Button print,search,filter;
	public static Combo combo;
	public static String printName="打印",searchName="查询",filterName="过滤";
	public static Combo getCombo() {
		return combo;
	}
	public static void setCombo(Combo combo) {
		CommonTopComp.combo = combo;
	}
	public static Button getPrint() {
		return print;
	}
	public static void setPrint(Button print) {
		CommonTopComp.print = print;
	}
	public static String getPrintName() {
		return printName;
	}
	public static void setPrintName(String printName) {
		CommonTopComp.printName = printName;
	}
	public static Button getSearch() {
		return search;
	}
	public static void setSearch(Button search) {
		CommonTopComp.search = search;
	}
	public static String getSearchName() {
		return searchName;
	}
	public static void setSearchName(String searchName) {
		CommonTopComp.searchName = searchName;
	}
	public static Text getText() {
		return text;
	}
	public static void setText(Text text) {
		CommonTopComp.text = text;
	}
	public static void create(Composite parent){
		 //分割符
		    
		    combo=new Combo(parent,SWT.READ_ONLY);
		    combo.setLayoutData(new RowData(100, 15));
			label = new Label(parent, SWT.SEPARATOR);
			label.setLayoutData(new RowData(3, 15));
//			 text
			text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new RowData(123, 15));
//			 button
			search = new Button(parent, SWT.None);
			search.setImage(SWTResourceManager.getImage(CommonTopComp.class,"/images/Serch.png"));
			search.setToolTipText(searchName);
			filter = new Button(parent, SWT.TOGGLE);
			filter.setImage(SWTResourceManager.getImage(CommonTopComp.class,"/images/filter.png"));
			filter.setToolTipText(filterName);
			print = new Button(parent, SWT.NONE);
			print.setImage(SWTResourceManager.getImage(CommonTopComp.class,"/images/Print.png"));
			print.setToolTipText(printName);
	}
	public static void createDialog(Composite parent){
		 //分割符
			label = new Label(parent, SWT.SEPARATOR);
			label.setLayoutData(new RowData(3, 15));
//			 text
			text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new RowData(100, 15));
//			 button
			search = new Button(parent, SWT.None);
			search.setImage(SWTResourceManager.getImage(CommonTopComp.class,"/images/Serch.png"));
//			search.setText(searchName);
	}
	public static void createDialogSim(Composite parent){
		 //分割符
			label = new Label(parent, SWT.SEPARATOR);
			label.setLayoutData(new RowData(3, 15));
//			 text
			text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new RowData(143, 15));
//			 button
			search = new Button(parent, SWT.None);
			search.setImage(SWTResourceManager.getImage(CommonTopComp.class,"/images/Serch.png"));
//			search.setText(searchName);
			combo=new Combo(parent,SWT.READ_ONLY);
			
	}
}
