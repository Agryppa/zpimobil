package pl.edu.pwr.zpiclient;



import java.util.ArrayList;

public class MockDB {
    private static MockDB ourInstance = new MockDB();

    private ArrayList<User> userList;

    public static MockDB getInstance() {
        if(ourInstance == null){
            return new MockDB();
        }
        return ourInstance;
    }

    private MockDB() {
        userList = new ArrayList<>();
        //userList.add(new Manager(1L, "Jan", "Kowalski", "manag","manag1"));
        userList.add(new Worker(2L, "Jerzy", "Jarzy", "work", "work1"));
        userList.add(new Worker(2L, "Jan", "Maria", "jan", "mar1a"));
    }

    /*public Manager getManagerByLoginPassword(String login, String password){
        for (User user:userList) {
            if(login.equals(user.getLogin()) && password.equals(user.getPassword())){
                return (Manager) user;
            }
        }
        return null;
    }
    public Manager getByUsername(String login){
        for (User user:userList) {
            if(login.equals(user.getLogin())){
                return (Manager) user;
            }
        }
        return null;
    }*/
    public Worker getWorkerByLoginPassword(String login, String password){
        for (User user:userList) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return (Worker) user;
            }
        }
        return null;
    }

}

