package task5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @author julychu
 * @date 2020/5/25
 * @desc 多人聊天服务器
 */
public class TcpServer {

    public static void main(String[] args) {
        List<Socket> socketList = new LinkedList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8899);
            while (true) {
                System.out.println();
                System.out.println("-----------------------------");
                System.out.println("服务器等待第" + (socketList.size() + 1) + "个连接...");
                socket = serverSocket.accept();
                socketList.add(socket);

                System.out.println();
                System.out.println("-----------------------------");
                System.out.println("第" + (socketList.size()) + "个客户端" + socket.getInetAddress().getHostAddress() +"连接成功");
                ServerRunnable serverThread = new ServerRunnable(socket, socketList);
                Thread thread = new Thread(serverThread);
                thread.start();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
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
