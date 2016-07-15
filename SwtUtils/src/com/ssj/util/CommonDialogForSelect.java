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
 * ����һ��ѡ��Ի���Ļ���
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

		// ��������
		gvLeft.setInput(getLeftViewerInput(object));

		gvRight.setInput(input.getInput());

		// XXX
		addCellEditor(gvRight);

		// �����¼�

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
				// ɾ��������¼
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
						// ��addRs�� û�� �� ����delRs��

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
	 * Ϊ������ӱ༭
	 * @param viewer
	 */
	public void addCellEditor(GridKillerViewer viewer) {

	}

	/**
	 * ���Group������
	 * @return
	 */
	public abstract String getLeftGroupName();
	/**
	 * �Ի���ı���
	 * @return
	 */
	public abstract String getTitle();
	/**
	 * �Ϸ�group������
	 * @return
	 */
	public abstract String getTopGroupName();

	/**
	 * �õ�--->֮��Ķ���
	 * @param selectedObj  ѡ�е���߱�����
	 * @param topObject  �Ϸ����Ķ���
	 * @return  �ұ߱������ʵ��
	 */
	public abstract Object getAddObject(Object selectedObj,Object topObject);

	/**
	 * �õ�<---֮��Ķ���  һ����߱�����ұ߱�������
	 * @param selectedObj  �ұ߱��Ķ��� 
	 * @return  ��߱������ʵ��
	 */
	public abstract Object getDelObject(Object selectedObj);

	/**
	 * �ҷ�Group������
	 * @return
	 */
	public abstract String getRightGroupName();

	/**
	 * ������ߵı��GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getLeftGridViewer(Composite

	parent);
	/**
	 * �����ϱߵı��GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getTopGridViewer(Composite parent);
	/**
	 * �����ұߵı��GridKillerViewer
	 * @param parent
	 * @return
	 */
	public abstract GridKillerViewer getRightGridViewer(Composite

	parent);

	/**
	 * ������ߵı��GridKillerViewer��input
	 * @param parent
	 * @return
	 */
	public abstract Collection getLeftViewerInput(Object obj);
	
	/**
	 * ����disposeʱ������
	 * @param topObject  �Ϸ����Ķ���
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
