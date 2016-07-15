package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Sample4_2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("按钮实例");
		Button bt1 = new Button(shell,SWT.NULL);//创建默认按钮
		bt1.setBounds(10,10,75,30);//设置按钮显示的位置及宽度。高度
		Button bt2 = new Button(shell,SWT.PUSH|SWT.BORDER);
		bt2.setText("SWT.PUSH");
		bt2.setBounds(90,10,75,30);
		Button check1 = new Button(shell,SWT.CHECK);//创建复选按钮
		check1.setText("SWT.CHECK");
		check1.setBounds(10,60,75,30);
		Button arrowLeft = new Button(shell,SWT.ARROW|SWT.LEFT);//箭头按钮向左
		arrowLeft.setBounds(10,130,75,20);
		Button arrowRight = new Button(shell,SWT.ARROW|SWT.RIGHT|SWT.BORDER);
		arrowRight.setBounds(90,130,75,20);
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
