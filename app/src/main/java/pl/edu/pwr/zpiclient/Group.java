package pl.edu.pwr.zpiclient;

public class Group {
    private long IdGroup;
    private String Name;
    private String Description;
    private int workerId;

    public long getIdGroup() {
        return IdGroup;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getWorkerId() {
        return workerId;
    }

    public Group() {
        this.IdGroup = -1;
        this.Name = "";
        this.Description = "";
    }

    public Group(long IdGroup, String Name, String Description) {
        this.IdGroup = IdGroup;
        this.Name = Name;
        this.Description = Description;
    }

    public Group(long IdGroup, String Name, String Description, int workerId) {
        this.IdGroup = IdGroup;
        this.Name = Name;
        this.Description = Description;
        this.workerId = workerId;
    }
}
