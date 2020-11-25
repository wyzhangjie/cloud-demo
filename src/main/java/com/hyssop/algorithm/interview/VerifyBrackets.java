package com.hyssop.algorithm.interview;

import java.util.Stack;

public class VerifyBrackets {


    Boolean isValid(String[] a) {
        int len = a.length;
        Stack<String> stringStack = new Stack<>();
        stringStack.push(a[0]);
        for(int i=1;i<len;i++){
            if(stringStack.size()>0 && isVarify(stringStack.peek(),a[i])){
                stringStack.pop();
            }else {
                stringStack.push(a[i]);
            }
        }
        if(stringStack.empty()) return true;
        else return false;
    }

    Boolean isVarify(String i, String j) {
        if (i.equals("(") && j.equals(")")) return true;
        if (i.equals("{") && j.equals("}")) return true;
        if (i.equals("[") && j.equals("]")) return true;
        return false;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"{","[","]", "}","{","}"};
        VerifyBrackets verifyBrackets = new VerifyBrackets();
        System.out.println(verifyBrackets.isValid(a));

    }

}
