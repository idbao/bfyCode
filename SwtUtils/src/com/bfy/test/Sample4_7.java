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
 * �˵�
 * @author Administrator
 *
 */
public class Sample4_7 {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("�˵�ʾ��");
		Menu mainMenu = new Menu(shell,SWT.BAR);
		shell.setMenuBar(mainMenu);
		{
			MenuItem fileItem = new MenuItem(mainMenu,SWT.CASCADE);
			fileItem.setText("�ļ�&F");
			Menu fileMenu = new Menu(shell,SWT.DROP_DOWN);
			fileItem.setMenu(fileMenu);
			{
				MenuItem newFileItem = new MenuItem(fileMenu,SWT.CASCADE);
				newFileItem.setText("�½�&N");
				//"�½�"�˵�
				Menu newFileMenu = new Menu(shell,SWT.DROP_DOWN);
				newFileItem.setMenu(newFileMenu);
				{
					//"�½���Ŀ"
					MenuItem newProjectItem = new MenuItem(newFileMenu,SWT.PUSH);
					newProjectItem.setText("��Ŀ\tCrtl+shit+N");
					//���ÿ�ݼ�
					newProjectItem.setAccelerator(SWT.CTRL+SWT.SHIFT+'N');
					//����¼�����
					newProjectItem.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e){
							Text text = new Text(shell,SWT.MULTI|SWT.BORDER|SWT.WRAP);
							text.setBounds(10,10,100,30);
							text.setText("��ѡ�����½���Ŀ");
						}
					});
					new MenuItem(newFileMenu,SWT.SEPARATOR);
					new MenuItem(newFileMenu,SWT.PUSH).setText("��");
					new MenuItem(newFileMenu,SWT.PUSH).setText("��");;
				}
				MenuItem openFileItem = new MenuItem(fileMenu,SWT.CASCADE);
				openFileItem.setText("��&o");
				MenuItem exitItem = new MenuItem(fileMenu,SWT.CASCADE);
				exitItem.setText("�˳�&E");
			}
			MenuItem helpItem = new MenuItem(mainMenu,SWT.CASCADE);
			helpItem.setText("����&H");
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
