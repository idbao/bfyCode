/*
 *@(#)CommonDialogComp.java  2009-9-14
 *
 *Copyright 2009 YHSOFT,All rights reserved.
 */
package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 对话框容器分为三部分，大小比例可以自己设定
 * 
 * @author ssj234
 * 
 */
public class CommonDialogComp {

	private Composite leftComp, midComp, rightComp, buttom, top;

	private Group group1, group2;

	private Button add, delete, okx, cancelx, search;

	private Text text;

	private Boolean whether = false;

	/**
	 * 
	 * @return
	 */
	public void create(Composite parent, int[] style, String group1Name,
			String group2Name, boolean language) {

		// 设置父容器
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout gridLayout = new GridLayout(style[0] + style[1] + style[2],
				true);
//		gridLayout.marginWidth=20;
		parent.setLayout(gridLayout);

		// 三个容器
		leftComp = new Composite(parent, SWT.NONE);
		midComp = new Composite(parent, SWT.NONE);
		rightComp = new Composite(parent, SWT.NONE);
		// 三个容器布局数据
		GridData gd1 = new GridData(GridData.FILL_BOTH);
		gd1.horizontalSpan = style[0];
		leftComp.setLayoutData(gd1);

		GridData gd2 = new GridData(GridData.FILL_BOTH);
		gd2.horizontalSpan = style[1];
		midComp.setLayoutData(gd2);

		GridData gd3 = new GridData(GridData.FILL_BOTH);
		gd3.horizontalSpan = style[2];
		rightComp.setLayoutData(gd3);

		// 容器内容
		leftComp.setLayout(new FillLayout());
		midComp.setLayout(new GridLayout(1, true));
		rightComp.setLayout(new FillLayout());

		// leftComp
		group1 = new Group(leftComp, SWT.NONE);
		group1.setText(group1Name);
		SeparateLayout sl = new SeparateLayout(group1);
		top = sl.getTopComp();
		buttom = sl.getButtomComp();
		CommonTopComp.createDialog(top);
		search = CommonTopComp.search;
		text = CommonTopComp.text;

		// midComp
		Label label1 = new Label(midComp, SWT.NONE);
		GridData g1 = new GridData(GridData.FILL_HORIZONTAL);
		g1.verticalSpan = 15;//20
		label1.setLayoutData(g1);
		add = new Button(midComp, SWT.NONE);
		delete = new Button(midComp, SWT.NONE);
		Label label2 = new Label(midComp, SWT.NONE);
		GridData g2 = new GridData(GridData.FILL_HORIZONTAL);
		g2.verticalSpan = 15;//20
		label2.setLayoutData(g2);
//		if (whether)
//			ok = new Button(midComp, SWT.NONE);
//		cancel = new Button(midComp, SWT.NONE);
		Label label3 = new Label(midComp, SWT.NONE);
		GridData g3 = new GridData(GridData.FILL_HORIZONTAL);
		g3.verticalSpan = 15;//20
		label3.setLayoutData(g3);

		add.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		add.setLayoutData(new GridData(80,30));
		delete.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		if (whether)
//			ok.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		cancel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (language) {
			add.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev1.jpg"));
			delete.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev2.jpg"));
//			if (whether)
//				ok.setText("确认");
//			cancel.setText("返回");
		} else {
			add.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev1.jpg"));
			delete.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev2.jpg"));
//			if (whether)
//				ok.setText("OK");
//			cancel.setText("Cancel");
		}

		// rightComp

		group2 = new Group(rightComp, SWT.NONE);
		group2.setText(group2Name);
	}

	/**
	 * 分为三个容器 ，无group
	 * 
	 * @param parent
	 * @param style
	 * @param language
	 */
	public void create(Composite parent, int[] style, boolean language) {

		// 设置父容器
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout gridLayout = new GridLayout(style[0] + style[1] + style[2],
				true);
		gridLayout.marginWidth=20;
		parent.setLayout(gridLayout);

		// 三个容器
		leftComp = new Composite(parent, SWT.NONE);
		midComp = new Composite(parent, SWT.NONE);
		rightComp = new Composite(parent, SWT.NONE);
		// 三个容器布局数据
		GridData gd1 = new GridData(GridData.FILL_BOTH);
		gd1.horizontalSpan = style[0];
		leftComp.setLayoutData(gd1);

		GridData gd2 = new GridData(GridData.FILL_BOTH);
		gd2.horizontalSpan = style[1];
		midComp.setLayoutData(gd2);

		GridData gd3 = new GridData(GridData.FILL_BOTH);
		gd3.horizontalSpan = style[2];
		rightComp.setLayoutData(gd3);

		// 容器内容
		leftComp.setLayout(new FillLayout());
		midComp.setLayout(new GridLayout(1, true));
		rightComp.setLayout(new FillLayout());

		// leftComp

		// midComp
		Label label1 = new Label(midComp, SWT.NONE);
		GridData g1 = new GridData(GridData.FILL_HORIZONTAL);
		g1.verticalSpan = 20;
		label1.setLayoutData(g1);
		add = new Button(midComp, SWT.NONE);
		delete = new Button(midComp, SWT.NONE);
		Label label2 = new Label(midComp, SWT.NONE);
		GridData g2 = new GridData(GridData.FILL_HORIZONTAL);
		g2.verticalSpan = 20;
		label2.setLayoutData(g2);
//		if (whether)
//			ok = new Button(midComp, SWT.NONE);
//		cancel = new Button(midComp, SWT.NONE);
		Label label3 = new Label(midComp, SWT.NONE);
		GridData g3 = new GridData(GridData.FILL_HORIZONTAL);
		g3.verticalSpan = 20;
		label3.setLayoutData(g3);

		add.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delete.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		if (whether)
//			ok.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		cancel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		if (language) {
			add.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev1.jpg"));
			delete.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev2.jpg"));
//			if (whether)
//				ok.setText("确认");
//			cancel.setText("返回");
		} else {
			add.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev1.jpg"));
			delete.setImage(SWTResourceManager.getImage(CommonDialogComp.class,"/images/select_prev2.jpg"));
//			if (whether)
//				ok.setText("OK");
//			cancel.setText("Cancel");
		}

		// rightComp

	}

	

	public Composite getLeftComp() {
		return leftComp;
	}

	public Composite getMidComp() {
		return midComp;
	}

	public Composite getRightComp() {
		return rightComp;
	}

	public Button getSearch() {
		return search;
	}

	public Text getText() {
		return text;
	}

	public Button getAdd() {
		return add;
	}

	public Composite getButtom() {
		return buttom;
	}

	public Button getCancel() {
		return null;
	}

	public Button getDelete() {
		return delete;
	}

	public Group getGroup1() {
		return group1;
	}

	public Group getGroup2() {
		return group2;
	}

	public Button getOk() {
		return null;
	}

	public Composite getTop() {
		return top;
	}

	public void setLeftComp(Composite leftComp) {
		this.leftComp = leftComp;
	}

	public void setMidComp(Composite midComp) {
		this.midComp = midComp;
	}

	public void setRightComp(Composite rightComp) {
		this.rightComp = rightComp;
	}

	public void setWhether(Boolean whether) {
		this.whether = whether;
	}

}
