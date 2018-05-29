import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {


    private static ArrayList<Socket> sockets = new ArrayList<Socket>();
    private static ArrayList<IMData> imdatas = new ArrayList<IMData>();
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
            sockets.add(socket);

            InetAddress address = socket.getInetAddress();
            String hostName = address.getHostName();
            String hostAddr = address.getHostAddress();
            byte[] addr = address.getAddress();
            JFClient client = new JFClient();
            client.setHostAddr(hostAddr);
            client.setHostName(hostName);
            System.out.println("hostName" + hostName);
            System.out.println("hostAddr" + hostAddr);

//            try {
//                socket.sendUrgentData(0xFF);
//                System.out.println("socket.isConnected()" + socket.isConnected());
//                System.out.println("socket.isClosed()" + socket.isClosed());
//            }catch (Exception e){
//                System.out.println("失去连接");
//                System.out.println("socket.isConnected()" + socket.isConnected());
//                System.out.println("socket.isClosed()" + socket.isClosed());
//            }

            new Thread(){
                @Override
                public void run() {
                    System.out.println("========");
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] buff = new byte[2048];
                        int hasRead = -1;
                        while ((hasRead = inputStream.read(buff)) > 0){
                            String result = new String(buff, 0, hasRead, "utf-8");
                            System.out.println("接受的数据：" + result);
                            JSONObject dataJsonObject = new JSONObject(result);
                            String data =  dataJsonObject.getString("data");
                            JSONObject jsonObject = new JSONObject(data);
                            String ip = jsonObject.getString("IP");
                            String userID =  jsonObject.getString("userID");
                            String userName =  jsonObject.getString("username");
                            String toUserID =  jsonObject.getString("toUserID");
                            String toUserName =  jsonObject.getString("toUsername");
                            String content =  jsonObject.getString("content");
                            System.out.println("userID: " + userID);
                            System.out.println("userName: " + userName);
                            System.out.println("toUserID: " + toUserID);
                            System.out.println("toUserName: " + toUserName);
                            System.out.println("content: " + content);
                            IMData imData = new IMData(ip,userID, userName, toUserID, toUserName, content);
                            imdatas.add(imData);
                            outputStream.write("JAVA已经接收到您的信息,返回数据阿拉斯吧".getBytes());
//                            for(int i = 0;i<sockets.size();i++){
//                                Socket socket1 = sockets.get(i);
//                                if(!socket1.getInetAddress().getHostAddress().equals(ip)){
//                                    OutputStream outputStream = socket1.getOutputStream();
//                                    outputStream.write(content.getBytes());
//                                    System.out.println("消息转发成功");
//                                }
//                            }
                        }
                        System.out.println("sssss");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();

        }


    }
}
