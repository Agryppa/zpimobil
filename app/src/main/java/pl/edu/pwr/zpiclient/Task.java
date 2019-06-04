package pl.edu.pwr.zpiclient;


public class Task {

    protected int idTask;
    protected String name;
    protected String description;
    protected String data;
    protected int idSupTask;
    protected int idManager;
    protected double progress;
    protected int idStatus;

    public Task() {
    }

    public Task(int idTask, String name, String description, String data, int idSupTask, int idManager, double progress, int idStatus) {
        this.idTask = idTask;
        this.name = name;
        this.description = description;
        this.data = data;
        this.idSupTask = idSupTask;
        this.idManager = idManager;
        this.progress = progress;
        this.idStatus = idStatus;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdSupTask() {
        return idSupTask;
    }

    public void setIdSupTask(int idSupTask) {
        this.idSupTask = idSupTask;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }




}