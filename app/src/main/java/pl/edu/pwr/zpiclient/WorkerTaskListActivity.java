package pl.edu.pwr.zpiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static pl.edu.pwr.zpiclient.WorkerMainActivity.tasks;

public class WorkerTaskListActivity extends AppCompatActivity {

    private Button summaryButton, settingsButton;
    private TextView activeText,plannedText;
    private ListView activeList,plannedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_task_list);
        setContents();
        setListeners();

        setData();
    }

    private void setContents(){
        summaryButton=findViewById(R.id.summaryButton);
        settingsButton=findViewById(R.id.settingsButton);

        activeText=findViewById(R.id.activeTaskText);
        plannedText=findViewById(R.id.plannedTaskText);

        activeList=findViewById(R.id.activeListView);
        plannedList=findViewById(R.id.plannedListView);
    }

    private void setListeners(){
        //TODO
    }

    private void setData(){
        //TODO to jest tylko mockup.

        ArrayList<Task> aktywne = tasks;
        ArrayList<Task> planowane = tasks;
        Task task;
        /*task=new Task("Pierwszy", "10.02.2019",1);
        aktywne.add(task);
        task=new Task("Drugi", "11.02.2019",2);
        aktywne.add(task);
        task=new Task("Trzeci", "12.02.2019",1);
        aktywne.add(task);
        task=new Task("Pierwszy2", "10.12.2019",2);
        aktywne.add(task);
        task=new Task("Drugi2", "11.12.2019",1);
        aktywne.add(task);
        task=new Task("Trzeci2", "12.12.2019",1);
        aktywne.add(task);*/

        /*task=new Task("Czwarty", "14.02.2019",1);
        planowane.add(task);
        task=new Task("Piaty", "15.02.2019",2);
        planowane.add(task);
        task=new Task("Szosty", "16.02.2019",1);
        planowane.add(task);*/

        TaskListAdapter aktywnyAdapter = new TaskListAdapter(this, aktywne, true);
        TaskListAdapter planowanyAdapter = new TaskListAdapter(this, planowane,false);

        activeList.setAdapter(aktywnyAdapter);
        plannedList.setAdapter(planowanyAdapter);
    }
}
