package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

/**
 * 为text加入监听 focus时加入边界
 * @author Administrator
 *
 */
public class TextListener implements FocusListener{
	private Control con;
	
	static Image image ;

	public TextListener(Control con){
		this.con=con;
		
	}

	
	
	public void focusGained(FocusEvent arg0) {
		final Point p=con.getLocation();
		final Point px=con.getSize();
		
//            GC gc_main = new GC(con.getParent());
//            gc_main.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
//            gc_main.drawRectangle(p.x-1, p.y-1, px.x+1, px.y+1);
//            gc_main.dispose();
	
            con.getParent().addPaintListener(new PaintListener(){

				public void paintControl(PaintEvent e) {
					GC gc_main = new GC(con.getParent());
		            gc_main.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		            gc_main.drawRectangle(p.x-1, p.y-1, px.x+1, px.y+1);
		            gc_main.dispose();
		           
				}
            	
            });
            con.getParent().redraw();
		
	}
	
	public void focusLost(FocusEvent e) {
		final Point p=con.getLocation();
		final Point px=con.getSize();
         
//		    GC gc_main = new GC(con.getParent());
//            gc_main.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//            gc_main.drawRectangle(p.x-1,  p.y-1, px.x+1, px.y+1);
//            gc_main.dispose();
		
		con.getParent().addPaintListener(new PaintListener(){

			public void paintControl(PaintEvent e) {
				GC gc_main = new GC(con.getParent());
				gc_main.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	            gc_main.drawRectangle(p.x-1,  p.y-1, px.x+1, px.y+1);
	            gc_main.dispose();
	            
			}
        	
        });
		
}
}
