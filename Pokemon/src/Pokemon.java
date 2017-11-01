public class Pokemon {


private String species;
private int attack;
private int defense;
private int speed;


    public Pokemon(String species, int attack, int defense, int speed) {
        this.species = species;
        this.attack = species.length() * 4 + 2;
        this.defense = species.length()* 2 + 7;
        this.speed = species.length() * 3 + 5;
    }


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
