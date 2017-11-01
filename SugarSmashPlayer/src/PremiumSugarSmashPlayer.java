public class PremiumSugarSmashPlayer extends SugarSmashPlayer
{
    private int boosters;


    public PremiumSugarSmashPlayer()
    {
        boosters = 3;

    }

    // override (public!) earnPoints() method here
    @Override
    public void earnPoints()
    { if(boosters > 0){
        super.setPoints(super.getPoints() + 500);

        boosters -= 1;
        }
        else{
        System.out.println("Out of boosters!");
        super.setPoints(super.getPoints() + 100);
    }

    }

    public void buyBoosters()
    {
        boosters = 3;

    }
}