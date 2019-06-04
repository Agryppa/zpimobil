package pl.edu.pwr.zpiclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String apiUri="https://192.168.0.73:8434/api/";
    private RestTemplate rest = new RestTemplate();
    public static Worker thisWorker=null;//tutaj są dane naszego workera w tym id


    //private String url = "http://localhost:8090/api/";
    //z emulatora 10.0.2.2
    //private String url = "http://10.0.2.2:8090/";
void getUserId(String login){
    RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
    //try{

        ArrayList<LinkedHashMap> response = restTemplate.exchange(apiUri+"workerList", HttpMethod.GET, new HttpEntity<String>(RTemplate.getInstance().sessionHeaders), ArrayList.class).getBody();



        for (int i = 0; i < response.size(); i++) {
            LinkedHashMap hm = (LinkedHashMap) response.get(i);
            if(login.equals((String)(hm.get("login")))){
                thisWorker=new Worker((int)(hm.get("id")),(String)(hm.get("name")),(String)(hm.get("surname")),(String)(hm.get("login")),
                    (String)(hm.get("password")), (String)(hm.get("position")), (int)(hm.get("idStatus")));
            }


        }
        Toast.makeText(getApplicationContext(), thisWorker.getName(),Toast.LENGTH_SHORT).show();
    //}catch (Exception e){}
}


String login(Worker w){
    try {
        //final String uri = "https://10.0.2.2:8434/api/login";
        //final String uri = "https://192.168.0.73:8434/api/login";
        final String uri=apiUri+"login";
        RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
        //RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        //map.add("username", w.name);
        //map.add("password", w.password);
        map.add("username", "work");
        map.add("password", "worker1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        SSLUtil.turnOffSslChecking();
        //String tmp = restTemplate.postForObject(uri, request, String.class);
        ResponseEntity<String> tmp = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
        String cookie = tmp.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", cookie);
        RTemplate.getInstance().sessionHeaders = requestHeaders;
        Toast.makeText(getApplicationContext(), tmp.getBody(),Toast.LENGTH_SHORT).show();

        return tmp.getBody();



    } catch (Exception e) {
        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }
    return null;
}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Gość");
            setSupportActionBar(toolbar);

            final Button loginButton = findViewById(R.id.LoginButton);
            final TextView loginText = findViewById(R.id.LoginText);
            final TextView passwordText = findViewById(R.id.PassText);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //loginText.setText("");
                    Worker temp = new Worker();
                    temp.setLogin(loginText.getText().toString());
                    temp.setPassword(passwordText.getText().toString());
                    String response=login(temp);
                    getUserId("work");
                    if("loginSuccess".equals(response)){
                        startActivity(new Intent(MainActivity.this, WorkerMainActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Niepoprawne dane logowania.", Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(MainActivity.this, RTemplate.getInstance().sessionHeaders.get("Cookie").toString(), Toast.LENGTH_SHORT);
                    RestTemplate restTemplate = RTemplate.getInstance().restTemplate;


                    /*try {
                        String response = (String) new Http_findLogin_Task().execute().get();
                        //loginText.setText(((User) response).toString())
                        if("loginSuccess".equals(response)){
                            startActivity(new Intent(MainActivity.this, WorkerMainActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this, "Niepoprawne dane logowania.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {

                        //e.printStackTrace();

                    } catch (ExecutionException e) {

                        //e.printStackTrace();

                    }*/
                }
            });
        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Toast.makeText(MainActivity.this, "Pola nie mogą być puste", Toast.LENGTH_SHORT).show();
            }

            return super.onOptionsItemSelected(item);
        }

   /* private void launch(Object res){

        //String pos = ((User)res).idSt;
        //UserInfo.login = ((User)res).id;
        UserInfo.user = (User)res;
        String pos = UserInfo.user.idSt;
        if(pos != null) {
            switch (pos) {
                case ("1"):
                    startActivity(new Intent(MainActivity.this, ManagerMain.class));
                    break;
                case ("2"):
                    //startActivity(new Intent(this, WorkerMainActivity.class));
                    //TODO activity obsługujące widok pracownika.
                    break;
                default:
                    break;
            }
        }

    }*/
    private class Http_findLogin_Task extends AsyncTask  {

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                //final String uri = "https://10.0.2.2:8434/api/login";
                //final String uri = "https://192.168.0.73:8434/api/login";
                final String uri = apiUri+"login";
                RestTemplate restTemplate = RTemplate.getInstance().restTemplate;
                //RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("username", "work");
                map.add("password", "worker1");

                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

                SSLUtil.turnOffSslChecking();
                //String tmp = restTemplate.postForObject(uri, request, String.class);
                ResponseEntity<String> tmp = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
                String cookie = tmp.getHeaders().get("Set-Cookie").get(0);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.add("Cookie", cookie);
                RTemplate.getInstance().sessionHeaders = requestHeaders;



                return tmp.getBody();


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }

}
