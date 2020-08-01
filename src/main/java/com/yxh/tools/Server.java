package com.yxh.tools;

import com.yxh.service.impl.OrderServiceImpl;
import com.yxh.util.SpringUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author YXH
 * @date 2020/7/28 - 15:39
 */
public class Server {
    public static final int PORT = 6666;//监听的端口号
    ServerSocket serverSocket;

    public static void main(String[] args) {
        System.out.println("服务器启动...\n");
        Server server = new Server();
        server.init();
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                System.out.println(client.getInetAddress().getHostAddress() + "...正在连接");
                // 处理这次连接
                new HandlerThread(client);
            }
        } catch (Exception e) {

            System.out.println("服务器异常: " + e.getMessage());
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class HandlerThread implements Runnable {

        private Socket socket;
        OrderServiceImpl orderService = (OrderServiceImpl ) SpringUtil.getBean(OrderServiceImpl.class);
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                //读取服务器端数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //接收wifi模块传来的数据
                String ret1 = reader.readLine();
                System.out.println("接收硬件：" + ret1);
                if (ret1.equals("1")) {
                    System.out.println("接收硬件：" + ret1);
                    orderService.updateTableStatus();//当送餐小车送完餐后 回到初始位置 发回1后，将标志位改为0 桌号改为0
                }

                //查询后厨已做完的桌号（0为暂无）
                Integer tableNum = orderService.selectTableStatusNum();
                char ch1 = (char) (tableNum + 48);
                if (tableNum != 0) {
                    System.out.println("后厨发送" + ch1);
                    orderService.updateTableStatus();
                    out.write(ch1);
                }

                out.close();
                reader.close();
            } catch (Exception e) {

                System.out.println("服务器 run 异常: " + e.getMessage());

            } finally {

                if (socket != null) {

                    try {

                        socket.close();

                    } catch (Exception e) {

                        socket = null;

                        System.out.println("服务端 finally 异常:" + e.getMessage());

                    }

                }

            }

        }

    }

}
