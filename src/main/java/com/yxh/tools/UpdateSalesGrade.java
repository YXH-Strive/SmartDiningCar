package com.yxh.tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author YXH
 * @date 2020/7/27 - 20:14
 */

@WebListener
public class UpdateSalesGrade implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("contextDestroyed");

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("contextInitialized");
        new Thread(new UpdateData()).start();
    }

}

class UpdateData implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        while(true) {

            try {
                //获取各个菜的销量 各个菜的评分
                System.out.println(1);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}

