package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.UploadFile;
import com.lqg.service.UploadFileService;

@Service(value = "uploadFileService")
@Transactional
public class UploadFileServiceImpl extends DaoSupportImpl<UploadFile> implements UploadFileService{

}
