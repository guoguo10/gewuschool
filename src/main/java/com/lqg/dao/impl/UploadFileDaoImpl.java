package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.UploadFileDao;
import com.lqg.model.UploadFile;
@Repository("uploadFileDao")
public class UploadFileDaoImpl extends DaoSupportImpl<UploadFile> implements UploadFileDao {

}
