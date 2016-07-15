package com.ssj.grid;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;

import com.ssj.util.SWTResourceManager;



public class GridKillerImageDescriptor extends ImageDescriptor {

	String path="";
	
	public GridKillerImageDescriptor(String path){
		this.path=path;
	}
	
	@Override
	public ImageData getImageData() {
		// TODO Auto-generated method stub
		return SWTResourceManager.getImage(GridKillerImageDescriptor.class, path).getImageData();
	}

}
