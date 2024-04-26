package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class SocketDemo {

  interface Lambda extends Serializable {

    int calculate(int a, int b);
  }

  static class Server {

    public static void main(String[] args) {
      System.out.println("server start...");

      try (ServerSocket ss = new ServerSocket(8080)) {
        while (true) {
          Socket s = ss.accept();

          new Thread(() -> {
            try {
              ObjectInputStream is = new ObjectInputStream(s.getInputStream());
              Lambda lambda = (Lambda) is.readObject();
              int a = ThreadLocalRandom.current().nextInt(10);
              int b = ThreadLocalRandom.current().nextInt(10);
              System.out.printf("%s %d op %d = %d%n", s.getRemoteSocketAddress().toString(), a, b,
                  lambda.calculate(a, b));
            } catch (IOException | ClassNotFoundException e) {
              throw new RuntimeException(e);
            }
          }).start();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }
  }

  // Server 虚拟机端必须有 Client0 这个类，相当于把实现绑定在了服务器端
  static class Client0 {
    int add(int a, int b) {
      return a + b;
    }
  }
}
