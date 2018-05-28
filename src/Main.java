import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException{
        System.out.println("Hello World!");

        ServerSocket serverSocket = new ServerSocket(30000);
        int count = 0;

        ArrayList arrayList = new ArrayList();
        while (true){
            final Socket socket = serverSocket.accept();
            count ++;
            System.out.println(count + "个链接已经建立");
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("您好，您收到了JAVA服务器的一条消息".getBytes());

            InetAddress address = socket.getInetAddress();
            String hostName = address.getHostName();
            String hostAddr = address.getHostAddress();
            byte[] addr = address.getAddress();
            JFClient client = new JFClient();
            client.setHostAddr(hostAddr);
            client.setHostName(hostName);
            System.out.println("hostName" + hostName);
            System.out.println("hostAddr" + hostAddr);

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
                            outputStream.write("JAVA已经接收到您的信息,返回数据阿拉斯吧".getBytes());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }
}
