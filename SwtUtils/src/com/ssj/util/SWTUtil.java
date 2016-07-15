package com.ssj.util;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

public class SWTUtil {//∆¡ƒªæ”÷–¿‡
	public static int width;
	 public static int height;
 public SWTUtil(Shell shell){
	 Rectangle rtg=shell.getMonitor().getClientArea();
	 width=rtg.width;
	 height=rtg.height;
 }
 
 public static void setCenter(Shell shell){
	 Rectangle rtg=shell.getMonitor().getClientArea();
	 width=rtg.width;
	 height=rtg.height;
	 
	 int x=shell.getSize().x;
	 int y=shell.getSize().y;
	 
	 Point p=new Point((width-x)/2,(height-y)/2); 
	 shell.setLocation(p);
 }
}
