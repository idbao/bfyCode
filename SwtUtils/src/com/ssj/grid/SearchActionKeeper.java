package com.ssj.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

import com.ssj.util.SWTResourceManager;


/**
 * ȫ�ֵ�������
 * @author Administrator
 *
 */
public class SearchActionKeeper {

	
	public static Shell shell;
	//�߽���ɫ �ͱ���ɫ
	public static Color borderClolr=SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT),
	                    backgroundColor=SWTResourceManager.getColor(SWT.COLOR_CYAN);
	
	public static int alpha=255;
	
	public final static int BORDER=1;
}
