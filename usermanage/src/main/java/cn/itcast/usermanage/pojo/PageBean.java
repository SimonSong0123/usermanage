package cn.itcast.usermanage.pojo;

import java.util.List;

public class PageBean<T> {

	private Long total;
	private List<T> rows;
	
	public PageBean(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public PageBean() {
		super();
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
