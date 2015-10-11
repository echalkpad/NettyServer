package com.game.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


//http://wiki.jikexueyuan.com/project/mybatis-in-action/code-generation-tool.html
public class mbgGenerator {
    public static void main(String[] args) {
    	generatorStart(null);
    }
    
    public static void generatorStart(String dir_)
    {
    	String dir = "src/com/game/mbgConfig/";
    	if(dir_ != null){
    		dir = dir_;
    	}
        File f = new File(dir);
        File[] files = f.listFiles();
        for(int i=0; i<files.length; i++){
        	if(files[i].isFile()){
        		int index = files[i].toString().lastIndexOf('\\');
        		String file = "../mbgConfig" + files[i].toString().substring(index).replace('\\', '/');
        		generatorFile(file);
        		System.out.println(file);
        	}
        }
    }
    
    public static void generatorFile(String cfg)
    {
    	List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = cfg;
        File configFile = new File(mbgGenerator.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}