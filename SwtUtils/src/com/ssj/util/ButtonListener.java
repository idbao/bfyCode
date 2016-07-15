/*
 *@(#)ButtonListener.java  2009-8-31
 *
 *Copyright 2009 YHSOFT,All rights reserved.
 */
package com.ssj.util;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
/**
 * 按钮的监听事件类 改变SashForm的比例
 * 
 * @author  ssj234
 */
public class ButtonListener implements SelectionListener{
public SashForm sash;
public int [] after;
public boolean sign=true;
public int [] later;
/**
 * 构造函数
 * @param sash  需改变的SashForm
 * @param after 改变之后的比例
 */
	public ButtonListener(SashForm sash, int [] after){
		this.sash=sash;
		this.after=after;
		later=sash.getWeights();
	}

	public void widgetSelected(SelectionEvent arg0) {
		if(sign){
			sash.setWeights(after);
			sign=false;
			}else{
				sash.setWeights(later);
				sign=true;
			}
	
 
		
	}

	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
