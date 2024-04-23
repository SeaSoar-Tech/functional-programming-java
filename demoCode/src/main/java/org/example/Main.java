package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Node;

public class Main {

  public static void main(String[] args) {
    List<Integer>[] g = new ArrayList[2];
    Arrays.fill(g, new ArrayList<>());
    g[0].add(1);
    g[1].add(2);

    for(var gg: g) {
      System.out.println(gg);
    }

  }
}