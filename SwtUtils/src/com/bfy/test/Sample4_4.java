package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * �ı���ʾ��
 * @author Administrator
 *
 */
public class Sample4_4 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("�ı���ʵ��");
		Text t1 = new Text(shell,SWT.NONE|SWT.BORDER);
		t1.setBounds(10,10,70,30);
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){//���display��æ����display����
				display.sleep();
			}
		}
		display.dispose();
	}

}
