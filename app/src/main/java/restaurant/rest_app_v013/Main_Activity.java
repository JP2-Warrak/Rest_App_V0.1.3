package restaurant.rest_app_v013;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button) findViewById(R.id.Nouvelle_Table_Button);
        Button appel = (Button) findViewById(R.id.Table_en_Cours_Button);
        Button display = (Button) findViewById(R.id.Reservations_Button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Nouvelle_Table_Activity.class);
                startActivity(i);
            }
        });
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Table_en_Cours_Activity.class);
                startActivity(i);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Reservation_Activity.class);
                startActivity(i);
            }
        });

    }
}