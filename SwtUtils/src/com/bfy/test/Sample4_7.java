package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 菜单
 * @author Administrator
 *
 */
public class Sample4_7 {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("菜单示例");
		Menu mainMenu = new Menu(shell,SWT.BAR);
		shell.setMenuBar(mainMenu);
		{
			MenuItem fileItem = new MenuItem(mainMenu,SWT.CASCADE);
			fileItem.setText("文件&F");
			Menu fileMenu = new Menu(shell,SWT.DROP_DOWN);
			fileItem.setMenu(fileMenu);
			{
				MenuItem newFileItem = new MenuItem(fileMenu,SWT.CASCADE);
				newFileItem.setText("新建&N");
				//"新建"菜单
				Menu newFileMenu = new Menu(shell,SWT.DROP_DOWN);
				newFileItem.setMenu(newFileMenu);
				{
					//"新建项目"
					MenuItem newProjectItem = new MenuItem(newFileMenu,SWT.PUSH);
					newProjectItem.setText("项目\tCrtl+shit+N");
					//设置快捷键
					newProjectItem.setAccelerator(SWT.CTRL+SWT.SHIFT+'N');
					//添加事件监听
					newProjectItem.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e){
							Text text = new Text(shell,SWT.MULTI|SWT.BORDER|SWT.WRAP);
							text.setBounds(10,10,100,30);
							text.setText("你选择了新建项目");
						}
					});
					new MenuItem(newFileMenu,SWT.SEPARATOR);
					new MenuItem(newFileMenu,SWT.PUSH).setText("包");
					new MenuItem(newFileMenu,SWT.PUSH).setText("类");;
				}
				MenuItem openFileItem = new MenuItem(fileMenu,SWT.CASCADE);
				openFileItem.setText("打开&o");
				MenuItem exitItem = new MenuItem(fileMenu,SWT.CASCADE);
				exitItem.setText("退出&E");
			}
			MenuItem helpItem = new MenuItem(mainMenu,SWT.CASCADE);
			helpItem.setText("帮助&H");
		}
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
		
	}

}
