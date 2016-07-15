package com.ssj.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ssj.grid.GridKillerViewer;



/**
 * �༭�Ի���Ļ���
 * @author ssj234
 *
 */
public abstract class CommonDialog extends TitleAreaDialog {
	private String topTitle;

	private int Width, Height;

	private CreateNewDialog cnd;
	private GridKillerViewer tv;
	private  Button first=null,forward=null,next=null,last=null,copy=null,
	continuing=null,add=null,delete=null,ok=null,cancel=null;

    private ICommonService service;
	
	public CommonDialog(Shell arg0) {
		super(arg0);
	}

	/**
	 * ���öԻ���ı��� ��С
	 * 
	 * @param topTitle
	 * @param bottomTitle
	 * @param width
	 * @param height
	 */
	public void setStyle(String topTitle, int width,
			int height) {
		this.topTitle = topTitle;
		
		this.Width = width;
		this.Height = height;
	}

	/**createContents
	 * ��дTitleAreaDialog��createDialogArea()����
	 */
	protected Control createDialogArea(Composite parent) {// ����
       setTitle(topTitle);
		GridLayout gl = new GridLayout();
		gl.marginWidth = 20;
		parent.setLayout(gl);

		parent.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE) {
					e.doit = false;
				}
			}
		});
		parent.addDisposeListener(new DisposeListener(){

			public void widgetDisposed(DisposeEvent arg0) {
				setDispose();
			}
		});
		
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		FillLayout fill = new FillLayout();
		fill.marginHeight = 20;
		fill.marginWidth = 20;
		topComp.setLayout(fill);
		cnd = new CreateNewDialog();
		setCompStyle(cnd, topComp);
		createRightComp(cnd.getGroupBottom());
		createLeftComp(cnd.getLeftComp());
		createBottomComp(cnd.getDownComp());
		if(!cnd.getExit().isDisposed()){
		cnd.getExit().addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				cancelForDrop();
				close();
			}
		});
		}
		
		if(cnd.getConfirm()!=null&&!cnd.getConfirm().isDisposed())
		{
			cnd.getConfirm().addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent arg0) {
					confirmForSave();
					close();
				}
			});
		}
		//������������룬ֻ�ɵ������
		
		return topComp;
	}
	
/**
 * ���ô����Ĳ���
 * @param cnd
 * @param parent
 */
	public abstract void setCompStyle(CreateNewDialog cnd, Composite parent);
/**
 * ���������������
 * @param topComp
 */
	public  abstract void createLeftComp(Composite parent);
	/**
	 * �����ұ���������
	 * @param topComp
	 */
	public abstract void createRightComp(Composite parent) ;
	/**
	 * �����ײ��������ݣ�û��ʱ�ÿ�
	 * @param topComp
	 */
	public abstract void createBottomComp(Composite parent) ;
