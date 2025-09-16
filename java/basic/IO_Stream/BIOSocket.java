package basic.IO_Stream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOSocket {
    public static final int PORT= 8080;

    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket client = serverSocket.accept();//会阻塞住，直到有客户端连接后才会继续执行。
            System.out.println("接受连接：\t"+client.getPort());
            new Thread(()->{
                try{
                    // 如果在这个过程重处理事很慢的，那么整个服务器就会阻塞住，无法处理其他的客户端连接。
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    System.out.println("开始阅读接受的信息");
                    while (true){
                        System.out.println(reader.readLine());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }

    }


}
