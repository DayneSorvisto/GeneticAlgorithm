package com.geneticalgorithm
import java.io.*;
import java.util.*;
public class Interpreter
{
    public static final String GOAL = "Hi";
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
    public static String randomProgram ()
    {
        String seed = "+.";
        Random rand = new Random();
        String str = "";
        // generate program between 5 and 1000 instructions long at random
        for (int i = 0;
        i &lt;
        rand.nextInt(1000)+5;
        ++i)
        {
            str+=seed.charAt((int) rand.nextInt(seed.length())) + "";
        }
        return str;
    }
    public static int metric (String a, String b)
    {
        char [] arraya = a.toLowerCase().toCharArray();
        char [] arrayb = b.toLowerCase().toCharArray();
        int sum = 0;
        int length = (arraya.length &lt;
        arrayb.length) ? arraya.length : arrayb.length;
        for (int i=0;
        i&lt;
        length;
        ++i)
        {
            sum+=Math.abs(arraya[i]-arrayb[i]);
        }
        return sum;
    }
    public static String[] pool(int n, String str)
    {
        String[] array = new String[n];
        for (int i = 0;
        i &lt;
        n;
        ++i)
        {
            array[i] = mutation(str);
        }
        return array;
    }
    public static String mutation(String a)
    {
        String seed = "+. ";
        Random rand = new Random();
        char randchar  = seed.charAt( (int) rand.nextInt(seed.length()));
        char [] array = a.toCharArray();
        int randpos = (int) rand.nextInt(a.length());
        array[randpos] = randchar;
        return new String(array);
    }
    public static void main (String [] args) throws IOException
    {
        int c;
        int [] data = new int[TAPESIZE];
        int i = 0;
        while ((c = bufferRead.read()) != -1)
        {
            data[i]=c;
            ++i;
        }
        BrainF brain = new BrainF();
        String p = randomProgram();
        String result1 = "";
        String result2 ="";
        while (metric(brain.code(p,0,0),GOAL) &gt;
        0)
        {
            try
            {
                String [] pool = pool(100000,p);
                //make a pool of 100 000  programs at a time
                HashMap&lt;
                String, String&gt;
                map = new HashMap&lt;
                String,String &gt;
                ();
                for ( String prg : pool )
                {
                    map.put(brain.code(prg,0,0),prg);
                }
                String fittest = closest(map.keySet().toArray(new String[map.size()]));
                p = map.get(fittest);
                result = p;
                result2= brain.code(p,0,0);
                System.out.println(p);
                System.out.println(fittest);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.print("The most fit program is " + result1 + "with outout: " + result2);
    }
    public static String closest (String [] pool)
    {
        class Metric implements Comparator&lt;
        String&gt;
        {
            public int compare(String a, String b)
            {
                return metric(b,GOAL)-metric(a,GOAL);
            }
        }
        return Collections.max(Arrays.asList(pool), new Metric());
    }
}
	
	 
