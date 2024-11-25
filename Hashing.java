import java.util.HashMap;
import java.util.Arrays;
public class Hashing {
    public static void main(String [] args){

        // Basic hashing operations using hashmap

        HashMap<String, Character> grades = new HashMap<String, Character>();
        grades.put("Tom Cruise", 'A');
        grades.put("Ethan Hunt", 'A');
        grades.put("William Cage", 'B');
        grades.put("Tom Cruise", 'O');
        grades.put("David Aames", 'A');

        System.out.println(grades);

        // HashTable with Chaining

        System.out.println("Hash Table with Chaining");
        HashTableWithChaining htc = new HashTableWithChaining(5);
        htc.put(1, "Tom Cruise");
        htc.put(2, "Ethan Hunt");
        htc.put(3, "William Cage");
        htc.put(4, "Jack Reacher");
        htc.put(5, "David Aames");
        htc.printTable();
        
        // HashTable with Linear Probing

        System.out.println("Hash Table with Linear Probing");
        HashTableWithLinearProbing htlp = new HashTableWithLinearProbing(5);
        htlp.put(1, "Tom Cruise");
        htlp.put(2, "Ethan Hunt");
        htlp.put(3, "William Cage");
        htlp.put(4, "Jack Reacher");
        htlp.put(5, "David Aames");
        htlp.printTable();

        // HashTable with Rehashing

        System.out.println("Hash Table with Rehashing");
        HashTableWithRehashing htr = new HashTableWithRehashing(5);
        htr.put(1, "Tom Cruise");
        htr.put(2, "Ethan Hunt");
        htr.put(3, "William Cage");
        htr.put(4, "Jack Reacher");
        System.out.println("Before rehashing");
        htr.printTable();
        htr.put(5, "David Aames");
        System.out.println("After rehashing");
        htr.printTable();

    }
}

// HashTable with chaining

class MapNode{
    int key;
    String value;
    MapNode(int key, String value){
        this.key = key;
        this.value = value;
    }

    public String toString(){
        return "[" + key + " -> " + value + "] ";
    }
}


class ListNode{
    MapNode node;
    ListNode next;
    ListNode(MapNode node, ListNode next){
        this.node = node;
        this.next = next;
    }
}

class LinkedList{
    ListNode root;
    LinkedList(MapNode node){
        this.root = new ListNode(node, null);
    }

    public void add(MapNode node){
        ListNode current = root;
        while(current.next != null){
            current = current.next;
        }
        current.next = new ListNode(node, null);
    }
}

class HashTableWithChaining{
    private final LinkedList [] table;
    HashTableWithChaining(int size){
        table = new LinkedList [size];
    }

    private int hashFunction(int key){
        return key % table.length;
    }

    public void put(int key, String value) {
        int index = hashFunction(key);
        if(table[index] == null){
            table[index] = new LinkedList(new MapNode(key, value));
            return;
        }
        ListNode bucket = table[index].root;
        while(bucket != null){
            MapNode node = bucket.node;
            if(node.key == key){
                node.value = value;
                return;
            }
            bucket = bucket.next;
        }
        table[index].add(new MapNode(key, value));
    }

    public String get(int key) {
        int index = hashFunction(key);
        ListNode bucket = table[index].root;
        while(bucket != null){
            MapNode node = bucket.node;
            if(node.key == key){
                return node.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Node " + i + ": ");
            if(table[i] == null)    continue;
            ListNode bucket = table[i].root;
            while(bucket != null){
                MapNode node = bucket.node;
                System.out.print(node);
                bucket = bucket.next;
            }
            System.out.println();
        }
    }
}

class HashTableWithLinearProbing{
    private final int size;
    private final int [] keys;
    private final String [] values;

    public HashTableWithLinearProbing(int size){
        this.size = size;
        keys = new int[size];
        values = new String[size];
        Arrays.fill(keys, -1);
    }

    private int hashFunction(int key){
        return key % size;
    }

    public void put(int key, String value){
        int index = hashFunction(key);
        while(keys[index] != -1 && keys[index] != key){
            index = (index + 1) % size;
        }
        keys[index] = key;
        values[index] = value;
    }

    public String get(int key){
        int index = hashFunction(key);
        int startIndex = index;
        while(keys[index] != -1){
            if(keys[index] == k)    return values[index];
            index = (index + 1) % size;
            if(index == startIndex) break;
        }
        return null;
    }

    public void printTable(){
        for(int i = 0; i < size; i++){
            System.out.printf("Index %d : ", i);
            if(keys[i] == -1)   System.out.println("Null");
            else{
                System.out.printf("[%d -> %s]\n", keys[i], values[i]);
            }
        }
    }
}

class HashTableWithRehashing{
    private int size, count;
    private int [] keys;
    private String [] values;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public HashTableWithRehashing(int size){
        this.size = size;
        this.keys = new int[size];
        this.values = new String[size];
        Arrays.fill(keys, -1);
        count = 0;
    }

    private int hashFunction(int key){
        return key % size;
    }

    private void rehash(){
        int newSize = size * 2;
        int [] oldKeys = keys;
        String [] oldValues = values;
        keys = new int[newSize];
        values = new String[newSize];
        Arrays.fill(keys, -1);
        size = newSize;
        count = 0;
        for(int i = 0; i < oldKeys.length; i++){
            if(oldKeys[i] != -1){
                put(oldKeys[i], oldValues[i]);
            }
        }
        System.out.println("HashTable rehashed. The new size is : " + size);
    }

    public void put(int key, String value){
        if((double) count/size > LOAD_FACTOR_THRESHOLD){
            rehash();
        }

        int index = hashFunction(key);
        while(keys[index] != -1 && keys[index] != key){
            index = (index + 1) % size;
        }
        if(keys[index] == -1)   count++;
        keys[index] = key;
        values[index] = value;
    }

    public String get(int key){
        int index = hashFunction(key);
        int startIndex = index;
        while(keys[index] != -1){
            if(keys[index] == key)  return values[index];
            index = (index + 1) % size;
            if(index == startIndex) break;
        }
        return null;
    }

    public void printTable(){
        for(int i = 0; i < size; i++){
            System.out.printf("Index %d : ", i);
            if(keys[i] == -1)   System.out.println("Null");
            else{
                System.out.printf("[%d -> %s]\n", keys[i], values[i]);
            }
        }
    }
    
}