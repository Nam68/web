package miyu.app;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileManagement {
	
	private static final String SERVER_PATH = "/epoche02/tomcat/webapps/ROOT/img/memory/";
	private static final String DB_PATH = "http://myyk.co.kr/img/memory/";
	
	private String getUuid() {
		return UUID.randomUUID().toString();
	}
	
	private String getExtension(MultipartFile file) {
		String name = file.getOriginalFilename();
		String extension = name.substring(name.lastIndexOf('.'));
		return extension; //.jpg처럼 닷(.)을 포함시켜서 반환함
	}
	
	private File getRootFolder() {
		File file = new File(SERVER_PATH);
		if(!file.exists()) file.mkdirs();
		return file;
	}
	
	private File getTempFolder() {
		File root = getRootFolder();
		File temp = new File(root.getPath()+"/temp");
		if(!temp.exists()) temp.mkdirs();
		return temp;
	}
	
	private void copyFile(MultipartFile originFile, File newFile) {
		try {
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(originFile.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	
	/* 아래부터는 public 메서드 */
	
	public void tempFileAdd(MultipartFile[] files) {
		for(MultipartFile originFile : files) {
			File newFile = new File(getTempFolder().getPath()+"/"+getUuid()+getExtension(originFile));
			copyFile(originFile, newFile);
		}
	}
	
	public ArrayList<String> getTempDbPath() {
		ArrayList<String> list = new ArrayList<String>();
		File[] tempFiles = getTempFolder().listFiles();
		
		for(File file : tempFiles) {
			if(file.isFile()) list.add(DB_PATH + file.getName());
		}
		return list;
	}
	
}
