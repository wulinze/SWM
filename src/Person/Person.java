package Person;


public class Person {
    private String name;
    private String comment;
    private boolean in,select;


    public Person(String name,String comment){
        this.name = name;
        this.comment = comment;
        this.in = false;
        this.select = false;
    }

    public void setName(String name){this.name = name;}
    public void setComment(String comment){this.comment = comment;}
    public void setIn(boolean in){this.in = in;}
    public void setSelect(boolean select){this.select = select;}

    public String getName(){return this.name;}
    public String getComment(){return this.comment;}
    public boolean getIn(){return this.in;}
    public boolean getSelect(){return this.select;}
}
