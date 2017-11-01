public class Jessie extends TeamRocket
{
    public void wobuffet()
    {
        System.out.println("Wobuffet, use your counter attack!");
    }

    @Override
    public void setMotto(String mottoLine)
    {
        System.out.println(mottoLine);
        mottoLine = "To denounce the evils of truth and love!";
        super.setMotto(mottoLine);
    }
}