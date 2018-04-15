package cn.itcast.usermanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.itcast.usermanage.pojo.PageBean;
import cn.itcast.usermanage.pojo.PageResult;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.CommonUserService;

@Controller
@RequestMapping("user")
public class CommonUserController {

	@Autowired
	private CommonUserService userService;
	@RequestMapping("list")
	@ResponseBody
	public PageBean<User> list(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows){
		return userService.queryPage(page,rows);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public PageResult save(User user){
		int count = userService.save(user);
		if (count == 0) {
			return PageResult.error();
		}
		return PageResult.ok();
	}

	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public PageResult delete(@RequestParam("ids")List<Long> ids){
		try {
			userService.delete(ids);
			return PageResult.ok();
		} catch (Exception e) {
			return PageResult.error();
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public PageResult update(User user){
		try {
			userService.update(user);
			return PageResult.ok();
		} catch (Exception e) {
			return PageResult.error();
		}
	}
	
	@RequestMapping("export/excel")
	public ModelAndView exportExcel(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="5")Integer rows){
		ModelAndView mv = new ModelAndView("excelView");
		PageBean<User> users = userService.queryPage(page, rows);
		mv.addObject("users", users.getRows());
		return mv;
	}
	
}
