package util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * @version V1.0
 * @ClassName:FtpUtil
 * @Description:悬赏令文件上传下载工具
 * @author:何林鸿
 * @date: 2018年7月29日
 */
public class FtpUtil {

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.connect(host, port);
            // 登录
            ftp.login(username, password);
			
			/*调用FTPClient.enterLocalPassiveMode();
			这个方法的意思就是每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据。
			为什么要这样做呢，因为ftp server可能每次开启不同的端口来传输数据，
			但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞。*/
            ftp.enterLocalPassiveMode();
            //获取连接返回值（200-300）
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                //中断连接
                ftp.disconnect();
                return result;
            }

            //切换到上传目录（if里面是先执行后判断，要留心）
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                //如果发现该目录不存在则创建一个目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    //每循环一次判断一次是否存在目录
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        //不存在则创建目录
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            //这里是创建好目录以后切换到该路径下，然后继续循环
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }

            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }

            input.close();
            //登出
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param host       FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.connect(host, port);
            // 登录
            ftp.login(username, password);
            // 得到返回值
            reply = ftp.getReplyCode();
            //判断是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }

            //通知server
            ftp.enterLocalPassiveMode();
            //变更路径
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            //获得文件列表
            FTPFile[] fs = ftp.listFiles();

            //遍历文件
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream out = new FileOutputStream(localFile);
                    //下载文件
                    ftp.retrieveFile(ff.getName(), out);
                    out.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

}