/**
 * �Ի���Dispose��ʱ����������
 *
 */
	public abstract void setDispose();
	
	/**
	 * ȷ��ʱ��������
	 */
	public void  confirmForSave(){
		
	}
	public abstract ICommonService getService();
	/**
	 * ��������������
	 *
	 */
	public void cancelForDrop(){
		
	}
	
	/**
	 * �ý����е�ֵ����������obj
	 * @param obj
	 */
	public abstract Object fillObject(Object obj);
	/**
	 * ʹ�ø�������Obj��������
	 * @param obj
	 */
	public abstract void fillText(Object obj);
	
	public abstract void fillTableOrList(Object obj);
	
	/**
	 * ���ð�ť
	 * @param grid
	 */
	public void setGridAndButton(final GridKillerViewer tv){
		service=getService();
		this.tv=tv;
		//��grid��������¼�
		final Grid grid=tv.getGrid();
		
		tv.addSelectionChangedListener(new ISelectionChangedListener(){

			public void selectionChanged(SelectionChangedEvent arg0) {
				gridListener();
			}
			
		});
		//grid�����������ϵ
		if(cnd!=null){
			 first=cnd.getFirst();
			 forward=cnd.getPrevious();
			 next=cnd.getNext();
		     last=cnd.getLast();
		     copy=cnd.getCopy();
		     continuing=cnd.getContinuing();
		     add=cnd.getAdd();
		     delete=cnd.getDelete();
		     ok=cnd.getOk();
		     cancel=cnd.getCancel();
     //XXX****************************����-����-ɾ��***************************************************/
		     //����
		     copy.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						if(!add.isEnabled()){//���״̬
							if(copy.getSelection()){//ѡ�и���
								fillText(tv.getSelectionObj());
							}else{//ȡ��ѡ����
								fillText(null);
							}
							
						}
					}
		     });
		     //����
		     add.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						add.setEnabled(false);//���Ӱ�ť������
						cancel.setEnabled(true);
						if(copy.getSelection()){//���Ʊ�ѡ��
							fillText(tv.getSelectionObj());
						}else{//����δ��ѡ��
							fillText(null);
						}
						fillTableOrList(null);
					}
		     });
             //ɾ��
		     delete.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						try{
						List list=tv.getSelectionList();//ѡ�е�List
						if(list.size()==0){//δѡ��
							MessageDialog.openError(null, MpStaticName.error, MpStaticName.deleteInfo);
						}else{//ɾ��
							int index=grid.getSelectionIndex();
							for(Iterator it=list.iterator();it.hasNext();){
								Object obj= it.next();
								obj=beforeDelete(obj);
								if(obj==null)
								{
									return;
								}
								service.delete(obj);
//								 TODO ����ģ��--------------
								List dele=(List) tv.getInput();
								dele.remove(obj);
								tv.remove(obj);//��tv���Ƴ�
								
								afterDelete(obj);
								
							}
							
							grid.setSelection(index);//����
							grid.showSelection();
							NotifierDialog.openCenter(MpStaticName.deleteOk, NotificationType.SUCCESS);//��ʾ
							
						}
						}catch (Exception e) {
							MessageDialog.openError(Display.getCurrent().getActiveShell(), MpStaticName.error, MpStaticName.deleteError);
							e.printStackTrace();
						}
					}
		     });
		     //ȷ����ť
		     ok.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						
						if(add.isEnabled()){//�޸�
							Object obj=tv.getSelectionObj();
							if(obj==null){
								MessageDialog.openError(null, MpStaticName.error, MpStaticName.updateError);
								ok.setEnabled(false);
								cancel.setEnabled(false);
								fillText(null);
								return;
							}
							obj=fillObject(obj);//���obj
							if(obj!=null){
						    beforeUpdate(obj);
							service.update(obj);//�޸�
							afterUpdate(obj);
							tv.update(obj, null);//������
							NotifierDialog.openCenter(MpStaticName.updateOk, NotificationType.SUCCESS);//��ʾ
							
							if(continuing.getSelection()){//������
								int index=grid.getSelectionIndex();
								int count = grid.getItemCount();
								if (index != count - 1) {
									grid.setSelection(index + 1);
									grid.showSelection();
									Object nextObj=tv.getSelectionObj();
				                    fillTableOrList(nextObj);
				                    fillText(nextObj);
								}
							}else{//��������
								fillTableOrList(obj);
								fillText(obj);
								cancel.setEnabled(false);//ȡ��������
							}
							}
						}else{//����
							Object obj=fillObject(null);
							if(obj==null)
								return;
							beforeSave(obj);
							service.save(obj);//���
							tv.add(obj);//������
							//����ģ��--------------
							List list=(List) tv.getInput();
							if(list==null){
								list=new ArrayList();
							}
							list.add(obj);
							afterSave(obj);
							int counter=grid.getItemCount();
							grid.setSelection(counter-1);
							grid.showSelection();
							NotifierDialog.openCenter(MpStaticName.saveOk, NotificationType.SUCCESS);//��ʾ
							if(continuing.getSelection()){//������
								if(copy.getSelection()){//����
									int index=grid.getSelectionIndex();
									int count = grid.getItemCount();
									if (index != count - 1) {
										grid.setSelection(index + 1);
										grid.showSelection();
										Object nextObj=tv.getSelectionObj();
					                    fillText(nextObj);
									}
								}else{//δ����
									fillText(null);
								}
								
							}else{//��������
								
								fillTableOrList(obj);
								fillText(obj);
								add.setEnabled(true);//���ӿ���
								cancel.setEnabled(false);//ȡ��������
							}
							
						}
						
						ok.setEnabled(false);//ȡ��������
					}
		     });
		     //ȡ��
		     cancel.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						add.setEnabled(true);
						Object obj=tv.getSelectionObj();
						fillTableOrList(obj);
						fillText(obj);
						cancel.setEnabled(false);//ȡ����ť���
						ok.setEnabled(false);//ȡ����ť���
					}
		     });
	//XXX*************************��һҳ ��һҳ�ĸ���ť*************************************/
			first.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					first.setEnabled(false);
					forward.setEnabled(false);
					next.setEnabled(true);
					last.setEnabled(true);
					grid.setSelection(0);
					grid.showSelection();
					fillText(tv.getSelectionObj());
					fillTableOrList(tv.getSelectionObj());
				}
			});
			
			forward.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					int index = grid.getSelectionIndex();
					int count =grid.getItemCount();
					if (index == 1) {
						first.setEnabled(false);
						forward.setEnabled(false);
						next.setEnabled(true);
						last.setEnabled(true);
						grid.setSelection(0);
						grid.showSelection();
					} else {
						if (index != count - 2) {
							next.setEnabled(true);
							last.setEnabled(true);
						}
						grid.setSelection(index - 1);
						grid.showSelection();
					}
					grid.showSelection();
					fillText(tv.getSelectionObj());
					fillTableOrList(tv.getSelectionObj());
				}
			});
			
			next.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					int index = grid.getSelectionIndex();
					int count = grid.getItemCount();
					if (index == count - 2) {
						first.setEnabled(true);
						forward.setEnabled(true);
						next.setEnabled(false);
						last.setEnabled(false);
						grid.setSelection(count - 1);
						grid.showSelection();

						
					} else {
						if (index != 1) {
							first.setEnabled(true);
							forward.setEnabled(true);
						}
						grid.setSelection(index + 1);
						grid.showSelection();
						
						
					}
					grid.showSelection();
					fillText(tv.getSelectionObj());
					fillTableOrList(tv.getSelectionObj());
				}
				

			});
			last.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					next.setEnabled(false);
					last.setEnabled(false);
					first.setEnabled(true);
					forward.setEnabled(true);
					int count = grid.getItemCount();
					grid.setSelection(count - 1);
					grid.showSelection();
					fillText(tv.getSelectionObj());
					fillTableOrList(tv.getSelectionObj());
				}
			});
			
			
		    
		}
	}
	/*************************************************************************/
	public Button getComfirm(){
		return cnd.getOk();
	}
	public Button getCancel(){
		return cnd.getCancel();
	}
	public void setTextListener(Composite parent){
		Control cs[]=parent.getChildren();
		List<Text> cts=new ArrayList<Text>();
		List<Combo> cbs=new ArrayList<Combo>();
		for(Control c:cs){
			
			if (c instanceof Text) {
				Text x = (Text) c;
				if(x.getLayoutData()==null){
					x.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				}
				cts.add(x);
			}else if(c instanceof Combo){
				Combo x = (Combo) c;
				if(x.getLayoutData()==null){
					x.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				}
				cbs.add(x);
			}
			}		
		int count=cts.size();
		int countx=cbs.size();
		
		Text []ct=new Text[count];
		Combo []cb=new Combo[countx];
		for(int i=0;i<count;i++){
			ct[i]=cts.get(i);
		}
		for(int i=0;i<countx;i++){
			cb[i]=cbs.get(i);
		}
		CTextListener.setListener(ct, getComfirm(), getCancel());
		CTextListener.setListener(cb, getComfirm(), getCancel());
	}
	/**
	 * ɾ����ʱ��Ҫ������
	 * @param obj
	 */
	public Object beforeDelete(Object obj){
		return obj;
	}
   public void afterDelete(Object obj){
		
	}
   /**
	 * �޸ĵ�ʱ��Ҫ������
	 * @param obj
	 */
   public void beforeUpdate(Object obj){
		
	}
  public void afterUpdate(Object obj){
		
	}
  /**
	 * ��ӵ�ʱ��Ҫ������
	 * @param obj
	 */
  public void beforeSave(Object obj){
		
	}
 public void afterSave(Object obj){
		
	}
	
