/**********************************************
Workshop 5
Course:     JAC444 - Fall2020
Last Name:  Xie
First Name: Yushi
ID:         142358167
Section:    NCC
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:       28-Oct-2020
**********************************************/


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccessFileHelper {
    
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RandomAccessFileHelper() {
    }
    
    public RandomAccessFileHelper(String fileName) {
        this.fileName = fileName;
    }

    public String readFile(int position, int size) throws IOException {
        RandomAccessFile file = new RandomAccessFile(getFileName(), "r");  
        file.seek(position);  
        byte[] bytes = new byte[size];  
        file.read(bytes);  
        file.close(); 
        String s = new String(bytes, StandardCharsets.UTF_8); 
        return s; 
    }

    public void appendFile(String data) throws IOException {
        RandomAccessFile raFile = new RandomAccessFile(getFileName(), "rw");
		raFile.seek(raFile.length());
		raFile.write(data.getBytes());
		raFile.close();
    }

    public void writeFile(String data, int position) throws IOException {
        RandomAccessFile file = new RandomAccessFile(getFileName(), "rw");
		file.seek(position);
		file.write(data.getBytes());
		file.close();
    }
    
    public int getFilePointer(String data) throws IOException {
        RandomAccessFile file = new RandomAccessFile(getFileName(), "r");
        file.seek(0);
        int r = 0;
        long current = 0;
        while (current < file.length()) {
            String result = file.readLine();
            if (result.equals(data))
            {
                r = (int) file.getFilePointer() + 81;
            }
            current = file.getFilePointer();
        }
        file.close();
        return r;
    }

    public int getLength() throws IOException {
        RandomAccessFile file = new RandomAccessFile(getFileName(), "r");
        return (int) file.length();
    }
}
