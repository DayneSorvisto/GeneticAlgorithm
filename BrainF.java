package com.geneticalgorithm;
import java.util.*;
import java.io.*;
public class BrainF
{
    private static final int TAPESIZE = 30000;
    public static int[] convert (String s)
    {
        int[] intArray = new int[TAPESIZE];
        char [] asChar = s.toCharArray();
        int i=0;
        for (char c : asChar)
        {
            intArray[i] = (int) c;
            i++;
        }
        return intArray;
    }
    //stores the tape
    public static byte[] tape = new byte[TAPESIZE];
    // data pointer
    public static int datapointer;
    public static int charpointer;
    public String code(String cstring, int charpointer1, int datapointer1) throws IOException
    {
        String out="";
        int [] c = convert(cstring);
        datapointer = datapointer1;
        charpointer = charpointer1;
        int limit = 0;
        // prevents infinite loops required since we cant solve the halting problem
        while (charpointer < c.length && limit < 3000)
        {
            limit++;
            switch((char) c[charpointer])
            {
                case Token.DEFAULT:
                charpointer++;
                case Token.NEXT: //increment the data pointer to point to next byte on tape
                datapointer++;
                charpointer++;
                break;
                case Token.PREV: //decrement the data pointer to point to prev byte on tape
                datapointer--;
                charpointer++;
                break;
                case Token.PLUS:
                tape[datapointer]++;
                charpointer++;
                break;
                case Token.MINUS:
                tape[datapointer]--;
                charpointer++;
                break;
                case Token.OUTPUT:
                char ch = (char) tape[datapointer];
                out=out+String.valueOf(ch);
                charpointer++;
                break;
                case Token.LEFT:
                if (tape[datapointer] == 0)
                {
                    int i = 1;
                    while (i > 0)
                    {
                        char c2 = (char) c[++charpointer];
                        if (c2 == Token.LEFT)
                        i++;
                        else if (c2 == Token.RIGHT)
                        i--;
                    }
                }
                charpointer ++;
                break;
                case Token.RIGHT:
                int i = 1;
                while ( i > 0)
                {
                    char c2 = (char) c[--charpointer];
                    if (c2 == Token.LEFT)
                    i--;
                    else if (c2 == Token.RIGHT)
                    i++;
                }
                break;
            }
        }
        return String.valueOf(out);
    }
    //the 6 brain fuck tokens and a default case
    static class Token
    {
        public final static char NEXT = '&gt;
        ';
        public final static char PREV = '&lt;
        ';
        public final static char OUTPUT = '.';
        public final static char INPUT = ',';
        public final static char LEFT = '[';
        public final static char RIGHT = ']';
        public final static char MINUS = '-';
        public final static char PLUS = '+';
        public final static char DEFAULT = ' ';
    }
}
	
	 
