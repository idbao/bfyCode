package com.ssj.util;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.ssj.grid.IGridPagination;

public interface ITableKillerViewer {

	
	public void setInput(List list, boolean flag);

	public void setInput(List list);
	
	public void setList(List list);
	
	public void setInput(IGridPagination iGridPage);
	
	public ISelection getSelection();
	
	public Object getSelectionObj();
	public List getSelectionList();
	public void setMulti(boolean pagination);
	public void removeSelectedItems();
	public void addWithInput(Object obj);
	public void delWithInput(Object obj);
	public int getPageIndex() ;
	public void setContent(int pageIndex, boolean real) ;
	public void setPagination(boolean pagination);
	public void addCombo(final Combo combo, final String property);
	public void addCombos(final Combo cbs[], final String propertys[]);
	public void getComboItems(Combo combo,String property);
	public void refreshCombo();
	public void selectFirst(List rs);
	public void setSearchControl(final Combo cb,final Text text,Widget search,final Widget filter);
	
	

}
