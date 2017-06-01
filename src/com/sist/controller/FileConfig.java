package com.sist.controller;

// com.sist.model.MainController를 완성해서 HandlerMapping에게 넘겨줘야 한다.
import java.io.*;

public class FileConfig {
	public static void main(String[] args) {
		FileConfig fc = new FileConfig();
		fc.componentScan("com.sist.model");
	}

	public void componentScan(String pack) {
		try {
			String path="C:/webDev/webStudy/MVCAnnotationProject/src/";
			path=path+pack.replace(".", "/");
			File dir = new File(path); //패키지명만 가지고 그 안에 있는 모든 java 파일 가져와서 이름만 짤라내서 패키지명에 붙여서 클래스 forName으로 쓸 도구만들자
			File[] files = dir.listFiles();
			for(File f:files){
				//System.out.println(f.getName());
				String str = pack+"."+f.getName().substring(0, f.getName().lastIndexOf("."));
				System.out.println(str);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
