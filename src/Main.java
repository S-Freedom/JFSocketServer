import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException{
        System.out.println("Hello World!");
        System.out.println("Hello World!");

        ServerSocket serverSocket = new ServerSocket(30000);
        while (true){
            final Socket socket = serverSocket.accept();
            new Thread(){

                @Override
                public void run() {
                    System.out.println("========");
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] buff = new byte[2048];
                        int hasRead = -1;
                        while ((hasRead = inputStream.read(buff)) > 0){
                            System.out.println("接受的数据：" + new String(buff, 0, hasRead, "utf-8"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("您好，您收到了JAVA服务器的一条消息".getBytes());
        }
    }
}
