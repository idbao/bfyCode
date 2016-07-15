package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 文本框示例
 * @author Administrator
 *
 */
public class Sample4_4 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("文本框实例");
		Text t1 = new Text(shell,SWT.NONE|SWT.BORDER);
		t1.setBounds(10,10,70,30);
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){//如果display不忙，则display休眠
				display.sleep();
			}
		}
		display.dispose();
	}

}
