package com.ssj.grid;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;


/*********************************************************************
 *�ļ���:GridKillerTopLeftRenderer.java
 *������λ���ں����
 *��Ȩ���ں��������
 *@author: ʷʥ��
 *@since: Jdk 5.0������
 *@version��1.0
 *@date 2009-12-29
 *���ϵ���ʽ
 *********************************************************************/
public class GridKillerTopLeftRenderer extends AbstractRenderer {
    /** 
     * {@inheritDoc}
     */
    public Point computeSize(GC gc, int wHint, int hHint, Object value)
    {
        return new Point(wHint, hHint);
    }

    /** 
     * {@inheritDoc}
     */
    public void paint(GC gc, Object value)
    {
        gc.setBackground(GridKillerColor.headColor);//COLOR_WIDGET_NORMAL_SHADOW

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width,
                         getBounds().height + 1);

        gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));

//        gc.drawString("���",getBounds().x+3 , getBounds().y+3);
        gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        gc.drawLine(getBounds().x - 1+getBounds().width,
			    getBounds().y + 1,
			    getBounds().x -1+getBounds().width,
			    getBounds().y + getBounds().height
					- 5);
//
//        gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
//                                                                           + getBounds().width,
//                    getBounds().y + getBounds().height - 1);

    }}
