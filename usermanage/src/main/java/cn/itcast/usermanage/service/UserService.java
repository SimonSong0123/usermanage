package cn.itcast.usermanage.service;

import cn.itcast.usermanage.pojo.PageBean;
import cn.itcast.usermanage.pojo.User;

public interface UserService {

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User queryById(Long id);

	/**
	 * 查询分页的方法
	 * @param page
	 * @param rows
	 * @return
	 */
	PageBean<User> queryPage(Integer page, Integer rows);

	/**
	 * 增加用户的方法
	 * @param user
	 * @return
	 */
	int save(User user);

	/**
	 * 删除用户的方法
	 * @param ids
	 */
	void delete(Long[] ids);

	/**
	 * 修改用户的方法
	 * @param user
	 */
	void update(User user);
}
