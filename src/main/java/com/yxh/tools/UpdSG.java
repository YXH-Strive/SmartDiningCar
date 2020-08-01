package com.yxh.tools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YXH
 * @date 2020/7/27 - 20:14
 */
public class UpdSG extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MyThread1 myThread1;

    @Override
    public void init() {
        String str = null;
        if (str == null && myThread1 == null) {
            myThread1 = new MyThread1();
            myThread1.start(); // servlet 上下文初始化时启动 socket
        }
    }

    @Override
    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void destory() {
        if (myThread1 != null && myThread1.isInterrupted()) {
            myThread1.interrupt();
        }
    }
}

/**
 * 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
 */
class MyThread1 extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ------------------ 开始执行 ---------------------------
            System.out.println("____FUCK TIME:" + System.currentTimeMillis());
        }
    }
}

