package com.ssj.grid;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
/**
 * 行头 空 时的样式
 * @author Administrator
 *
 */
public class GridKillerEmptyRowHeaderRenderer extends AbstractRenderer {


    /** 
     * {@inheritDoc}
     */
    public void paint(GC gc, Object value)
    {

        gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));//COLOR_WHITE

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height + 1);

        gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

        Grid grid = (Grid) value;
        
        if (!grid.getCellSelectionEnabled())
        {
        
            gc.drawLine(getBounds().x, getBounds().y, getBounds().x + getBounds().width - 1,
                        getBounds().y);
            gc.drawLine(getBounds().x, getBounds().y, getBounds().x, getBounds().y + getBounds().height
                                                                     - 1);
    
            gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
            gc.drawLine(getBounds().x + 1, getBounds().y + 1,
                        getBounds().x + getBounds().width - 2, getBounds().y + 1);
            gc.drawLine(getBounds().x + 1, getBounds().y + 1, getBounds().x + 1,
                        getBounds().y + getBounds().height - 2);
    
            gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
            gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                              + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
            gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                               + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
    
            gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
            gc.drawLine(getBounds().x + getBounds().width - 2, getBounds().y + 1,
                        getBounds().x + getBounds().width - 2, getBounds().y + getBounds().height
                                                               - 2);
            gc.drawLine(getBounds().x + 1, getBounds().y + getBounds().height - 2,
                        getBounds().x + getBounds().width - 2, getBounds().y + getBounds().height
                                                               - 2);
        }
        else
        {
            gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

            gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                              + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
            gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                               + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
        }

    }

    /** 
     * {@inheritDoc}
     */
    public Point computeSize(GC gc, int wHint, int hHint, Object value)
    {
        return new Point(wHint, hHint);
    }

}

