package com.ssj.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
/**
 * ´´½¨SashForm
 * @author ssj234
 *
 */
public class CreateSashForm {

	private SashForm sash;

	public Composite[] create(Composite parent,int weights[],int style){
		int count =weights.length;
		Composite[] comps=new Composite[count];
		 sash=new SashForm(parent,SWT.NONE|style);
		for(int i=0;i<count;i++){
			Composite comp=new Composite(sash,SWT.NONE);
			FillLayout fill=new FillLayout();
//			fill.marginWidth=5;
			comp.setLayout(fill);
			comps[i]=comp;
		}
		sash.setWeights(weights);
		return comps;
		
	}

	public SashForm getSash() {
		return sash;
	}
	public void setSashWidth(){
		sash.SASH_WIDTH=0;
	}
}
