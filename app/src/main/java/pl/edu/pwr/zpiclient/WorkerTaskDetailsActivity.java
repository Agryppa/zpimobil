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

public class WorkerTaskDetailsActivity extends AppCompatActivity {
    ArrayList<Resource>resources=null;//lista wszystkich surowców z przypisanymi idtask
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_task_details);
        RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
        try {
            ArrayList<LinkedHashMap> response = restTemplate.exchange(apiUri + "resourceList", HttpMethod.GET, new HttpEntity<String>(RTemplate.getInstance().sessionHeaders), ArrayList.class).getBody();
            resources = new ArrayList<>();
            for (int i = 0; i < response.size(); i++) {
                LinkedHashMap hm = (LinkedHashMap) response.get(i);
                Resource r = new Resource((int) (hm.get("idResource")), (String) (hm.get("name")), (String) (hm.get("description")),
                        (int) (hm.get("idTask")));
                resources.add(r);
            }
            Toast.makeText(getApplicationContext(), resources.get(0).getName(), Toast.LENGTH_SHORT).show();
        }catch(Exception e){}
    }
}
