package com.citiustech.externalizeconfigdemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.externalizeconfigdemo.config.MyConfig;
import com.citiustech.externalizeconfigdemo.model.DatabaseConfig;

@RestController
public class HomeController {
	
	@Autowired
	private Environment env;
	
	@Value("${app.version}")
	private String appVersion;
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.author}")
	private String appAauthor;
	
	@Value("#{${database.config}}")
	private Map<String, String> databaseConfig;
	
	@Value("${server.ips}")
	private List<String> serverIPs;
	
	@Autowired
	private DatabaseConfig config;
	
	@Autowired
	private MyConfig myConfig;
	
	@GetMapping("/properties")
	public String getProperties(){
		return String.format("App Version => %s | App Name => %s | App Author Name => %s%n", appVersion,appName,appAauthor);
	}
	
	@GetMapping("/dbconfig")
	public Map<String, String> getDatabaseConfig(){
		return databaseConfig;
	}
	
	@GetMapping("/server-ips")
	public List<String> getServerIps(){
		return serverIPs;
	}
	
	@GetMapping("/database-config")
	public String getDatabaseConfiguration(){
		return config.toString();
	}
	
	@GetMapping("/myconfig")
	public String getMyConfig(){
		return myConfig.toString();
	}
	
	@GetMapping("/env-variables")
	public String getEnvironmentVariables(){
		Map<String, String> map = System.getenv();
		map.forEach((k, v) -> System.out.println(k + "=" + env.getProperty(k)));
		return "Map";
	}
}
