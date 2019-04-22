package org.yyt.mybatisex.test;

import java.io.File;

public class mythistest {

	public static void main(String[] args) {
		File file=new File("src/main/resources/mybatis-config.xml");
		if(file.exists()) {
			System.out.println("文件存在");
		}else {
			System.out.println("文件不存在");
		}
	}

}
