LRU cache design

Used following data structure to achieve
1. Hashmap to store the cache values  -> Achives O(1) for put and get
2. Doubly linked list to keep track of recent used elements
