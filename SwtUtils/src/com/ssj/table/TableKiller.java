/*
 *@(#)TableKiller.java  2009-8-20
 *
 *Copyright 2009 Ssj234,All rights reserved.
 */
package com.ssj.table;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.ssj.grid.GridKiller;
import com.ssj.grid.GridKillerContentProvider;
import com.ssj.grid.GridKillerLabelProvider;
import com.ssj.grid.GridKillerViewerSorter;
import com.ssj.grid.IGridPagination;
import com.ssj.util.SWTResourceManager;



/**
 * ����Table�Ĺ�����
 * 
 * @author ssj234
 * 
 */
public class TableKiller {

	private int PER = 20;// ÿҳ��ʾ������

	private int height = 10;// �и�

	private TableKillerViewer tv;// tv���� ������������

	private IGridPagination iGridPage;// ��ҳ�ӿ�

	private List  inputAllList;// ѡ�е�List ,�����ListҪ���ҳʱ��List

	private int widthCount;//�е�����
	
	private SashForm sash;//grid��base�����д��ڵ�sash
	
	private int index;//grid��sash�Ĳ���

	private Table table;// ������grid ��� �൱��table

	private boolean multi = false,// �Ƿ��ѡ Ĭ��Ϊfalse ��ѡ
			pagination = false,// �Ƿ��ҳ Ĭ��Ϊfalse ����ҳ
            doubleClickChangeSash=false,//˫���Ƿ����sashForm(����еĻ�)
            export=true;//�Ƿ���ʾ�����˵�
	private boolean  inputList = false;// ��־λ

	private String formatType = "yyyy-MM-dd HH:mm:ss";// ʱ��ת����ʽ

	private String trueName = "��", falseName = "Ů";// ����true��falseʱ����ʾ

	public Class cla;// ����е�ʵ�������

	private int pageCount,// ��ҳ��
			pageIndex = 0// �ڼ�ҳ
			, size;// ������

	protected String[] attName;// ��ͷ��ʾ��

	private IAction[] actions;// ����Ĳ˵�����


	/*------------NOT SO IMPORTENT------------*/
	private CLabel cmdFirst, cmdPrevious, cmdNext, cmdLast, gridInfo;// ���Clabel

	private GridData gridData;// ���ָ�ʽ

	private Composite buttomComp;// �ײ�����

	private CCombo cbJump;// ������
	
	private final static Color selectedColor=SWTResourceManager.getColor(30 ,144 ,255);//ѡ��ʱ������ɫ
	private final static Color selectedTextColor=SWTResourceManager.getColor(SWT.COLOR_WHITE);//ѡ��ʱ������ɫ
	private final static Font textFont=SWTResourceManager.getFont("����", 9, SWT.NORMAL);

