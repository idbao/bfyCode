package com.ssj.util;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 点击之后显示提示，同时查询数据库 耗时操作时 出现提示
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
				MessageDialog.openError(null, "发生错误", e
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
	System.out.println("这里是所做的事情");
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
