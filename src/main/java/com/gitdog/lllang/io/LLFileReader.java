package com.gitdog.lllang.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class LLFileReader {
	
	public LLFileReader(String file){
		setFile(file);
	}
	
	public String getFileContent(){
		StringBuffer tempResult = new StringBuffer();
		while(this.next()){
			tempResult.append(this.getChar());
		}
		return tempResult + "";
	}
	
	public static void setFile(String file){
		try {
			Reader reader = new FileReader(file);
			br = new BufferedReader(reader);
		} catch (FileNotFoundException e) {
			System.out.println("��ȡ�ļ�" + file + "����");
		}
		
	}
	
	public char getChar(){
		int tempChar;
/*		int tempchar;
		while((tempchar = br.read()) != -1){
			// ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
            // ������������ַ�ֿ���ʾʱ���ỻ�����С�
            // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
            if (((char) tempchar) != '\r') {
                System.out.print((char) tempchar);
            }
		}*/
		
		try {
			if((tempChar = br.read()) != -1){
				if((char)tempChar != '\r'){
					return (char) tempChar;
				}
			}else{
				this.hasNext = false;
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			this.hasNext = false;
			System.out.println("��ȡ���������ַ��Ƿ���ȷ");
		}
		return 0;
		
		
	}
	
	public boolean next(){
		return this.hasNext;
	}
	
	
	public static String getResouce(String path){
		return get(Thread.currentThread().getContextClassLoader().getResource("").getPath() + path);
	}
	
	public static String get(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            
            try {
                InputStreamReader input = new InputStreamReader(stream,
                    Charset.defaultCharset());
                Reader reader = new BufferedReader(input);
                
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[8192];
                int read;
                
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }
                
                // HACK: The parser expects every statement to end in a newline,
                // even the very last one, so we'll just tack one on here in
                // case the file doesn't have one.
                builder.append("\n");
                
                return builder.toString();
            } finally {
                stream.close();
            }
        } catch (IOException ex) {
            return null;
        }
    }
	
	private static BufferedReader br;
	private boolean hasNext = true;
}
