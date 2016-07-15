package com.ssj.grid;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;


/*********************************************************************
 *�ļ���:GridKillerEmptyColumnHeaderRenderer.java
 *������λ���ں����
 *��Ȩ���ں��������
 *@author: ʷʥ��
 *@since: Jdk 5.0������
 *@version��1.0
 *@date 2010-1-4
 *********************************************************************/
public class GridKillerEmptyColumnHeaderRenderer extends AbstractRenderer {

	public Point computeSize(GC gc, int wHint, int hHint, Object arg3) {
		 return new Point(wHint, hHint);
	}

	public void paint(GC gc, Object arg1) {

        gc.setBackground(GridKillerColor.headColor);//SWTResourceManager.getColor(152, 245 ,255)--getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND)

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width + 1,
                         getBounds().height);
	}

}
