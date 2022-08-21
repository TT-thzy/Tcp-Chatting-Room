package com.qq.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
public class FileUtil {
	 static File file=new File("resources\\账号和密码.txt");
	    static {
	        if (!file.exists()){
	            try {
	                file.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	    }

	    public static void save(String str) {

	        BufferedWriter writer=null;
	        try {
	            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
	            writer.write(str);
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static Set<String> get(){
	        Set<String> set=new HashSet<>();
	        BufferedReader reader=null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String s=null;
	            while ((s=reader.readLine())!=null){
	                set.add(s);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            try {
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return set;
	    }
	    


}
