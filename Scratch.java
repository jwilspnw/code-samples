import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by CWAuxSt02 on 2/18/2016.
 */
public class Scratch {

    static Tile[][] grid = new Tile[4][4];

    public static void main(String args[])
    {
        /*
        Scanner kb = new Scanner(System.in);
        boolean gotInput = false;
        int n = 0;
        while (!gotInput){
            try{
                System.out.println("Which Fibonibbo numnum would you like?");
                n = kb.nextInt();
                if (n < 0) throw new Exception("Negative fib numbers are undefined");
                gotInput = true;
            } catch (Exception e){
                System.out.println("Invalid data entered.");
                kb.next();
            }
        }
        System.out.print(fib(n));
        */
        //System.out.println("Max enemies with one bomb = " + maxEnemies());
        //int[] testArr = {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,1,2};
        //for (int i = 0; i < 20; i++) System.out.println("num = " + i + "    " + doesAppear(testArr, i));

        /*
        long start;
        long end;
        start = System.currentTimeMillis();
        spitPermuations("abcdefghijklmn");
        end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
        */

        Item[] testI = {new Item(3,25),new Item(2,20), new Item(1,15), new Item(4,40),new Item(5,50), new Item(1,15), new Item(4,40)};
        //buKnapSack(testI, 5);

        int m = 1000;
        int[][] table = {{0,2,m,1,8},
                         {6,0,3,2,m},
                         {m,m,0,4,m},
                         {m,m,2,0,3},
                         {3,m,m,m,0}};
        Floyd(table);
    }


    public static void gridSetup()
    {
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Tile();
            }
        }
        grid[0][0].setType('e');
        grid[0][3].setType('x');
        grid[1][0].setType('x');
        grid[1][1].setType('x');
        grid[1][3].setType('x');
        grid[2][2].setType('e');
        grid[3][1].setType('x');
    }

    public static int maxEnemies()
    {
        gridSetup();
        setEnemies();
        return (int)getEnemies();
    }

    public static void setEnemies()
    {
        int n = grid.length;
        byte currentType = ' ', active = 1;
        for(int i = 0; i < n; i++)
        {
            //set left to right
            for(int j = 1; j < n; j++)
            {
                if (grid[i][j].getType() == ' ')
                {
                    if ((grid[i][j-1].getType() == 'e') || (grid[i][j-1].getELeft() != 0)) grid[i][j].setELeft(active);
                }
            }
            // set right to left
            for(int j = n-2; j >= 0; j--)
            {
                if (grid[i][j].getType() == ' ')
                {
                    if ((grid[i][j+1].getType() == 'e') || (grid[i][j+1].getERight() != 0)) grid[i][j].setERight(active);
                }
            }
        }
        for(int j = 0; j < n; j++)
        {
            //set up to down
            for(int i = 1; i < n; i++)
            {
                if (grid[i][j].getType() == ' ')
                {
                    if ((grid[i-1][j].getType() == 'e') || (grid[i-1][j].getEUp() != 0)) grid[i][j].setEUp(active);
                }
            }
            // set down to up
            for(int i = n-2; i >= 0; i--)
            {
                if (grid[i][j].getType() == ' ')
                {
                    if ((grid[i+1][j].getType() == 'e') || (grid[i+1][j].getEDown() != 0)) grid[i][j].setEDown(active);
                }
            }
        }
    }

    public static byte getEnemies()
    {
        byte highCount = -1;
        for(Tile[] col : grid)
        {
            for(Tile elem : col)
            {
                highCount = (byte)Math.max(elem.getETotal(), highCount);
                System.out.print(elem.getETotal() + " ");
            }
            System.out.println();
        }
        return highCount;
    }


    public static int fib(int n)
    {
        if ((n == 0) || (n == 1)) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static boolean doesAppear(int[] circArray, int key)
    {
        // error checking, for empty array
        if (circArray.length == 0)
        {
            return false;
        }
        else if (circArray.length == 1)
        {
            if (circArray[0] == key)
            {
                return true;
            }
            else
            {
                return false;
            }
        } else {
            Indices indices = new Indices(0, circArray.length - 1);
            int s = 0, e = circArray.length - 1;

            while (s < e){
                System.out.println("Start = " + s + "   End = " + e);
                if ((circArray[s] == key) || (circArray[e] == key)) return true;
                if (splitter(circArray, s, --e, key))
                {
                    e = (e / 2);
                }
                else
                {
                    s = e/2 + s/2;
                }

                //indices = new Indices(s,e);
            }
            return false;
        }
    }

    private static boolean splitter(int[] circArray, int start, int end, int key)
    {
        // {4, 5, 6, 7, 8, 9, 1, 2, 3}
        // {9, 1, 2, 3, 4, 5, 6, 7, 8}
        boolean firstHalf;
        if ((key >= circArray[start]) && (key <= circArray[end / 2]))
        {
            //indices = new Indices(start, end / 2);
            firstHalf = true;
        }
        else if ((key > circArray[end / 2]) && (key <= end))
        {
            //indices = new Indices(end / 2, end);
            firstHalf = false;
        }
        else if ((key <= circArray[end / 2]) && (key > circArray[end]))
        {
            //indices = new Indices(end / 2, end);
            firstHalf = true;
        }
        else
        {
            //indices = new Indices(start, end / 2);
            firstHalf = false;
        }
        return firstHalf;
    }

    public static void spitPermuations(String inStr)
    {
        StringBuilder tempStr = new StringBuilder(inStr);
        StringBuilder delC;
        Queue<StringBuilder> permQ = new LinkedList<>();
        permQ.add(tempStr);
        while (permQ.peek().length() != 0)
        {
            tempStr = permQ.remove();
            System.out.println(tempStr.toString());
            for(int i = tempStr.length() - 1; i >= 0; i--)
            {
                delC = new StringBuilder(tempStr);
                permQ.add(delC.deleteCharAt(i));
                //System.out.println(permQ.remove().toString());
            }
            System.out.println(permQ.remove().toString());
            //System.out.println(permQ.remove().toString());
        }
    }

    public static void buKnapSack(Item[] item, int W){
        int i, w, n = item.length;
        int[][] K = new int[n+1][W+1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if (item[i-1].getWt() <= w)
                    K[i][w] = Math.max(item[i-1].getVal() + K[i-1][w-item[i-1].getWt()],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
            System.out.println(Arrays.toString(K[i]));
        }

        //return K[n][W];

    }

    public static void Floyd(int[][] masa)
    {
        // Assumes that masa is an NxN matrix
        int n = masa.length;
        for(int a = 0; a < n; a++)
        {
            for(int b = 0; b < n; b++)
            {
                for(int c = 0; c < n; c++)
                {
                    masa[b][c] = Math.min(masa[b][c], (masa[b][a] + masa[a][c]));
                }
            }
        }

        for(int[] row : masa)
        {
            for(int i : row)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
