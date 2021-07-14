package com.ns.design;

import java.util.HashMap;
import java.util.Map;

//Data structure used
//1. Hashmap to store the cache values
//2. DoublyLinkedList to maintain cache eviction policy LRU. It acts as double ended queue
//3. Most recently used elements are inserted from front and least recently used elements are used from rear end

public class LRUCache<T> {
    private final Map<String,Node> map;
    private final  int capacity;
    private int size =0;
    private DoublyLinkedList internalList;

    public LRUCache(final int capacity){
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.internalList = new DoublyLinkedList();
    }

    public T get(final String key){

        if(!map.containsKey(key)) return null;

        Node node= map.get(key);
        internalList.moveNodetoFront(node);

        return node.value;
    }

    public void put(final String key,final T value){
        Node node = map.get(key);
        if(node != null){
            node.value = value;
            internalList.moveNodetoFront(node);
        }else {
            //Remove the oldest used element if size reaches capacity. i.e., from rearend
            if (size == capacity){
                map.remove(internalList.getRearNodeKey());
                internalList.removeRearNode();
                size--;
            }
            Node node1 = new Node(key, value);
            map.put(key,node1);
            internalList.insertFrontNode(node1);
            size++;
        }
    }

    private  class Node{
        String key;
        T value;
        Node next,prev;

        public  Node(final String key, final T value){
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

    }

    private  class DoublyLinkedList{
           Node rear,front;

           DoublyLinkedList(){
               rear=front=null;
           }

           public void moveNodetoFront(Node node){
               if (node == front) return;
               if (node == rear){
                   rear.next = null;
                   rear = node.prev;
               }else {
                   node.prev.next = node.next;
                   node.next.prev = node.prev;
               }
               insertFrontNode(node);


           }
           public void insertFrontNode(Node node){
               if (rear==null && front ==null){
                   rear=front=node;
                   return;
               }
               node.next = front;
               front.prev = node;
               node.prev = null;
               front = node;
           }

           public void removeRearNode(){
               if(rear==null) return;

               System.out.println("Removing the node LRU node:"+rear.key);
                if (rear == front){
                    rear=front=null;
                }  else {

                    rear = rear.prev;
                    rear.next = null;
                }
           }

           public String getRearNodeKey(){
               return rear.key;
           }
        
    }

}
