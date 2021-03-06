package com.ssj.grid;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.nebula.widgets.grid.internal.TextUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

import com.ssj.util.SWTResourceManager;

/**
 * 行头的一些设置
 * @author Administrator
 *
 */
public class GridKillerRowHeaderRenderer extends AbstractRenderer {

    int leftMargin = 6;

    int rightMargin = 8;

    int topMargin = 3;

    int bottomMargin = 3;

    private TextLayout textLayout;
   Color lineColor= SWTResourceManager.getColor(30 ,144 ,255);
    /**
     * {@inheritDoc}
     */
    
    public void paint(GC gc, Object value)
    {
    	
        GridItem item = (GridItem) value;
        String text = getHeaderText(item);

        gc.setFont(getDisplay().getSystemFont());
        
        Color background = getHeaderBackground(item);
        if( background == null ) {
        	//XXX 背景色
        	background = GridKillerColor.rowColor;//124 252 0--getDisplay().getSystemColor(SWT.COLOR_CYAN)
        }
        gc.setBackground(background);

        if (isSelected() && item.getParent().getCellSelectionEnabled())
        {
            gc.setBackground(item.getParent().getCellHeaderSelectionBackground());
        }

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height + 1);

        if (!item.getParent().getCellSelectionEnabled())
        {
        	
            if (isSelected())
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));//COLOR_WHITE
                //TODO 加入
//                gc.setBackground(lineColor);
//                gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
            }
            else
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
            }



            gc.drawLine(getBounds().x, getBounds().y, getBounds().x + getBounds().width - 1,
                        getBounds().y);
            gc.drawLine(getBounds().x, getBounds().y, getBounds().x, getBounds().y + getBounds().height
                                                                     - 1);

            if (!isSelected())
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));//getDisplay().getSystemColor(SWT.COLOR_WHITE)
                gc.drawLine(getBounds().x + 1, getBounds().y + 1,
                            getBounds().x + getBounds().width - 2, getBounds().y + 1);
                gc.drawLine(getBounds().x + 1, getBounds().y + 1, getBounds().x + 1,
                            getBounds().y + getBounds().height - 2);
            }

            if (isSelected())
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
            }
            else
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
            }
            gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                              + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
            gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                               + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);

            if (!isSelected())
            {
                gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
                gc.drawLine(getBounds().x + getBounds().width - 2, getBounds().y + 1,
                            getBounds().x + getBounds().width - 2, getBounds().y + getBounds().height
                                                                   - 2);
                gc.drawLine(getBounds().x + 1, getBounds().y + getBounds().height - 2,
                            getBounds().x + getBounds().width - 2, getBounds().y + getBounds().height
                                                                   - 2);
            }
        }
        else
        {
            gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

            gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                              + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
            gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                               + getBounds().width - 1,
                        getBounds().y + getBounds().height - 1);
        }

        int x = leftMargin;

        Image image = getHeaderImage(item);
//        if(image==null)
//        	image=SWTResourceManager.getImage(GridKillerRowHeaderRenderer.class,"/images/1.gif");

        if( image != null ) {
        	if( isSelected() && !item.getParent().getCellSelectionEnabled() ) {
        		gc.drawImage(image, x + 1, getBounds().y + 1 + (getBounds().height - image.getBounds().height)/2);
        		x += 1;
        	} else {
        		gc.drawImage(image, x, getBounds().y + (getBounds().height - image.getBounds().height)/2);
        	}
        	x += image.getBounds().width + 5;
        }

        int width = getBounds().width - x;

        width -= rightMargin;

        Color foreground = getHeaderForeground(item);
        if( foreground == null ) {
        	foreground = GridKillerColor.rowTextColor;//TODO 139 37 0 getDisplay().getSystemColor(SWT.COLOR_RED)
        }
//        gc.setFont(SWTResourceManager.getFont("宋体", 7, SWT.BOLD));//TODO
        gc.setForeground(foreground);

        
        int y = getBounds().y;
        int selectionOffset = 0;
        if (isSelected() && !item.getParent().getCellSelectionEnabled())
        {
            selectionOffset = 1;
        }
        
        if (!item.getParent().isWordWrapHeader())
        {
            y += (getBounds().height - gc.stringExtent(text).y) / 2;
            gc.drawString(TextUtils.getShortString(gc, text, width), getBounds().x + x + selectionOffset, y + selectionOffset, true);
        }
        else
        {
          getTextLayout(gc, item);
          textLayout.setWidth(width < 1 ? 1 : width);
          textLayout.setText(text);
          
          if (item.getParent().isAutoHeight())
          {
            // Look through all columns to get the max height needed for this item
          int columnCount = item.getParent().getColumnCount();
          int maxHeight = textLayout.getBounds().height + topMargin + bottomMargin;
          for (int i=0; i<columnCount; i++)
          {
            GridColumn column = item.getParent().getColumn(i);
            if (column.getWordWrap())
            {
              int height = column.getCellRenderer().computeSize(gc, column.getWidth(), SWT.DEFAULT, item).y;
              maxHeight = Math.max(maxHeight, height);
            }
          }
            
          if (maxHeight != item.getHeight())
          {
            item.setHeight(maxHeight);
          }
          }
          
          textLayout.draw(gc, getBounds().x + x + selectionOffset, y + selectionOffset);
        }

    }

    /**
     * {@inheritDoc}
     */
    public Point computeSize(GC gc, int wHint, int hHint, Object value)
    {
        GridItem item = (GridItem) value;

        String text = getHeaderText(item);
        Image image = getHeaderImage(item);

        int x = 0;

        x += leftMargin;

        if( image != null ) {
        	x += image.getBounds().width + 5;
        }

        x += gc.stringExtent(text).x + rightMargin;

        int y = 0;

        y += topMargin;

        if( image != null ) {
        	y += Math.max(gc.getFontMetrics().getHeight(),image.getBounds().height);
        } else {
        	y += gc.getFontMetrics().getHeight();
        }


        y += bottomMargin;

        return new Point(x, y);
    }

    private Image getHeaderImage(GridItem item) {
    	return item.getHeaderImage();
    }

    private String getHeaderText(GridItem item)
    {
        String text = item.getHeaderText();
        if (text == null)
        {
            text = (item.getParent().indexOf(item) + 1) + "";
        }
        return text;
    }
    
    private Color getHeaderBackground(GridItem item) {
    	return item.getHeaderBackground();
    }
    
    private Color getHeaderForeground(GridItem item) {
    	return item.getHeaderForeground();
    }
    
    private void getTextLayout(GC gc, GridItem gridItem)
    {
        if (textLayout == null)
        {
            textLayout = new TextLayout(gc.getDevice());
            textLayout.setFont(gc.getFont());
            gridItem.getParent().addDisposeListener(new DisposeListener()
            {                
                public void widgetDisposed(DisposeEvent e)
                {
                    textLayout.dispose();
                }                
            });
        }
    }
}
