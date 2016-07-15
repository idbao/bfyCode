package com.ssj.grid;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ssj.util.SWTResourceManager;


/**
 * 菜单  -->搜索
 * @author Administrator
 *
 */
public class SearchAction extends Action {
	String text="搜索";
	protected GridKillerViewer tv;
	
	private boolean drag=false;
	public SearchAction(GridKillerViewer tv){
		setText(text);
		this.tv=tv;
	}
	@Override
	public void run() {
		if(SearchActionKeeper.shell==null){//全局未创建
			
			SearchActionKeeper.shell=new Shell(Display.getCurrent().getActiveShell(),SWT.NO_TRIM|SWT.BORDER);
			SearchActionKeeper.shell.setText("表格搜索栏");
			SearchActionKeeper.shell.setAlpha(SearchActionKeeper.alpha);
			//背景色 模式
//			SearchActionKeeper.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
			SearchActionKeeper.shell.setImage(SWTResourceManager.getImage(SearchAction.class, "/images/Serch.png"));
			Point point=tv.getGrid().getLocation();
			Point size=tv.getGrid().getSize();
			SearchActionKeeper.shell.setLocation(point.x+size.x/2, point.y+size.y-30);
			
			
			SearchActionKeeper.shell.setBackground(SearchActionKeeper.borderClolr);
			FillLayout rl=new FillLayout(SWT.HORIZONTAL);
			rl.marginHeight=SearchActionKeeper.BORDER;
			rl.marginWidth=SearchActionKeeper.BORDER;
			SearchActionKeeper.shell.setLayout(rl);
			SearchTopComp.create(SearchActionKeeper.shell);
			//关闭
			SearchTopComp.close.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					ViewerFilter filters[]=tv.getFilters();
					for(int i=0;i<filters.length;i++)
					{
						tv.removeFilter(filters[i]);
					}
					
					SearchActionKeeper.shell.dispose();
					SearchActionKeeper.shell=null;
				}
			});
			
			//拖动
			SearchTopComp.clabel.addMouseListener(new MouseListener(){
                 
				public void mouseDoubleClick(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void mouseDown(MouseEvent arg0) {
					drag=true;
				}

				public void mouseUp(MouseEvent arg0) {
					drag=false;
					
				}
				
			});
			
			SearchTopComp.clabel.addMouseMoveListener(new MouseMoveListener(){

				public void mouseMove(MouseEvent e) {
					if(drag){
						Point point=SearchActionKeeper.shell.getLocation();
						SearchActionKeeper.shell.setLocation(point.x+e.x, point.y+e.y);
					}
				}
				
			});
			
			
			SearchActionKeeper.shell.pack();
			SearchActionKeeper.shell.open();
			
			 tv.setSearchControl(SearchTopComp.combo,SearchTopComp.text,SearchTopComp.search,SearchTopComp.filter);
			}else{
				//shell已创建
				tv.setSearchControl(SearchTopComp.combo,SearchTopComp.text,SearchTopComp.search,SearchTopComp.filter);
			}
		//设置焦点
		SearchActionKeeper.shell.forceFocus();
		}
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return new GridKillerImageDescriptor("/images/EndTime.png");
	}
	
	
}
