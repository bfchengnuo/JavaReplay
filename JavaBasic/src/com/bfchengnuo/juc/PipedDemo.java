package com.bfchengnuo.juc;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道的使用，区别与其他 IO，用于线程之间的通讯;
 *
 * 线程之间的数据交换还可以使用 {@link java.util.concurrent.Exchanger} 完成，可以使用遗传算法，
 * 选出两个人作为交配对象，交换两个人的数据，使用交叉规则计算出 2 个交配结果；可以用于校对工作。
 *
 * @author 冰封承諾Andy
 * @date 2020/6/5
 */
public class PipedDemo {
    public static void main(String[] args) throws IOException {
        // 面向字符流的做演示
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        // 使用之前必须连接，否则会 IO 异常
        pipedWriter.connect(pipedReader);

        new Thread(new Print(pipedReader), "printThread").start();

        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                pipedWriter.write(receive);
            }
        } finally {
            pipedWriter.close();
        }
    }

    static class Print implements Runnable{
        PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
