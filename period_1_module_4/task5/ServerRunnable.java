package task5;

import utils.FileUtil;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerRunnable implements Runnable {

    private Socket socket;
    private List<Socket> socketList;

    public ServerRunnable(Socket s, List<Socket> socketList) {
        socket = s;
        this.socketList = socketList;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.println();
                System.out.println("-----------------------------");
                System.out.println("连接数: " + socketList.size());
                System.out.println("服务器读取{" + (socketList.indexOf(socket) + 1) + " : " + socket.getInetAddress() + "}消息中...");
                // 读消息
                Object obj = ois.readObject();
                if (!(obj instanceof UserMessage)) {
                    return;
                }
                UserMessage userMessage = (UserMessage) obj;
                User user = userMessage.getUser();
                String msg = userMessage.getMsg();
                System.out.println();
                System.out.println("-----------------------------");
                if (UserMessage.TYPE_MSG.equals(userMessage.getType())) { //消息
                    System.out.println("服务器获取到第" + (socketList.indexOf(socket) + 1) + "个连接{ "
                            + user.getName() + " " + user.getId() + " }发送的消息:  " + msg);
                    // 回发消息
                    for (Socket s : socketList) {
                        if (s == socket) {
                            continue;
                        }
                        ObjectOutputStream allOos = new ObjectOutputStream(s.getOutputStream());
                        allOos.writeObject(userMessage);
                        allOos.flush();
                    }
                    System.out.println("服务器回发消息成功！");
                } else if (UserMessage.TYPE_FILE.equals(userMessage.getType())) { //文件
                    //读文件
                    long fileSize = userMessage.getFileSize(); //文件字节长度
                    System.out.println("服务器获取到第" + (socketList.indexOf(socket) + 1) + "个连接{ "
                            + user.getName() + " " + user.getId() + " }发送的文件:  " + msg + "(" + fileSize +")");
                    String filePath = "/data/server/" + user.getId() + "/" + msg;

                    long count = 0;
                    FileUtil.createDistDir(new File(filePath).getParentFile());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                    byte[] bArr = new byte[1024];

                    while (count < fileSize) { //读到为空？？？
                        int res = ois.read(bArr);
                        bos.write(bArr, 0, res);
                        count = count + res;
                        bos.flush();
                    }
                    bos.close();
                    System.out.println("保存文件结束～开始分发文件～");
                    File file = new File(filePath);
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    //--- 发文件
                    for (Socket s : socketList) {
                        if (s == socket) {
                            continue;
                        }
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        userMessage.setType(UserMessage.TYPE_FILE);
                        userMessage.setMsg(file.getName());
                        userMessage.setFileSize(bis.available());
                        oos.writeObject(userMessage);
                        oos.flush();

                        byte[] bbArr = new byte[1024];
                        int res1;
                        while ((res1 = bis.read(bbArr)) != -1) {
                            oos.write(bbArr, 0, res1);
                            oos.flush();
                        }
                    }
                    System.out.println("服务器分发文件成功！");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            if (null != socket) {
//                socketList.remove(socket);
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
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
