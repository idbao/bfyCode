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
 * 表格的输入
 * 
 * @author Administrator
 * 
 */
public class GridKillerViewer extends GridTableViewer implements ITableKillerViewer {

	protected GridKiller killer;

	protected int index;
    
	private List<Combo> cbsOfThisGv=new ArrayList<Combo>();
	private List<String> prsOfThisGv=new ArrayList<String>();

	protected boolean pagination = false;// 分页是否

	protected GridKillerViewer thisGridKillerViewer;

	public GridKillerViewer(Composite parent, int style) {
		super(parent, style);
	}

	public void setInput(List list) {
		
		super.setInput(list);
		
		refreshCombo();
	}
  
/**
 * 设置输入，并选中第一个
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
			throw new RuntimeException("只有设置为分页时，此方法有效");
		}
	}

	// 分页
	public void setInput(IGridPagination iGridPage) {
		if (pagination) {
			killer.setIGridPage(iGridPage);
			killer.setGridInput();
		} else {
			throw new RuntimeException(
					"当表格设置为不分页样式时,使用了setInput(IGridPagination iGridPage)方法，应当使用setInput(List list)方法");
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
 * 增加一个对象 并且更新模型值
 * @param obj
 */
	public void addWithInput(Object obj){
		add(obj);
		Collection rs=(Collection) getInput();
		rs.add(obj);
	}
	/**
	 * 删除一个对象 并且更新模型值
	 * @param obj
	 */
	public void delWithInput(Object obj){
		remove(obj);
		Collection rs=(Collection) getInput();
		rs.remove(obj);
	}
	/**
	 * 返回当前时第几页(分页时使用)
	 * 
	 */
	public int getPageIndex() {
		return killer.getPageIndex();
	}

	/**
	 * 给定页数 显示内容（接口分页时使用）
	 * 
	 * @param pageIndex
	 * @param real
	 *            true时 查询数据库设置输入 false 不查询数据库设置输入，只设置info和按钮
	 */
	public void setContent(int pageIndex, boolean real) {
		killer.setPageIndex(pageIndex);
		killer.setContentByPageIndex(pageIndex, real);

	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	/** *************************下拉框与表格进行关联(待测试)************************************* */
	/**
	 * 对于单一的对话框,注意:对此表格setInput时不要使用setInput(Object obj);
	 *  对于分页表格无效
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
				//加入过滤器
				int selectIndex=combo.getSelectionIndex();
				
					//移除过滤器
					if(filter!=null)
					{
						removeFilter(filter);
					}
				
			if(selectIndex!=0)
				{
					String selectItem=combo.getItem(selectIndex);
				   //加入过滤器
					filter=new GridViewerFilter(killer.cla,selectItem,property);
					addFilter(filter);
				}
			//选中第一个
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
	 * 对于多个下拉框
	 * 对于分页表格无效
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
 * 设置下拉框的取值,可以通过从表格中查找(需注意设置显示列时，必须显示下拉框对应的列)，也可以使用反射得到。此处使用前者。
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
		
		
		//找到
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
	//下拉框进行刷新
	public void refreshCombo(){
		int i=0;
		//重置下拉框的值
		for(Combo combo:cbsOfThisGv)
		{
			combo.removeAll();
			getComboItems(combo,prsOfThisGv.get(i));
			i++;
		}
		 //移除所有过滤器
		ViewerFilter fiters[]= getFilters();
		for(int j=0;j<fiters.length;j++)
		{
			removeFilter(fiters[j]);
		}
		
	}
	/** *************************下拉框与表格进行关联(待测试)************************************* */
	/**
	 * 得到get方法
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
	 * 对话框的输入
	 * 
	 * @param inputGridKillerViewer
	 *            【被输入表格】的数据会放到这个表格中去，并且，如果【被输入表格】已被选中某个对象
	 *            则这个表格将也选中这个对象，执行监听事件,否则，选中第一项
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
 * 选中rs的第一个
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
	 * 加入搜索,过滤和打印的功能*/
	
	

	public GridKiller getKiller() {
	    return killer;
	}
	
	/**
	 * 设置查找和过滤控件
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
		//搜索按钮
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
			      //到达尾部 返回
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
		
		//使用过滤器 若contentText改变 则移除过滤器  过滤功能
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
						//移除过滤器
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
						//移除过滤器
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
						//加入过滤器
						gridFilter=new GridViewerFilter(killer.cla,frontTextCtent,killer.attName[index]);
						addFilter(gridFilter);
					}
					else
					{
						//移除过滤器
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
 * 过滤器(用于过滤)
 * @author Administrator
 *
 */
class GridViewerFilter extends ViewerFilter{

	private Class clazz;//类名
	private String text;//要查询的
	private String property;//属性名
	
	private Method midmethod;
	private String objContent;//反射得到的某个属性的内容
	public GridViewerFilter(Class clas,String text,String property){
		this.clazz=clas;
		this.text=text;
		this.property=property;
		
	}
	
	public boolean select(Viewer viewer, Object list, Object obj) {
		Class cla=clazz;
		try{
		Object o = cla.newInstance();// 新建一个pack类的实例
		o = obj;
		if (property.indexOf(".") < 0) {//未找到.
			String newName = getUpp(property);
			
			try {
				midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
			} catch (NoSuchMethodException e) {// 捕获异常，方法改为is
				newName = getUpper(property);// 属性对应的方法名称
				midmethod = cla.getDeclaredMethod(newName, null);// 获得方法
			}
			o = midmethod.invoke(o, new String[] {});
			objContent=o.toString();
		}
		else// 找到
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
					newxx = getUpper(name);// 属性对应的方法名称
					midmethod = cla.getDeclaredMethod(newxx, null);// 获得方法
				}
				cla = midmethod.getReturnType();
				o = midmethod.invoke(o, new String[] {});
			}
			objContent=o.toString();
		}
		}catch (Exception e) {
			System.out.println("搜索时出现问题");
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
