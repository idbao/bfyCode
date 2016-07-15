package com.ssj.util;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class SetLayoutDateUtil {

	
	public static void  scan(Composite com_top ){
		Control cs[]=com_top.getChildren();
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
		System.out.println(count+"-->"+countx);
		Text []ct=new Text[count];
		Combo []cb=new Combo[countx];
		for(int i=0;i<count;i++){
			ct[i]=cts.get(i);
		}
		for(int i=0;i<countx;i++){
			cb[i]=cbs.get(i);
		}
	}
}
