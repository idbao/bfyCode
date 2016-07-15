package com.ssj.table;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;

import com.ssj.grid.GridKillerImageDescriptor;
import com.ssj.util.MpStaticName;





public class ExportAction extends Action {

	
	public ExportAction(TableViewer gridViewer){
		
		setText(MpStaticName.export);
		setMenuCreator(new ExportMenuDropAction(gridViewer));
	}
	

		//REFARSH表格ref刷新 重新读取数据
	
	@Override
	public int getStyle() {
		
		return  AS_DROP_DOWN_MENU ;
		
	}


	public void run() {
		super.run();
	}
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/MainSystem1.png");
	}
}
