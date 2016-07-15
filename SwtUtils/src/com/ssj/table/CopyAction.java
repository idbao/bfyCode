package com.ssj.table;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

import com.ssj.grid.GridKillerImageDescriptor;
import com.ssj.util.MpStaticName;

/**
 * ¸´ÖÆ²Ëµ¥
 * @author ssj234
 *
 */
public class CopyAction extends Action {
	
	private TableViewer tv;
	public CopyAction(TableViewer tv){
		setText(MpStaticName.copy);
		this.tv=tv;
	}
	@Override
	public void run() {
		TableItem []items= tv.getTable().getSelection();
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String copy="";
		int count =tv.getTable().getColumnCount();
		if(items!=null){
		for(TableItem item:items){
			for(int i=0;i<count;i++){
				copy+=item.getText(i);
			}
		}
		
		String rtfText = "{\\rtf1\\b Hello World}";
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rftTransfer = RTFTransfer.getInstance();
		clipboard.setContents(new String[]{copy, rtfText}, new Transfer[]{textTransfer, rftTransfer});
		clipboard.dispose();
	}
	}
	
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/EditConfigItem.png");
	}
}
