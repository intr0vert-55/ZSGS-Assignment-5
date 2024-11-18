import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
public class MapAssignment {
    public static void main(String[] args) {

        // Basic Map Operations
        Map<String, Integer> ages = new HashMap<String, Integer>();
        ages.put("Alice", 30); ages.put("Bob", 25); ages.put("Charlie", 35); ages.put("Diana", 28); ages.put("Edward", 40);
        System.out.println("Age of Charlie : " + ages.get("Charlie"));
        System.out.println("All names and ages : " + ages);

        // Iterate through a map
        Map<String, Integer> fruits = new TreeMap<String, Integer>();
        fruits.put("banana", 2); fruits.put("apple", 5); fruits.put("cherry", 7); fruits.put("date", 3);
        Iterator<Map.Entry<String, Integer>> iter = fruits.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

        // Check and remove entries
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("John", 85); hm.put("Jane", 92); hm.put("Tom", 76); hm.put("Lucy", 89);
        if(hm.containsKey("Tom")){
            System.out.println("Tom is present in the hashmap");
            hm.remove("Tom");
            System.out.println("Updated map : " + hm);
        }

        // Count Frequency
        String s = "programming";
        Map<Character, Integer> frequency = countFrequency(s);
        System.out.println("The frequency of each characters in the string " + s + " is " + frequency);

        // Merge two maps
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("a", 1); map1.put("b", 2);
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("b", 3); map2.put("c", 4);
        Map<String, Integer> mergedMap = new HashMap<String, Integer>(map1);
        map2.forEach((key, value) -> mergedMap.merge(key, value, (v1, v2) -> v2));  // Overwrites the value of the 1st map if both maps have the same values
        System.out.println(mergedMap);

    }

    // Counts the frequency of each characters
    public static Map<Character, Integer> countFrequency(String s){
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for(char ch : s.toCharArray()){
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        return frequency;
    }
}
