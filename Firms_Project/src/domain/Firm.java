package domain;

public class Firm implements Identifiable<String> {
    private String id;
    private String name;


    public Firm (String id, String n){
        this.id=id;
        this.name=n;

    }

    public Firm (){
        this.id= "";
        this.name="";
    }

    @Override
    public String toString(){
        return "Firm with id: " + this.id + ", named: " + this.name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
