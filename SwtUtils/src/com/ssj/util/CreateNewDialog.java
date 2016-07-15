package com.ssj.util;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
/**
 * 创建新的对话框布局
 * @author ssj234
 *
 */
public class CreateNewDialog {

	private Button exit,confirm;//返回
	private Button copy, continuing,add,delete,first,previous,next,last,ok,cancel;
	private SashForm upSash,mainSash;
	private Composite upComp,downComp,leftComp,rightComp,groupBottom;
	private Group group;
	private SeparateLayout separateLayout;
	
	public void createDialog(Composite parent,int [] upDown,int [] leftRight,String groupTitle){
		//底部不为空
		FillLayout fill=new FillLayout();
		parent.setLayout(fill);
		
		CreateSashForm sf1=new CreateSashForm();
		Composite compA[]=sf1.create(parent, upDown, SWT.VERTICAL);
		mainSash=sf1.getSash();
		mainSash.SASH_WIDTH=0;
		upComp= compA[0];
		downComp=compA[1];
		separateLayout=new SeparateLayout(compA[0],GridData.HORIZONTAL_ALIGN_END);
		exit=new Button(separateLayout.getTopComp(),SWT.FLAT);
		exit.setText(MpStaticName.exit);
		
		CreateSashForm sf2=new CreateSashForm();
		Composite compB[]=sf2.create(separateLayout.getButtomComp(), leftRight, SWT.HORIZONTAL);
		upSash=sf2.getSash();
		upSash.SASH_WIDTH=0;
		leftComp= compB[0];
		rightComp=compB[1];
		
		group=new Group(compB[1],SWT.NONE);
		group.setText(groupTitle);
		SeparateLayout s2=new SeparateLayout(group);
		/******************按钮们********************/
		createButton(s2.getTopComp());
		groupBottom=s2.getButtomComp();
		//
		
		
		
	}
	
	
	public void createDialog(Composite parent,int[] leftRight,String groupTitle){
		//底部为空
		FillLayout fill=new FillLayout();
		parent.setLayout(fill);
		separateLayout=new SeparateLayout(parent,GridData.HORIZONTAL_ALIGN_END);
		exit=new Button(separateLayout.getTopComp(),SWT.FLAT);
		exit.setText(MpStaticName.exit);
		
		CreateSashForm sf2=new CreateSashForm();
		Composite compC[]=sf2.create(separateLayout.getButtomComp(), leftRight, SWT.HORIZONTAL);
		upSash=sf2.getSash();
		upSash.SASH_WIDTH=0;
		leftComp= compC[0];
		rightComp=compC[1];
		
		compC[1].setLayout(new FillLayout());
		group=new Group(compC[1],SWT.NONE);
		group.setText(groupTitle);
		SeparateLayout sl1=new SeparateLayout(group);
		/*****************按钮们**********************/
		createButton(sl1.getTopComp());
		groupBottom=sl1.getButtomComp();
	}
	
	public void createDialog(Composite parent,int [] upDown,String groupTitle,int [] leftRight){
		//底部不为空
		FillLayout fill=new FillLayout();
		parent.setLayout(fill);
		
		CreateSashForm sf1=new CreateSashForm();
		Composite compA[]=sf1.create(parent, upDown, SWT.VERTICAL);
		mainSash=sf1.getSash();
		mainSash.SASH_WIDTH=0;
		upComp= compA[0];
		downComp=compA[1];
		separateLayout=new SeparateLayout(compA[0],GridData.HORIZONTAL_ALIGN_END);
		exit=new Button(separateLayout.getTopComp(),SWT.FLAT);
		exit.setText(MpStaticName.exit);
		
		CreateSashForm sf2=new CreateSashForm();
		Composite compB[]=sf2.create(separateLayout.getButtomComp(), leftRight, SWT.HORIZONTAL);
		upSash=sf2.getSash();
		upSash.SASH_WIDTH=0;
		leftComp= compB[0];
		rightComp=compB[1];
		
		group=new Group(compB[1],SWT.NONE);
		group.setText(groupTitle);
		group.setLayout(new FillLayout());
//		SeparateLayout s2=new SeparateLayout(group);
		/******************按钮们********************/
//		createButton(s2.getTopComp());
		groupBottom=group;
		//
		
		
		
	}
	
