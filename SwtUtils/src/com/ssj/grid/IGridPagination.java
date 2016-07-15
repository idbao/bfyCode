package com.ssj.grid;

import java.util.List;

public interface IGridPagination {
	
	/**
	 * �õ�һ����Χ�ڵ�����(��ʱ�����һЩ����������)
	 * @param pageIndex
	 * @param resultCount
	 * ����������ֻ��Ҫ����Ϊ��ȡ��һ����Χ�ڵ�����
	 * query.setFirstResult(pageIndex);
	 * query.setMaxResults(resultCount);
	 * @return
	 */
	public List getResult(int pageIndex,int resultCount);
	
	/**
	 * ���أ���Ҫ����������(��ʱ�����һЩ����������)
	 * @return
	 */
	public int getCount();
	/**
	 * �õ����е����ݣ����ڵ�������ҳ������Ϊ��������ҳ�͵������С�(��ʱ�����һЩ����������)
	 * *******���Կ�ʵ��******
	 * @return
	 */
	public List getAllResult();
}
