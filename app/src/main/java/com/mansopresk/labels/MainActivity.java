package com.mansopresk.labels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    EditText et_user, et_pass, et_date;
    Spinner spin;
    CheckBox cb;
    SeekBar seek;
    ToggleButton tb;
    Switch aSwitch;
    RadioGroup rg;
    RatingBar r_bar;

    String countries[] = {"India", "USA", "Australia"};

    String spinner,radio,name,user,pass;

    float rating;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user = findViewById(R.id.user_et);
        et_pass = findViewById(R.id.pass_et);
        et_date = findViewById(R.id.date);
        spin = findViewById(R.id.spin);
        cb = findViewById(R.id.cb);
        seek = findViewById(R.id.seek);
        tb = findViewById(R.id.toggle);
        aSwitch = findViewById(R.id.switch_btn);
        rg = findViewById(R.id.rg);
        r_bar = findViewById(R.id.rating);

        //spinner code
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countries);
        spin.setAdapter(arrayAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                spinner = spin.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //toggle button code
        tb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                toggle = tb.getText().toString().trim();
//                Toast.makeText(getApplicationContext(), "" +toggle, Toast.LENGTH_SHORT).show();
            }
        });

        //switch code
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        //date code to get current system date
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        et_date.setText(s);

        //seek bar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //shared preferences




//        if(editor != null)
//        {
//           // name = prefs.getString("username", user);
//            Intent i = new Intent(this,NavActivity.class);
//            startActivity(i);
//        }

        sharedpreferences = getSharedPreferences("details",MODE_PRIVATE);
        String user = sharedpreferences.getString("username", null);
        String pass = sharedpreferences.getString("password", null);

        if(user!=null)
        {
            Intent i=new Intent(this,NavActivity.class);
            startActivity(i);
        }

//        sharedpreferences = getSharedPreferences("userdetails",MODE_PRIVATE);
//        String uname = sharedpreferences.getString("username",null);
//        if(uname!=null){
//            Intent i=new Intent(this,NavActivity.class);
//            startActivity(i);
//        }


}

    public void login(View v)
    {
         user = et_user.getText().toString().trim();
         pass = et_pass.getText().toString().trim();

        if(cb.isChecked())
        {
            if(user.isEmpty())
            {
                et_user.requestFocus();
                et_user.setError("Empty");
            }
            else if(pass.isEmpty())
            {
                et_pass.requestFocus();
                et_pass.setError("Empty");
            }
            else
            {
                editor = getSharedPreferences("details", MODE_PRIVATE).edit();
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.apply();
                editor.commit();

                if(sharedpreferences!=null) {
                    Intent i = new Intent(MainActivity.this, NavActivity.class);
                    startActivity(i);
                }

                // Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "You need to accept terms and conditions", Toast.LENGTH_SHORT).show();
        }

    }

    public void clear(View v)
    {
        et_user.setText("");
        et_pass.setText("");
    }

    public void validate(View v)
    {
        radio = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        rating = r_bar.getRating();

        Toast.makeText(this, ""+radio+"\n"+rating+"\n"+spinner, Toast.LENGTH_SHORT).show();
    }

    public void test(View v)
    {
        if(tb.isChecked() &&(aSwitch.isChecked()))
        {
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();
        }
    }

}
