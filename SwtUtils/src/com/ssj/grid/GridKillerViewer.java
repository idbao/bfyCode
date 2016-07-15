package com.ssj.grid;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import notifier.NotificationType;
import notifier.NotifierDialog;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;

import com.ssj.util.ITableKillerViewer;

/**
 * ��������
 * 
 * @author Administrator
 * 
 */
public class GridKillerViewer extends GridTableViewer implements ITableKillerViewer {

	protected GridKiller killer;

	protected int index;
    
	private List<Combo> cbsOfThisGv=new ArrayList<Combo>();
	private List<String> prsOfThisGv=new ArrayList<String>();

	protected boolean pagination = false;// ��ҳ�Ƿ�

	protected GridKillerViewer thisGridKillerViewer;

	public GridKillerViewer(Composite parent, int style) {
		super(parent, style);
	}

	public void setInput(List list) {
		
		super.setInput(list);
		
		refreshCombo();
	}
  
/**
 * �������룬��ѡ�е�һ��
 * @param list
 * @param flag
 */
	public void setInput(List list, boolean flag) {
		if (true) {
			super.setInput(list);
			selectFirst(list);
		}
		refreshCombo();
	}

	public void setList(List list) {
		if (pagination) {
			killer.setGridInput(list);
		} else {
			throw new RuntimeException("ֻ������Ϊ��ҳʱ���˷�����Ч");
		}
	}

	// ��ҳ
	public void setInput(IGridPagination iGridPage) {
		if (pagination) {
			killer.setIGridPage(iGridPage);
			killer.setGridInput();
		} else {
			throw new RuntimeException(
					"���������Ϊ����ҳ��ʽʱ,ʹ����setInput(IGridPagination iGridPage)������Ӧ��ʹ��setInput(List list)����");
		}
	}

	public ISelection getSelection() {
		return super.getSelection();
	}

	public Object getSelectionObj() {
		IStructuredSelection s = (IStructuredSelection) getSelection();
		if(s.isEmpty()){
			return null;
		}else{
		Object obj = s.getFirstElement();
		return obj;
		}
	}

	public List getSelectionList() {
		IStructuredSelection s = (IStructuredSelection) getSelection();
		List rs = s.toList();
		return rs;
	}

	public void setMulti(boolean pagination) {
		this.pagination = pagination;
	}

	public void setKiller(GridKiller killer) {
		this.killer = killer;
	}

	public void removeSelectedItems() {

		GridItem items[] = getGrid().getSelection();
		for (GridItem item : items) {
			item.dispose();
		}

	}
/**
 * ����һ������ ���Ҹ���ģ��ֵ
 * @param obj
 */
	public void addWithInput(Object obj){
		add(obj);
		Collection rs=(Collection) getInput();
		rs.add(obj);
	}
	/**
	 * ɾ��һ������ ���Ҹ���ģ��ֵ
	 * @param obj
	 */
	public void delWithInput(Object obj){
		remove(obj);
		Collection rs=(Collection) getInput();
		rs.remove(obj);
	}
	/**
	 * ���ص�ǰʱ�ڼ�ҳ(��ҳʱʹ��)
	 * 
	 */
	public int getPageIndex() {
		return killer.getPageIndex();
	}

