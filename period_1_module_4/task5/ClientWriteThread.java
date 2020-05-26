package task5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread {
    private Socket socket;
    private UserMessage userMessage ;
    private User user;

    public ClientWriteThread(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() {
        ObjectOutputStream output = null;
        Scanner scanner = null;

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("------[写线程]请输入要发送的消息（file:\\\\ 开头，表示传输文件）:");
                scanner = new Scanner(System.in);
                String msg = scanner.next();

                // 文件传输
                if (msg.startsWith("file:\\\\")) {
                    File file;
                    do {
                        file = null;
                        System.out.println("请输入文件完整路径及名称(end:\\\\ 开头 退出文件传输):");
                        String filePath = scanner.next();
                        if ("end:\\\\".equals(filePath)) {
                            System.out.println("--- 退出文件传输 ---");
                            break;
                        }
                        file = new File(filePath);
                    } while (!file.exists() || !file.isFile());

                    if (null == file) {
                        continue;
                    }

                    BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file));
                    // 开始文件传输
                    userMessage = new UserMessage(UserMessage.TYPE_FILE, user);
                    userMessage.setMsg(file.getName());
                    userMessage.setFileSize(fileInput.available());
                    output.writeObject(userMessage);
                    output.flush();
                    // 文件传输中
                    byte[] bArr = new byte[1024];
                    int res;
                    while ((res = fileInput.read(bArr)) != -1) {
                        output.write(bArr, 0, res);
                        output.flush();
                    }
                    System.out.println("发送文件成功！");
                } else { // 消息传输
                    userMessage = new UserMessage(UserMessage.TYPE_MSG, user);
                    userMessage.setMsg(msg);
                    output.writeObject(userMessage);
                    System.out.println();
                    System.out.println("客户端" + user.getName() + "发送消息:" + msg);
                    output.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
