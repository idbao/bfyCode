package com.ssj.util.test;

import java.util.ArrayList;
import java.util.List;

public class PeopleFactory {

	
	
	public static List getPeople(){
		
		
		List rs=new ArrayList();
		
		for(int i=0;i<100;i++)
		{
			People p=new People();
			p.setId(i);
			p.setAdd("add"+i);
			p.setPassword("password"+i);
			rs.add(p);
		}
		
		return rs;
	}
}
