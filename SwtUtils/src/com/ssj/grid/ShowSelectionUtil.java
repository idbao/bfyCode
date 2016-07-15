package com.ssj.grid;

import org.eclipse.swt.widgets.ScrollBar;



/**
 * 这个是进入dialog时的显示出定位的util类
 * 
 * @author Administrator
 * 
 */
public class ShowSelectionUtil {
    public static void showSelection(GridKillerViewer table) {
	ScrollBar verticalBar = table.getGrid().getVerticalBar();
	ScrollBar horizontalBar = table.getGrid().getHorizontalBar();
	int i = table.getGrid().getSelectionIndex();
	int j = table.getGrid().getItemCount();
//	System.out.println(i);
//	System.out.println(j);
	verticalBar.setPageIncrement(6);
	verticalBar.setMaximum(j + 5);
	verticalBar.setSelection(i);
	horizontalBar.setVisible(true);
	verticalBar.setVisible(true);
    }
}
