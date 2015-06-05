package com.lqg.action.profile;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.Collection;
import com.lqg.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * collectionAction
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("collectionAction")
public class CollectionAction extends BaseAction implements ModelDriven<Collection>{
	private static final long serialVersionUID = 1L;
	private static final String COLLECTION = null;
	private Collection collection = new Collection();
	@Override
	public Collection getModel() {
		return collection;
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
		
		collectionDao.saveOrUpdate(collection);
		return list();
	}
	/**
	 * 列表
	 *  @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = collectionDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(collection.getId() != null && collection.getId() > 0){
			this.collection = collectionDao.get(collection.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 *  @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(collection.getId() != null && collection.getId() > 0){
			collectionDao.delete(collection.getId());
			}
		return list();
	}
	/**
	 * 名字找collection
	 *  @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {collection.getParent()};
		pageModel= collectionDao.find(-1, -1, where, queryParams);
		
		return COLLECTION;
	}
	
	
	// pageModel
	private PageModel<Collection> pageModel;
	
	
	public PageModel<Collection> getPageModel() {
		return pageModel;
	}
	public Collection getCollection() {
		return collection;
	}
	
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
}
