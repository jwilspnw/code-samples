import java.util.Random;

public class BombermanBoard {

    public static void main(String[] args)
    {
        Tile[][] board;
        /*if (args.length == 0)
        {
            board = new Tile[args[0]][args[0]];
        }
        else
        {*/
            board = new Tile[15][15];
        //}
        
        board = buildBoard(board);
        
        System.out.println("Max enemies defeated with one bomb on " + board.length + "x" + board[0].length + " board");
        displayBoard(board);
        System.out.println("Is: "+ maxEnemies(board));
    }
    
    public static void displayBoard(Tile[][] board)
    {
        for(Tile[] x : board)
        {
            System.out.print("| ");
            for(Tile y : x)
            {
                System.out.print(y.getType() + " ");
            }
            System.out.println("|");
        }
    }
    
    public static Tile[][] buildBoard(Tile[][] board)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++) board[i][j] = setRandTile(board[i][j]);
        }
        return board;
    }
    
    public static Tile setRandTile(Tile emptyTile)
    {
        Random rand = new Random();
        int tileTyper = rand.nextInt();
        
        // There are 3 different types of type possible: e (enemies), x (walls) and open spaces
        if ((tileTyper % 3) == 2)
        {
            emptyTile = new Tile('e');
        }
        else if ((tileTyper % 3) == 1)
        {
            emptyTile = new Tile('x');
        }
        else
        {
            emptyTile = new Tile();
        }
        return emptyTile;
    }
    
    public static int maxEnemies(Tile[][] board)
    {
        setEnemies(board);
        return (int)getEnemies(board);
    }
    
    public static void setEnemies(Tile[][] board)
    {
        int n = board.length;
        byte currentType = ' ', active = 1;
        
        for(int i = 0; i < n; i++)
        {
            //set left to right
            for(int j = 1; j < n; j++)
            {
                if (board[i][j].getType() == ' ')
                {
                    if ((board[i][j-1].getType() == 'e') || (board[i][j-1].getELeft() != 0)) board[i][j].setELeft(active);
                }
            }
            // set right to left
            for(int j = n-2; j >= 0; j--)
            {
                if (board[i][j].getType() == ' ')
                {
                    if ((board[i][j+1].getType() == 'e') || (board[i][j+1].getERight() != 0)) board[i][j].setERight(active);
                }
            }
        }
        
        for(int j = 0; j < n; j++)
        {
            //set up to down
            for(int i = 1; i < n; i++)
            {
                if (board[i][j].getType() == ' ')
                {
                    if ((board[i-1][j].getType() == 'e') || (board[i-1][j].getEUp() != 0)) board[i][j].setEUp(active);
                }
            }
            // set down to up
            for(int i = n-2; i >= 0; i--)
            {
                if (board[i][j].getType() == ' ')
                {
                    if ((board[i+1][j].getType() == 'e') || (board[i+1][j].getEDown() != 0)) board[i][j].setEDown(active);
                }
            }
        }
    }

    public static byte getEnemies(Tile[][] board)
    {
        byte highCount = -1;
        for(Tile[] col : board)
        {
            for(Tile elem : col) highCount = (byte)Math.max(elem.getETotal(), highCount);
        }
        return highCount;
    }
}