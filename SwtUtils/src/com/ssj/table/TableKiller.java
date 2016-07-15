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
 * 创建Table的工具类
 * 
 * @author ssj234
 * 
 */
public class TableKiller {

	private int PER = 20;// 每页显示的数量

	private int height = 10;// 行高

	private TableKillerViewer tv;// tv对象 管理数据输入

	private IGridPagination iGridPage;// 分页接口

	private List  inputAllList;// 选中的List ,输入大List要求分页时的List

	private int widthCount;//列的数量
	
	private SashForm sash;//grid的base容器中存在的sash
	
	private int index;//grid的sash的层数

	private Table table;// 创建的grid 表格 相当于table

	private boolean multi = false,// 是否多选 默认为false 单选
			pagination = false,// 是否分页 默认为false 不分页
            doubleClickChangeSash=false,//双击是否充满sashForm(如果有的话)
            export=true;//是否显示导出菜单
	private boolean  inputList = false;// 标志位

	private String formatType = "yyyy-MM-dd HH:mm:ss";// 时间转换格式

	private String trueName = "男", falseName = "女";// 定义true和false时的显示

	public Class cla;// 表格中的实体类的类

	private int pageCount,// 总页数
			pageIndex = 0// 第几页
			, size;// 总条数

	protected String[] attName;// 表头显示的

	private IAction[] actions;// 加入的菜单数组


	/*------------NOT SO IMPORTENT------------*/
	private CLabel cmdFirst, cmdPrevious, cmdNext, cmdLast, gridInfo;// 五个Clabel

	private GridData gridData;// 布局格式

	private Composite buttomComp;// 底部容器

	private CCombo cbJump;// 下拉框
	
	private final static Color selectedColor=SWTResourceManager.getColor(30 ,144 ,255);//选择时背景颜色
	private final static Color selectedTextColor=SWTResourceManager.getColor(SWT.COLOR_WHITE);//选择时文字颜色
	private final static Font textFont=SWTResourceManager.getFont("宋体", 9, SWT.NORMAL);

