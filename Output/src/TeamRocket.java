public class TeamRocket
{
    private String motto;

    public void setMotto(String mottoLine)
    {
        motto = mottoLine;
    }

    public String getMotto()
    {
        return motto;
    }

    public void blastOff()
    {
        System.out.println("Looks like Team Rocket's blasting off again! *ding*");
    }
}