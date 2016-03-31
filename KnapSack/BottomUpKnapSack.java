import java.util.Arrays;

public class BottomUpKnapSack
{
    
    public static void main(String[] args)
    {
        // Create an array of available items with weight and value
        Item[] testI = {new Item(3,25),new Item(2,20), new Item(1,15), new Item(4,40),new Item(5,50), new Item(1,20)};
        
        // Check maximum value that can be carried with a weight capacity of 5
        buKnapSack(testI, 5);
    }
    
    public static void buKnapSack(Item[] item, int W)
    {
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
    }
}