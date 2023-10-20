package edu.iastate.cs228.hw4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author Luke Pavia
 */

public class MessageDecoder {
	
	public static double bit;
	public static double character;
	public static int charCount;
    private static int staticCharIdx = 0;

    public static void main(String[] args)
    {
        String fileName;
        String Message = "";
        ArrayList<String> lines = new ArrayList<String>();
        

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter filename to decode:  ");
        fileName = scan.next();
        scan.close();
        
        System.out.println("character" + "        " + "code");
		System.out.println("-------------------------------");

        
        try
        {
            File f = new File(fileName);
          Scanner fileScanner = new Scanner(f);

          while(fileScanner.hasNext())
          {
              lines.add(fileScanner.nextLine());
          }
          fileScanner.close();
          
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
            return;
        }

        

        for(int i = 0; i < lines.size(); i++) {
            Message += lines.get(i) + '\n';
        }
        Message += lines.get(lines.size() - 1);

        MsgTree mainTree = new MsgTree(Message);

        MessageDecoder.printCodes(mainTree, "");
        
        int len = lines.get(lines.size() - 1).length();
       
        System.out.println();
        System.out.println();
        System.out.println("MESSAGE:");
        
        while(staticCharIdx < len) {
            decode(mainTree, lines.get(lines.size() - 1));
        }
        
        statistcs();

    }
    
    /**
     * Prints out the average bits/char, total amount of characters, and space savings.
     */
    private static void statistcs() {
    	System.out.println(" ");

		System.out.println("STATISTICS : ");

		double avg = bit/character;
		double space = 0.0 ; 
		double uncompressed = character * 16.0;
		space = (1 - (bit/uncompressed))*100;
		
		String bitAvg = String.format("%.1f", avg);
		String spaceSavings = String.format("%.1f", space);
		
		
		System.out.println( "Avg  bits/char: " + "          " + bitAvg);
		System.out.println("Total characters:" + "         "+ charCount );
		System.out.println("Space savings:   " + "         " + spaceSavings);
    }
    
    /**
     * method to print characters and binary codes
     * @param root
     * @param code
     */
    public static void printCodes(MsgTree root, String code){

        if(root == null)
        {
            return;
        }

        if(root.payloadChar != '^' && root.payloadChar == '\n' )
        {	
        	System.out.println("   \\n " + "           " + code);
        }
        else if (root.payloadChar != '^' && root.payloadChar != '\n'){
            	bit += code.length();
				character++;
                System.out.println("   " + root.payloadChar + "             " + code);
            }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }




    /**
     * Prints the decoded message into console by using
     * @param codes
     * @param msg
     *
     *
     */
        public static void decode(MsgTree codes, String msg) {


            while(codes.left != null && codes.right != null)
            {
                if(msg.charAt(staticCharIdx) == '0')
                {
                    codes = codes.left;
                }
                else
                {
                    codes = codes.right;
                }
                staticCharIdx +=1;
            }
            if(codes.payloadChar == '\n')
            {
            	charCount++;
                System.out.print("\n");
            }
            else {
            	charCount++;
                System.out.print(codes.payloadChar);
            }


        }


    }

