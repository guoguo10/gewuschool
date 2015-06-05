package service;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.lqg.dao.BookItemDao;
import com.lqg.memcache.MemCachedTool;
import com.lqg.model.Admin;
import com.lqg.model.BookItem;
import com.lqg.model.PageModel;
import com.lqg.service.AdminService;
/**
 * junit鐨勮缃� * 
 * @author QiuguoLi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:applicationContext-common.xml")
 
public class ServiceTest {   
	@Autowired
	private AdminService adminService;	
	@Autowired
	private SessionFactory sessionFactory;
	//@Autowired
	//private MemCachedTool memcachedCache;
	private Session session;
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		//daoSupport=new DaoSupport<BookItem>(); 
		//daoSupport=(DaoSupport<BookItem>)factory.getBean("bookItemDao");			
		session = SessionFactoryUtils.getSession(sessionFactory, true);
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	@After
	public void tearDown() throws Exception {
		adminService=null;
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.releaseSession(s, sessionFactory);
		//SessionFactoryUtils.closeSessionIfNecessary(s, sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testFindIntInt() {
		//Map<String, String> orderby = new HashMap<String, String>(1);
		//orderby.put("createTime", "desc");		
		String username="admin";
		String password="admin";
		Admin admin=new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
	    adminService.saveOrUpdate(admin);
	    List<Admin> adminList=adminService.find(1,8).getList();
		for(Admin admin1: adminList){
			System.out.println(admin1.getPassword()+" "+admin1.getId()+"\n");
		}
    	
		 } 		
	
}



