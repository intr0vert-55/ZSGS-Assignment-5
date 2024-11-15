import java.util.Vector;
import java.util.Stack;
import java.util.Arrays;
import java.util.Enumeration;
public class VectorAndStack {
    public static void main(String [] args){

        // Basic Vector Operations
        Vector<Integer> nums = new Vector<Integer>();
        for(int i = 1; i < 6; i++)  nums.add(i * 10);
        System.out.println(nums.get(2));

        // Enumeration
        Vector<String> fruits = new Vector<String>(Arrays.asList("apple", "banana", "cherry", "date", "fig"));
        Enumeration<String> enumeration = fruits.elements();
        int index = 0;
        while(enumeration.hasMoreElements()){
            String s = enumeration.nextElement();
            System.out.println(s);  // Enumerator cannot be used to modify the elements. Thus we are using it's index to modify it here.
            if(s.equals("cherry"))  fruits.set(index, "coconut");   
            index++;
        }
        System.out.println("Modified Fruits : " + fruits);

        // Insert and Remove elements
        Vector<Character> chars = new Vector<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E'));
        System.out.println("Chars : " + chars);
        chars.add(2, 'X');
        chars.remove(0);
        System.out.println("Modified chars : " + chars);

        // Check for vector and element size
        Vector<Double> doubles = new Vector<Double>();
        for(int i = 0; i < 5; i++)  doubles.add(Math.random() * 10);
        System.out.println("Random doubles : " + doubles);
        System.out.println("If 10.5 is present : " + doubles.contains(10.5));
        System.out.println("Size before adding an element : " + doubles.size());
        doubles.add(Math.random() * 10);
        System.out.println("Size after adding an element : " + doubles.size());

        // Vector to array and vice versa
        Vector<String> colors = new Vector<String>(Arrays.asList("red", "green", "blue", "yellow"));
        System.out.println("Colors vector : " + colors);
        String [] colorArr = new String [colors.size()];
        colors.toArray(colorArr);
        System.out.println("Colors array : " + Arrays.toString(colorArr));
        colors = new Vector<String>(Arrays.asList(colorArr));
        System.out.println("Array to colors vector again : " + colors);

        // Basic Stack Operation
        Stack<Integer> stackOfNums = new Stack<Integer>();
        for(int i = 1; i < 6; i++)  stackOfNums.push(i * 10);
        System.out.println("Remaining elements after popping " + stackOfNums.pop() + " : " + stackOfNums);

        // Peek and search in stack
        Stack<String> top4 = new Stack<String>();
        top4.push("first"); top4.push("second"); top4.push("third"); top4.push("fourth"); 
        System.out.println("Peeking an element from the stack : " + top4.peek());
        System.out.println("Searching for second using search() method : " + top4.search("second"));

        // Reverse a string using stack
        String string = "hello";
        System.out.printf("The reverse of %s is : %s\n", string, reverse(string));

        // Balanced Paranthesis
        String balanced = "(())";
        String imbalanced = "(()";
        System.out.printf("The string %s is %s\n", balanced, (isBalanced(balanced) ? "balanced" : "imbalanced"));
        System.out.printf("The string %s is %s\n", imbalanced, (isBalanced(imbalanced) ? "balanced" : "imbalanced"));

        // Stack to array
        Stack<Double> stack = new Stack<Double>();
        for(int i = 1; i < 5; i++)  stack.push((double)(i + i/10.0));
        System.out.println("Stack : " + stack);
        Double [] doubleArr = new Double[stack.size()];
        stack.toArray(doubleArr);
        System.out.println("Stack to double array : " + Arrays.toString(doubleArr));
        stack = new Stack<Double>();
        for(Double d : doubleArr)   stack.push(d);
        System.out.println("After adding the elements back to the stack : " + stack);
    }

    // Reversing a string using stack
    public static String reverse(String s){
        Stack<Character> stack = new Stack<Character>();
        for(char ch : s.toCharArray()){
            stack.push(ch);
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.toString();
    }

    // Balanced Paranthesis

    public static boolean isBalanced(String s){
        Stack<Character> stack = new Stack<Character>();
        for(char ch : s.toCharArray()){
            if(ch == ')'){
                if(stack.isEmpty()) return false;
                stack.pop();
            } else if(ch == '('){
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
