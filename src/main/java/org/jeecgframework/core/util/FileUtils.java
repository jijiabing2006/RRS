package org.jeecgframework.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 文件操作工具类
 * 
 * @author 张代浩
 * 
 */
public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class);

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i + 1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 获取文件名称[不含后缀名]
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
	}

	/**
	 * 获取文件名称[不含后缀名] 不去掉文件目录的空格
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix2(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}

	/**
	 * 文件复制 方法摘要：这里一句话描述方法的用途
	 * 
	 * @param
	 * @return void
	 */
	public static void copyFile(String inputFile, String outputFile)
			throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		byte[] buf = new byte[10240];
		try {
			while ((temp = fis.read(buf)) != -1) {
				fos.write(buf, 0, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断文件是否为图片<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isPicture(String filename) {
		// 文件名称为空的场合
		if (oConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		// String tmpName = getExtend(filename);
		String tmpName = filename;
		// 声明图片后缀名数组
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
				{ "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
				{ "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			// 判断单个类型文件的场合
			if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断文件是否为DWG<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isDwg(String filename) {
		// 文件名称为空的场合
		if (oConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		String tmpName = getExtend(filename);
		// 声明图片后缀名数组
		if (tmpName.equals("dwg")) {
			return true;
		}
		return false;
	}

	/**
	 * 删除指定的文件
	 * 
	 * @param strFileName
	 *            指定绝对路径的文件名
	 * @return 如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			org.jeecgframework.core.util.LogUtil.info("错误: " + strFileName
					+ "不存在!");
			return false;
		}

		org.jeecgframework.core.util.LogUtil.info("--------成功删除文件---------"
				+ strFileName);
		return fileDelete.delete();
	}

	/**
	 * 递归删除文件夹(删除文件夹,包括path里的最后一个文件夹)
	 * 
	 * @param path
	 * @return true：删除文件夹成功 false：失败
	 * @throws Exception
	 */
	public static boolean delDir(String path) throws Exception {
		boolean bIsSuccess = false;
		File dir = new File(path);
		if (dir.exists()) {
			File[] tmp = dir.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					delDir(path + "/" + tmp[i].getName());
				} else {
					tmp[i].delete();
				}
			}
			bIsSuccess = dir.delete();
		} else {
			System.out.println(path + "不存在......");
			bIsSuccess = false;
		}
		return bIsSuccess;
	}

	/**
	 * 目录不存在，新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static boolean newFolder(String folderPath) {
		folderPath=StringUtils.replace(folderPath,"\\", "/");
		String splitStr = "";
		if (folderPath.indexOf("/") != -1)
			splitStr = "/";
		else
			splitStr = "\\";
		StringTokenizer st = new StringTokenizer(folderPath, splitStr);
		String folder = st.nextToken() + splitStr;
		String subFolder = folder;
		while (st.hasMoreTokens()) {
			folder = st.nextToken() + splitStr;
			subFolder += folder;
			File myFilePath = new File(subFolder);
			if (!myFilePath.exists())
				myFilePath.mkdir();
		}
		return true;
	}

	public static void cleanDirectory(String file) throws IOException {
		// TODO Auto-generated method stub
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(new File(file));
		} catch (IOException e) {
			System.out.println("清空目录操作出错");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFileName
	 *            源文件完整路径
	 * @param destDirName
	 *            目的目录完整路径
	 * @return 文件移动成功返回true，否则返回false
	 */
	public static boolean moveFile(String srcFileName, String destDirName) {

		File srcFile = new File(srcFileName);
		if (!srcFile.exists() || !srcFile.isFile())
			return false;

		File destDir = new File(destDirName);
		if (!destDir.exists())
			destDir.mkdirs();

		return srcFile.renameTo(new File(destDirName + File.separator
				+ srcFile.getName()));
	}

	/**
	 * 移动目录
	 * 
	 * @param srcDirName
	 *            源目录完整路径
	 * @param destDirName
	 *            目的目录完整路径
	 * @return 目录移动成功返回true，否则返回false
	 */
	public static boolean moveDirectory(String srcDirName, String destDirName) {

		File srcDir = new File(srcDirName);
		if (!srcDir.exists() || !srcDir.isDirectory())
			return false;

		File destDir = new File(destDirName);
		if (!destDir.exists())
			destDir.mkdirs();

		/**
		 * 如果是文件则移动，否则递归移动文件夹。删除最终的空源文件夹 注意移动文件夹时保持文件夹的树状结构
		 */
		File[] sourceFiles = srcDir.listFiles();
		for (File sourceFile : sourceFiles) {
			if (sourceFile.isFile())
				moveFile(sourceFile.getAbsolutePath(),
						destDir.getAbsolutePath());
			else if (sourceFile.isDirectory())
				moveDirectory(
						sourceFile.getAbsolutePath(),
						destDir.getAbsolutePath() + File.separator
								+ sourceFile.getName());
			else
				;
		}
		return srcDir.delete();
	}
}
