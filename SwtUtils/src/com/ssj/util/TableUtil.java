package com.ssj.util;

import javax.swing.text.TableView;

import org.eclipse.swt.widgets.Composite;

import com.ssj.grid.GridKiller;
import com.ssj.grid.GridKillerViewer;
import com.ssj.table.TableKiller;
import com.ssj.table.TableKillerViewer;

public class TableUtil {

	public static final int GRID=1;
	public static final int TABLE=2;
	private ITableKillerViewer tableViewer;
	private TableKiller tableKiller;
	private GridKiller gridKiller;
	private Class clazz;
	private String columns[];
	private String propertys[];
	private int widths[];
	private boolean multi=false,pagination=false;
	
	public void create(Composite parent,int style){
		
		if(style==GRID)
		{
			gridKiller=new GridKiller();
			gridKiller.setInputType(clazz, propertys);
			gridKiller.setStyle(multi, pagination);
			gridKiller.create(parent, columns, widths);
			tableViewer=gridKiller.getGridViewer();
		}else{
			tableKiller=new TableKiller();
			tableKiller.setInputType(clazz, propertys);
			tableKiller.setStyle(multi, pagination);
			tableKiller.create(parent, columns, widths);
			tableViewer=tableKiller.getTableViewer();
		}
		
	}
	
public void create(Composite parent,int style,int tableStyle){
		
		if(style==GRID)
		{
			gridKiller=new GridKiller();
			gridKiller.setInputType(clazz, propertys);
			gridKiller.setStyle(multi, pagination);
			gridKiller.create(parent, columns, widths,tableStyle);
			tableViewer=gridKiller.getGridViewer();
		}else{
			tableKiller=new TableKiller();
			tableKiller.setInputType(clazz, propertys);
			tableKiller.setStyle(multi, pagination);
			tableKiller.create(parent, columns, widths,tableStyle);
			tableViewer=tableKiller.getTableViewer();
		}
		
	}

	public void setElement(Class clazz,String columns[],String propertys[],int widths[]){
		this.clazz=clazz;
		this.columns=columns;
		this.propertys=propertys;
		this.widths=widths;
	}

	public ITableKillerViewer getTableViewer() {
		return tableViewer;
	}

	public void setStyle(boolean multi, boolean pagination) {
		this.pagination = pagination;
		this.multi = multi;
	}
	
}
