package information;
import java.io.File;

import javax.naming.directory.DirContext;
public class readFile {
	public boolean FileName(String fileName){
		boolean isExistence=false;
		String dirName="src/地面资料";
		File thisDir=new File(dirName);
		if(thisDir.isDirectory()){
			String Dir[]=thisDir.list();
			for(int i=0;i<Dir.length;i++){
				if(Dir[i].equals(fileName)){
					isExistence=true;
				}else{
					isExistence=false;
				}
				
			}
		}
		return isExistence;
	}
}
