public class CircularSearch
{
    public static void main(String[] args)
    {
        int[] circArray = {6,7,8,9,10,11,12,13,14,15,1,2,3,4,5};
        System.out.println(doesAppear(circArray, 9));
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
                System.out.println("Start = " + circArray[s] + "   End = " + circArray[e]);
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
}