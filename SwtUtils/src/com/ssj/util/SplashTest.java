package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * ��ʱ������ͼ����ʾ
 * 
 * @author ssj234
 * 
 */
public class SplashTest {

    Shell shell;

    Image image;

    public void ssj(Display display, Shell par) {
	if (par == null)
	    return;
	shell = new Shell(par, SWT.NO_TRIM | SWT.NO_FOCUS);
	image = SWTResourceManager
		.getImage(SplashTest.class, "/images/wait.bmp");
	ImageData imdata = image.getImageData();
	shell.setSize(imdata.width, imdata.height);
	// shell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));

	// Ȼ��splash��ʱ��ͼƬ������Ļ���롣

	// Rectangle p = display.getBounds();
	// Point p=CreateParentComp.shell.getSize();
	Point p = par.getSize();
	Point l = par.getLocation();

	int shellX = (p.x - imdata.width) / 2;

	int shellY = (p.y - imdata.height) / 2;

	// shell.setSize(p.x, p.y);

	shell.setLocation(l.x + shellX, l.y + shellY);

	// Ȼ���shell��������ͼƬ��
	// shell.setLayout(new FillLayout());
	// Canvas canvas = new Canvas(shell, SWT.NONE);
	// canvas.addPaintListener(new PaintListener() {
	// public void paintControl(PaintEvent e) {
	// Image image=SWTResourceManager.getImage(ImageTest.class,
	// "/images/loading.gif");
	// ImageData data=image.getImageData();
	// data.scaledTo(455, 295);
	// Image im=new Image(e.display,data);
	// e.gc.drawImage(im,0,0);
	// im.dispose();
	// }
	// });

	shell.open();
	GC gc = new GC(shell);

	gc.drawImage(image, 0, 0);
	/*
         * OS.SetWindowLong(shell.handle, OS.GWL_EXSTYLE, OS.GetWindowLong(
         * shell.handle, OS.GWL_EXSTYLE) ^ 0x80000); // load User32.dll lib
         * TCHAR lpLibFileName = new TCHAR(0, "user32.dll", true); int hInst =
         * OS.LoadLibrary(lpLibFileName); if (hInst != 0){ // �趨���ú������� String
         * name = "SetLayeredWindowAttributes"; byte[] lpProcName = new
         * byte[name.length()]; for (int i = 0; i < lpProcName.length; i++){
         * lpProcName[i] = (byte) name.charAt(i); } // ����DLL���������ַ int fun =
         * OS.GetProcAddress(hInst, lpProcName); // ���������� if (fun != 0){ //
         * 150Ϊ͸����,��0-255֮�� OS.CallWindowProc(fun, shell.handle, 0, 10, 2); } //
         * �ͷ�lib OS.FreeLibrary(hInst);
         */

	// Ȼ���ͷ����е���Դ��

    }

    public Shell getShell() {
	return this.shell;
    }

    public Image getImage() {
	return this.image;
    }

}
