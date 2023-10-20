package edu.iastate.cs228.hw4;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author Luke Pavia
 */

public class MsgTree {

    public char payloadChar;
    public MsgTree left;
    public MsgTree right;




    /*Can use a static char idx to the tree string for recursive
    solution, but it is not strictly necessary*/
    private static int staticCharIdx = 0;
    
    

    //Constructor building the tree from a string
    public MsgTree(String encodingString){
        payloadChar = encodingString.charAt(staticCharIdx);

        staticCharIdx += 1;
        left = new MsgTree(encodingString.charAt(staticCharIdx));

        if(left.payloadChar == '^')
        {
            left = new MsgTree(encodingString);
        }

        staticCharIdx += 1;
        right = new MsgTree(encodingString.charAt(staticCharIdx));

        if(right.payloadChar == '^')
        {
            right = new MsgTree(encodingString);
        }
    }

    //Constructor for a single node with null children
    public MsgTree(char payloadChar){
        this.payloadChar = payloadChar;
    }
    
        
     
    
}
