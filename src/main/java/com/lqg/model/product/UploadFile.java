package com.lqg.model.product;

import java.io.Serializable;

/**
 * �ϴ��ļ�����
 * @author Li qiuguo
 */
public class UploadFile implements Serializable{
	private static final long serialVersionUID = 1L;
	// ���
	private Integer id;
	// �ļ�·��
	private String path;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
