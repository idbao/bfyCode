package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Sample4_2 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("��ťʵ��");
		Button bt1 = new Button(shell,SWT.NULL);//����Ĭ�ϰ�ť
		bt1.setBounds(10,10,75,30);//���ð�ť��ʾ��λ�ü���ȡ��߶�
		Button bt2 = new Button(shell,SWT.PUSH|SWT.BORDER);
		bt2.setText("SWT.PUSH");
		bt2.setBounds(90,10,75,30);
		Button check1 = new Button(shell,SWT.CHECK);//������ѡ��ť
		check1.setText("SWT.CHECK");
		check1.setBounds(10,60,75,30);
		Button arrowLeft = new Button(shell,SWT.ARROW|SWT.LEFT);//��ͷ��ť����
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
