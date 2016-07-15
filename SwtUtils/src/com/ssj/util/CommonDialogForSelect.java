package com.ssj.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.ssj.grid.GridKillerViewer;



/**
 * 创建一个选择对话框的基类
 * @author ssj234
 *
 */
public abstract class CommonDialogForSelect extends CommonDialog {

	private Object object;

	private GridKillerViewer gvLeft, gvRight, input;

	private List<Object> addRs = new ArrayList<Object>(), delRs = new

	ArrayList<Object>();

	public CommonDialogForSelect(Shell arg0, Object

	obj, GridKillerViewer input) {
		super(arg0);
		this.input = input;
		this.object = obj;
		super.setStyle(getTitle(), 80, 80);
	}



	@Override
	public void createBottomComp(Composite parent) {
		CommonDialogComp cdc = new CommonDialogComp();
		cdc.create(parent, new int[] { 4, 1, 5 }, getLeftGroupName

		(), getRightGroupName(), true);

		gvLeft = getLeftGridViewer(cdc.getButtom());

		gvRight = getRightGridViewer(cdc.getGroup2());

		// 设置输入
		gvLeft.setInput(getLeftViewerInput(object));

		gvRight.setInput(input.getInput());

		// XXX
		addCellEditor(gvRight);

		// 监听事件

		cdc.getAdd().addSelectionListener(new SelectionAdapter

		() {

			public void widgetSelected(SelectionEvent arg0)

			{
				List list = gvLeft.getSelectionList();
				if (list.size() > 0) {
					gvLeft.removeSelectedItems();
					for (int i = 0; i < list.size(); i++) {
						Object selectObj = list.get(i);

						Object addObject = getAddObject

						(selectObj,object);

						gvLeft.delWithInput(selectObj);
						gvRight.addWithInput

						(addObject);
						addRs.add(addObject);
					}
				} else {
					NotifierDialog.openCenter

					(MpStaticName.deleteInfo,

					NotificationType.ERROR);
				}
				gvLeft.getGrid().deselectAll();
			}
		});

		cdc.getDelete().addSelectionListener(new

		SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0)

			{
				// 删除词条记录
				List list = gvRight.getSelectionList();
				if (list.size() > 0) {
					gvRight.removeSelectedItems();
					for (int i = 0; i < list.size(); i++) {
						Object selectedObj =

						list.get(i);
						Object

						addObject = getDelObject(selectedObj);

						gvRight.delWithInput

						(selectedObj);
						gvLeft.addWithInput

						(addObject);
						// 若addRs中 没有 则 加入delRs中

						if (!addRs.remove

						(selectedObj)) {
							delRs.add

							(selectedObj);
						}
					}

				} else {
					NotifierDialog.openCenter

					(MpStaticName.deleteInfo,

					NotificationType.ERROR);
				}
				gvRight.getGrid().deselectAll();
			}
		});

	}

	@Override
	public void createLeftComp(Composite parent) {
	}

	@Override
	public void createRightComp(Composite parent) {

		List list = new ArrayList();
		list.add(object);

		GridKillerViewer gv = getTopGridViewer(parent);
		gv.setInput(list);

	}

	/**
	 * 为表格增加编辑
	 * @param viewer
	 */
	public void addCellEditor(GridKillerViewer viewer) {

	}

	/**
	 * 左边Group的名字
	 * @return
	 */
	public abstract String getLeftGroupName();
	/**
	 * 对话框的标题
	 * @return
	 */
	public abstract String getTitle();
	/**
	 * 上方group的名字
	 * @return
	 */
	public abstract String getTopGroupName();

	/**
	 * 得到--->之后的对象
	 * @param selectedObj  选中的左边表格对象
	 * @param topObject  上方表格的对象
	 * @return  右边表格对象的实例
	 */
	public abstract Object getAddObject(Object selectedObj,Object topObject);

	/**
	 * 得到<---之后的对象  一般左边表格是右边表格的属性
	 * @param selectedObj  右边表格的对象 
	 * @return  左边表格对象的实例
	 */
	public abstract Object getDelObject(Object selectedObj);

	/**
	 * 右方Group的名称
	 * @return
	 */
	public abstract String getRightGroupName();

	/**
	 * 返回左边的表格GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getLeftGridViewer(Composite

	parent);
	/**
	 * 返回上边的表格GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getTopGridViewer(Composite parent);
	/**
	 * 返回右边的表格GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getRightGridViewer(Composite

	parent);

	/**
	 * 返回左边的表格GridKillerViewer的input
	 * @param parent
	 * @return
	 */
	public abstract Collection getLeftViewerInput(Object obj);
	
	/**
	 * 返回dispose时的输入
	 * @param topObject  上方表格的对象
	 * @return
	 */
	public abstract Collection getDisposeResult(Object topObject);

	public abstract ICommonService  getService();
	@Override
	public void setCompStyle(CreateNewDialog cnd, Composite parent)

	{
		cnd.createDialog(parent, new int[] { 1, 9 },

		getTopGroupName(), new int[] { 0, 1 }, true);
	}

	@Override
	public void setDispose() {
		input.setInput(getDisposeResult(object));
	}



	public void confirmForSave() {

		for (Object selItem : addRs) {

			getService().save

			(selItem);
		}
		for (Object selItem : delRs) {
			getService().delete

			(selItem);
		}
	}

	@Override
	public Object fillObject(Object obj) {
		return null;
	}

	@Override
	public void fillTableOrList(Object obj) {
	}

	@Override
	public void fillText(Object obj) {
	}
}
