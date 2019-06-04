package pl.edu.pwr.zpiclient;

public class Worker extends User {

    String position;
    long idStatus;

    public Worker(){
        this.id = -1L;
        this.login = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.position = "";
        this.idStatus = 0;
    }
    public Worker(long id, String name, String surname, String login, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.position = "";
        this.idStatus = 0;
    }
    public Worker(long id, String name, String surname, String login, String password, String position, long idStatus){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.position = position;
        this.idStatus = idStatus;
    }

    public boolean getByLoginPassword(){
        if(this.login == null || this.password == null || this.login.isEmpty() || this.password.isEmpty()){
            return false;
        }
        Worker worker = MockDB.getInstance().getWorkerByLoginPassword(this.login, this.password);
        if(worker != null){
            this.id = worker.id;
            this.name = worker.name;
            this.surname = worker.surname;
            this.password = "";
            return true;
        }
        return false;
    }
    public void save(){

    }
    public boolean fieldsHaveErrors(){
        return false;
    }
}

