package com.ssj.grid;

import java.util.List;

public interface IGridPagination {
	
	/**
	 * 得到一定范围内的数据(有时需加入一些的条件查找)
	 * @param pageIndex
	 * @param resultCount
	 * 这两个参数只需要设置为：取出一定范围内的数据
	 * query.setFirstResult(pageIndex);
	 * query.setMaxResults(resultCount);
	 * @return
	 */
	public List getResult(int pageIndex,int resultCount);
	
	/**
	 * 返回：需要的数据总数(有时需加入一些的条件查找)
	 * @return
	 */
	public int getCount();
	/**
	 * 得到所有的数据，用于导出【分页导出分为：导出本页和导出所有】(有时需加入一些的条件查找)
	 * *******可以空实现******
	 * @return
	 */
	public List getAllResult();
}
