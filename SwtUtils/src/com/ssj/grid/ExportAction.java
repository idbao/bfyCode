package com.ssj.grid;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;

public class ExportAction extends Action {

	protected GridTableViewer gridViewer;
	public ExportAction(GridTableViewer gridViewer){
		this.gridViewer=gridViewer;
		setText("导出");
		setMenuCreator(new ExportMenuDropAction(gridViewer));
	}
	

		//REFARSH表格ref刷新 重新读取数据
	
	@Override
	public int getStyle() {
		
		return  AS_DROP_DOWN_MENU ;
		
	}


	public void run() {
		System.out.println(super.getStyle());
		super.run();
	}
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/MainSystem1.png");
	}
}
