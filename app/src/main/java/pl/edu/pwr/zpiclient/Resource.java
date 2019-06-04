package pl.edu.pwr.zpiclient;

public class Resource {
    private long IdResource;
    private String Name;
    private String Description;
    private int IdTask;

    public int getIdTask() {
        return IdTask;
    }

    public void setIdTask(int idTask) {
        IdTask = idTask;
    }

    public Resource() {
        this.IdResource = -1;
        this.Name = "";
        this.Description = "";
    }

    public Resource(long IdResource, String Name, String Description) {
        this.IdResource = IdResource;
        this.Name = Name;
        this.Description = Description;
    }

    public Resource(long IdResource, String Name, String Description, int idTask) {
        this.IdResource = IdResource;
        this.Name = Name;
        this.Description = Description;
        this.IdTask = idTask;
    }

    public long getIdResource() {
        return IdResource;
    }

    public void setIdResource(long idResource) {
        IdResource = idResource;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
