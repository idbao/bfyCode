package com.ssj.util;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
/**
 * 为text和combo加入监听
 * @author ssj234
 *
 */
public class CTextListener {
	private static  Text []cts;
	private static Combo []cbs;

	public static void setListener(final Text []cs,final Button bu,final Button bu2){
		cts=cs;
		for(int j=0;j<cs.length;j++){
			final int k=j;
			
			cs[j].addModifyListener(new ModifyListener(){

				public void modifyText(ModifyEvent arg0) {
					if(cs[k].isFocusControl()){
					if(all(cs)&&all(cbs)){
						bu.setEnabled(true);
						bu2.setEnabled(true);
					}else{
						bu.setEnabled(false);
						bu2.setEnabled(true);
					}
					
					
					}
				}
				
				
			});
		}
	}
	/**
	 * 
	 * @param cs
	 * @param bu
	 * @param bu2
	 */
	public static void setListener(final Combo []cs,final Button bu,final Button bu2){
		cbs=cs;
		for(int j=0;j<cs.length;j++){
			final int k=j;
		
			cs[j].addModifyListener(new ModifyListener(){

				public void modifyText(ModifyEvent arg0) {
					if(cs[k].isFocusControl()){
					if(all(cs)&&all(cts)){
						bu.setEnabled(true);
						bu2.setEnabled(true);
					}else{
						bu.setEnabled(false);
						bu2.setEnabled(true);
					}
					
					
					}
				}
				
				
			});
		}
	}
	
	public static boolean all(Text cs[]){
		for(int l=0;l<cs.length;l++){
			if(cs[l].getText()==null||cs[l].getText().equals("")){
				
				return false;
			}
		}
		return true;
	}
	public static boolean all(Combo cs[]){
		for(int l=0;l<cs.length;l++){
			if(cs[l].getText()==null||cs[l].getText().equals("")){
				
				return false;
			}
		}
		return true;
	}
	
}