	/**
	 * ����ҳ�� ��ʾ���ݣ��ӿڷ�ҳʱʹ�ã�
	 * 
	 * @param pageIndex
	 * @param real
	 *            trueʱ ��ѯ���ݿ��������� false ����ѯ���ݿ��������룬ֻ����info�Ͱ�ť
	 */
	public void setContent(int pageIndex, boolean real) {
		killer.setPageIndex(pageIndex);
		killer.setContentByPageIndex(pageIndex, real);

	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	/** *************************������������й���(������)************************************* */
	/**
	 * ���ڵ�һ�ĶԻ���,ע��:�Դ˱��setInputʱ��Ҫʹ��setInput(Object obj);
	 *  ���ڷ�ҳ�����Ч
	 * @param combo
	 * @param property
	 * @param collection
	 */
	public void addCombo(final Combo combo, final String property) {
		cbsOfThisGv.add(combo);
		prsOfThisGv.add(property);
		getComboItems(combo,property);
		combo.setVisibleItemCount(10);
		combo.addSelectionListener(new SelectionAdapter(){
			GridViewerFilter filter;
			public void widgetSelected(SelectionEvent arg0) {
				//���������
				int selectIndex=combo.getSelectionIndex();
				
					//�Ƴ�������
					if(filter!=null)
					{
						removeFilter(filter);
					}
				
			if(selectIndex!=0)
				{
					String selectItem=combo.getItem(selectIndex);
				   //���������
					filter=new GridViewerFilter(killer.cla,selectItem,property);
					addFilter(filter);
				}
			//ѡ�е�һ��
			if(getGrid().getItemCount()>0)
			{
			getGrid().select(0);
			ISelection select=getSelection();
			getGrid().deselectAll();
			setSelection(select);
			getGrid().showSelection();
			}
			}
		});
		
		
	}

	/**
	 * ���ڶ��������
	 * ���ڷ�ҳ�����Ч
	 * @param cbs
	 * @param propertys
	 * @param collection
	 */
	public void addCombos(final Combo cbs[], final String propertys[]) {

	  for(int i=0;i<cbs.length;i++)
	  {
		  addCombo(cbs[i],propertys[i]);
	  }
	  
	  
	
	
	}
/**
 * �����������ȡֵ,����ͨ���ӱ���в���(��ע��������ʾ��ʱ��������ʾ�������Ӧ����)��Ҳ����ʹ�÷���õ����˴�ʹ��ǰ�ߡ�
 */
	public void getComboItems(Combo combo,String property)
	{
		
		Set<String> rs=new HashSet<String>();
		String attName[]=killer.attName;
		int len=attName.length;
		int index=-1;
		
		for(int i=0;i<len;i++)
		{
			if(attName[i]!=null&&attName[i].equals(property))
			{
				index=i;
				break;
			}
		}
		
		
		//�ҵ�
		if(index>=0)
		{
			GridItem items[]=getGrid().getItems();
			for(int i=0;i<items.length;i++)
			{
				String content=items[i].getText(index);
				rs.add(content);
			}
			
			combo.add("<"+getGrid().getColumn(index).getText()+">");
		}
		
		
		int i=1;
		for(String s:rs)
		{
			combo.add(s, i);
			i++;
		}
		combo.select(0);
		
	}
	//���������ˢ��
	public void refreshCombo(){
		int i=0;
		//�����������ֵ
		for(Combo combo:cbsOfThisGv)
		{
			combo.removeAll();
			getComboItems(combo,prsOfThisGv.get(i));
			i++;
		}
		 //�Ƴ����й�����
		ViewerFilter fiters[]= getFilters();
		for(int j=0;j<fiters.length;j++)
		{
			removeFilter(fiters[j]);
		}
		
	}
	/** *************************������������й���(������)************************************* */
	/**
	 * �õ�get����
	 * 
	 * @param element
	 * @return
	 */
	protected String getUpp(String element) {
		String first = element.substring(0, 1);

		String upper = first.toUpperCase();

		String newName = "get" + upper + element.substring(1);
		return newName;
	}

	/**
	 * �Ի��������
	 * 
	 * @param inputGridKillerViewer
	 *            ���������񡿵����ݻ�ŵ���������ȥ�����ң���������������ѱ�ѡ��ĳ������
	 *            ��������Ҳѡ���������ִ�м����¼�,����ѡ�е�һ��
	 */
	public void setInput(GridKillerViewer inputGridKillerViewer) {
		Collection collection = (Collection) inputGridKillerViewer.getInput();
		this.setInput(collection);
		ISelection selection = inputGridKillerViewer.getSelection();
		if (!selection.isEmpty()) {
			setSelection(selection);
		} else {
			getGrid().setSelection(0);
			setSelection(getSelection());
		}
		getGrid().showSelection();
		ShowSelectionUtil.showSelection(this);
		refreshCombo();
	}
/**
 * ѡ��rs�ĵ�һ��
 * @param rs
 */
	public void selectFirst(List rs){
		if (rs != null && rs.size() > 0) 
		{
			getGrid().select(0);
			ISelection select=getSelection();
			getGrid().deselectAll();
			setSelection(select);
			getGrid().showSelection();
			ShowSelectionUtil.showSelection(this);
		} else 
		{
			Object nu = null;
			setInput(nu);
			refreshCombo();
		}
	}
	
	/**
	 * ��������,���˺ʹ�ӡ�Ĺ���*/
	
	

	public GridKiller getKiller() {
	    return killer;
	}
	
	/**
	 * ���ò��Һ͹��˿ؼ�
	 * @param cb
	 * @param text
	 * @param search
	 * @param filter
	 */
	public void setSearchControl(final Combo cb,final Text text,Widget search,final Widget filter){
		cb.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				text.forceFocus();
			}
		});
		GridColumn cols[]=getGrid().getColumns();
		int len=cols.length;
		final String shows[]=new String[len];
		for(int i=0;i<len;i++)
		{
			shows[i]=cols[i].getText();
		}
		
		cb.setItems(shows);
		
		if(getGrid().isDisposed()){
			return;
		}
		//������ť
		search.addListener(SWT.Selection, new Listener(){
			String frontTextCtent="";
			int searchIndex=0;
			public void handleEvent(Event arg0) {
				
				String textContent=text.getText().trim();
				String comboContent=cb.getText();
				
				if(!textContent.equals(frontTextCtent))
				{
					frontTextCtent=textContent;
					searchIndex=0;
				}
				
				if(comboContent.equals("")||textContent.equals(""))
				{
					return;
				}
				int index=-1;
				
				for(int i=0;i<shows.length;i++)
				{
					if(shows[i].equals(comboContent))
					{
						index=i;
					}
				}
				
				
				if(index>=0)
				{
			      GridItem items[]=getGrid().getItems();
			      for(int i=searchIndex;i<items.length;i++)
			      {
			    	  String content=items[i].getText(index);
			    	 
			    	  if(content.indexOf(textContent)>=0)
			    	  {
			    		  searchIndex=i+1;
			    		  getGrid().select(i);
					      getGrid().showSelection();
					      setSelection(getSelection());
			    		  return;
			    	  }
			    	 
			      }
			      //����β�� ����
			      searchIndex=0;
			      for(int i=searchIndex;i<items.length;i++)
			      {
			    	  String content=items[i].getText(index);
			    	 
			    	  if(content.indexOf(textContent)>=0)
			    	  {
			    		  searchIndex=i+1;
			    		  getGrid().select(i);
					      getGrid().showSelection();
					      setSelection(getSelection());
			    		  return;
			    	  }
			    	 
			      }
				
				}
			}
		
			
		});
		
		//ʹ�ù����� ��contentText�ı� ���Ƴ�������  ���˹���
		filter.addListener(SWT.Selection, new Listener(){
			String frontTextCtent="",frontComboContent="";
			GridViewerFilter gridFilter;
			boolean isSelected=false;
			public void handleEvent(Event arg0) {
					isSelected=!isSelected;
					String textContent=text.getText().trim();
					String comboContent=cb.getText();
					
					if(textContent.equals("")||!isSelected)
					{
						//�Ƴ�������
						if(gridFilter!=null)
						{
						removeFilter(gridFilter);
						return;
						}
						return;
					}
					
					if(!textContent.equals(frontTextCtent)||!comboContent.equals(frontComboContent))
					{
						frontTextCtent=textContent;
						frontComboContent=comboContent;
						//�Ƴ�������
						if(gridFilter!=null)
						{
						removeFilter(gridFilter);
						}
					}
					
					int index=-1;
					
					for(int i=0;i<shows.length;i++)
					{
						if(shows[i].equals(comboContent))
						{
							index=i;
						}
					}
					
					
					if(index>=0)
					{
						//���������
						gridFilter=new GridViewerFilter(killer.cla,frontTextCtent,killer.attName[index]);
						addFilter(gridFilter);
					}
					else
					{
						//�Ƴ�������
						if(gridFilter!=null)
						{
						removeFilter(gridFilter);
						}
					}
				}
		});
	}
	
	
}
/**
 * ������(���ڹ���)
 * @author Administrator
 *
 */
