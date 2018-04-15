package cn.itcast.usermanage.pojo;

public class PageResult {

	private Integer status;// 结果的状态码
	private Object data;// 结果的数据
	
	public PageResult() {
	}
	
	public PageResult(Integer status) {
		super();
		this.status = status;
	}

	public PageResult(Integer status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}
	// 提供一个默认的成功状态方法
	public static PageResult ok(){
		return new PageResult(200);
	}
	// 提供一个默认的异常状态方法
	public static PageResult error(){
		return new PageResult(500);
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
