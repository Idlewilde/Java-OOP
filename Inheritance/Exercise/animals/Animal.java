package animals;

public class Animal {
    private String name;
    private String gender;
    private int age;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        if(name.trim().isEmpty()){throw new IllegalArgumentException("Invalid input!");}
        this.name = name;
    }

    private void setGender(String gender) {
        if(gender.trim().isEmpty()){throw new IllegalArgumentException("Invalid input!");}
        this.gender = gender;
    }

    private void setAge(int age) {
        if(age<0){throw new IllegalArgumentException("Invalid input!");}

        this.age = age;
    }

    public String produceSound() {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                +System.lineSeparator()
                +this.name+" "+this.age+" "+this.gender
                +System.lineSeparator()
                +this.produceSound();
    }
}
