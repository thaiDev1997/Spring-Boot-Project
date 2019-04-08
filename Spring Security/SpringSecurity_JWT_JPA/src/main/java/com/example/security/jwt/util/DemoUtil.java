package com.example.security.jwt.util;

import com.example.security.jwt.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DemoUtil {

    @Autowired
    private User userBean;

    public ResponseEntity<?> saveObjectDataToFile() throws Exception {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("io.txt"));
             ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("io.txt"));
             BufferedReader  bufferedReader = new BufferedReader(new FileReader("io.txt"))){
            StringBuilder msg = new StringBuilder();
            // write Object
            objectOutputStream.writeObject(this.userBean);
            msg.append("<h3 style='color: green'>Saving object data is successfully!</h3>");
            // read Object
            Object ioObj = objectInputStream.readObject();
            msg.append("<h4>File's data : ");
            String dataASCII = "";
            String dataByte = "";
            while (bufferedReader.read() != -1) {
                int byteData = bufferedReader.read();
                dataASCII += (char) byteData + "(" + byteData + ")";
                dataByte += byteData;
            }
            msg.append("<br/>- ASCII : " + dataASCII);
            msg.append("<br/>- Byte : " + dataByte);
            msg.append("</h4>");
            if (ioObj.getClass() == this.userBean.getClass()) {
                User ioUser = (User) ioObj;
                msg.append("\n<h4 style='color: orange'>You have just save User data : " + ioUser.toString() + "</h4>");
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public ResponseEntity<?> writeUserDataWithJsonType() throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonUser = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.userBean);
            return new ResponseEntity<>(jsonUser, HttpStatus.OK);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
