package task4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author julychu
 * @date 2020/5/25
 * @desc 客户端
 */
public class TcpClient {

    public static void main(String[] args) {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8888);
            // 写对象
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            User user = new User(Constant.adminName, Constant.adminName);
            UserMessage userMessage = new UserMessage(UserMessage.TYPE_CHECK, user);
            objectOutputStream.writeObject(userMessage);
            System.out.println("客户端发送消息：" + userMessage);
            //读消息
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            if (!(object instanceof UserMessage)) {
                return;
            }

            UserMessage userMessage1 = (UserMessage) object;
            System.out.println("客户端收到消息：" + userMessage1);
            if (UserMessage.TYPE_SUC.equals(userMessage1.getType())) {
                System.out.println("用户登录成功");
            } else if (UserMessage.TYPE_FAIL.equals(userMessage1.getType())) {
                System.out.println("用户登录失败");
            }

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
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
