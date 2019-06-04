package pl.edu.pwr.zpiclient;

public class User {
    protected long id;
    protected String login;
    protected String password;
    protected String name;
    protected String surname;

    public User(){

    }
    public User(String login, String password, String name, String surname){
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
