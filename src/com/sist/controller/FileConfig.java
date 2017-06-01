package com.sist.controller;

// com.sist.model.MainController�� �ϼ��ؼ� HandlerMapping���� �Ѱ���� �Ѵ�.
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
			File dir = new File(path); //��Ű���� ������ �� �ȿ� �ִ� ��� java ���� �����ͼ� �̸��� ©�󳻼� ��Ű���� �ٿ��� Ŭ���� forName���� �� ����������
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