	/**
	 * ����Grid�����巽�����ⲿ����ʹ�ã�ֻ��ʹ�����ݿⴴ��
	 * @param comp  ������
	 * @param columnsName  ��������
	 * @param widths   �п�
	 */
	public void create(Composite comp, String[] columnsName, int widths[],int tableStyle) {
		//XXX*--------*******************************������ҳ��********************************------------*/

		if (!pagination) {
			FillLayout fill=new FillLayout();
			fill.marginHeight=8;
			fill.marginWidth=8;
			comp.setLayout(fill);
			if (multi) {/*--------��ѡ------------*/

				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			} else {/*--------��ѡ------------*/
				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			}
			table = tv.getTable();
			table.addListener(SWT.EraseItem, new Listener(){

				public void handleEvent(Event event) {
					event.detail &= ~SWT.HOT;
					if((event.detail & SWT.SELECTED)==0) return;

					Table table =(Table)event.widget;
//					TableItem item = (TableItem) event.item;
					int clientWidth = table.getClientArea().width;
					
					GC gc = event.gc;
					//Color oldForeground = gc.getForeground();
					//Color oldBackGround = gc.getBackground();
					
					gc.setForeground(selectedTextColor);
					gc.setBackground(selectedColor);
					gc.fillRectangle(0, event.y, clientWidth, event.height);
					
					//gc.setForeground(oldForeground);
					//gc.setBackground(oldBackGround);
					event.detail &= ~ SWT.SELECTED;
					
				}
				
			});
			table.setHeaderVisible(true);// ��ͷ�ɼ�
			
			table.setLinesVisible(true);// �߲��ɼ�
			widthCount = columnsName.length;//����
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				
//����������
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// ȡ��
						if (sortType) {
//							col.setSort(SWT.UP);
						} else {
//							col.setSort(SWT.DOWN);
						}

						tv.setSorter(sortType ? new GridKillerViewerSorter(k,
								cla, attName) : new GridKillerViewerSorter(-k,
								cla, attName));
						
					

					}
				});

			}
			//�����ǩ����������
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));
		/*--------*******************************������ҳ��********************************------------*/
		} else {
		//XXX--------*******************************����ҳ��********************************------------*/
			GridLayout gl = new GridLayout();
			gl.horizontalSpacing = 0;
			gl.verticalSpacing = 0;
			gl.marginWidth = 8;
			gl.marginHeight = 8;
			comp.setLayout(gl);

			Composite topComp = new Composite(comp, SWT.NONE);// buttom����
			topComp.setLayoutData(new GridData(GridData.FILL_BOTH));// buttom����
			topComp.setLayout(new FillLayout());

			buttomComp = new Composite(comp, SWT.NONE);// top����
			RowLayout row = new RowLayout();
			row.marginTop = 5;
			buttomComp.setLayout(row);// top��������Ϊrow����
			buttomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));// top����ˮƽ����
			// buttomComp.setLayout(new GridLayout(7,false));

			GridLayout gridLayout = new GridLayout();
			gridLayout.marginWidth = 2;
			gridLayout.marginHeight = 0;
			gridLayout.numColumns = 12;
			buttomComp.setLayout(gridLayout);

			gridInfo = new CLabel(buttomComp, SWT.LEFT | SWT.BORDER);
			gridInfo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


			Label label_4 = new Label(buttomComp, SWT.SEPARATOR);
			gridData = new GridData();
			gridData.heightHint = 14;
			label_4.setLayoutData(gridData);

			cmdFirst = new CLabel(buttomComp, SWT.NONE);
			cmdFirst.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/begin.gif"));
			cmdFirst.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex = 0;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					
					setEnable(1);
					setEnable(2);
					if (pageCount == 1) {// ֻ��һҳ
						setEnable(3);
						setEnable(4);
					} else {
						setAble(3);
						setAble(4);
					}

				}
			});

			cmdPrevious = new CLabel(buttomComp, SWT.NONE);
			cmdPrevious.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/back.gif"));
			cmdPrevious.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex--;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setAble(3);
					setAble(4);
					if (pageIndex == 0) {// ��һҳ
						setEnable(1);
						setEnable(2);
					} else {
						setAble(1);
						setAble(2);
					}
				}
			});

			cmdNext = new CLabel(buttomComp, SWT.NONE);
			cmdNext.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/forward.gif"));
			cmdNext.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex++;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setAble(1);
					setAble(2);
					if (pageIndex == pageCount - 1) {// ���һҳ
						setEnable(3);
						setEnable(4);
					} else {
						setAble(3);
						setAble(4);
					}
				}
			});

			cmdLast = new CLabel(buttomComp, SWT.NONE);
			cmdLast.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/end.gif"));
			cmdLast.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex = pageCount - 1;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setEnable(3);
					setEnable(4);

					setAble(1);
					setAble(2);

				}
			});
			setEnable(1);
			setEnable(2);
			

			Label label_5 = new Label(buttomComp, SWT.SEPARATOR);
			gridData = new GridData();
			gridData.heightHint = 14;
			GridData gridData_1 = new GridData();
			gridData_1.heightHint = 14;
			label_5.setLayoutData(gridData_1);

			Label label = new Label(buttomComp, SWT.NONE);
			label.setText("��");

			cbJump = new CCombo(buttomComp, SWT.READ_ONLY);
			gridData.widthHint = 14;
			cbJump.setLayoutData(gridData);
			cbJump.setText("1");
			cbJump.setSize(23, 23);

			Label label_1 = new Label(buttomComp, SWT.NONE);
			label_1.setText("ҳ");

			if (multi) {/*--------��ѡ------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			} else {/*--------��ѡ------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			}
			
			table = tv.getTable();
			table.setHeaderVisible(true);// ��ͷ�ɼ�
			
			table.setLinesVisible(true);// �߲��ɼ�
			table.addListener(SWT.EraseItem, new Listener(){

				public void handleEvent(Event event) {
					event.detail &= ~SWT.HOT;
					if((event.detail & SWT.SELECTED)==0) return;

					Table table =(Table)event.widget;
					//TableItem item = (TableItem) event.item;
					int clientWidth = table.getClientArea().width;
					
					GC gc = event.gc;
					//Color oldForeground = gc.getForeground();
					//Color oldBackGround = gc.getBackground();
					
					gc.setForeground(selectedTextColor);
					gc.setBackground(selectedColor);
					gc.fillRectangle(0, event.y, clientWidth, event.height);
					
					//gc.setForeground(oldForeground);
					//gc.setBackground(oldBackGround);
					event.detail &= ~ SWT.SELECTED;
					
				}
				
			});
			
//			grid.setBackgroundImage(SWTResourceManager.getImage(
//					GridKiller.class, "/images/junlee.jpg"));
			widthCount = columnsName.length;//����
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER|SWT.FLAT);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				col.setSort(SWT.UP);
				//���������
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// ȡ��
						if (sortType) {
//							col.setSort(SWT.UP);
						} else {
//							col.setSort(SWT.DOWN);
						}

						tv.setSorter(sortType ? new GridKillerViewerSorter(k,
								cla, attName) : new GridKillerViewerSorter(-k,
								cla, attName));

					
					

					}
				});

			}
			//�����������ͱ�ǩ��
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));

		}
		/*--------*******************************����ҳ��********************************------------*/
		
		tv.setPagination(pagination);//����Ϊ��ҳ ������������
		tv.setKiller(this);
		openMenu();//�򿪲˵�
		if(doubleClickChangeSash){
			addSashLintener(table);	
		}
		/*--------*******************************��********************************------------*/
		if(height!=0){
			table.addListener(SWT.MeasureItem, new Listener() {
		        public void handleEvent(Event event) {
		           event.height = event.gc.getFontMetrics().getHeight() + height;
		        }
		     });
			}
	}
	
	
	public void create(Composite comp, String[] columnsName, int widths[]) {
		//XXX*--------*******************************������ҳ��********************************------------*/

		if (!pagination) {
			FillLayout fill=new FillLayout();
			fill.marginHeight=8;
			fill.marginWidth=8;
			comp.setLayout(fill);
			if (multi) {/*--------��ѡ------------*/

				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION);
			} else {/*--------��ѡ------------*/
				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION);
			}
			table = tv.getTable();
			table.addListener(SWT.EraseItem, new Listener(){

				public void handleEvent(Event event) {
					event.detail &= ~SWT.HOT;
					if((event.detail & SWT.SELECTED)==0) return;

					Table table =(Table)event.widget;
//					TableItem item = (TableItem) event.item;
					int clientWidth = table.getClientArea().width;
					
					GC gc = event.gc;
					//Color oldForeground = gc.getForeground();
					//Color oldBackGround = gc.getBackground();
					
					gc.setForeground(selectedTextColor);
					gc.setBackground(selectedColor);
					gc.fillRectangle(0, event.y, clientWidth, event.height);
					
					//gc.setForeground(oldForeground);
					//gc.setBackground(oldBackGround);
					event.detail &= ~ SWT.SELECTED;
					
				}
				
			});
			table.setHeaderVisible(true);// ��ͷ�ɼ�
			
			table.setLinesVisible(true);// �߲��ɼ�
			widthCount = columnsName.length;//����
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				
//����������
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// ȡ��
						if (sortType) {
//							col.setSort(SWT.UP);
						} else {
//							col.setSort(SWT.DOWN);
						}

						tv.setSorter(sortType ? new GridKillerViewerSorter(k,
								cla, attName) : new GridKillerViewerSorter(-k,
								cla, attName));
						
					

					}
				});

			}
			//�����ǩ����������
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));
		/*--------*******************************������ҳ��********************************------------*/
		} else {
		//XXX--------*******************************����ҳ��********************************------------*/
			GridLayout gl = new GridLayout();
			gl.horizontalSpacing = 0;
			gl.verticalSpacing = 0;
			gl.marginWidth = 8;
			gl.marginHeight = 8;
			comp.setLayout(gl);

			Composite topComp = new Composite(comp, SWT.NONE);// buttom����
			topComp.setLayoutData(new GridData(GridData.FILL_BOTH));// buttom����
			topComp.setLayout(new FillLayout());

			buttomComp = new Composite(comp, SWT.NONE);// top����
			RowLayout row = new RowLayout();
			row.marginTop = 5;
			buttomComp.setLayout(row);// top��������Ϊrow����
			buttomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));// top����ˮƽ����
			// buttomComp.setLayout(new GridLayout(7,false));

			GridLayout gridLayout = new GridLayout();
			gridLayout.marginWidth = 2;
			gridLayout.marginHeight = 0;
			gridLayout.numColumns = 12;
			buttomComp.setLayout(gridLayout);

			gridInfo = new CLabel(buttomComp, SWT.LEFT | SWT.BORDER);
			gridInfo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


			Label label_4 = new Label(buttomComp, SWT.SEPARATOR);
			gridData = new GridData();
			gridData.heightHint = 14;
			label_4.setLayoutData(gridData);

			cmdFirst = new CLabel(buttomComp, SWT.NONE);
			cmdFirst.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/begin.gif"));
			cmdFirst.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex = 0;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					
					setEnable(1);
					setEnable(2);
					if (pageCount == 1) {// ֻ��һҳ
						setEnable(3);
						setEnable(4);
					} else {
						setAble(3);
						setAble(4);
					}

				}
			});

			cmdPrevious = new CLabel(buttomComp, SWT.NONE);
			cmdPrevious.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/back.gif"));
			cmdPrevious.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex--;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setAble(3);
					setAble(4);
					if (pageIndex == 0) {// ��һҳ
						setEnable(1);
						setEnable(2);
					} else {
						setAble(1);
						setAble(2);
					}
				}
			});

			cmdNext = new CLabel(buttomComp, SWT.NONE);
			cmdNext.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/forward.gif"));
			cmdNext.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex++;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setAble(1);
					setAble(2);
					if (pageIndex == pageCount - 1) {// ���һҳ
						setEnable(3);
						setEnable(4);
					} else {
						setAble(3);
						setAble(4);
					}
				}
			});

			cmdLast = new CLabel(buttomComp, SWT.NONE);
			cmdLast.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/end.gif"));
			cmdLast.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					pageIndex = pageCount - 1;
					if (inputList) {
						setListContent(pageIndex);
					} else {
						setContent(pageIndex);
					}
					//
					setEnable(3);
					setEnable(4);

					setAble(1);
					setAble(2);

				}
			});
			setEnable(1);
			setEnable(2);
			

			Label label_5 = new Label(buttomComp, SWT.SEPARATOR);
			gridData = new GridData();
			gridData.heightHint = 14;
			GridData gridData_1 = new GridData();
			gridData_1.heightHint = 14;
			label_5.setLayoutData(gridData_1);

			Label label = new Label(buttomComp, SWT.NONE);
			label.setText("��");

			cbJump = new CCombo(buttomComp, SWT.READ_ONLY);
			gridData.widthHint = 14;
			cbJump.setLayoutData(gridData);
			cbJump.setText("1");
			cbJump.setSize(23, 23);

			Label label_1 = new Label(buttomComp, SWT.NONE);
			label_1.setText("ҳ");

			if (multi) {/*--------��ѡ------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION);
			} else {/*--------��ѡ------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION);
			}
			
			table = tv.getTable();
			table.setHeaderVisible(true);// ��ͷ�ɼ�
			
			table.setLinesVisible(true);// �߲��ɼ�
			table.addListener(SWT.EraseItem, new Listener(){

				public void handleEvent(Event event) {
					event.detail &= ~SWT.HOT;
					if((event.detail & SWT.SELECTED)==0) return;

					Table table =(Table)event.widget;
					//TableItem item = (TableItem) event.item;
					int clientWidth = table.getClientArea().width;
					
					GC gc = event.gc;
					//Color oldForeground = gc.getForeground();
					//Color oldBackGround = gc.getBackground();
					
					gc.setForeground(selectedTextColor);
					gc.setBackground(selectedColor);
					gc.fillRectangle(0, event.y, clientWidth, event.height);
					
					//gc.setForeground(oldForeground);
					//gc.setBackground(oldBackGround);
					event.detail &= ~ SWT.SELECTED;
					
				}
				
			});
			
//			grid.setBackgroundImage(SWTResourceManager.getImage(
//					GridKiller.class, "/images/junlee.jpg"));
			widthCount = columnsName.length;//����
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER|SWT.FLAT);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				col.setSort(SWT.UP);
				//���������
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// ȡ��
						if (sortType) {
//							col.setSort(SWT.UP);
						} else {
//							col.setSort(SWT.DOWN);
						}

						tv.setSorter(sortType ? new GridKillerViewerSorter(k,
								cla, attName) : new GridKillerViewerSorter(-k,
								cla, attName));

					
					

					}
				});

			}
			//�����������ͱ�ǩ��
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));

		}
		/*--------*******************************����ҳ��********************************------------*/
		
		tv.setPagination(pagination);//����Ϊ��ҳ ������������
		tv.setKiller(this);
		openMenu();//�򿪲˵�
		if(doubleClickChangeSash){
			addSashLintener(table);	
		}
		/*--------*******************************��********************************------------*/
		if(height!=0){
			table.addListener(SWT.MeasureItem, new Listener() {
		        public void handleEvent(Event event) {
		           event.height = event.gc.getFontMetrics().getHeight() + height;
		        }
		     });
			}
	}
	/**
	 * ��������Ѱ��grid��base�����е�sash
	 * @param table
	 */
	public void addSashLintener(final Table grid){
		
		Composite comp=grid.getParent();
		  sash=null;
		for(int i=0;i<3;i++){
			comp=comp.getParent();
			if(comp==null){
				break;
			}else{
				if (comp instanceof SashForm) {
					index=i;
					sash = (SashForm) comp;
					break;
				}
			}
		}
		if(sash!=null){
			final int old[]=sash.getWeights();
		grid.addMouseListener(new MouseAdapter(){

			boolean flag=false;
			public void mouseDoubleClick(MouseEvent arg0) {
				if(!flag){
					Composite comp=table;
					for(int j=0;j<index+1;j++){
						comp=comp.getParent();
					}
					sash.setMaximizedControl(comp);
					flag=true;
				}else{
					sash.setMaximizedControl(null);
					sash.setWeights(old);
					flag=false;
				}
				
			}


	});
		}
	}


