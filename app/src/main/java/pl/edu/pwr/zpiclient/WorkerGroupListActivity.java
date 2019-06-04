package pl.edu.pwr.zpiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import static pl.edu.pwr.zpiclient.MainActivity.apiUri;

public class WorkerGroupListActivity extends AppCompatActivity {
    public static ArrayList<Group> groups=null;//wszystkie grupy, przynajmniej na razie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_group_list);

        RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
        //try{

            ArrayList<LinkedHashMap> response = restTemplate.exchange(apiUri+"groupList", HttpMethod.GET, new HttpEntity<String>(RTemplate.getInstance().sessionHeaders), ArrayList.class).getBody();
            groups = new ArrayList<>();
            for (int i = 0; i < response.size(); i++) {
                LinkedHashMap hm = (LinkedHashMap) response.get(i);
                Group g = new Group((int) (hm.get("idGroup")), (String) (hm.get("name")), (String) (hm.get("description")),
                        (int) (hm.get("workerId")));
                groups.add(g);
            }
            Toast.makeText(getApplicationContext(), groups.get(0).getName(), Toast.LENGTH_SHORT).show();
        //}catch (Exception e){}

    }




}
