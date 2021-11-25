package miyu.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	
	private int copyFile(File originFile, File newFile) {
		try {
			FileInputStream fis = new FileInputStream(originFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			
			byte[] buf = new byte[1024]; //한번에 가져올 데이터 양을 설정
			
			int readData;
			while((readData = fis.read(buf)) > 0) {
				fos.write(buf, 0, readData);
			}
			
			fis.close();
			fos.close();
			
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
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
	
	public int tmpMemoryImgCopy(List<String> list) {
		File temp = getTempFolder();
		for(String imgRoot : list) {
			String name = imgRoot.substring(imgRoot.lastIndexOf("/")+1); //파일 이름 가져오기
			
			File target = new File(SERVER_PATH + name); //실제 파일 위치
			if(target.exists()) {
				File tempFile = new File(temp.getPath()+"/"+name); //임시파일 생성
				int check = copyFile(target, tempFile); //파일 복사
				
				if(check == -1) return check; //복사시 오류가 뜨면 오류반환
			}
		}
		return 1;
	}
	
}
