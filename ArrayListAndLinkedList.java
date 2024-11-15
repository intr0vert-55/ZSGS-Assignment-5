import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
public class ArrayListAndLinkedList{
    public static void main(String [] args){

        // Fruits
        List<String> fruits = new ArrayList<String>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Pomegranate");
        System.out.println(fruits.get(2));   // 3rd fruit

        // Iterate and remove
        List<Integer> nums = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("nums before removing : " + nums);
        Iterator<Integer> iter = nums.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            if(num > 30)    iter.remove();
        }
        System.out.println("nums after removing : " + nums);

        // Searching
        List<Character> chars = new ArrayList<Character>();
        // Adds random characters
        for(int i = 0; i < 10; i++){
            char ch = (char) (Math.random() * 127);
            chars.add(ch);
        }
        System.out.println("Random char array : " + chars);
        // Searching for 5 random characters
        for(int i = 0; i < 5; i++){
            char ch = (char) (Math.random() * 127);
            System.out.println("Target : " + ch + ". Result : " +search(chars, ch));
        }

        // Sorting
        List<Integer> randomNums = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            int rand = (int) (Math.random() * 100);
            randomNums.add(rand);
        }
        System.out.println("Before Sorting : " + randomNums);
        // Collections.sort(randomNums);   // inbuilt method
        mergeSort(randomNums);  // MergeSort implementation
        System.out.println("After Sorting : " + randomNums);

        // Conversion
        List<String> someFruits = Arrays.asList("apple", "banana", "cherry");
        System.out.println("ArrayList : " + someFruits);
        String [] arr = new String [someFruits.size()];
        arr = someFruits.toArray(arr);
        System.out.println("Array : " + Arrays.toString(arr));
        System.out.println("ArrayList again : " + Arrays.asList(arr));

        // Linked List
        List<Integer> list = new LinkedList<Integer>();
        list.add(5); list.add(10); list.add(15); list.add(20); list.add(25);
        System.out.println("First : " + list.get(0) + " Last : " + list.get(list.size() - 1));

        // Iterate
        List<String> numberStrings = new LinkedList<String>(Arrays.asList("one", "two", "three", "four", "five"));
        ListIterator<String> listIter = numberStrings.listIterator();
        while(listIter.hasNext()){
            String s = listIter.next();
            if(s.equals("three"))   listIter.set("THREE");
        }
        System.out.println("NumberStrings : " + numberStrings);

        // Operations
        List<Integer> numbers = new LinkedList<Integer>();
        for(int i = 1; i < 11; i++)  numbers.add(i);
        numbers.remove(2);  // removing 3rd element
        numbers.remove(numbers.size() - 1);     // removing last element
        System.out.println("Numbers : " + numbers);

        // Queue behaviour
        Queue<String> queue = new LinkedList<String>();
        queue.add("Alice"); 
        queue.add("Bob"); 
        queue.add("Charlie"); 
        queue.add("Diana");
        queue.poll();   // removing 2 elements
        queue.poll(); 
        System.out.println("Queue : " + queue);

        // Reverse
        List<Character> charList = new LinkedList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E'));
        System.out.println("Before Reversing : " + charList);
        Collections.reverse(charList);
        System.out.println("After Reversing : " + charList);
    }

    // Linear search
    public static int search(List<Character> chars, char target){
        for(int i = 0; i < chars.size(); i++){
            if(chars.get(i) == target)  return i;
        }
        return -1;
    }

    public static void mergeSort(List<Integer> nums){
        seperate(nums);
    }

    public static void seperate(List<Integer> nums){
        int size = nums.size();
        if(size == 1)   return;
        List<Integer> left = new ArrayList<>(nums.subList(0, size/2));
        List<Integer> right = new ArrayList<>(nums.subList(size/2, size));
        seperate(left);
        seperate(right);
        merge(left, right, nums);
    }

    public static void merge(List<Integer> left, List<Integer> right, List<Integer> nums){
        int lp = 0, rp = 0, np = 0;
        while(lp < left.size() && rp < right.size()){
            if(left.get(lp) < right.get(rp)){
                nums.set(np++, left.get(lp++));
            } else{
                nums.set(np++, right.get(rp++));
            }
        }

        while(lp < left.size()){
            nums.set(np++, left.get(lp++));
        }

        while(rp < right.size()){
            nums.set(np++, right.get(rp++));
        }
    }

}