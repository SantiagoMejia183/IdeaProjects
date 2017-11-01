public class Meowth extends TeamRocket
{
    private String attack;

    public void setAttack(String currentAttack)
    {
        attack = currentAttack;
    }

    public String getAttack()
    {
        return attack;
    }

    public void blastOff(String newBlastOffMessage) {
        System.out.println(newBlastOffMessage);
    }
}