package com.ssj.util;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * ���֮����ʾ��ʾ��ͬʱ��ѯ���ݿ� ��ʱ����ʱ ������ʾ
 * 
 * @author ssj234
 * 
 */
public class ShowClickImage {
    private Shell shell;

    public void init() {
	Display.getCurrent().asyncExec(new Runnable() {

	    public void run() {
		final SplashTest sj = new SplashTest();

		sj.ssj(Display.getCurrent(), Display.getCurrent()
			.getActiveShell());

		shell = sj.getShell();
		try {
		    Display.getCurrent().asyncExec(new Runnable() {

			public void run() {
			    try {
				setting();
			    } catch (Exception e) {
				e.printStackTrace();
				MessageDialog.openError(null, "��������", e
					.getMessage());
				dispose();
			    }
			    dispose();
			}
		    });
		} catch (Exception e) {

		}
	    }
	});
    }

    public void setting() {
	System.out.println("����������������");
    }

    public void dispose() {
	// NotifierDialog.dispose();
	if (shell == null)
	    return;
	shell.dispose();

    }

    public void run() {
	init();
    }

}
