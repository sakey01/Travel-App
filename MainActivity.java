package travelapp.com;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button bSubmit, bClear;
    RadioButton bTrain, bBoat, bPlane;
    RadioGroup radioGroup;
    Spinner spinner;
    TextView infoTitle, info;
    ArrayAdapter<String> adapter;
    ArrayList<String> choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bSubmit = findViewById(R.id.bSubmit);
        bClear = findViewById(R.id.bClear);
        bTrain = findViewById(R.id.trainb);
        bBoat = findViewById(R.id.boatb);
        bPlane = findViewById(R.id.planeb);
        radioGroup = findViewById(R.id.radioMain);
        spinner = findViewById(R.id.spinner);
        infoTitle = findViewById(R.id.infoTitle);
        info = findViewById(R.id.info);

        choice = new ArrayList<>();
        choice.add("No selection");

        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, choice);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        bBoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice.clear();
                choice.add("Canary Wharf Pier");
                choice.add("London Eye Cruise");
                choice.add("Thames River Sightseeing");
                adapter.notifyDataSetChanged();
            }
        });

        bTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice.clear();
                choice.add("Liverpool");
                choice.add("Birmingham");
                choice.add("Manchester");
                choice.add("Leeds");
                adapter.notifyDataSetChanged();
            }
        });

        bPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice.clear();
                choice.add("Tokyo");
                choice.add("Sydney");
                choice.add("New York");
                choice.add("Paris");
                adapter.notifyDataSetChanged();
            }
        });
        // set the listener for the submit button
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if a radio button is selected
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select a travel mode", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedChoice = spinner.getSelectedItem().toString();
                if (selectedChoice.equals("No selection")) {
                    infoTitle.setText("");
                    Toast.makeText(MainActivity.this, "Please select a destination", Toast.LENGTH_SHORT).show();
                    return;
                }
                // set the title and description based on the selected choice
                infoTitle.setText(selectedChoice);
                String resourceName = selectedChoice.toLowerCase().replace(" ", "");
                int resourceId = getResources().getIdentifier(resourceName, "string", getPackageName());

                if (resourceId != 0) {
                    info.setText(resourceId);
                } else {
                    info.setText("");
                }
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                choice.clear();
                choice.add("No selection");
                adapter.notifyDataSetChanged();
                infoTitle.setText("");
                info.setText("");
                Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
