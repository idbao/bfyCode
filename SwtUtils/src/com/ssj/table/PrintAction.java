package com.ssj.table;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.nebula.widgets.grid.internal.TextUtils;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.theme.DrawData;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ssj.grid.GridKillerImageDescriptor;

public class PrintAction extends Action {
	String text="¥Ú”°";
	protected TableKillerViewer tv;
	public PrintAction(TableKillerViewer tv){
		setText(text);
		this.tv=tv;
	}
	@Override
	public void run() {
		TableItem []items= tv.getTable().getItems();
		int x=10,y=10;
		int len=items.length;
		int wid=tv.getTable().getColumnCount();
		
		
		int widths[]=new int[wid];
		
		for(int i=0;i<wid;i++)
		{
		TableColumn col=tv.getTable().getColumn(i);
		widths[i]=col.getText().length();
		}
		
		
		for(int i=0;i<wid;i++)
		{
			int strLenth=widths[i];
			for(int j=0;j<len;j++)
			{
				int strL=items[j].getText(i).length();
				if(strL>strLenth)
				{
					strLenth=strL;
				}
				widths[i]=strLenth;
			}
		
		}
		
		
		
		
		PrinterData data=Printer.getDefaultPrinterData();
		
		if(data==null)
		{
			return;
		}

		Printer printer=new Printer(data);
		
		if(printer.startJob("grid"))
		{
			GC gc=new GC(printer);
			printer.startPage();
			
			for(int s=0;s<wid;s++)
			{
				String str=tv.getTable().getColumn(s).getText();
				gc.drawText(str, x, y,DrawData.DRAW_VCENTER);
				x=x+(30*widths[s]);
			}
			
			x=0;
			y=y+50;
			
			for(int i=0;i<len;i++)
			{
				for(int j=0;j<wid;j++)
				{
					String str=items[i].getText(j);
					gc.drawString(str, x, y);
					
					x=x+(30*widths[j]);;
				}
				x=0;
				y=y+50;
			}
			
			printer.endPage();
			printer.endJob();
			gc.dispose();
		}
		printer.dispose();
		
	}
	
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/Print.png");
	}
	
}
