package restaurant.rest_app_v013;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")

public class Reservation_Activity extends AppCompatActivity {

    JSONParser jsonParser;
    ProgressDialog progressDialog;
    int value;
    String[] names, emails, mobiles, ids;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        jsonParser = new JSONParser();
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
            }
        });
        new DisplayAllTask().execute();
    }


    class DisplayAllTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Reservation_Activity.this);
            progressDialog.setTitle("Wait...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            List<NameValuePair> list = new ArrayList<NameValuePair>();


            JSONObject jsonObject = jsonParser.makeHttpRequest("http://dacie.fr/tests/resto/PHP/Reservations.php", "POST", list);

            try {
                if (jsonObject != null && !jsonObject.isNull("value")) {
                    value = jsonObject.getInt("value");
                    JSONArray jsonArray = jsonObject.getJSONArray("students");
                    names = new String[jsonArray.length()];
                    emails = new String[jsonArray.length()];
                    mobiles = new String[jsonArray.length()];
                    ids = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objcet = jsonArray.getJSONObject(i);
                        ids[i] = objcet.getString("ID");
                        names[i] = objcet.getString("Name");
                        emails[i] = objcet.getString("Email");
                        mobiles[i] = objcet.getString("Mobile");
                    }
                } else {
                    value = 0;
                }

            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (value == 1) {
                Toast.makeText(getApplicationContext(), "Done...", Toast.LENGTH_LONG).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Reservation_Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }
    }
}