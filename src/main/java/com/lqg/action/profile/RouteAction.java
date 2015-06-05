package com.lqg.action.profile;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.Route;
import com.opensymphony.xwork2.ModelDriven;
/**
 * routeAction
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("routeAction")
public class RouteAction extends BaseAction implements ModelDriven<Route>{
	private static final long serialVersionUID = 1L;
	private static final String ROUTE = "route";
	private Route route = new Route();
	@Override
	public Route getModel() {
		return route;
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
		
		routeDao.saveOrUpdate(route);
		return list();
	}
	/**
	 * 列表
	 *  @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = routeDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(route.getId() != null && route.getId() > 0){
			this.route = routeDao.get(route.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(route.getId() != null && route.getId() > 0){
			routeDao.delete(route.getId());
			}
		return list();
	}
	/**
	 *名字找route
	 * @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {route.getParent()};
		pageModel= routeDao.find(-1, -1, where, queryParams);
		
		return ROUTE;
	}
	
	
	// pageModel
	private PageModel<Route> pageModel;
	
	
	public PageModel<Route> getPageModel() {
		return pageModel;
	}
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
}
