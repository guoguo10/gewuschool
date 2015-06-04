package com.lqg.action.user;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.product.UploadFile;
import com.lqg.model.user.Parent;
import com.lqg.model.user.Student;
import com.lqg.model.user.Teacher;
import com.lqg.util.EmailUtil;
import com.lqg.util.Md5s;
import com.lqg.util.MessageInfo;
import com.lqg.util.OperateImage;
import com.opensymphony.xwork2.ModelDriven;
/**
 * userAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("userAction")
public class UserAction extends BaseAction implements ModelDriven<Student>{
	private static final long serialVersionUID = 1L;
	private static final String HOME = "home";
	private static final String ACCOUNT = "account";
	private static final String CLASS = "class";
	private static final String PREPAGE = "prePage";
	private static final String REGISTERERROR = "registerError";
	private static final String LOGONERROR = "logonError";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String LINKSN = "linkSn";
	private static final String USERPROFILE = "userprofile";
	private String prePage;
	private String erroMessage;
	private String imageMessage;
	private File pic;   //上传的图片
	private String picFileName; //图片的名字
	private UploadFile uploadFile=new UploadFile();//上传的图片
	private OperateImage image=new OperateImage();
	private String cuttingImageName;
	private int cuttingImageX;
	private int cuttingImageY;
	private int cuttingImageWidth;
	private int cuttingImageHeight;
	@Autowired
	public EmailUtil emailUtil;	
	public String login() throws Exception{
		return USER_LOGIN;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		String email=student.getEmail();
		String password=Md5s.md5s(student.getPassword());
		 HttpServletRequest req = ServletActionContext.getRequest();
		String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_active.html?";  
		boolean unique = studentDao.isUnique(email)&&teacherDao.isUnique(email)
				&&parentDao.isUnique(email);
		
		if(category.equals("student")){		
		if(unique){
			student.setPassword(password);
			String hasmibao=email+password+new Date();
			student.setRandomCode(Md5s.md5s(hasmibao));
			studentDao.save(student);
			Student savestudent=studentDao.login(email, password);
			if(savestudent==null){
		   		setErroMessage("savestudent娑撹櫣鈹栭惃锟");
		   		return REGISTERERROR;
		   	}
			String activeHtml=activeAddress+"student.id="+savestudent.getId()+"&student.randomCode="+savestudent.getRandomCode()+"&category=student";
			String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
			MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
		   	message.setTo(email);
		   	message.setSubject("濞夈劌鍞界拋銈堢槈");
		   	message.setSendDate(new Date());
		   	message.setMsg("濞嗐垼绻"+savestudent.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
		   	if(emailUtil==null){
		   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
		   		return REGISTERERROR;
		   	}
		    emailUtil.doSend(message);			
			setErroMessage("濞夈劌鍞介幋鎰閿涘矁顕崚浼村仏缁犺鲸绺哄ú鏄忣吇鐠囷拷");
			
			prePage = (String) session.get("prePage");	
			return USER_LOGIN;
		}else{
			setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑鏁為崘宀冪箖閿涘矁顕惄瀛樺复閻ц缍");
			return REGISTERERROR;
		}}
		else if(category.equals("teacher")){			
			if(unique){
				Teacher teacher=new Teacher();
				teacher.setUsername(student.getUsername());
				teacher.setEmail(email);
				teacher.setPassword(password);
				String hasmibao=email+password+new Date();
				teacher.setRandomCode(Md5s.md5s(hasmibao));
				teacherDao.save(teacher);
				Teacher saveteacher=teacherDao.login(email, password);
				if(saveteacher==null){
			   		setErroMessage("saveteacher娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
				String activeHtml=activeAddress+"student.id="+saveteacher.getId()+"&student.randomCode="+saveteacher.getRandomCode()+"&category=teacher";
				String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
				MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
			   	message.setTo(email);
			   	message.setSubject("濞夈劌鍞界拋銈堢槈");
			   	message.setSendDate(new Date());
			   	message.setMsg("濞嗐垼绻"+saveteacher.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
			   	if(emailUtil==null){
			   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
			    emailUtil.doSend(message);			
				setErroMessage("濞夈劌鍞介幋鎰閿涘矁顕崚浼村仏缁犺鲸绺哄ú鏄忣吇鐠囷拷");
				
				prePage = (String) session.get("prePage");
				return USER_LOGIN;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
			}else{
				setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑鏁為崘宀冪箖閿涘矁顕惄瀛樺复閻ц缍");
				return REGISTERERROR;
			}}
		else if (category.equals("parent")){			
			if(unique){
				Parent parent=new Parent();
				parent.setUsername(student.getUsername());
				parent.setEmail(email);
				parent.setPassword(password);
				String hasmibao=email+password+new Date();
				parent.setRandomCode(Md5s.md5s(hasmibao));
				parentDao.save(parent);
				Parent saveparent=parentDao.login(email, password);
				if(saveparent==null){
			   		setErroMessage("saveparent娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
				String activeHtml=activeAddress+"student.id="+saveparent.getId()+"&student.randomCode="+saveparent.getRandomCode()+"&category=parent";
				String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
				MessageInfo message = new MessageInfo();
			   	message.setTo(email);
			   	message.setSubject("濞夈劌鍞界拋銈堢槈");
			   	message.setSendDate(new Date());
			   	message.setMsg("濞嗐垼绻"+saveparent.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
			   	if(emailUtil==null){
			   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
			    emailUtil.doSend(message);	
				setErroMessage("濞夈劌鍞介幋鎰閿涘矁顕崚浼村仏缁犺鲸绺哄ú鏄忣吇鐠囷拷");
				//閼惧嘲褰囩捄瀹犳祮閸掓壆娅ラ梽鍡欐櫕闂堫澀绠ｉ崜宥囨畱妞ょ敻娼伴崷鏉挎絻閿涘瞼鏁遍幏锔藉焻閸ｃ劍褰佹笟锟�		        prePage = (String) session.get("prePage");
				return USER_LOGIN;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
			}else{
				setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑鏁為崘宀冪箖閿涘矁顕惄瀛樺复閻ц缍");
				return REGISTERERROR;
			}}
		else {
			
			setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑鏁為崘宀冪箖閿涘矁顕惄瀛樺复閻ц缍");
			return REGISTERERROR;
		}

	}
	/**
	 * denglu
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
			Student logonstudent=studentDao.login(student.getEmail(), Md5s.md5s(student.getPassword()));
		Parent logonparent=parentDao.login(student.getEmail(), Md5s.md5s(student.getPassword()));
		Teacher logonteacher=teacherDao.login(student.getEmail(), Md5s.md5s(student.getPassword()));
		if(logonstudent!=null){
			if(logonstudent.getActive().equals("1")){
			session.put("user", logonstudent);
			session.put("type", "student");
			setErroMessage("閻ц缍嶉幋鎰");
		     prePage = (String) session.get("prePage");	        
	       
	       //session.remove("prePage");
	        if (prePage == null) {
	            return HOME;
	        } else {	        	
	        	 return LOGONERROR;
	        }
			}else{
				 HttpServletRequest req = ServletActionContext.getRequest();
				String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_active.html?";  
				String activeHtml=activeAddress+"student.id="+logonstudent.getId()+"&student.randomCode="+logonstudent.getRandomCode()+"&category=student";
				String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
				MessageInfo message = new MessageInfo(); 		   	
			   	message.setTo(logonstudent.getEmail());
			   	message.setSubject("濞夈劌鍞界拋銈堢槈");
			   	message.setSendDate(new Date());
			   	message.setMsg("濞嗐垼绻"+logonstudent.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
			   	if(emailUtil==null){
			   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
			    emailUtil.doSend(message);					
				setErroMessage("鐠囥儵鍋栫粻杈ㄧ梾閺堝绺哄ú浼欑礉閹存垳婊戝鑼病閸欐垿锟藉┑锟芥た闁剧偓甯撮崚鐗堝亶閻ㄥ嫰鍋栫粻閬嶅櫡閿涘矁顕惂璇茬秿闁喚顔堝┑锟芥た");
				return LOGONERROR;
			}
			
			}
		else if(logonparent!=null){
			if(logonparent.getActive().equals("1")){
			session.put("user", logonparent);
			session.put("type", "parent");
			setErroMessage("閻ц缍嶉幋鎰");
			  prePage = (String) session.get("prePage");
	        
           //session.remove("prePage");	        
	        if (prePage == null) {
	                return HOME;
	        } else {
	        	return LOGONERROR;
	        }
			}else{
				HttpServletRequest req = ServletActionContext.getRequest();
				String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_active.html?";  
				String activeHtml=activeAddress+"student.id="+logonparent.getId()+"&student.randomCode="+logonparent.getRandomCode()+"&category=parent";
				String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
				MessageInfo message = new MessageInfo();
			   	message.setTo(logonparent.getEmail());
			   	message.setSubject("濞夈劌鍞界拋銈堢槈");
			   	message.setSendDate(new Date());
			   	message.setMsg("濞嗐垼绻"+logonparent.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
			   	if(emailUtil==null){
			   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
			    emailUtil.doSend(message);					
				setErroMessage("鐠囥儵鍋栫粻杈ㄧ梾閺堝绺哄ú浼欑礉閹存垳婊戝鑼病閸欐垿锟藉┑锟芥た闁剧偓甯撮崚鐗堝亶閻ㄥ嫰鍋栫粻閬嶅櫡閿涘矁顕惂璇茬秿闁喚顔堝┑锟芥た");
				return LOGONERROR;
				
			}
		}
		else if(logonteacher!=null){
			if(logonteacher.getActive().equals("1")){
			session.put("user", logonteacher);
			session.put("type", "teacher");
			 setErroMessage("閻ц缍嶉幋鎰");
			 prePage = (String) session.get("prePage");
	        
	        //session.remove("prePage");	       
	        if (prePage == null) {
	              return HOME;
	        } else {
	        	return LOGONERROR;
	        }
			}else{
				HttpServletRequest req = ServletActionContext.getRequest();
				String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_active.html?";  
				String activeHtml=activeAddress+"student.id="+logonteacher.getId()+"&student.randomCode="+logonteacher.getRandomCode()+"&category=teacher";
				String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
				MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
			   	message.setTo(logonteacher.getEmail());
			   	message.setSubject("濞夈劌鍞界拋銈堢槈");
			   	message.setSendDate(new Date());
			   	message.setMsg("濞嗐垼绻"+logonteacher.getUsername()+"閸旂姴鍙嗛弽鑲╁⒖鐎涳箓娅岄敍灞惧亶瀹歌尙绮″▔銊ュ斀閹存劕濮涢敍宀冾嚞閻愮懓鍤稉瀣桨閻ㄥ嫰鎽奸幒銉ㄧ箻鐞涘本绺哄ú浼欑礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
			   	if(emailUtil==null){
			   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
			   		return REGISTERERROR;
			   	}
			    emailUtil.doSend(message);					
				setErroMessage("鐠囥儵鍋栫粻杈ㄧ梾閺堝绺哄ú浼欑礉閹存垳婊戝鑼病閸欐垿锟藉┑锟芥た闁剧偓甯撮崚鐗堝亶閻ㄥ嫰鍋栫粻閬嶅櫡閿涘矁顕惂璇茬秿闁喚顔堝┑锟芥た");
				return LOGONERROR;
				
			}
		}
		else {
			setErroMessage("鐎靛棛鐖滈崪宀勫仏缁犲彉绗夋稉锟藉毀");
			return LOGONERROR;
		}
	}
	/**
	 * tuichu
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		if(session != null && session.size() > 0){
			session.remove("user");
			session.remove("type");
			setErroMessage("闁拷鍤幋鎰");
		
	        session.remove("prePage");
			
		}
		return LOGOUT;
	}
	
	public String home() throws Exception{
		
		if(session.get("user")!= null){			
			 
				 return HOME;
		}else{
			return ERROR;
		}
		
	}
	
public String account() throws Exception{
		
	if(session.get("user")!= null){		
		 
			 return ACCOUNT;		 
		
	}else{
			return ERROR;
		}
	}
public String myClass() throws Exception{
	
	if(session.get("user")!= null){	
		 
			 return CLASS;
		 }else{
			return ERROR;
		}
	}
/*
 * email
 */
