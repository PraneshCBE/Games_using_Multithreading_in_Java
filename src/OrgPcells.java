
public class OrgPcells
{
    private final int value;
    public OrgPcells(int value)
    {
        this.value=value;
    }
    public int getValue()
    {
        return this.value;
    }
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }


}
