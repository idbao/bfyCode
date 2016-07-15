package com.ssj.grid;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;


/*********************************************************************
 *文件名:GridKillerTopLeftRenderer.java
 *开发单位：亿海软件
 *版权归亿海软件所有
 *@author: 史圣杰
 *@since: Jdk 5.0或以上
 *@version：1.0
 *@date 2009-12-29
 *左上的样式
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

//        gc.drawString("序号",getBounds().x+3 , getBounds().y+3);
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
