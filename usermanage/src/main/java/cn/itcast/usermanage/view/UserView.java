package cn.itcast.usermanage.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import cn.itcast.usermanage.pojo.User;

public class UserView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 从model对象中获取userList
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) model.get("users");
		// 创建Excel的sheet
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("会员列表");

		// 创建标题行
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("用户名");
		header.createCell(2).setCellValue("姓名");
		header.createCell(3).setCellValue("年龄");
		header.createCell(4).setCellValue("性别");
		header.createCell(5).setCellValue("出生日期");
		header.createCell(6).setCellValue("创建时间");
		header.createCell(7).setCellValue("更新时间");

		// 填充数据
		int rowNum = 1;
		for (User user : userList) {
			HSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUserName());
			row.createCell(2).setCellValue(user.getName());
			row.createCell(3).setCellValue(user.getAge());
			String sexStr;
			if (user.getSex() == 1) {
				sexStr = "男";
			} else if (user.getSex() == 2) {
				sexStr = "女";
			} else {
				sexStr = "未知";
			}
			row.createCell(4).setCellValue(sexStr);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			row.createCell(5).setCellValue(dateFormat.format(user.getBirthday()));
			row.createCell(6).setCellValue(sdf.format(user.getCreated()));
			row.createCell(7).setCellValue(sdf.format(user.getUpdated()));

			rowNum++;
		}
		// 设置相应头信息，以附件形式下载并且指定文件名
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String("会员列表.xls".getBytes(), "ISO-8859-1"));
	}

}
