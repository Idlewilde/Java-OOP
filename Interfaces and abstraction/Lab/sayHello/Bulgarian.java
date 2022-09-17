package sayHello;

public class Bulgarian implements Person{
    public Bulgarian(String name) {
        this.name = name;
    }

    private String name;
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
