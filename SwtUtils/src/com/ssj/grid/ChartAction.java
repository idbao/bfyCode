package com.ssj.grid;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import com.ssj.util.MpStaticName;



public class ChartAction extends Action {
	GridTableViewer tv;
	int chartType=0;
	String titleName,bottomName,unit=MpStaticName.count;
	public ChartAction(GridTableViewer tv,int chartType){
		setText(MpStaticName.chart);
		this.tv=tv;
		this.chartType=chartType;
	}

	
	public void run() {
	
		Collection list=(Collection) tv.getInput();
		if(list==null||list.size()==0){
			return;
		}
		DefaultCategoryDataset  dataset =new DefaultCategoryDataset();//建立数据集
		
		for(Iterator it=list.iterator();it.hasNext();){

//			dataset.addValue(spe.getAmount(),spe.getId().getMpMaintainItem().getMaintainItemName(),spe.getId().getMtSpeciality().getSpecialityName());
		}
		
		
		 JFreeChart chart=ChartFactory.createBarChart3D(titleName, bottomName, unit, dataset, PlotOrientation.VERTICAL, true, true, false);
		 
		 CategoryPlot plot = chart.getCategoryPlot();
		 plot.setForegroundAlpha(0.8f);
		 
		 BarRenderer3D renderer =   new BarRenderer3D();
		 renderer.setBaseOutlinePaint(Color.BLACK);
		 renderer.setItemMargin(0.3);
		 renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	        renderer.setItemLabelsVisible(true);
	        renderer.setBaseToolTipGenerator(ca);
	        plot.setRenderer(renderer);
	        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
	       
	        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
	        Shell shell =new Shell();
	        shell.setText(MpStaticName.chartShellTitle);
//	        shell.setAlpha(200);
	        shell.setLayout(new FillLayout());
	        
	       ChartComposite comp=new ChartComposite(shell,
	    		   SWT.NONE,chart,true);
	      
	       comp.pack();
	       
	       shell.open();

		super.run();
	}
	 CategoryToolTipGenerator ca=new CategoryToolTipGenerator(){

			public String generateToolTip(CategoryDataset dataSet, int one, int two) {
				DefaultCategoryDataset d=(DefaultCategoryDataset) dataSet;
				
				return d.getColumnKey(two).toString();
			}
     	
     };
}
