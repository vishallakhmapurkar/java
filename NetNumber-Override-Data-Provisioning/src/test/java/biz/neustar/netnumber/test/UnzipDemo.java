package biz.neustar.netnumber.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipDemo {
	 public static void main(String[] args) {
	        String zipName = "data.zip";
	 
	        try {
	            FileInputStream fis = new FileInputStream(zipName);
	            ZipInputStream zis = new ZipInputStream(
	                    new BufferedInputStream(fis));
	            ZipEntry entry;
	 
	            //
	            // Read each entry from the ZipInputStream until no
	            // more entry found indicated by a null return value
	            // of the getNextEntry() method.
	            //
	            while ((entry = zis.getNextEntry()) != null) {
	                System.out.println("Unzipping: " + entry.getName());
	 
	                int size;
	                byte[] buffer = new byte[2048];
	 
	                FileOutputStream fos =
	                        new FileOutputStream(entry.getName());
	                BufferedOutputStream bos =
	                        new BufferedOutputStream(fos, buffer.length);
	 
	                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
	                    bos.write(buffer, 0, size);
	                }
	                bos.flush();
	                bos.close();
	            }
	 
	            zis.close();
	            fis.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
