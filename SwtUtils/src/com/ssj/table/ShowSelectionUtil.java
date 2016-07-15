package com.ssj.table;

import org.eclipse.swt.widgets.ScrollBar;



/**
 * 这个是进入dialog时的显示出定位的util类
 * 
 * @author Administrator
 * 
 */
public class ShowSelectionUtil {
    public static void showSelection(TableKillerViewer table) {
	ScrollBar verticalBar = table.getTable().getVerticalBar();
	ScrollBar horizontalBar = table.getTable().getHorizontalBar();
	int i = table.getTable().getSelectionIndex();
	int j = table.getTable().getItemCount();
//	System.out.println(i);
//	System.out.println(j);
	verticalBar.setPageIncrement(6);
	verticalBar.setMaximum(j + 5);
	verticalBar.setSelection(i);
	horizontalBar.setVisible(true);
	verticalBar.setVisible(true);
    }
}
