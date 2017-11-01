public class Patient {


    private String id;
    private int age;
    private String bType;
    private String rhFactor;

    public Patient(String id, int age, String bType, String rhFactor) {
        this.id = id;
        this.age = age;
        this.bType = bType;
        this.rhFactor = rhFactor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }
}
