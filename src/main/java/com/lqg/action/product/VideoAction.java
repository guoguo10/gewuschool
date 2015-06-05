package com.lqg.action.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.BookItem;
import com.lqg.model.PageModel;
import com.lqg.model.UploadFile;
import com.lqg.model.Video;
import com.lqg.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;
/**
 * videoAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("videoAction")
public class VideoAction extends BaseAction implements ModelDriven<Video>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 选择
	 * @return String
	 * @throws Exception
	 */
	public String select() throws Exception {
		if(video.getId() != null && video.getId() > 0){
			video = videoDao.get(video.getId());
			video.setBrowseTimes(video.getBrowseTimes() + 1);
			videoDao.update(video);
		}
		return SELECT;
	}
	/**
	 * 查找
	 * @return String
	 * @throws Exception
	 */
	public String getByBookItemId() throws Exception{
		if(video.getBookItem().getId() != null){
			String where = "where bookItem.id = ?";
			Object[] queryParams = {video.getBookItem().getId()};
			pageModel = videoDao.find(pageNo, pageSize, where, queryParams);
		}
		return LIST;
	}
	/**
	 * 新的video
	 * @return
	 * @throws Exception
	 */
	public String findNewVideo() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		pageModel = videoDao.find(1, 5, orderby );
		image.put("url", "01.gif");
		return "list";
	}
	/**
	 * 找感谢的视频
	 * @return
	 * @throws Exception
	 */
	public String findThankVideo() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		pageModel = videoDao.find(1, 5, orderby );
		image.put("url", "03.gif");
		return "list";
	}
	/**
	 * commend video
	 * @return
	 * @throws Exception
	 */
	public String findCommendVideo() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		String where = "where recommended = ?";
		Object[] queryParams = {true};
		pageModel = videoDao.find(where, queryParams, orderby, 1, 5);
		image.put("url", "06.gif");
		return "list";
	}
	/**
	 * 喜欢的video
	 * @return
	 * @throws Exception
	 */
	public String findEnjoyVideo() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browserTimes", "desc");
		pageModel = videoDao.find(1, 5, orderby );
		image.put("url", "07.gif");
		return "list";
	}
	/**
	 * 名字找video
	 *  @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		if(video.getName() != null){
			String where = "where name like ?";
			Object[] queryParams = {"%" + video.getName() + "%"};
			pageModel = videoDao.find(pageNo, pageSize, where, queryParams );
		}
		image.put("url", "04.gif");
		return LIST;
	}
	/**
	 * 流量次数多的视频
	 * @return String
	 * @throws Exception
	 */
	public String findByBrowseTimes() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browseTimes", "desc");
		pageModel = videoDao.find(1, 8, orderby );
		return "list";
	}
	/**
	 * 评论的视频
	 *  @return String
	 * @throws Exception
	 */
	public String findByCommend() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		String where = "where recommended = ?";
		Object[] queryParams = {true};
		pageModel = videoDao.find(where, queryParams, orderby, pageNo, pageSize);
		return "findList";
	}
	/**
	 * 感谢次数多的视频
	 * @return String
	 * @throws Exception
	 */
	public String findByThankCount() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		pageModel = videoDao.find(1, 6, orderby );
		return "findList";
	}
	
	
	/**
	 * 添加视频	 */
	@Override
	public String add() throws Exception {
		createBookItemTree();
		return INPUT;
	}
	/**
	 *保存视频
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(file != null ){
			//上传的路径
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdir();
			}
			String fileName = StringUitl.getStringTime() + ".jpg";
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(new File(dir,fileName)); 
				byte[] bs = new byte[1024 * 4]; 
				int len = -1;
				while((len = fis.read(bs)) != -1){
					fos.write(bs, 0, len);
					}
				UploadFile uploadFile = new UploadFile();
				uploadFile.setPath(fileName);
				video.setClipFile(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				fos.flush();
				fos.close();
				fis.close();
			}
		}
		
		if(video.getBookItem() != null && video.getBookItem().getId() != null){
			video.setBookItem(bookItemDao.load(video.getBookItem().getId()));
		}
		
		if(video.getClipFile() != null && video.getClipFile().getId() != null){
			video.setClipFile(uploadFileDao.load(video.getClipFile().getId()));
		}
		videoDao.saveOrUpdate(video);
		return list();
	}
	/**
	 * 列表视频
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		pageModel = videoDao.find(pageNo, pageSize);
		return LIST;
	}
	/**
	 * 编辑
	 *  @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.video = videoDao.get(video.getId());
		createBookItemTree();
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		videoDao.delete(video.getId());
		return list();
		}
	/**
	 * 书的树生成
	 */
	private void createBookItemTree(){
		String where = "where level=1";
		PageModel<BookItem> pageModel = bookItemDao.find(-1, -1,where ,null);
		List<BookItem> allCategorys = pageModel.getList();
		map = new LinkedHashMap<Integer, String>();
		for(BookItem category : allCategorys){
			setNodeMap(map,category,false);
		}
	}
	/**
	 * 树的递归
	 * @param node 起始点
	 *  @param flag 是否为父节点
	 */
	private void setNodeMap(Map<Integer, String> map,BookItem node,boolean flag){
		if (node == null) {
			return;
			}
		int level = node.getLevel();
		StringBuffer sb = new StringBuffer();
		if (level > 1) {
			for (int i = 0; i < level; i++) {
				 sb.append("闁跨喐鏋婚幏锟�");	
				 }
			sb.append(flag ? "闁跨喐鏋婚幏锟" : "闁跨喐鏋婚幏锟");
		}
		map.put(node.getId(), sb.append(node.getName()).toString());
		Set<BookItem> children = node.getChildren();
		
		if(children != null &&  children.size() > 0){
			int i = 0;
				for (BookItem child : children) {
				boolean b = true;
				if(i == children.size()-1){
					b = false;
				}
				setNodeMap(map,child,b);
				}
		}
	}
	/**
	 * 确保save
	 */
	public void validateSave() {
		if(!StringUitl.validateString(video.getName())){
			addFieldError("name", "闁跨喐鏋婚幏宄版惂闁跨喐鏋婚幏閿嬫嫚闁跨喐鏋婚幏鐑芥晸鏉炴浜烽幏椋庢躬闁跨噦鎷");
		}
		if(!StringUitl.validateInt(video.getGoodCount())){
			addFieldError("baseprice", "闁跨喓鍗抽惂鍛婂闁跨喐顢欓棃鈺傚闁跨喐鏋婚幏宄板晸闁跨喐鏋婚幏鐑芥晸閺傘倖瀚");
		}
		if(!StringUitl.validateInt(video.getBadCount())){
			addFieldError("marketprice", "闁跨喎褰ㄧ粵瑙勫闁跨喐顢欓棃鈺傚闁跨喐鏋婚幏宄板晸闁跨喐鏋婚幏鐑芥晸閺傘倖瀚");
		}
		if(!StringUitl.validateInt(video.getThankCount())){
			addFieldError("sellprice", "闁跨喐鏋婚幏鐑芥晸濡楁柧鐜棃鈺傚闁跨喐鏋婚幏宄板晸闁跨喐鏋婚幏鐑芥晸閺傘倖瀚");
		}
		if(!StringUitl.validateString(video.getDesc())){
			addFieldError("name", "闁跨喐鏋婚幏宄版惂鐠囨挳鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚规稉娲晸缁夐潻缍囬幏锟");
		}
		createBookItemTree();
	}


	// 闁跨喐鏋婚幏宄版惂闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	private Video video = new Video();
	// 闁跨喕绶濇潏鐐闁跨喍鑼庣涵閿嬪
	private File file;
	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻敓锟�
	private Map<Integer, String> map;
	// 闁跨喐鏋婚幏鐑姐�闁跨喐鏋婚幏鐑芥晸閿燂拷
	private PageModel<Video> pageModel;
	
	Map<String, String> image = new HashMap<String, String>();
	
	public Map<String, String> getImage() {
		return image;
	}
	public void setImage(Map<String, String> image) {
		this.image = image;
	}
	@Override
	public Video getModel() {
		return video;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Map<Integer, String> getMap() {
		return map;
	}
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
	public PageModel<Video> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<Video> pageModel) {
		this.pageModel = pageModel;
	}
}
