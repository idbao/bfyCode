package com.ssj.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.ssj.util.SWTResourceManager;



public class GridKillerColor {

	public static Color headColor=SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND);//�е���ɫSWT.COLOR_WIDGET_BACKGROUND
	public static Color rowColor=SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND);//�е���ɫSWT.COLOR_WIDGET_BACKGROUND-===152, 245 ,255
	
	public static Color headTextColor=SWTResourceManager.getColor(SWT.COLOR_BLACK);
	public static Color rowTextColor=SWTResourceManager.getColor(139, 37 ,0);
	public static Color selectedColor=SWTResourceManager.getColor(30 ,144 ,255);//ѡ��ʱ������ɫ
	public static Color selectedTextColor=SWTResourceManager.getColor(SWT.COLOR_WHITE);//ѡ��ʱ������ɫ
	
	public static Font textFont=SWTResourceManager.getFont("����", 9, SWT.NORMAL);
}
