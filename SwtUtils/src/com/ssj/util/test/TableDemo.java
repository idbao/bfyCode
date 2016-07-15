package com.ssj.util.test;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import com.ssj.util.ITableKillerViewer;
import com.ssj.util.SeparateLayout;
import com.ssj.util.TableUtil;

public class TableDemo extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TableDemo shell = new TableDemo(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public TableDemo(Display display) {
		super(display, SWT.SHELL_TRIM);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);
		setLayout(new FillLayout());
		
		SeparateLayout sl=new SeparateLayout(getShell());
		
		Combo cb=new Combo(sl.getTopComp(),SWT.READ_ONLY);
		
		TableUtil tu=new TableUtil();
		tu.setElement(People.class, new String[]{"ID","√‹¬Î","µÿ÷∑"}, new String[]{"id","password","add"}, new int []{100,100,100});
		tu.setStyle(true,true);
		tu.create(sl.getButtomComp(), TableUtil.GRID);
		ITableKillerViewer tv=tu.getTableViewer();
		tv.setList(PeopleFactory.getPeople());
		tv.addCombo(cb, "add");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
