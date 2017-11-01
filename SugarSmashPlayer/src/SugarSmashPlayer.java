public class SugarSmashPlayer
{
    private int idNumber;
    private String name;
    private int points;

    public SugarSmashPlayer() {
        idNumber = 123456;
        name = "Raul";
        points = 0;
    }

    public SugarSmashPlayer(int idNumber, String name, int points) {
        this.idNumber = idNumber;
        this.name = name;
        this.points = points;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void earnPoints(){

        points = getPoints() + 100;

    }
}