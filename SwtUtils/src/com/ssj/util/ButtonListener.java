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
 * ��ť�ļ����¼��� �ı�SashForm�ı���
 * 
 * @author  ssj234
 */
public class ButtonListener implements SelectionListener{
public SashForm sash;
public int [] after;
public boolean sign=true;
public int [] later;
/**
 * ���캯��
 * @param sash  ��ı��SashForm
 * @param after �ı�֮��ı���
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
