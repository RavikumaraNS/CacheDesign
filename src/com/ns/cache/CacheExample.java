package com.ns.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CacheExample {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        LRUCache lruCache = new LRUCache(5);
        System.out.println("Enter the case value");
        String key;
        while(true) {
            System.out.println("value to scan: 1.Put 2. Get 0.Exit");
            int value = Integer.parseInt(bufferedReader.readLine());
            switch (value){
                case 1:
                    System.out.println("Enter key");
                    key = bufferedReader.readLine();
                    System.out.println("Enter value");
                    String cacheVal = bufferedReader.readLine();
                    lruCache.put(key, cacheVal);
                    break;
                case 2:
                    System.out.println("Enter key");
                    key = bufferedReader.readLine();
                    System.out.println("value for the key :"+ key + " :is:"+lruCache.get(key));
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("No options");

            }

        }
    }

}
