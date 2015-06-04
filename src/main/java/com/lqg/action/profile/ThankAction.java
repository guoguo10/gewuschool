package com.lqg.action.profile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.profile.Thank;
import com.lqg.model.user.Parent;
import com.lqg.model.user.Student;
import com.lqg.model.user.Teacher;
import com.lqg.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;
/**
 * thankAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("thankAction")
public class ThankAction extends BaseAction implements ModelDriven<Thank>{
	private static final long serialVersionUID = 1L;
	private static final String THANK = "thank";
	private static final String LIMITEDTHANKS = "limitedThanks";
	/**
	 *列表
	 * @return String
	 * @throws Exception
	 */
	public String select() throws Exception {
		if(thank.getId() != null && thank.getId() > 0){
			thank = thankDao.get(thank.getId());
			thankDao.update(thank);
		}
		return SELECT;
	}
	/**
	 * videioid获取thank
	 * @return String
	 * @throws Exception
	 */
	public String getByVideoId() throws Exception{
		if(thank.getVideo().getId() != null){
			String where = "where videoId = ?";
			Object[] queryParams = {thank.getVideo().getId()};
			Map<String, String> orderby = new  HashMap<String,String>();
			orderby.put("id", "desc");
			pageModel = thankDao.find( where, queryParams,orderby,pageNo, pageSize);
		}
		return LIMITEDTHANKS;
	}
		
	/**
	 * 添加	 */
	@Override
	public String add() throws Exception {
		
		return INPUT;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(thank.getVideo() != null && thank.getVideo().getId() != null){
			thank.setVideo(videoDao.load(thank.getVideo().getId()));
			thank.setCreateTime(new Date());	
			if(session.get("type").equals("student")){
				 Student student=(Student)session.get("user");
				 thank.setStudent(student);
			 }
			 if(session.get("type").equals("teacher")){
				Teacher teacher=(Teacher)session.get("user");
				 thank.setTeacher(teacher);
			 }
			 if(session.get("type").equals("parent")){
				 Parent parent=(Parent)session.get("user");
				thank.setParent(parent);
			 }
		}		
		thankDao.saveOrUpdate(thank);
		
		return THANK;
	}
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		pageModel = thankDao.find(pageNo, pageSize);
		return LIST;
	}
	/**
	 * 编辑
	 *  @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.thank = thankDao.get(thank.getId());
		return EDIT;
	}
	/**
	 *删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		thankDao.delete(thank.getId());
		return list();
		}
	
	/**
	 *确保保存
	 */
	public void validateSave() {
		if(!StringUitl.validateString(thank.getContent())){
			addFieldError("name", "闁跨喐鏋婚幏宄版惂闁跨喐鏋婚幏閿嬫嫚闁跨喐鏋婚幏鐑芥晸鏉炴浜烽幏椋庢躬闁跨噦鎷�");
		}
		
	}


	// modelDriven
	private Thank thank = new Thank();
	// pageModel
	private PageModel<Thank> pageModel;
	
	@Override
	public Thank getModel() {
		return thank;
	}
	public Thank getThank() {
		return thank;
	}
	public void setThank(Thank thank) {
		this.thank = thank;
	}
	public PageModel<Thank> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<Thank> pageModel) {
		this.pageModel = pageModel;
	}
}
