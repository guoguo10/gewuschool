package com.lqg.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class OperateImage {
	  //源文件路径
    private String srcpath;  
    // 目标路径
    private String subpath;  
    // 图片起点
    private int x;  
    private int y;  
    // 图片剪裁大小
    private int width;  
    private int height; 
    //设置源路径
    public void setSrcpath(String srcpath){
    	this.srcpath=srcpath;
    }
    public String getSrcpath(){
    	return this.srcpath;
    }
    //设置目标路径
    public void setSubpath(String subpath){
    	this.subpath=subpath;
    }
    public String getSubpath(){
    	return this.subpath;
    }
    //设置起点x
    public void setX(int x){
    	this.x=x;
    }
    public int getX(){
    	return this.x;
    }
    //设置起点y
    public void setY(int y){
    	this.y=y;
    }
    public int getY(){
    	return this.y;
    }
    //设置高度
    public void setHeight(int height){
    	this.height=height;
    }
    public int getHeight(){
    	return this.height;
    }
    //设置宽度
    public void setWidth(int width){
    	this.width=width;
    }
    public int getWidth(){
    	return this.width;
    }
    public OperateImage() {  
    }  
  //截切图片的方法
    public void cut() throws IOException {  
        FileInputStream is = null;  
        ImageInputStream iis = null;  
        try {  
            // 开启文件流
            is = new FileInputStream(srcpath);  
            //设置imageIo对象，用来操作图像
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");  
            ImageReader reader = it.next();  
            // 包装文件输入流为图像输入流 
            iis = ImageIO.createImageInputStream(is);  
            //reader工具设置输入流            
            reader.setInput(iis, true);  
          //设置默认参数
            ImageReadParam param = reader.getDefaultReadParam();  
          //设置截切的位置
            Rectangle rect = new Rectangle(getX(), getY(), getWidth(), getHeight());  
          //设置reader的参数为截切的范围
            param.setSourceRegion(rect);  
           //读取为缓冲图片
            BufferedImage bi = reader.read(0, param);  
            // 写入目标文件处
            ImageIO.write(bi, "jpg", new File(subpath));  
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }  
  
    } 

}