	/**
	 * 创建Grid的主体方法，外部不能使用，只能使用数据库创建
	 * @param comp  父容器
	 * @param columnsName  列名数组
	 * @param widths   列宽
	 */
	public void create(Composite comp, String[] columnsName, int widths[],int tableStyle) {
		//XXX*--------*******************************《不分页》********************************------------*/

		if (!pagination) {
			FillLayout fill=new FillLayout();
			fill.marginHeight=8;
			fill.marginWidth=8;
			comp.setLayout(fill);
			if (multi) {/*--------多选------------*/

				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			} else {/*--------单选------------*/
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
			table.setHeaderVisible(true);// 表头可见
			
			table.setLinesVisible(true);// 线不可见
			widthCount = columnsName.length;//列数
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				
//加入排序器
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// 取反
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
			//加入标签器和内容器
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));
		/*--------*******************************《不分页》********************************------------*/
		} else {
		//XXX--------*******************************《分页》********************************------------*/
			GridLayout gl = new GridLayout();
			gl.horizontalSpacing = 0;
			gl.verticalSpacing = 0;
			gl.marginWidth = 8;
			gl.marginHeight = 8;
			comp.setLayout(gl);

			Composite topComp = new Composite(comp, SWT.NONE);// buttom容器
			topComp.setLayoutData(new GridData(GridData.FILL_BOTH));// buttom充满
			topComp.setLayout(new FillLayout());

			buttomComp = new Composite(comp, SWT.NONE);// top容器
			RowLayout row = new RowLayout();
			row.marginTop = 5;
			buttomComp.setLayout(row);// top容器设置为row布局
			buttomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));// top容器水平充满
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
					if (pageCount == 1) {// 只有一页
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
					if (pageIndex == 0) {// 第一页
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
					if (pageIndex == pageCount - 1) {// 最后一页
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
			label.setText("第");

			cbJump = new CCombo(buttomComp, SWT.READ_ONLY);
			gridData.widthHint = 14;
			cbJump.setLayoutData(gridData);
			cbJump.setText("1");
			cbJump.setSize(23, 23);

			Label label_1 = new Label(buttomComp, SWT.NONE);
			label_1.setText("页");

			if (multi) {/*--------多选------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			} else {/*--------单选------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION|tableStyle);
			}
			
			table = tv.getTable();
			table.setHeaderVisible(true);// 表头可见
			
			table.setLinesVisible(true);// 线不可见
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
			widthCount = columnsName.length;//列数
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER|SWT.FLAT);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				col.setSort(SWT.UP);
				//加入监听器
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// 取反
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
			//加入内容器和标签器
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));

		}
		/*--------*******************************《分页》********************************------------*/
		
		tv.setPagination(pagination);//设置为分页 便于输入数据
		tv.setKiller(this);
		openMenu();//打开菜单
		if(doubleClickChangeSash){
			addSashLintener(table);	
		}
		/*--------*******************************高********************************------------*/
		if(height!=0){
			table.addListener(SWT.MeasureItem, new Listener() {
		        public void handleEvent(Event event) {
		           event.height = event.gc.getFontMetrics().getHeight() + height;
		        }
		     });
			}
	}
	
	
	public void create(Composite comp, String[] columnsName, int widths[]) {
		//XXX*--------*******************************《不分页》********************************------------*/

		if (!pagination) {
			FillLayout fill=new FillLayout();
			fill.marginHeight=8;
			fill.marginWidth=8;
			comp.setLayout(fill);
			if (multi) {/*--------多选------------*/

				tv = new TableKillerViewer(comp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION);
			} else {/*--------单选------------*/
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
			table.setHeaderVisible(true);// 表头可见
			
			table.setLinesVisible(true);// 线不可见
			widthCount = columnsName.length;//列数
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				
//加入排序器
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// 取反
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
			//加入标签器和内容器
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));
		/*--------*******************************《不分页》********************************------------*/
		} else {
		//XXX--------*******************************《分页》********************************------------*/
			GridLayout gl = new GridLayout();
			gl.horizontalSpacing = 0;
			gl.verticalSpacing = 0;
			gl.marginWidth = 8;
			gl.marginHeight = 8;
			comp.setLayout(gl);

			Composite topComp = new Composite(comp, SWT.NONE);// buttom容器
			topComp.setLayoutData(new GridData(GridData.FILL_BOTH));// buttom充满
			topComp.setLayout(new FillLayout());

			buttomComp = new Composite(comp, SWT.NONE);// top容器
			RowLayout row = new RowLayout();
			row.marginTop = 5;
			buttomComp.setLayout(row);// top容器设置为row布局
			buttomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));// top容器水平充满
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
					if (pageCount == 1) {// 只有一页
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
					if (pageIndex == 0) {// 第一页
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
					if (pageIndex == pageCount - 1) {// 最后一页
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
			label.setText("第");

			cbJump = new CCombo(buttomComp, SWT.READ_ONLY);
			gridData.widthHint = 14;
			cbJump.setLayoutData(gridData);
			cbJump.setText("1");
			cbJump.setSize(23, 23);

			Label label_1 = new Label(buttomComp, SWT.NONE);
			label_1.setText("页");

			if (multi) {/*--------多选------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL|SWT.FULL_SELECTION);
			} else {/*--------单选------------*/
				tv = new TableKillerViewer(topComp, SWT.BORDER | SWT.V_SCROLL
						| SWT.H_SCROLL|SWT.FULL_SELECTION);
			}
			
			table = tv.getTable();
			table.setHeaderVisible(true);// 表头可见
			
			table.setLinesVisible(true);// 线不可见
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
			widthCount = columnsName.length;//列数
			for (int i = 0; i < widthCount; i++) {
				final int k = i + 1;
				final TableColumn col = new TableColumn(table, SWT.CENTER|SWT.FLAT);
				col.setText(columnsName[i]);
				col.setWidth(widths[i]);
				col.setMoveable(true);
				
//				col.setSort(SWT.UP);
				//加入监听器
				col.addSelectionListener(new SelectionAdapter() {
					boolean sortType = true;

					public void widgetSelected(SelectionEvent arg0) {
						sortType = !sortType;// 取反
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
			//加入内容器和标签器
			tv.setContentProvider(new GridKillerContentProvider());
			tv.setLabelProvider(new GridKillerLabelProvider(cla, attName,
					formatType, trueName, falseName));

		}
		/*--------*******************************《分页》********************************------------*/
		
		tv.setPagination(pagination);//设置为分页 便于输入数据
		tv.setKiller(this);
		openMenu();//打开菜单
		if(doubleClickChangeSash){
			addSashLintener(table);	
		}
		/*--------*******************************高********************************------------*/
		if(height!=0){
			table.addListener(SWT.MeasureItem, new Listener() {
		        public void handleEvent(Event event) {
		           event.height = event.gc.getFontMetrics().getHeight() + height;
		        }
		     });
			}
	}
	/**
	 * 向下三层寻找grid的base容器中的sash
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
 * 打开菜单
 *
 */
	public void openMenu() {
		/*
		 * 以下为一个ActionGroup对象
		 */
		TableKillerActionGroup action = new TableKillerActionGroup(tv);
		if (actions != null && actions.length > 0) {
			action.setActions(actions);
		}
		
		action.setExport(export);//设置是否导出★设置之后，可以还原★
		
		
		// 调用fillContentMenu方法将按钮注入到菜单对象中
		action.fillContextMenu(new MenuManager());
	}
/**
 * 设置数据显示类型
 * @param cla   要显示的实体类的Class
 * @param attName  列对应的实体类的属性数组
 */
	public void setInputType(Class cla, String[] attName) {
		this.cla = cla;
		this.attName = attName;
	}
/**
 * 得到GridKillerViewer
 * @return  GridKillerViewer
 */
	public TableKillerViewer getTableViewer() {
		return this.tv;
	}
/**
 * 得到该Grid 相当于table
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
 * 点击按钮
 * @param i
 */
	private void setButtonChange(int i) {
		if (i == 1) {// 第一页

			pageIndex = 0;
			setInfo();
			//
			setEnable(1);
			setEnable(2);
			if (pageCount == 1) {// 只有一页
				setEnable(3);
				setEnable(4);
			} else {
				setAble(3);
				setAble(4);
			}

		} else if (i == 2) {// 上一页

			pageIndex--;
			setInfo();
			//
			setAble(3);
			setAble(4);
			if (pageIndex == 0) {// 第一页
				setEnable(1);
				setEnable(2);
			} else {
				setAble(1);
				setAble(2);
			}

		} else if (i == 3) {// 下一页

			pageIndex++;
			setInfo();
			//
			setAble(1);
			setAble(2);
			if (pageIndex == pageCount - 1) {// 最后一页
				setEnable(3);
				setEnable(4);
			} else {
				setAble(3);
				setAble(4);
			}

		} else if (i == 4) {// 最后一页

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
		gridInfo.setText("第" + (pageIndex + 1) + "页,共" + pageCount + "页,每页"
				+ PER + "条,共" + size + "条");
		

	}
/**
 * 设置是否分页
 * @param multi 是否可多选
 */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
/**
 * 设置是否分页
 * @param pagination 是否可分页
 */
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
/**
 * 设置行高
 * @param height 行高
 */
	public void setHeight(int height) {
		this.height = height;
	}
/**
 * 设置样式
 * @param multi 多选
 * @param pagination 分页
 */
	public void setStyle(boolean multi, boolean pagination) {
		this.pagination = pagination;
		this.multi = multi;
	}
/**
 * 设置右键菜单
 * @param acs 右键的菜单数组IAction[]
 */
	public void setMenus(IAction acs[]) {
		this.actions = acs;
	}

	/**
	 * 设置分页接口
	 * @param gridPage
	 */

	public void setIGridPage(IGridPagination gridPage) {
		iGridPage = gridPage;
	}
/**
 * 分页（接口）时使用
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
	 * 当输入一个大的List 需要分页时，viewer调用此方法
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
 * 分页(大List)时使用
 * @param pageIndex
 */
	private void setListContent(int pageIndex) {
		

		if (pageIndex == 0) {// 第一页
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
 * 分页(接口)时 使用
 * @param pageIndex
 */
	protected void setContent(int pageIndex) {
		

		if (pageIndex == 0) {// 第一页
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
	 * 设置是否双击改变SashForm 
	 * @param doubleClickChangeSash  默认为true
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
