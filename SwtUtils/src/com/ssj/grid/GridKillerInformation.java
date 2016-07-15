package com.ssj.grid;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GridKillerInformation extends Dialog {

	private Grid grid;

	private int heightCount;

	public GridKillerInformation(Shell arg0, Grid grid, int count) {
		super(arg0);
		this.grid = grid;
		int perHight = count / 2 + 1;
		this.heightCount = (int) (perHight * 12.7);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite parentComp = new Composite(parent, SWT.NONE);
		parentComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		parentComp.setLayout(new FillLayout());

		/* 滚动容器 */
		ScrolledComposite scrollComp = new ScrolledComposite(parentComp,
				SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);
		scrollComp.setLayout(new FillLayout());
		/* topComp创建在滚动容器上 */
		Composite topComp = new Composite(scrollComp, SWT.NONE);
		scrollComp.setContent(topComp);

		FillLayout fill = new FillLayout();
		fill.marginHeight = 20;
		fill.marginWidth = 20;
		topComp.setLayout(fill);
		Composite mainComp = new Composite(topComp, SWT.NONE);
		mainComp.setLayout(new GridLayout(4, false));
		if (grid != null) {
			GridItem items[] = grid.getSelection();
			GridColumn columns[] = grid.getColumns();
			int count = columns.length;

			for (int i = 0; i < count; i++) {
				new Label(mainComp, SWT.NONE).setText(columns[i].getText());
				Text text = new Text(mainComp, SWT.BORDER);
				text.setText(items[0].getText(i));
				text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			}

		}
		// topComp.setLayoutData(new GridData(GridData.FILL_BOTH));

		topComp.setSize(topComp.computeSize(getInitialSize().x-40, SWT.DEFAULT));
//		topComp.setSize(getInitialSize().x-40,getInitialSize().y);
		topComp.layout();

		return parent;
	}

	protected Point getInitialSize() {// 对话框大小
		Rectangle rtg = this.getShell().getMonitor().getClientArea();
		int width = rtg.width;
		int height = rtg.height;
		return new Point(width * 70 / 100, height * 50 / 100);//heightCount
	}

	protected void configureShell(Shell newShell) {// 标题

		super.configureShell(newShell);

		newShell.setText("详细信息");

	}

	public boolean close() {

		return super.close();
	}

	protected Point getInitialLocation(Point arg0) {// 位置
		Rectangle rtg = getShell().getMonitor().getClientArea();
		int width = rtg.width;
		int height = rtg.height;

		int x = getInitialSize().x;
		int y = getInitialSize().y;

		Point p = new Point((width - x) / 2, (height - y) / 2);
		return p;
	}

	protected Button createButton(Composite comp, int id, String label,
			boolean defaultButton) {// 取消OK和cancel按钮

		return null;
	}

	@Override
	protected int getShellStyle() {
		// TODO Auto-generated method stub
		return super.getShellStyle() | SWT.RESIZE;
	}

}
