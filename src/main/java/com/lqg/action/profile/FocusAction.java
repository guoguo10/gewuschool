package com.lqg.action.profile;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.profile.Focus;
import com.opensymphony.xwork2.ModelDriven;
/**
 * focusAction
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("focusAction")
public class FocusAction extends BaseAction implements ModelDriven<Focus>{
	private static final long serialVersionUID = 1L;
	private static final String FOCUS = "focus";
	private Focus focus = new Focus();
	@Override
	public Focus getModel() {
		return focus;
	}
	
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
		
		focusDao.saveOrUpdate(focus);
		return list();
	}
	/**
	 * 列表
	 *  @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = focusDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(focus.getId() != null && focus.getId() > 0){
			this.focus = focusDao.get(focus.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(focus.getId() != null && focus.getId() > 0){
			focusDao.delete(focus.getId());
			}
		return list();
	}
	/**
	 * 名字找focus
	 *  @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {focus.getParent()};
		pageModel= focusDao.find(-1, -1, where, queryParams);
		
		return FOCUS;
	}
	
	
	// pageModel
	private PageModel<Focus> pageModel;
	
	
	public PageModel<Focus> getPageModel() {
		return pageModel;
	}
	public Focus getFocus() {
		return focus;
	}
	
	public void setFocus(Focus focus) {
		this.focus = focus;
	}

	
}
