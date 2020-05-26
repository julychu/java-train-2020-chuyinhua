package task4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author julychu
 * @date 2020/5/25
 * @desc 使用基于 tcp 协议的编程模型实现将 UserMessage 类型对象由客户端发送给服务器
 */
public class TcpServer {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(8888);
                System.out.println("-----------------------------");
                System.out.println("服务器等待连接...");
                Socket socket = serverSocket.accept();
                System.out.println("服务器等待连接成功");

                objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object object = objectInputStream.readObject();
                if (!(object instanceof UserMessage)) {
                    return;
                }

                UserMessage userMessage = (UserMessage) object;
                System.out.println("服务器收到客户端消息：" + userMessage);
                if (!UserMessage.TYPE_CHECK.equals(userMessage.getType())) {
                    return;
                }

                // 回发消息
                User user = userMessage.getUser();
                boolean isAdmin = Constant.adminName.equals(user.getName()) && Constant.adminPsw.equals(user.getPsw());
                userMessage.setType(isAdmin ? UserMessage.TYPE_SUC : UserMessage.TYPE_FAIL);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(userMessage);
                System.out.println("服务器回发消息成功！");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != objectOutputStream) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != objectInputStream) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != serverSocket) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
