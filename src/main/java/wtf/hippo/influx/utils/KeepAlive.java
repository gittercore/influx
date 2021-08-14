package wtf.hippo.influx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeepAlive {

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public void run() throws IOException {
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec("node " + System.getProperty("user.dir") + "/src/main/java/wtf/hippo/copper/utils/keepalive.js");
                    printResults(process);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File file = new File(System.getProperty("user.dir") + "/src/main/java/wtf/hippo/copper/utils/keepalive.js");
                if(file.exists()) {
                    System.out.println("finally");
                } else{
                    System.out.println(System.getProperty("user.dir"));
                }

            }

        };
        thread1.start();

        /*
        ../../src/main/java/wtf/hippo/copper/utils/keepalive.js
         */
    }

}