package com.ssj.table;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.actions.ActionGroup;
/**
 * TableÓÒ¼ü²Ëµ¥
 * @author Administrator
 *
 */
public class TableKillerActionGroup extends ActionGroup {
	private TableKillerViewer tv;
	private IAction [] actions;
	private boolean  export=true;
	public TableKillerActionGroup(TableKillerViewer tableviewer) {
		this.tv = tableviewer;
	}
	public void fillContextMenu(IMenuManager menuManager) {
		
	MenuManager menu=(MenuManager)menuManager;
	
	menu.add(new CopyAction(tv));
	if(export){
		menu.add(new ExportAction(tv));
		}
	if(actions!=null&&actions.length>0){
	for(IAction ac:actions){
		menu.add(ac);
	}
	}
	menu.add(new PrintAction(tv));
	menu.add(new SearchAction(tv));
	Table table=tv.getTable();
	Menu mm=menu.createContextMenu(table);

	table.setMenu(mm);
	}
	public IAction[] getActions() {
		return actions;
	}
	public void setActions(IAction[] actions) {
		this.actions = actions;
	}
	public void setExport(boolean export) {
		this.export = export;
	}
	
	

}
