package com.ssj.grid;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class CopyAction extends Action {
	String text="复制";
	protected GridTableViewer tv;
	public CopyAction(GridTableViewer tv){
		setText(text);
		this.tv=tv;
	}
	@Override
	public void run() {
		GridItem []items= tv.getGrid().getSelection();
		if(items==null){
			return;
		}
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String copy="";
		int count =tv.getGrid().getColumnCount();
		try{
		for(GridItem item:items){
			
			for(int i=0;i<count;i++){
				
				copy+=item.getText(i);
				
			}
		}
		}catch (Exception e) {
			MessageDialog.openError(null, "错误提示", "发生错误！");
		}
		String rtfText = "{\\rtf1\\b Hello World}";
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rftTransfer = RTFTransfer.getInstance();
		try{
		clipboard.setContents(new String[]{copy, rtfText}, new Transfer[]{textTransfer, rftTransfer});
		}catch (Exception e) {
			return;
		}
		clipboard.dispose();
	}
	
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/EditConfigItem.png");
	}
	
}