/**
 * �򿪲˵�
 *
 */
	public void openMenu() {
		/*
		 * ����Ϊһ��ActionGroup����
		 */
		TableKillerActionGroup action = new TableKillerActionGroup(tv);
		if (actions != null && actions.length > 0) {
			action.setActions(actions);
		}
		
		action.setExport(export);//�����Ƿ񵼳�������֮�󣬿��Ի�ԭ��
		
		
		// ����fillContentMenu��������ťע�뵽�˵�������
		action.fillContextMenu(new MenuManager());
	}
/**
 * ����������ʾ����
 * @param cla   Ҫ��ʾ��ʵ�����Class
 * @param attName  �ж�Ӧ��ʵ�������������
 */
	public void setInputType(Class cla, String[] attName) {
		this.cla = cla;
		this.attName = attName;
	}
/**
 * �õ�GridKillerViewer
 * @return  GridKillerViewer
 */
	public TableKillerViewer getTableViewer() {
		return this.tv;
	}
/**
 * �õ���Grid �൱��table
 * @return Grid
 */
	public Table getTable() {
		return this.table;
	}

	private void setEnable(int i) {// cmdFirst,cmdPrevious,cmdNext,cmdLast
		if (i == 1) {
			cmdFirst.setEnabled(false);
			cmdFirst.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/begin_dis.gif"));
		} else if (i == 2) {
			cmdPrevious.setEnabled(false);
			cmdPrevious.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/back_dis.gif"));
		} else if (i == 3) {
			cmdNext.setEnabled(false);
			cmdNext.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/forward_dis.gif"));
		} else if (i == 4) {
			cmdLast.setEnabled(false);
			cmdLast.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/end_dis.gif"));
		}

	}

	private void setAble(int i) {// cmdFirst,cmdPrevious,cmdNext,cmdLast
		if (i == 1) {
			cmdFirst.setEnabled(true);
			cmdFirst.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/begin.gif"));
		} else if (i == 2) {
			cmdPrevious.setEnabled(true);
			cmdPrevious.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/back.gif"));
		} else if (i == 3) {
			cmdNext.setEnabled(true);
			cmdNext.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/forward.gif"));
		} else if (i == 4) {
			cmdLast.setEnabled(true);
			cmdLast.setImage(SWTResourceManager.getImage(GridKiller.class,
					"/images/end.gif"));
		}

	}
