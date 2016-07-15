package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HelloSwt {

	public static void main(String[] args) {
		Display dis = new Display();//创建一个display对象
		Shell shell = new Shell(dis);//shell是程序的主窗体
		shell.setLayout(new FillLayout());
		Text hello = new Text(shell,SWT.MULTI);//显示多行文本的文本框
		shell.setText("java应用程序");//设置窗体标题
		shell.setSize(200,100);//设置窗体大小
		Color color = new Color(Display.getCurrent(),255,255,255);
		shell.setBackground(color);
		hello.setText("hehhehhe");//设置文本信息
		hello.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!dis.readAndDispatch()){//如果dis不忙
				dis.sleep();//休眠
			}
		}
		dis.dispose();//销毁dis
		
	}

}
