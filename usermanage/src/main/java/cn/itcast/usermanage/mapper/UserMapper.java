package cn.itcast.usermanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.usermanage.pojo.User;

public interface UserMapper {
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User queryById(Long id);

	//查询总条数的方法
	//Long queryCount();

	//查询分页数据的方法
	//List<User> queryPage(@Param("start")Integer start, @Param("rows")Integer rows);

	/**
	 * 查询所有用户的方法
	 * @return
	 */
	List<User> queryAll();

	/**
	 * 增加用户的方法
	 * @param user
	 * @return
	 */
	int save(User user);

	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	void delete(@Param("ids")Long[] ids);

	/**
	 * 删除用户的方法
	 * @param user
	 */
	void update(User user);

}

















