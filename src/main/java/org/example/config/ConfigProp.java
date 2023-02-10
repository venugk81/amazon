package org.example.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProp {
    private ConfigProp(){}
    public static Properties prop;

    public static Properties getConfig(){
        if(prop==null){
            try{
                prop=new Properties();
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                        "/properties/application.properties");
                prop.load(fis);
            }catch (Exception oExp){
                oExp.printStackTrace();
            }
        }
        return prop;
    }

    public String getProperty(String propKey){
        return (String) prop.get(propKey);
    }



}