	public void createDialog(Composite parent,int [] upDown,String groupTitle,int [] leftRight,boolean flag){
		//底部不为空
		FillLayout fill=new FillLayout();
		parent.setLayout(fill);
		
		CreateSashForm sf1=new CreateSashForm();
		Composite compA[]=sf1.create(parent, upDown, SWT.VERTICAL);
		mainSash=sf1.getSash();
		mainSash.SASH_WIDTH=0;
		upComp= compA[0];
		downComp=compA[1];
		separateLayout=new SeparateLayout(compA[0],GridData.HORIZONTAL_ALIGN_END);
		confirm=new Button(separateLayout.getTopComp(),SWT.FLAT);
		confirm.setText(MpStaticName.confirm);
		
		exit=new Button(separateLayout.getTopComp(),SWT.FLAT);
		exit.setText(MpStaticName.cancel);
		
		CreateSashForm sf2=new CreateSashForm();
		Composite compB[]=sf2.create(separateLayout.getButtomComp(), leftRight, SWT.HORIZONTAL);
		upSash=sf2.getSash();
		upSash.SASH_WIDTH=0;
		leftComp= compB[0];
		rightComp=compB[1];
		
		group=new Group(compB[1],SWT.NONE);
		group.setText(groupTitle);
		group.setLayout(new FillLayout());
//		SeparateLayout s2=new SeparateLayout(group);
		/******************按钮们********************/
//		createButton(s2.getTopComp());
		groupBottom=group;
		//
		
		
		
	}
	
	public void createButton(Composite parent){
		parent.setLayout(null);
		parent.setLayout(new RowLayout());
		copy=new Button(parent,SWT.CHECK);
		copy.setText(MpStaticName.copy);
		continuing=new Button(parent,SWT.CHECK);
		continuing.setText(MpStaticName.continuing);
		add=new Button(parent,SWT.NONE);
		add.setText(MpStaticName.add);
		delete=new Button(parent,SWT.NONE);
		delete.setText(MpStaticName.delete);
		first=new Button(parent,SWT.NONE);
		first.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_013.PNG"));
		previous=new Button(parent,SWT.NONE);
		previous.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_012.PNG"));
		next=new Button(parent,SWT.NONE);
		next.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_037.PNG"));
		last=new Button(parent,SWT.NONE);
		last.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_038.PNG"));
		ok=new Button(parent,SWT.NONE);
		ok.setEnabled(false);
		ok.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_155.png"));
		cancel=new Button(parent,SWT.NONE);
		cancel.setEnabled(false);
		cancel.setImage(SWTResourceManager.getImage(CreateNewDialog.class,
		"/images/Developer_Icons_114.PNG"));
	}
	
	
	
	public Button getAdd() {
		return add;
	}
	public Button getCancel() {
		return cancel;
	}
	public Button getContinuing() {
		return continuing;
	}
	public Button getCopy() {
		return copy;
	}
	public Button getDelete() {
		return delete;
	}
	public Composite getDownComp() {
		return downComp;
	}
	public Button getExit() {
		return exit;
	}
	public Button getFirst() {
		return first;
	}
	public Group getGroup() {
		return group;
	}
	public Composite getGroupBottom() {
		return groupBottom;
	}
	public Button getLast() {
		return last;
	}
	public Composite getLeftComp() {
		return leftComp;
	}
	public Button getNext() {
		return next;
	}
	public Button getPrevious() {
		return previous;
	}
	public Button getOk() {
		return ok;
	}


	public Button getConfirm() {
		return confirm;
	}


	public void setConfirm(Button confirm) {
		this.confirm = confirm;
	}


	public SeparateLayout getSeparateLayout() {
		return separateLayout;
	}

   
}
