package com.bfy.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HelloSwt {

	public static void main(String[] args) {
		Display dis = new Display();//����һ��display����
		Shell shell = new Shell(dis);//shell�ǳ����������
		shell.setLayout(new FillLayout());
		Text hello = new Text(shell,SWT.MULTI);//��ʾ�����ı����ı���
		shell.setText("javaӦ�ó���");//���ô������
		shell.setSize(200,100);//���ô����С
		Color color = new Color(Display.getCurrent(),255,255,255);
		shell.setBackground(color);
		hello.setText("hehhehhe");//�����ı���Ϣ
		hello.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!dis.readAndDispatch()){//���dis��æ
				dis.sleep();//����
			}
		}
		dis.dispose();//����dis
		
	}

}
