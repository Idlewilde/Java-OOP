package militaryElite;

public class SpyImpl extends SoldierImpl implements Spy{
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName,String codeNumber) {
        super(id, firstName, lastName);
        setCodeNumber(codeNumber);
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %d%nCode Number: %s", getFirstName(), getLastName(), getId(), codeNumber);
    }
}
