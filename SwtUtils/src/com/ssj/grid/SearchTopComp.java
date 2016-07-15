/*
 *@(#)CommonTopComp.java  2009-8-31
 *
 *Copyright 2009 YHSOFT,All rights reserved.
 */
package com.ssj.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.ssj.util.SWTResourceManager;

/**
 * 搜索框的各个控件
 * 
 * @author ssj
 */
public class SearchTopComp {
	public static Text text;

	public static Label label;

	public static ToolItem close, search, filter;

	public static Combo combo;

	public static CLabel clabel;

	public static String closeName = "关闭", searchName = "查询",
			filterName = "过滤";

	public static void create(Composite parent) {
		
		Composite topComp=new Composite(parent,SWT.NONE);
		topComp.setLayout(new RowLayout(SWT.HORIZONTAL));
		topComp.setBackground(SearchActionKeeper.backgroundColor);
		topComp.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		
		clabel = new CLabel(topComp, SWT.NONE);
		clabel.setImage(SWTResourceManager.getImage(SearchTopComp.class,
				"/images/2.gif"));
		
//		clabel.setBackground(SearchActionKeeper.bgClolr);
		clabel.setToolTipText("点击移动");
		clabel.setCursor(new Cursor(Display.getCurrent(),SWT.CURSOR_SIZEALL));

		// 分割符

		new Label(topComp, SWT.SEPARATOR).setLayoutData(new RowData(3, 20));

		combo = new Combo(topComp, SWT.READ_ONLY);
		combo.setLayoutData(new RowData(100, 15));
		label = new Label(topComp, SWT.SEPARATOR);
		label.setLayoutData(new RowData(3, 20));
		// text
		text = new Text(topComp, SWT.BORDER);
		text.setLayoutData(new RowData(123, 15));
		new Label(topComp, SWT.SEPARATOR).setLayoutData(new RowData(3, 20));
		ToolBar tb = new ToolBar(topComp, SWT.NONE);
		
//		tb.setBackground(SearchActionKeeper.bgClolr);
		// button
		search = new ToolItem(tb, SWT.None);
		search.setImage(SWTResourceManager.getImage(SearchTopComp.class,
				"/images/Serch.png"));
		search.setToolTipText(searchName);

		filter = new ToolItem(tb, SWT.CHECK);
		filter.setImage(SWTResourceManager.getImage(SearchTopComp.class,
				"/images/filter.png"));
		filter.setToolTipText(filterName);

		close = new ToolItem(tb, SWT.NONE);
		close.setImage(SWTResourceManager.getImage(SearchTopComp.class,
				"/images/delete_obj.gif"));
		close.setToolTipText(closeName);
	}

}
