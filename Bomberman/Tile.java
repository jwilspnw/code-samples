public class Tile {
    private byte eUp, eDown, eLeft, eRight;
    private char type;

    public Tile(){
        this.eUp = 0;
        this.eDown = 0;
        this.eLeft = 0;
        this.eRight = 0;
        this.type = ' ';
    }

    public Tile(char type){
        this();
        this.type = type;
    }

    public void setEUp(byte eUp)
    {
        this.eUp = eUp;
    }
    public void setEDown(byte eDown)
    {
        this.eDown = eDown;
    }
    public void setELeft(byte eLeft)
    {
        this.eLeft = eLeft;
    }
    public void setERight(byte eRight)
    {
        this.eRight = eRight;
    }
    public void setType(char type){
        this.type = type;
    }

    public byte getEUp()
    {
        return eUp;
    }
    public byte getEDown()
    {
        return eDown;
    }
    public byte getELeft()
    {
        return eLeft;
    }
    public byte getERight()
    {
        return eRight;
    }

    public byte getETotal()
    {
        return (byte)(eUp + eDown + eLeft + eRight);
    }

    public char getType()
    {
        return type;
    }
}