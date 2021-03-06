package com.aiyi.server.manager.nginx.common;

import java.io.FileOutputStream;
import java.io.IOException;

import com.aiyi.server.manager.nginx.conf.Configer;
import com.aiyi.server.manager.nginx.exception.NginxServiceManagerException;
import com.aiyi.server.manager.nginx.manager.NginxManager;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;

/**
 * Nginx 配置工具类
 * @Project : nginx-gui
 * @Program Name : com.aiyi.server.manager.nginx.common.NginxUtils.java
 * @Description : 
 * @Author : 郭胜凯
 * @Creation Date : 2018年2月22日 下午6:20:18
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 *                      郭胜凯 2018年2月22日 create
 */
public class NginxUtils {

	/**
	 * 读配置
	 * @Description : 
	 * @return : NgxConfig
	 * @Creation Date : 2018年2月22日 下午6:20:29
	 * @Author : 郭胜凯
	 */
	public static NgxConfig read() {
		try {
			return NgxConfig.read(Configer.getNginxConfPath());
		} catch (IOException e) {
			throw new NginxServiceManagerException("读取Nginx配置文件失败");
		}
	}
	
	/**
	 * 写配置
	 * @Description : 
	 * @return : void
	 * @Creation Date : 2018年2月23日 下午7:32:26
	 * @Author : 郭胜凯
	 */
	public static void save(NgxConfig conf) {
		try(FileOutputStream out = new FileOutputStream(Configer.getNginxConfPath())) {
			out.write(toString(conf).getBytes("UTF-8"));
			out.flush();
		} catch (Exception e) {
			throw new NginxServiceManagerException("Nginx配置文件写入失败");
		}
	}
	
	
	/**
	 * 配置到文本
	 * @Description : 
	 * @return : String
	 * @Creation Date : 2018年2月23日 下午7:32:38
	 * @Author : 郭胜凯
	 */
	public static String toString(NgxConfig conf) {
		if (null == conf) {
			throw new NginxServiceManagerException("不能写入空配置");
		}
		NgxDumper dumper = new NgxDumper(conf);
		return dumper.dump();
	}
	
	/**
	 * 写配置
	 * @Description : 
	 * @return : void
	 * @Creation Date : 2018年2月23日 下午7:33:44
	 * @Author : 郭胜凯
	 */
	public static void save(String conf) {
		try(FileOutputStream out = new FileOutputStream(Configer.getNginxConfPath())) {
			out.write(conf.getBytes("UTF-8"));
			out.flush();
		} catch (Exception e) {
			throw new NginxServiceManagerException("Nginx配置文件写入失败");
		}
	}
	
	/**
	 * 校验Nginx配置文件
	 * @Description : 
	 * @return : void
	 * @Creation Date : 2018年2月26日 上午9:59:18
	 * @Author : 郭胜凯
	 */
	public static void check() {
		// TODO Auto-generated method stub
		
	}
}
