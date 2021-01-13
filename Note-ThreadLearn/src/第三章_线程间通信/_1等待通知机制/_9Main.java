package 第三章_线程间通信._1等待通知机制;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * create by cairuojin on 2019/01/02
 * 利用管道进行线程间的通信:字节流
 */

class WriteData{
    synchronized public void writeMethod(PipedOutputStream outputStream){    //写方法
        try {
            System.out.println("write:");
            for(int i = 0; i < 37; i ++){
                String outData = "" + (i + 1);
                outputStream.write(outData.getBytes());         //往管道中写入字节流
                System.out.print(outData);
            }
            System.out.println();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData{
    public void readMethod(PipedInputStream inputStream){       //读方法
        try {
            System.out.println("read:");
            byte[] byteArray = new byte[20];
            int readLength = inputStream.read(byteArray);
            while (readLength != -1){
                String newData = new String(byteArray,0,readLength);    //从管道中读取数据
                System.out.print(newData);
                readLength = inputStream.read(byteArray);
            }
            System.out.println();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Mythread15 extends Thread{            //写线程
    private WriteData writeData;
    private PipedOutputStream outputStream;
    public Mythread15(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }
    @Override
    public void run() {
        super.run();
        writeData.writeMethod(outputStream);
    }
}
class Mythread16 extends Thread{            //读线程
    private ReadData readData;
    private PipedInputStream inputStream;
    public Mythread16(ReadData readData, PipedInputStream inputStream) {
        this.readData = readData;
        this.inputStream = inputStream;
    }
    @Override
    public void run() {
        super.run();
        readData.readMethod(inputStream);
    }
}

public class _9Main {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedOutputStream outputStream = new PipedOutputStream();
            PipedInputStream inputStream = new PipedInputStream();
            inputStream.connect(outputStream);
            Mythread15 writeThread = new Mythread15(writeData,outputStream);
            Mythread16 readThread = new Mythread16(readData,inputStream);
            readThread.start();
            Thread.sleep(2000);
            writeThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
