package task5;

import utils.FileUtil;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientReadThread extends Thread {
    private Socket socket;
    private UserMessage userMessage;
    private User user;

    public ClientReadThread(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            while (true) {
                System.out.println("------[读线程] 等待收消息～");
                ois = new ObjectInputStream(socket.getInputStream());
                Object object = ois.readObject();
                if (!(object instanceof UserMessage)) {
                    return;
                }
                UserMessage uMsg = (UserMessage) object;
                User user1 = uMsg.getUser();
                System.out.println();
                System.out.println("-----------------------------");

                if (UserMessage.TYPE_FILE.equals(uMsg.getType())) { //文件传输
                    String msg = uMsg.getMsg();
                    long fileSize = uMsg.getFileSize();
                    System.out.println("{" + user1.getName() + " " + user1.getId() + "}发来文件: " + msg + "(" + fileSize +")");
                    String filePath = "/data/client/" + user.getId() + "/" + msg;
                    FileUtil.createDistDir(new File(filePath).getParentFile());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                    byte[] bArr = new byte[1024];
                    int count = 0;
                    while (count < fileSize) {
                        int res = ois.read(bArr);
                        bos.write(bArr, 0, res);
                        count = count + res;
                        bos.flush();
                    }
                    bos.close();
                    System.out.println("接收文件成功");
                } else if(UserMessage.TYPE_MSG.equals(uMsg.getType())) {// 消息传输
                    System.out.println("{" + user1.getName() + " " + user1.getId() + "}发来消息: ");
                    System.out.println(uMsg.getMsg());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
