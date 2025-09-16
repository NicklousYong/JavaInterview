package basic.IO_Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOClient {
    public  static final int PORT = 8080;
    public  static final String ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket =   new Socket(ADDRESS,PORT);
            System.out.println("连接成功");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("~~~~~~NettyClient request");
            out.println("发送第一行~~~~~~");
            out.println("发送第二行~~~~~~");
            out.println("发送完毕~~~~~~");
            System.out.println("输出流开始输出");
            String response= in.readLine();
            System.out.println("NettyClient "+ response);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
