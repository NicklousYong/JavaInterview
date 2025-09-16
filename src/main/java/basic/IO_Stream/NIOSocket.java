package basic.IO_Stream;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIOSocket {

    private static LinkedList<SocketChannel>  clients = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // new socket开启监听
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(800));
        // 设置阻塞接受客户端；连接
        socketChannel.configureBlocking(true);
        // 开启client线程处理
        startClientChannelHandleThread();
        while (true) {
            //阻塞等待客户端连接
            SocketChannel client = socketChannel.accept();
            if(client==null){

            }else {
                System.out.println("接受连接：\t" + client.getRemoteAddress());
                //设置非阻塞
                client.configureBlocking(false);
                clients.add(client);
            }
        }
    }

    private  static void startClientChannelHandleThread(){
        new Thread(
                ()->{
                    while(true){
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        //处理客户端连接
                        for (SocketChannel c : clients) {
                            //非阻塞，>0 表示读取到的字节数量，0或-1表示为读取到或读取异常
                            int num = 0;
                            try {
                                num = c.read(buffer);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            if(num>0){
                                buffer.flip();
                                byte[] data = new byte[num];
                                buffer.get(data);
                                System.out.println("收到来自客户端的信息：\t"+new String(data));
                                buffer.clear();
                            }
                        }
                    }



                }


        ).start();



    }


}
