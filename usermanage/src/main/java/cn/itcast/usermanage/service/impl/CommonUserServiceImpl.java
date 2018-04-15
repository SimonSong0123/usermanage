package cn.itcast.usermanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.itcast.usermanage.mapper.CommonUserMapper;
import cn.itcast.usermanage.pojo.PageBean;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.CommonUserService;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class CommonUserServiceImpl implements CommonUserService {
	
	@Autowired
	private CommonUserMapper userMapper;

	@Override
	public User queryById(Long id) {
		User user = new User();
		user.setId(id);
		return userMapper.selectOne(user);
	}

	@Override
	public PageBean<User> queryPage(Integer page, Integer rows) {
		//总条数
		//Long total = userMapper.queryCount();
		//查询分页数据
		//List<User> users = userMapper.queryPage((page-1)*rows,rows);
		PageHelper.startPage(page, rows, true);
		List<User> users = userMapper.selectAll();
		PageInfo<User> pageInfo = new PageInfo<>(users);
		PageBean<User> pageBean = new PageBean<User>(pageInfo.getTotal(),users);
		return pageBean;
	}

	@Override
	public int save(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public void delete(List<Long> ids) {
		Example example = new Example(User.class);
		example.createCriteria().andIn("id", ids);
		userMapper.deleteByExample(example);
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}



}
