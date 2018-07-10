package com.blog.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author shibo
 */
public class MyBatisGenerator {
    public void generator() throws Exception {
        List<String> warnings = new ArrayList();

        boolean overwrite = true;
//String classPath = MyBatisGenerator.class.getClassLoader().getResource("").getPath();
        //File configFile = new File(classPath + "conf/generatorConfig.xml");
        //替换为
        File configFile = Resources.getResourceAsFile("config/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration config = cp.parseConfiguration(configFile);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(config, callback, warnings);

        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {

            MyBatisGenerator mg = new MyBatisGenerator();

            mg.generator();

            System.out.println("generat success.");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
