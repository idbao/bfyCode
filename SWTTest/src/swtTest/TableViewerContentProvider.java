package swtTest;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TableViewerContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List){
			return ((List)inputElement).toArray();
		}else{
			return new Object[0];
		}
	}

}
