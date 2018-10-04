package com.xsl;

import static org.junit.Assert.assertTrue;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.XslDatetime;
import pojo.XslTask;
import pojo.XslTaskExample;
import service.JsonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.HashSet;

import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
//    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
//
//    @Test
//    public void testJedisSingle() {
//        //创建一个jedis的对象。
//        Jedis jedis = new Jedis("39.108.211.37", 6379);
//        //调用jedis对象的方法，方法名称和redis的命令一致。
//        jedis.set("key1", "jedis test");
//        String string = jedis.get("key1");
//        System.out.println(string);
//        //关闭jedis。
//        jedis.close();
//    }
//    /**
//     * 使用连接池
//     */
//    @Test
//    public void testJedisPool() {
//        //创建jedis连接池
//        JedisPool pool = new JedisPool("192.168.25.153", 6379);
//        //从连接池中获得Jedis对象
//        Jedis jedis = pool.getResource();
//        String string = jedis.get("key1");
//        System.out.println(string);
//        //关闭jedis对象
//        jedis.close();
//        pool.close();
//    }
public static void main(String[] args) throws Exception {
    FTPClient ftpClient = new FTPClient();
    ftpClient.connect("47.93.200.190");
    ftpClient.login("myftpuse", "myftpuse");
    FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ASUS\\Desktop\\test.png"));
    ftpClient.changeWorkingDirectory("/home/ftp/images");
    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    ftpClient.storeFile("test1.png", inputStream);
    inputStream.close();

    ftpClient.logout();
}

}