/**
 * �����ť
 * @param i
 */
	private void setButtonChange(int i) {
		if (i == 1) {// ��һҳ

			pageIndex = 0;
			setInfo();
			//
			setEnable(1);
			setEnable(2);
			if (pageCount == 1) {// ֻ��һҳ
				setEnable(3);
				setEnable(4);
			} else {
				setAble(3);
				setAble(4);
			}

		} else if (i == 2) {// ��һҳ

			pageIndex--;
			setInfo();
			//
			setAble(3);
			setAble(4);
			if (pageIndex == 0) {// ��һҳ
				setEnable(1);
				setEnable(2);
			} else {
				setAble(1);
				setAble(2);
			}

		} else if (i == 3) {// ��һҳ

			pageIndex++;
			setInfo();
			//
			setAble(1);
			setAble(2);
			if (pageIndex == pageCount - 1) {// ���һҳ
				setEnable(3);
				setEnable(4);
			} else {
				setAble(3);
				setAble(4);
			}

		} else if (i == 4) {// ���һҳ

			pageIndex = pageCount - 1;
			setInfo();
			//
			setEnable(3);
			setEnable(4);

			setAble(1);
			setAble(2);

		}
	}

	
	private void setInfo() {
		gridInfo.setText("��" + (pageIndex + 1) + "ҳ,��" + pageCount + "ҳ,ÿҳ"
				+ PER + "��,��" + size + "��");
		

	}
