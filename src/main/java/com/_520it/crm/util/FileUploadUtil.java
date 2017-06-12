package com._520it.crm.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

public class FileUploadUtil {
	public static final String suffix = "_small";

	public static String uploadFile(File file, String fileName)
			throws Exception {
		String uuid = UUID.randomUUID().toString();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		fileName = uuid + fileType;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath(
				"/images/pet");
		File targetFile = new File(path, fileName);
		FileUtils.copyFile(file, targetFile);

		// 缩略图是在文件后面加上_small
		String smallImg = uuid + suffix + fileType;
		File smallTargetFile = new File(path, smallImg);
		// 生成缩略图
		Thumbnails.of(targetFile).scale(0.4f).toFile(smallTargetFile);
		return "/upload/" + fileName;
	}

	/**
	 * 删除文件
	 * @param
	 */
	public static void deleteFile(String pic) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path=request.getSession().getServletContext().getRealPath("/")+pic;
		File file=new File(path);
		if(file.exists()) file.delete();
		
		path=request.getSession().getServletContext().getRealPath("/")+ pic.substring(0,pic.indexOf("."))+FileUploadUtil.suffix+pic.substring(pic.indexOf("."));
		file=new File(path);
		if(file.exists()) file.delete();
	}
}
