public class DemoSugarSmash
{
    public static void main(String[] args)
    {
        //Test 1
        System.out.println("Test 1");
        SugarSmashPlayer ssPlayer = new SugarSmashPlayer();
        ssPlayer.setIdNumber(1111);
        ssPlayer.setName("Alex");
        ssPlayer.earnPoints();
        ssPlayer.earnPoints();
        System.out.println(ssPlayer.getName() + "(playerID: " + ssPlayer.getIdNumber()
                + ") has scored " + ssPlayer.getPoints() + " points.");
        System.out.println();

        //Test 2
        System.out.println("Test 2");
        ssPlayer.setPoints(123456789);
        System.out.println(ssPlayer.getName() + "(playerID: " + ssPlayer.getIdNumber()
                + ") has hacked the game and set their score to " + ssPlayer.getPoints() + "!");
        System.out.println();

        //Test 3
        System.out.println("Test 3");
        PremiumSugarSmashPlayer pssPlayer = new PremiumSugarSmashPlayer();
        pssPlayer.setIdNumber(2222);
        pssPlayer.setName("Cory");
        pssPlayer.earnPoints();
        pssPlayer.earnPoints();
        pssPlayer.earnPoints();
        System.out.println(pssPlayer.getName() + "(playerID: " + pssPlayer.getIdNumber()
                + ") has scored " + pssPlayer.getPoints() + " points.");
        System.out.println();

        //Test 4
        System.out.println("Test 4");
        pssPlayer.earnPoints();
        System.out.println(pssPlayer.getName() + "(playerID: " + pssPlayer.getIdNumber()
                + ") has scored " + pssPlayer.getPoints() + " points.");
        System.out.println();

        //Test 5
        System.out.println("Test 5");
        pssPlayer.buyBoosters();
        pssPlayer.earnPoints();
        System.out.println(pssPlayer.getName() + "(playerID: " + pssPlayer.getIdNumber()
                + ") has scored " + pssPlayer.getPoints() + " points.");
        System.out.println();
    }
}