/********************************������ʽ*****************************************/
	protected Point getInitialSize() {// �Ի����С
		Rectangle rtg = this.getShell().getMonitor().getClientArea();
		int width = rtg.width;
		int height = rtg.height;
		return new Point(width * Width / 100, height * Height / 100);
	}

	protected void configureShell(Shell newShell) {// ����

		super.configureShell(newShell);

		newShell.setText(topTitle);

	}

	protected Point getInitialLocation(Point arg0) {// λ��
		Rectangle rtg = getShell().getMonitor().getClientArea();
		int width = rtg.width;
		int height = rtg.height;

		int x = getInitialSize().x;
		int y = getInitialSize().y;

		Point p = new Point((width - x) / 2, (height - y) / 2);
		return p;
	}

	protected Button createButton(Composite comp, int id, String label,
			boolean defaultButton) {// ȡ��OK��cancel��ť

		return null;
	}
	public void gridListener(){
		Object obj=tv.getSelectionObj();
		if(add.isEnabled()){//�޸� ���text��table
		fillText(obj);
		fillTableOrList(obj);
		ok.setEnabled(false);
		cancel.setEnabled(false);
		}else{//���
			if(copy.getSelection()){//���Ʊ�ѡ��,���text
				fillText(obj);
			}else{//����δ��ѡ��
				fillText(null);
			}
			ok.setEnabled(false);
		}
		Grid grid=tv.getGrid();
		int count=grid.getItemCount();
		int index=grid.getSelectionIndex();
			if (index != 0) {
				first.setEnabled(true);
				forward.setEnabled(true);
			} else {
				first.setEnabled(false);
				forward.setEnabled(false);
			}
			if (index != count - 1) {
				next.setEnabled(true);
				last.setEnabled(true);
			} else {
				next.setEnabled(false);
				last.setEnabled(false);
			}
		
	
	}

	public boolean close() {

		return super.close();
	}

	public CreateNewDialog getCnd() {
		return cnd;
	}

	@Override
	protected int getShellStyle() {
		// TODO Auto-generated method stub
		return super.getShellStyle()|SWT.RESIZE;
	}




}
