package com.ssj.util;

import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 提示信息框
 * @author ouy
 */
public class CToolTip extends ToolTip {
	private String message;
	org.eclipse.swt.widgets.ToolTip tt;
	public CToolTip(Control control) {
		super(control);
		
	}
	public CToolTip(Control control,String message){
		super(control);
		this.message = message;
	}
	
	protected Composite createToolTipContentArea(Event event, Composite parent) {
		parent.setLayout(new FillLayout());
		
		

		
		CLabel label=new CLabel(parent,SWT.SHADOW_IN);
		label.setLayout(new FillLayout());
			
		label.setImage(SWTResourceManager.getImage(CToolTip.class,
				"/images/information.png"));
		
		label.setText(message);
		
		
		label.setBackground(new Color[]{Display.getCurrent().getSystemColor(SWT.COLOR_CYAN),
				Display.getCurrent().getSystemColor(SWT.COLOR_WHITE),
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND)}, new int[]{25,100});
		return parent;
	}

	
}
