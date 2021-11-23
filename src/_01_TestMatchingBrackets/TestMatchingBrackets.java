package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {

    	Stack<Character> chars = new Stack<>();
    	
    	
    	for(String s : b.split("")) {
    		if(s.equals("{")) {
    			chars.push('{');
    		}else if(s.equals("}") && chars.size() > 0) {
    			chars.pop();
    		}else {
    			return false;
    		}
    	}
    	
    	
    	
    	
    	
        return chars.isEmpty();
    }
}