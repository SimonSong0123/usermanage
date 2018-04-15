package cn.itcast.usermanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.itcast.usermanage.mapper.UserMapper;
import cn.itcast.usermanage.pojo.PageBean;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryById(Long id) {
		return userMapper.queryById(id);
	}

	@Override
	public PageBean<User> queryPage(Integer page, Integer rows) {
		//总条数
		//Long total = userMapper.queryCount();
		//查询分页数据
		//List<User> users = userMapper.queryPage((page-1)*rows,rows);
		PageHelper.startPage(page, rows, true);
		List<User> users = userMapper.queryAll();
		PageInfo<User> pageInfo = new PageInfo<>(users);
		PageBean<User> pageBean = new PageBean<User>(pageInfo.getTotal(),users);
		return pageBean;
	}

	@Override
	public int save(User user) {
		return userMapper.save(user);
	}

	@Override
	public void delete(Long[] ids) {
		userMapper.delete(ids);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}



}
