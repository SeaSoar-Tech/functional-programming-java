package org.example;
class Node{
  Node pre, nxt;
  char c;

  Node(){ c = '\0';}
  Node(char ch){
    c = ch;
  }

  void insert(Node node){
    this.nxt = node;
    node.pre = this;
  }

  void delete(){
    var preNode = this.pre;
    if(preNode != null){
      preNode.nxt = null;
    }
  }
}

public class Main {

  public static void main(String[] args) {
    Node node = new Node();
    System.out.println((int)node.c);
  }
}