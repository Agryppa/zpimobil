package pl.edu.pwr.zpiclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static pl.edu.pwr.zpiclient.MainActivity.apiUri;

public class WorkerMainActivity extends AppCompatActivity {
    private TextView workerName, position;
    private ImageView photo;
    private Button taskList,groupList,leaveList;
    public static ArrayList<Task> tasks=null; //lista zadan pobierana za kazdym odpaleniem worker main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);
        setContent();
        setListeners();
        RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
        try{
        //ArrayList<Task> response = restTemplate.getForEntity("https://10.0.2.2:8434/api/taskList", ArrayList.class).getBody();
            //exchange get with session ID elo benc
            ArrayList<LinkedHashMap> response = restTemplate.exchange(apiUri+"taskList", HttpMethod.GET, new HttpEntity<String>(RTemplate.getInstance().sessionHeaders), ArrayList.class).getBody();

            //tasks=response;
            tasks = new ArrayList<>();
            for (int i = 0; i < response.size(); i++) {
                LinkedHashMap hm = (LinkedHashMap) response.get(i);
                Task t = new Task((int) (hm.get("idTask")), (String) (hm.get("name")), (String) (hm.get("description")),
                        (String) (hm.get("data")), (int) (hm.get("idSupTask")), (int) (hm.get("idManager")),
                        (double) (hm.get("progress")), (int) (hm.get("idStatus")));
                tasks.add(t);
            }
            //Toast.makeText(getApplicationContext(), response.toString(),Toast.LENGTH_SHORT).show();
        }catch (Exception e){
        }

    }

    private void setContent(){
        workerName=findViewById(R.id.workerNameText);
        position=findViewById(R.id.positionText);

        photo=findViewById(R.id.workerPhotoView);

        taskList=findViewById(R.id.taskListButton);
        groupList=findViewById(R.id.groupListButton);
        leaveList=findViewById(R.id.leaveListButton);
    }

    private void setListeners(){
        taskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerMainActivity.this,WorkerTaskListActivity.class));
            }
        });

        groupList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerMainActivity.this,WorkerGroupListActivity.class));
            }
        });

        leaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerMainActivity.this,WorkerLeaveActivity.class));
            }
        });
    }
}