/**
 * �����Ƿ��ҳ
 * @param multi �Ƿ�ɶ�ѡ
 */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
/**
 * �����Ƿ��ҳ
 * @param pagination �Ƿ�ɷ�ҳ
 */
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
/**
 * �����и�
 * @param height �и�
 */
	public void setHeight(int height) {
		this.height = height;
	}
/**
 * ������ʽ
 * @param multi ��ѡ
 * @param pagination ��ҳ
 */
	public void setStyle(boolean multi, boolean pagination) {
		this.pagination = pagination;
		this.multi = multi;
	}
/**
 * �����Ҽ��˵�
 * @param acs �Ҽ��Ĳ˵�����IAction[]
 */
	public void setMenus(IAction acs[]) {
		this.actions = acs;
	}

	/**
	 * ���÷�ҳ�ӿ�
	 * @param gridPage
	 */

	public void setIGridPage(IGridPagination gridPage) {
		iGridPage = gridPage;
	}
/**
 * ��ҳ���ӿڣ�ʱʹ��
 *
 */
	protected void setGridInput() {
		size = iGridPage.getCount();
		if (size % PER == 0) {
			pageCount = size / PER;
		} else {
			pageCount = size / PER + 1;
		}
		setContent(0);
		if (pageCount == 1) {
			setEnable(3);
			setEnable(4);
		}

		String[] its = new String[pageCount];
		for (int ss = 1; ss <= pageCount; ss++) {
			its[ss - 1] = String.valueOf(ss);
		}
		cbJump.setItems(its);
		cbJump.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pageIndex = cbJump.getSelectionIndex();
				setContent(pageIndex);
				if (pageIndex == 0) {
					setButtonChange(1);
				} else if (pageIndex == pageCount - 1) {
					setButtonChange(4);
				} else {
					setAble(1);
					setAble(2);
					setAble(3);
					setAble(4);
				}
			}
		});
	}

	/**
	 * ������һ�����List ��Ҫ��ҳʱ��viewer���ô˷���
	 * 
	 * @param list
	 */
	protected void setGridInput(List list) {
		inputList = true;
		inputAllList = list;
		size = list.size();
		if(size==0)
			return;
		if (size % PER == 0) {
			pageCount = size / PER;
		} else {
			pageCount = size / PER + 1;
		}
		setListContent(0);

		if (pageCount == 1) {
			setEnable(3);
			setEnable(4);
		}else{
			setAble(3);
			setAble(4);
		}
		String[] its = new String[pageCount];
		for (int ss = 1; ss <= pageCount; ss++) {
			its[ss - 1] = String.valueOf(ss);
		}
		cbJump.setItems(its);
		cbJump.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pageIndex = cbJump.getSelectionIndex();
				setListContent(pageIndex);
				if (pageIndex == 0) {
					setButtonChange(1);
				} else if (pageIndex == pageCount - 1) {
					setButtonChange(4);
				} else {
					setAble(1);
					setAble(2);
					setAble(3);
					setAble(4);
				}
			}
		});
	}