class GridViewerFilter extends ViewerFilter{

	private Class clazz;//����
	private String text;//Ҫ��ѯ��
	private String property;//������
	
	private Method midmethod;
	private String objContent;//����õ���ĳ�����Ե�����
	public GridViewerFilter(Class clas,String text,String property){
		this.clazz=clas;
		this.text=text;
		this.property=property;
		
	}
	
	public boolean select(Viewer viewer, Object list, Object obj) {
		Class cla=clazz;
		try{
		Object o = cla.newInstance();// �½�һ��pack���ʵ��
		o = obj;
		if (property.indexOf(".") < 0) {//δ�ҵ�.
			String newName = getUpp(property);
			
			try {
				midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
			} catch (NoSuchMethodException e) {// �����쳣��������Ϊis
				newName = getUpper(property);// ���Զ�Ӧ�ķ�������
				midmethod = cla.getDeclaredMethod(newName, null);// ��÷���
			}
			o = midmethod.invoke(o, new String[] {});
			objContent=o.toString();
		}
		else// �ҵ�
		{
			String[] eles = property.split("\\.");
			int count = eles.length, k = 0;
			for (int j = 0; j < count; j++) {
				String name = eles[j];
				String newxx = getUpp(name);
				k++;
				try {
					midmethod = cla.getDeclaredMethod(newxx, null);
				} catch (NoSuchMethodException e) {
					newxx = getUpper(name);// ���Զ�Ӧ�ķ�������
					midmethod = cla.getDeclaredMethod(newxx, null);// ��÷���
				}
				cla = midmethod.getReturnType();
				o = midmethod.invoke(o, new String[] {});
			}
			objContent=o.toString();
		}
		}catch (Exception e) {
			System.out.println("����ʱ��������");
			e.printStackTrace();
			
		}
		
		if(objContent!=null&&objContent.indexOf(text)>=0)
		{
		return true;	
		}
		return false;
	}
	
	/*----------------------------------------------*/
	public String getUpp(String element) {
		String first = element.substring(0, 1);

		String upper = first.toUpperCase();

		String newName = "get" + upper + element.substring(1);
		return newName;
	}
	public String getUpper(String element) {
		String first = element.substring(0, 1);

		String upper = first.toUpperCase();

		String newName = "is" + upper + element.substring(1);
		return newName;
	}
}
