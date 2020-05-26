package task5;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author julychu
 * @date 2020/5/25
 * @desc 多人聊天客户端
 */
public class TcpClient {

    public static void main(String[] args) {
        //客户端信息，标识唯一身份
        User user = new User("A", 1);
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8899);
            System.out.println("{" + user.getName() + ":" + user.getId() + " : " + socket.getInetAddress() + "}连接服务器成功～");


            ClientWriteThread writeThread = new ClientWriteThread(socket, user);
            writeThread.start();

            ClientReadThread readThread = new ClientReadThread(socket, user);
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            if (null != socket) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
