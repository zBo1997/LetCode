package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            while (true) {
                // 从控制台读取输入
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入：");
                String input = scanner.nextLine();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(input.getBytes(StandardCharsets.UTF_8));
                InputStream inputStream = socket.getInputStream();
                //读取这个流并且输出到控制台
                byte[] data = new byte[1024];
                int read = inputStream.read(data);
                System.out.println(new String(data, 0, read, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }
}