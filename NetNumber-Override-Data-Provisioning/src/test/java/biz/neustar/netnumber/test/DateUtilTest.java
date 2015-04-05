package biz.neustar.netnumber.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import biz.neustar.netnumber.util.DateUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class DateUtilTest {
	public static void main(String[] args) {
		System.out.println(DateUtils.getCurrentDateAsString());

		File f = new File("c:\\text");
       System.out.println(isFileReadyToProcess(f));
		
	}
	private static boolean isFileReadyToProcess(File bootStrapFile){
		boolean result = false;
		try {
			if(bootStrapFile !=null){
				if(bootStrapFile.exists()){
					if(bootStrapFile.delete()){
						if(bootStrapFile.createNewFile()){
							if(bootStrapFile.canExecute() && bootStrapFile.canRead() && bootStrapFile.canWrite()){
								result = true;
							}
						}
					}
					
				}else if(bootStrapFile.createNewFile()){
					if(bootStrapFile.canExecute() && bootStrapFile.canRead() && bootStrapFile.canWrite()){
						result = true;
					}
				}	
			}
			
		} catch (IOException e) {
			result = false;
		}
		return result;
		
	}
}