/**
 * ��ҳ(��List)ʱʹ��
 * @param pageIndex
 */
	private void setListContent(int pageIndex) {
		

		if (pageIndex == 0) {// ��һҳ
			if (size < PER) {
				tv.setInput(inputAllList.subList(pageIndex, size));
			} else {
				tv.setInput(inputAllList.subList(pageIndex, PER));
			}
		} else {
			if (pageIndex + 1 < pageCount) {

				tv.setInput(inputAllList.subList((pageIndex) * PER,
						(pageIndex + 1) * PER));
			} else {
				tv.setInput(inputAllList.subList((pageIndex) * PER, size));
			}
		}
		setInfo();
		
		
	}
/**
 * ��ҳ(�ӿ�)ʱ ʹ��
 * @param pageIndex
 */
	protected void setContent(int pageIndex) {
		

		if (pageIndex == 0) {// ��һҳ
			if (size < PER) {
				tv.setInput(iGridPage.getResult(pageIndex, size));
			} else {
				tv.setInput(iGridPage.getResult(pageIndex, PER));
			}
		} else {
			if (pageIndex + 1 < pageCount) {
				tv.setInput(iGridPage.getResult((pageIndex) * PER, PER));
			} else {
				tv.setInput(iGridPage.getResult((pageIndex) * PER, size
						- (pageIndex * PER)));
			}
		}
		
		setInfo();
		
		
	}

	protected void setContentByPageIndex(int index,boolean flag){
		if(flag){
			size=iGridPage.getCount();
		setContent(index);
		}else{
			setInfo();
		}
		if(index==0&&pageCount!=1){
			setEnable(1);
			setEnable(2);
			setAble(3);
			setAble(4);
		}else if(index==0&&pageCount==1){
			setEnable(1);
			setEnable(2);
			setEnable(3);
			setEnable(4);
			
		}else if(index>0&&index<pageCount-1){
			setAble(1);
			setAble(2);
			setAble(3);
			setAble(4);
		}else{
			setEnable(3);
			setEnable(4);
			setAble(1);
			setAble(2);
		}
	}
	public void setPerCount(int count){
		this.PER=count;
	}

	/**
	 * �����Ƿ�˫���ı�SashForm 
	 * @param doubleClickChangeSash  Ĭ��Ϊtrue
	 */
	public void setDoubleClickChangeSash(boolean doubleClickChangeSash) {
		this.doubleClickChangeSash = doubleClickChangeSash;
	}
	public void setExport(boolean export) {
		this.export = export;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Class getCla() {
		return cla;
	}

}
