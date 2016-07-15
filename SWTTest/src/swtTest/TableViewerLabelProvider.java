package swtTest;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableViewerLabelProvider implements ITableLabelProvider{

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 由此方法决定记录在表格的每一列是显示什么文字
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		People people = (People) element;
		if(columnIndex==0){
			return people.getId().toString();
		}
		if(columnIndex==1){
			return people.getName();
		}
		if(columnIndex==2){
			return people.isSex()?"男":"女";
		}
		if(columnIndex==3){
			return people.getAge()+"";
		}
		if(columnIndex==4){
			return people.getCreateDate().toString();
		}
		return "";
	}

}
