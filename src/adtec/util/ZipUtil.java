package adtec.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * ZipUtil
 * </p>
 * <p>
 * zip包解压
 * </p>
 * 
 * @version 1.0 2013-3-19 yangzhj
 *          <p>
 *          修改者姓名 修改内容说明
 *          </p>
 * @see 参考类1
 */
public class ZipUtil {
	private Logger log = Logger.getLogger(ZipUtil.class);
	public static final String log001 = "对象关闭异常";

	// 全局分隔符
	private String fileSep = File.separator;

	/**
	 * 
	 * 解压缩zipFile
	 * 
	 * @param file
	 *            要解压的zip文件对象
	 * 
	 * @param outputDir
	 *            要解压到某个指定的目录下
	 * 
	 * @throws IOException
	 */

	public File unzip(String zipFileName, String outputDir) {

		File fileName = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipInputStream zis = null;

		try {

			// 创建输入字节流

			fis = new FileInputStream(zipFileName);

			// 根据输入字节流创建输入字符流

			BufferedInputStream bis = new BufferedInputStream(fis);

			// 根据字符流，创建ZIP文件输入流

			zis = new ZipInputStream(bis);

			// zip文件条目，表示zip文件

			ZipEntry entry;

			// 循环读取文件条目，只要不为空，就进行处理

			fileName = this.getRealFileName(outputDir);

			while ((entry = zis.getNextEntry()) != null) {

				int count;

				byte date[] = new byte[2048];

				// 如果条目是文件目录，则继续执行
				if (entry.isDirectory()) {

					continue;

				} else {

					String[] dirs = entry.getName().split("/", entry.getName().length());

					File dirFile = new File(fileName, dirs[dirs.length - 1]);
					try {

						fos = new FileOutputStream(dirFile);
						bos = new BufferedOutputStream(fos);

						while ((count = zis.read(date)) != -1) {

							bos.write(date, 0, count);

						}

						bos.flush();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw e;
					} finally {
						if (fos != null) {
							fos.close();
						}
						if (bos != null) {
							bos.close();
						}
					}

				}

			}

		} catch (Exception e) {

			log.error("unzip exception");

		} finally {

			try {
				if (fis != null) {
					fis.close();
				}

				if (zis != null) {
					zis.close();
				}
			} catch (IOException e) {
				log.debug("IOException");
			}
		}
		return fileName;
	}

	private File getRealFileName(String outputDir) {

		// 创建文件对象
		int rmFileInt = 0;
		String rmFileStr = "";
		Random random = new Random();
		rmFileInt = random.nextInt(1000000);// 取6位随机数
		rmFileInt = Math.abs(rmFileInt);
		rmFileStr = String.format("%06d", rmFileInt);

		String rmFilePath = outputDir + fileSep + rmFileStr;
		File rmFile = new File(rmFilePath);

		while (true) {
			if (rmFile.exists()) {
				rmFileInt = random.nextInt(1000000);// 取6位随机数
				rmFileInt = Math.abs(rmFileInt);
				rmFileStr = String.format("%06d", rmFileInt);
				rmFile = new File(outputDir + fileSep + rmFileStr);
			} else {
				// rmFile = this.getFileFolder(rmFile);
				boolean flag = rmFile.mkdirs();
				if (!flag) {
					log.error("error");
				}
				break;
			}
		}
		return rmFile;
	}

	/**
	 * 生成附件、解压文件目录公共方法
	 * 
	 * @param dir
	 * @return
	 */
	public File getFileFolder(File rmFile) {

		// 生成附件目录，放开每一级目录的权限
		boolean flag = rmFile.mkdir();
		if (flag) {
			rmFile.setExecutable(true, false);
			rmFile.setReadable(true, false);
			rmFile.setWritable(true, false);
		} else {
			log.error("error");
		}

		return rmFile;
	}
}
