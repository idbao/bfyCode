package com.ssj.table;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
/**
 * Œ™TableKillerº”»Î–Ú∫≈
 * @author ssj234
 *
 */
public class ItemsNumberForTable {
    private static HashMap numberLabelForTableViewer = new HashMap();
    public static void setItemsNumber(TableViewer tableViewer){
        List numberLabel = new ArrayList();
        List oldNumberLabel = (List)getNumberLabelForTableViewer().get(tableViewer);
        if(oldNumberLabel!=null){
            for(Iterator iter = oldNumberLabel.iterator();iter.hasNext();){
                CLabel cLabel = (CLabel)iter.next();
                cLabel.dispose();
            }
        }
//        Image image = new Image(Display.getDefault(), 1, 25);
        TableItem[] tableItem = tableViewer.getTable().getItems();
        TableEditor te[] = new TableEditor[tableItem.length];    
        for (int i = 0; i < tableItem.length; i++) {
            te[i] = new TableEditor(tableViewer.getTable());
            te[i].horizontalAlignment = SWT.CENTER;
            te[i].grabHorizontal = true;
            
            final CLabel lbl = new CLabel(tableViewer.getTable(), SWT.BORDER);
            lbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
//            lbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
            
            lbl.setAlignment(SWT.CENTER);
            lbl.setEnabled(false);
            lbl.setText("" + (i+1));
            te[i].setEditor(lbl, tableItem[i], 0);
//            tableItem[i].setImage(image);     
            numberLabel.add(lbl);
        }
        numberLabelForTableViewer.put(tableViewer, numberLabel);
        
    }
    private static HashMap getNumberLabelForTableViewer(){
        return numberLabelForTableViewer;
    }
}


