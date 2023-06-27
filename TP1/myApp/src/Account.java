public class Account {

    public String name;
    String pwd;
    Boolean logged_in;

    Account(String name, String pwd){
        this.name=name;
        this.pwd=pwd;
        this.logged_in= false;

    }

}