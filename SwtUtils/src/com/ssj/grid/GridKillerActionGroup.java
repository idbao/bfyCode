package com.ssj.grid;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;
/**
 * 表格的菜单
 * @author ssj234
 *
 */
public class GridKillerActionGroup extends ActionGroup {
	protected GridKillerViewer tv;
	protected IAction [] actions;
	protected int chartType=0;
	protected boolean  export=true;
	public GridKillerActionGroup(GridKillerViewer tableviewer) {
		this.tv = tableviewer;
	}
	public void fillContextMenu(IMenuManager menuManager) {
		
	MenuManager menu=(MenuManager)menuManager;
	menu.add(new CopyAction(tv));
	
	menu.add(new SearchAction(tv));
	if(export){
	menu.add(new ExportAction(tv));
	}
	if(chartType!=0){
		menu.add(new ChartAction(tv,chartType));
	}
	if(actions!=null&&actions.length>0){
	for(IAction ac:actions){
		menu.add(ac);
	}
	}
	menu.add(new PrintAction(tv));
	Grid table=tv.getGrid();
	Menu mm=menu.createContextMenu(table);
	table.setMenu(mm);
	}
	public IAction[] getActions() {
		return actions;
	}
	public void setActions(IAction[] actions) {
		this.actions = actions;
	}
	public int getChartType() {
		return chartType;
	}
	public void setChartType(int chartType) {
		this.chartType = chartType;
	}
	public void setExport(boolean export) {
		this.export = export;
	}
	
	

}