public String email() throws Exception{
	
	if(session.get("user")!= null){
		
		 
			 return EMAIL;		
		 
	}
	
		else{
			return ERROR;
		}
	}
/*
 * password
 */
public String password() throws Exception{
	
	if(session.get("user")!= null){
		
		 
			 return PASSWORD;		
		 
	}
	
		else{
			return ERROR;
		}
	}
/*
 * inkSn
 */
public String linkSn() throws Exception{
	
	if(session.get("user")!= null){
		
		 
			 return LINKSN;		
		 
	}
	
		else{
			return ERROR;
		}
	}
/*
 * upload action
 */

public String uploadPic() {  
    String[] str = { ".jpg", ".jpeg", ".png", ".gif" };  
    // 
   // TbUser curruser = (TbUser) getValue(SCOPE_SESSION, "curruser");  
    // 
    if (pic == null || pic.length() > 4194304) {          
		
    	setImageMessage("閺傚洣娆㈡潻鍥с亣");
        return "imageLimited";  
    }  
    for (String s : str) {  
        if (picFileName.endsWith(s)) { 
            String realPath = ServletActionContext.getServletContext().getRealPath("/img/headicon");// 閸︹暟omcat娑擃厺绻氱�妯烘禈閻楀洨娈戠�鐐烘鐠侯垰绶� ==  "webRoot/img/headicon"
            String name="";
            String username="";
            if(session.get("type").equals("student")){
            	username="student"+((Student) session.get("user")).getId();
            	name=name+username+"_"+new Date().toString();
            }
            if(session.get("type").equals("parent")){
            	username="parent"+((Parent) session.get("user")).getId();
            	name=name+username+"_"+new Date().toString();
            }
            if(session.get("type").equals("teacher")){
            	username="teacher"+((Teacher) session.get("user")).getId();
            	name=name+username+"_"+new Date().getTime();
            }
            String fileName = name+ ".jpg";//
            File imageDir = new File(realPath);  // 闁插矂娼版潏鎾冲弳閻楃懓鐣鹃惄顔肩秿
            File temp = null;
            int n = 0;
            File[] filelist = imageDir.listFiles();
            for (int i = 0; i < filelist.length; i++) {  //闁秴宸婚惄顔肩秿娑撳澧嶉張澶嬫瀮娴狅拷
             temp = filelist[i];
             if(temp.getName().contains(username)){     //鏉堟挸鍙嗛弬鍥︽閹碉拷瀵橀崥顐ゆ畱鐎涙顑�
              System.out.println(temp.getName());
              n++;
              temp.delete();     //閸掔娀娅庨弬鍥︽
             }
            }
            File saveFile = new File(new File(realPath), fileName); // 閸︺劏顕氱�鐐烘鐠侯垰绶炴稉瀣杽娓氬瀵叉稉锟介嚋閺傚洣娆� 
            
            // 閸掋倖鏌囬悥鍓佹窗瑜版洘妲搁崥锕�摠閸︼拷 
            if (!saveFile.getParentFile().exists()) {  
                saveFile.getParentFile().mkdirs();  
            }  
            try {  
                // 閹笛嗩攽閺傚洣娆㈡稉濠佺炊  
                // FileUtils 缁鎮�org.apache.commons.io.FileUtils;  
                // 閺勭棗ommons-io閸栧懍鑵戦惃鍕剁礉commons-fileupload 韫囧懘銆忔笟婵婄  
                // commons-io閸栧懎鐤勯悳鐗堟瀮娴犳湹绗傚▎鈽呯礉鐎圭偤妾稉濠傛皑閺勵垰鐨㈡稉锟介嚋閺傚洣娆㈡潪顒佸床閹存劖绁﹂弬鍥︽鏉╂稖顢戠拠璇插晸  
                FileUtils.copyFile(pic, saveFile);  
               	uploadFile.setPath(fileName);//鑴＄尗鑴拌剻鑴﹁剾鑹楅晛鑴欐病椴佽劃
			    } catch (IOException e) {  
            	setImageMessage("閺傚洣娆㈡稉濠佺炊婢惰精瑙");
                return "imageError";  
            }  
        }  
    } 
    setImageMessage("閺傚洣娆㈡稉濠佺炊閹存劕濮");
    return "imageSuccess";  
}  
//截切图片
public String cutPic(){  
	    String hostPath = ServletActionContext.getServletContext().getRealPath("/img/headicon");// 閸︹暟omcat娑擃厺绻氱�妯烘禈閻楀洨娈戠�鐐烘鐠侯垰绶� ==  "webRoot/img/" 
        String name=hostPath+"/"+getCuttingImageName();
        image.setSrcpath(name);  
        int index=getCuttingImageName().lastIndexOf("_");
        String nameCut=getCuttingImageName().substring(0,index)+"_headicon"+new Date().getTime()+".jpg";
        String cutImage=hostPath+"/"+nameCut;
        image.setSubpath(cutImage); 
        image.setX(getCuttingImageX());
        image.setY(getCuttingImageY());
        image.setWidth(getCuttingImageWidth());
        image.setHeight(getCuttingImageHeight());
        try {  
            image.cut(); //閹笛嗩攽鐟佷礁澹�幙宥勭稊  閹笛嗩攽鐎瑰苯鎮楅崡鍐插讲閻㈢喐鍨氶惄顔界垼閸ユ儳婀�鐟扮安閺傚洣娆㈡径鐟板敶閵嗭拷/span> 
            File guoduFile = new File(name); // 閸︺劏顕氱�鐐烘鐠侯垰绶炴稉瀣杽娓氬瀵叉稉锟介嚋閺傚洣娆� 
            // 閸掋倖鏌囬崢鐔告降娑撳﹣绱堕惃鍕弾閻楀洤鐡ㄩ崷銊ュ灩闂勩倧绱濋梼鍙夘剾閺堝秴濮熼崳銊х舶閻栧棙寮� 
            if (guoduFile.exists()) {  
                guoduFile.delete();  
            } 
            File cutFile = new File(cutImage); // 閸︺劏顕氱�鐐烘鐠侯垰绶炴稉瀣杽娓氬瀵叉稉锟介嚋閺傚洣娆� 
            // 閸掋倖鏌囨稉濠佺炊閸氬海娈戦悡褏澧栫�妯烘躬閸掔娀娅庨敍宀勬Щ濮濄垺婀囬崝鈥虫珤缂佹瑧鍨庡锟�
            int indexCut=getCuttingImageName().indexOf("_headicon");
            if (indexCut>=0) {  
            	 nameCut=getCuttingImageName().substring(0,indexCut)+"_headicon"+new Date().getTime()+".jpg";
            	 cutFile.renameTo(new   File(hostPath+"/"+nameCut));   //閺�懓鎮�
            } 
            
            uploadFile.setPath(nameCut);//鑴＄尗鑴拌剻鑴﹁剾鑹楅晛鑴欐病椴佽劃
			uploadFileDao.save(uploadFile);
			if(session.get("type").equals("student")){				
				Student student = studentDao.load(((Student) session.get("user")).getId());
				student.setImage(uploadFile);
				studentDao.update(student);				
				Student studentcut = studentDao.load(student.getId());
				session.remove("user");
				session.put("user", studentcut);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
			}
			if(session.get("type").equals("parent")){
				Parent parent=parentDao.load(((Parent) session.get("user")).getId());
				parent.setImage(uploadFile);
				parentDao.update(parent);
				Parent parentcut = parentDao.load(parent.getId());
				session.remove("user");
				session.put("user", parentcut);//鐏忓棗顔嶉梹鎸庢杹閸忣櫥ser 閻ㄥ墕ession
			}
			if(session.get("type").equals("teacher")){
				Teacher teacher=teacherDao.load(((Teacher) session.get("user")).getId());
				teacher.setImage(uploadFile);
				teacherDao.update(teacher);
				Teacher teachercut = teacherDao.load(teacher.getId());
				session.remove("user");
				session.put("user", teachercut);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
			}
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "cutsuccess";  
    }  
/**
 * 娑擃亙姹夌挧鍕灐娣囶喗鏁�
 * @return
 * @throws Exception
 */
public String saveProfile() throws Exception{
	if(session.get("type").equals("student")){			
		Student studentM=studentDao.load(((Student) session.get("user")).getId());
		if(student.getNickname()!=null){
			studentM.setNickname(student.getNickname());
		}
		if(student.getUsername()!=null){
		studentM.setUsername(student.getUsername());
		}
		if(student.getSex()!=null){
		studentM.setSex(student.getSex());
		}
		if(student.getBirthday()!=null){
		studentM.setBirthday(student.getBirthday());
		}
		if(student.getSchool()!=null){
		studentM.setSchool(student.getSchool());
		}
		if(student.getAptcourses()!=null){
		studentM.setAptcourses(student.getAptcourses());
		}
		if(student.getIntroduction()!=null){
		studentM.setIntroduction(student.getIntroduction());
		}
		studentDao.update(studentM);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
		Student studentprofile = studentDao.load(((Student) session.get("user")).getId());
		session.remove("user");
		session.put("user", studentprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
		setErroMessage("娣囶喗鏁奸幋鎰");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
	}
	else if(session.get("type").equals("teacher")){			  
			Teacher teacher=teacherDao.load(((Teacher) session.get("user")).getId());
			if(student.getNickname()!=null){
				teacher.setNickname(student.getNickname());
			}
			if(student.getUsername()!=null){
			teacher.setUsername(student.getUsername());
			}
			if(student.getSex()!=null){
			teacher.setSex(student.getSex());
			}
			if(student.getBirthday()!=null){
			teacher.setBirthday(student.getBirthday());
			}
			if(student.getSchool()!=null){
			teacher.setSchool(student.getSchool());
			}
			if(student.getAptcourses()!=null){
			teacher.setAptcourses(student.getAptcourses());
			}
			if(student.getIntroduction()!=null){
			teacher.setIntroduction(student.getIntroduction());
			}
			teacherDao.update(teacher);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
			Teacher teacherprofile = teacherDao.load(((Teacher) session.get("user")).getId());
			session.remove("user");
			session.put("user", teacherprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
			setErroMessage("娣囶喗鏁奸幋鎰");			
			return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else if (session.get("type").equals("parent")){			
		Parent parent=parentDao.load(((Parent) session.get("user")).getId());
		if(student.getNickname()!=null){
			parent.setNickname(student.getNickname());
		}
		if(student.getUsername()!=null){
			parent.setUsername(student.getUsername());
		}
		if(student.getSex()!=null){
			parent.setSex(student.getSex());
		}
		if(student.getBirthday()!=null){
			parent.setBirthday(student.getBirthday());
		}	
		
		if(student.getIntroduction()!=null){
			parent.setIntroduction(student.getIntroduction());
		}
		parentDao.update(parent);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
		Parent parentprofile = parentDao.load(((Parent) session.get("user")).getId());
		session.remove("user");
		session.put("user", parentprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
		setErroMessage("娣囶喗鏁奸幋鎰");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else {
		setErroMessage("濞屸剝婀佹穱顔芥暭");
		return USERPROFILE;
	}

}
/**
 * 娑擃亙姹夌�鍡欑垳娣囶喗鏁�
 * @return
 * @throws Exception
 */
public String modifyPassword() throws Exception{
	String encrypt=Md5s.md5s(student.getPassword());
	if(session.get("type").equals("student")){			
		Student studentM=studentDao.load(((Student) session.get("user")).getId());
		if(!(encrypt.equals(studentM.getPassword()))){
			setErroMessage("閸樼喎鐦戦惍渚�晩鐠囷拷");
			return USERPROFILE;
		}
		studentM.setPassword(Md5s.md5s(getRepassword()));
		studentDao.update(studentM);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
		Student studentprofile = studentDao.load(((Student) session.get("user")).getId());
		session.remove("user");
		session.put("user", studentprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
		setErroMessage("娣囶喗鏁奸幋鎰");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
	}
	else if(session.get("type").equals("teacher")){			  
			Teacher teacher=teacherDao.load(((Teacher) session.get("user")).getId());
			if(!(encrypt.equals(teacher.getPassword()))){
				setErroMessage("閸樼喎鐦戦惍渚�晩鐠囷拷");
				return USERPROFILE;
			}
			teacher.setPassword(Md5s.md5s(getRepassword()));		
			teacherDao.update(teacher);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
			Teacher teacherprofile = teacherDao.load(((Teacher) session.get("user")).getId());
			session.remove("user");
			session.put("user", teacherprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
			setErroMessage("娣囶喗鏁奸幋鎰");			
			return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else if (session.get("type").equals("parent")){			
		Parent parent=parentDao.load(((Parent) session.get("user")).getId());
		if(!(encrypt.equals(parent.getPassword()))){
			setErroMessage("閸樼喎鐦戦惍渚�晩鐠囷拷");
			return USERPROFILE;
		}
		parent.setPassword(Md5s.md5s(getRepassword()));		
		parentDao.update(parent);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
		Parent parentprofile = parentDao.load(((Parent) session.get("user")).getId());
		session.remove("user");
		session.put("user", parentprofile);//鐏忓棗顒熼悽鐔告杹閸忣櫥ser 閻ㄥ墕ession
		setErroMessage("娣囶喗鏁奸幋鎰");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else {
		setErroMessage("濞屸剝婀佹穱顔芥暭");
		return USERPROFILE;
	}

}
/**
 * 娑擃亙姹夐柇顔绢唸濠碉拷妞�
 * @return
 * @throws Exception
 */
public String active() throws Exception{	
	if(getCategory().equals("student")){			
		Student studentM=studentDao.load(getStudent().getId());
		if(studentM==null){			
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(studentM.getActive().equals("1")){
			setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑绺哄ú浼欑礉鐠囬娅ヨぐ锟");
			return USERPROFILE;
		}
		if(!(student.getRandomCode().equals(studentM.getRandomCode()))){
			setErroMessage("濮濄倝鍋栫粻鍙樼瑝閸忎浇顔忛棃鐐寸《閹垮秳缍");
			return USERPROFILE;
		}
		studentM.setActive("1");
		studentDao.update(studentM);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�	
		setErroMessage("濮濄倝鍋栫粻杈ㄧ负濞茬粯鍨氶崝鐕傜礉鐠囬娅ヨぐ锟");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
	}
	else if(getCategory().equals("teacher")){			  
		Teacher teacher=teacherDao.load(getStudent().getId());
		if(teacher==null){			
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(teacher.getActive().equals("1")){
			setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑绺哄ú浼欑礉鐠囬娅ヨぐ锟");
			return USERPROFILE;
		}
		if(!(student.getRandomCode().equals(teacher.getRandomCode()))){
			setErroMessage("濮濄倝鍋栫粻鍙樼瑝閸忎浇顔忛棃鐐寸《閹垮秳缍");
			return USERPROFILE;
		}
		teacher.setActive("1");
		teacherDao.update(teacher);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�	
		setErroMessage("濮濄倝鍋栫粻杈ㄧ负濞茬粯鍨氶崝鐕傜礉鐠囬娅ヨぐ锟");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else if (getCategory().equals("parent")){			
		Parent parent=parentDao.load(getStudent().getId());
		if(parent==null){			
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(parent.getActive().equals("1")){
			setErroMessage("濮濄倝鍋栫粻鍗炲嚒缂佸繑绺哄ú浼欑礉鐠囬娅ヨぐ锟");
			return USERPROFILE;
		}
		if(!(student.getRandomCode().equals(parent.getRandomCode()))){
			setErroMessage("濮濄倝鍋栫粻鍙樼瑝閸忎浇顔忛棃鐐寸《閹垮秳缍");
			return USERPROFILE;
		}
		parent.setActive("1");
		parentDao.update(parent);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�	
		setErroMessage("濮濄倝鍋栫粻杈ㄧ负濞茬粯鍨氶崝鐕傜礉鐠囬娅ヨぐ锟");			
		return USERPROFILE;//闁跨喐鏋婚幏鐑芥晸閹搭亙绱幏宄版喅闁跨喐鏋婚幏宄扮秿妞ょ敻鏁撻弬銈嗗
		}
	else {
		setErroMessage("濮濄倝鍋栫粻杈ㄧ负濞茶銇戠拹銉礉鐠囩兘鍣搁弬鐗堢负濞诧拷");
		return USERPROFILE;
	}

}
/**
 * 娑擃亙姹夌�鍡欑垳閹垫儳娲栭崣鎴︼拷闁剧偓甯�
 * @return
 * @throws Exception
 */
public String sendPasswordFindLink() throws Exception{	
	String where = "where email = ?";
	Object[] queryParams = {getStudent().getEmail()};	
	List<Student> studentfindlist= studentDao.find(-1,-1, where, queryParams).getList();//閹笛嗩攽閺屻儴顕楅弬瑙勭《
	List <Teacher> teacherfindlist= teacherDao.find(-1,-1, where, queryParams).getList();//閹笛嗩攽閺屻儴顕楅弬瑙勭《
	List <Parent> parentfindlist= parentDao.find(-1,-1, where, queryParams).getList();//閹笛嗩攽閺屻儴顕楅弬瑙勭《
	if(studentfindlist!=null && studentfindlist.size() > 0){
		Student studentFind=studentfindlist.get(0);		
	HttpServletRequest req = ServletActionContext.getRequest();
	String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_setnewpassword.html?";  
	String activeHtml=activeAddress+"student.id="+studentFind.getId()+"&student.randomCode="+studentFind.getRandomCode()+"&category=student";
	String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
	MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
   	message.setTo(studentFind.getEmail());
   	message.setSubject("閹垫儳娲栫�鍡欑垳");
   	message.setSendDate(new Date());
   	message.setMsg("濞嗐垼绻"+studentFind.getUsername()+"閹垫儳娲栫�鍡欑垳"+"鐠囬鍋ｉ崙璁崇瑓闂堛垻娈戦柧鐐复鏉╂稖顢戠�鍡欑垳閺備即鍣哥純顕嗙礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
   	if(emailUtil==null){
   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
   		return USERPROFILE;
   	}
    emailUtil.doSend(message);					
	setErroMessage("閹存垳婊戝鑼病閸欐垿锟介幍鎯ф礀鐎靛棛鐖滈柧鐐复閸掔増鍋嶉惃鍕仏缁犻亶鍣烽敍宀冾嚞閻ц缍嶉柇顔绢唸閺屻儲鏁");
	return USERPROFILE;
	}
	else if(parentfindlist!=null && parentfindlist.size() > 0){
		Parent parentFind=parentfindlist.get(0);		
	HttpServletRequest req = ServletActionContext.getRequest();
	String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_setnewpassword.html?"; 
	String activeHtml=activeAddress+"student.id="+parentFind.getId()+"&student.randomCode="+parentFind.getRandomCode()+"&category=parent";
	String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
	MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
   	message.setTo(parentFind.getEmail());
   	message.setSubject("閹垫儳娲栫�鍡欑垳");
   	message.setSendDate(new Date());
   	message.setMsg("濞嗐垼绻"+parentFind.getUsername()+"閹垫儳娲栫�鍡欑垳"+"鐠囬鍋ｉ崙璁崇瑓闂堛垻娈戦柧鐐复鏉╂稖顢戠�鍡欑垳閺備即鍣哥純顕嗙礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
   	if(emailUtil==null){
   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
   		return USERPROFILE;
   	}
    emailUtil.doSend(message);					
	setErroMessage("閹存垳婊戝鑼病閸欐垿锟介幍鎯ф礀鐎靛棛鐖滈柧鐐复閸掔増鍋嶉惃鍕仏缁犻亶鍣烽敍宀冾嚞閻ц缍嶉柇顔绢唸閺屻儲鏁");
	return USERPROFILE;
	}
	else if(teacherfindlist!=null && teacherfindlist.size() > 0){
		Teacher teacherFind=teacherfindlist.get(0);		
	HttpServletRequest req = ServletActionContext.getRequest();
	String activeAddress= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/user/user_setnewpassword.html?";  
	String activeHtml=activeAddress+"student.id="+teacherFind.getId()+"&student.randomCode="+teacherFind.getRandomCode()+"&category=teacher";
	String activeLink="<a href="+"\""+activeHtml+"\""+">"+activeHtml+"</a>";
	MessageInfo message = new MessageInfo();//璺姃鑴虫幊鑴劉鑹楅晛鑴ㄨ劀鑴у瀯纰岃剾闇茶劖鑴ц锤 		   	
   	message.setTo(teacherFind.getEmail());
   	message.setSubject("閹垫儳娲栫�鍡欑垳");
   	message.setSendDate(new Date());
   	message.setMsg("濞嗐垼绻"+teacherFind.getUsername()+"閹垫儳娲栫�鍡欑垳"+"鐠囬鍋ｉ崙璁崇瑓闂堛垻娈戦柧鐐复鏉╂稖顢戠�鍡欑垳閺備即鍣哥純顕嗙礉閹存牞锟芥径宥呭煑闁剧偓甯撮崷銊х秹妞ゅ吀绗傞幍鎾崇磻"+"\n"+activeLink);
   	if(emailUtil==null){
   		setErroMessage("emailUtil娑撹櫣鈹栭惃锟");
   		return USERPROFILE;
   	}
    emailUtil.doSend(message);					
	setErroMessage("閹存垳婊戝鑼病閸欐垿锟介幍鎯ф礀鐎靛棛鐖滈柧鐐复閸掔増鍋嶉惃鍕仏缁犻亶鍣烽敍宀冾嚞閻ц缍嶉柇顔绢唸閺屻儲鏁");
	return USERPROFILE;
	}
	else {
		setErroMessage("濮濄倝鍋栫粻杈箷濞屸剝婀佸▔銊ュ斀");
		return USERPROFILE;
	}

}
/**
 * 鐠哄疇娴嗛崚鐗堝閸ョ偛鐦戦惍浣烘畱闁插秶鐤嗘い鐢告桨
 */
public String setnewpassword(){
	session.put("resetpassuserid", student.getId());
	session.put("resetpassuserhashcode", student.getRandomCode());
	session.put("resetpassusercategory", getCategory());
	return "newpasswordjsp";
	
}
/**
 * 娑擃亙姹夐幍鎯ф礀鐎靛棛鐖滈柌宥囩枂
 * @return
 * @throws Exception
 */
public String passwordfindActive() throws Exception{
	String encrypt=Md5s.md5s(student.getPassword());
	if(session.get("resetpassusercategory").equals("student")){			
		Student studentM=studentDao.load((int)session.get("resetpassuserid"));
		if(studentM==null){
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(!(session.get("resetpassuserhashcode").equals(studentM.getRandomCode()))){	
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("闁喚顔堟稉宥呭帒鐠佹悂娼▔鏇熸惙娴ｏ拷");
			return USERPROFILE;
		}
		studentM.setPassword(encrypt);
		studentDao.update(studentM);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�
		session.remove("resetpassusercategory");
		session.remove("resetpassuserid");
		session.remove("resetpassuserhashcode");
		setErroMessage("闁喚顔堢�鍡欑垳瀹歌尙绮￠柌宥嗘煀鐠佸墽鐤嗘禍鍡礉鐠囬鏁ら弬鎵畱鐎靛棛鐖滈惂璇茬秿");
		return USERPROFILE;
	}
	else if(session.get("resetpassusercategory").equals("parent")){			
		Parent parent=parentDao.load((int)session.get("resetpassuserid"));
		if(parent==null){
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(!(session.get("resetpassuserhashcode").equals(parent.getRandomCode()))){	
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("闁喚顔堟稉宥呭帒鐠佹悂娼▔鏇熸惙娴ｏ拷");
			return USERPROFILE;
		}
		parent.setPassword(encrypt);
		parentDao.update(parent);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�	
		session.remove("resetpassusercategory");
		session.remove("resetpassuserid");
		session.remove("resetpassuserhashcode");
		setErroMessage("闁喚顔堢�鍡欑垳瀹歌尙绮￠柌宥嗘煀鐠佸墽鐤嗘禍鍡礉鐠囬鏁ら弬鎵畱鐎靛棛鐖滈惂璇茬秿");
		return USERPROFILE;
	}
	else if(session.get("resetpassusercategory").equals("teacher")){			
		Teacher teacher=teacherDao.load((int)session.get("resetpassuserid"));
		if(teacher==null){
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("濮濄倝鍋栫粻杈ㄧ梾閺堝鏁為崘宀嬬礉鐠囧嘲鍘涘▔銊ュ斀");
			return USERPROFILE;
		}
		if(!(session.get("resetpassuserhashcode").equals(teacher.getRandomCode()))){	
			session.remove("resetpassusercategory");
			session.remove("resetpassuserid");
			session.remove("resetpassuserhashcode");
			setErroMessage("闁喚顔堟稉宥呭帒鐠佹悂娼▔鏇熸惙娴ｏ拷");
			return USERPROFILE;
		}
		teacher.setPassword(encrypt);
		teacherDao.update(teacher);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚瑰▔銊╂晸閺傘倖瀚归柨鐔告灮閹烽攱浼�	
		session.remove("resetpassusercategory");
		session.remove("resetpassuserid");
		session.remove("resetpassuserhashcode");
		setErroMessage("闁喚顔堢�鍡欑垳瀹歌尙绮￠柌宥嗘煀鐠佸墽鐤嗘禍鍡礉鐠囬鏁ら弬鎵畱鐎靛棛鐖滈惂璇茬秿");
		return USERPROFILE;
		}
	else {
		session.remove("resetpassusercategory");
		session.remove("resetpassuserid");
		session.remove("resetpassuserhashcode");
		setErroMessage("闁喚顔堢�鍡欑垳鐠佸墽鐤嗘径杈Е閿涘矁顕崘宥嗩偧閸欐垿锟介幍鎯ф礀鐎靛棛鐖滅拠閿嬬湴");
		return USERPROFILE;
	}

}
	// 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归崨锟�	
private Student student = new Student();
	// 绾噣鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	private String repassword;
        //閻ц缍嶉懓鍛閸拷
	private String category;
	  
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrePage() {
		return prePage;
	}
	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}
	@Override
	public Student getModel() {
		return student;
	}
	public String getErroMessage() {
		return erroMessage;
	}
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public String getImageMessage() {
		return imageMessage;
	}
	public void setImageMessage(String imageMessage) {
		this.imageMessage = imageMessage;
	}
	public OperateImage getImage() {
		return image;
	}
	public void setImage(OperateImage image) {
		this.image = image;
	}
	public String getCuttingImageName() {
		return cuttingImageName;
	}
	public void setCuttingImageName(String cuttingImageName) {
		this.cuttingImageName = cuttingImageName;
	}
	public int getCuttingImageX() {
		return cuttingImageX;
	}
	public void setCuttingImageX(int cuttingImageX) {
		this.cuttingImageX = cuttingImageX;
	}
	public int getCuttingImageY() {
		return cuttingImageY;
	}
	public void setCuttingImageY(int cuttingImageY) {
		this.cuttingImageY = cuttingImageY;
	}
	public int getCuttingImageWidth() {
		return cuttingImageWidth;
	}
	public void setCuttingImageWidth(int cuttingImageWidth) {
		this.cuttingImageWidth = cuttingImageWidth;
	}
	public int getCuttingImageHeight() {
		return cuttingImageHeight;
	}
	public void setCuttingImageHeight(int cuttingImageHeight) {
		this.cuttingImageHeight = cuttingImageHeight;
	}
	
	
}
