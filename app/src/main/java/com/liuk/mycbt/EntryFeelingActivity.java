package com.liuk.mycbt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;

import java.util.Arrays;

public class EntryFeelingActivity extends Activity {

    private LinearLayout feelings_wall;
    private Button next_button;
    private static final String[] FEELINGS = {"Happy", "Sad", "Depressed", "Anxious",
            "Guilty", "Calm", "Lonely", "Tired", "Nervous", "Content",
            "Hopeful", "Hopeless", "Hollow", "Pained", "Inadequate",
            "Worthless", "Nervous", "Overjoyed", "Excited", "Proud",
            "Panicked", "Angry", "Frustrated", "Neutral"};
    private AutoCompleteTextView feelings_input;
    private ImageButton info_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_feeling);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, FEELINGS);
        feelings_input = (AutoCompleteTextView)findViewById(R.id.feelings_input);
        feelings_input.setAdapter(adapter);

        feelings_wall = (LinearLayout) findViewById(R.id.feeling_list);


        feelings_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                addFeeling(text);
                feelings_input.setText("");
            }

        });

        //to delete suggestion text
        feelings_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                feelings_input.setText("");
                return false;
            }
        });

        feelings_input.addTextChangedListener(new TextWatcher() {
                                                  public void afterTextChanged(Editable s) {
                                                  }

                                                  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                  }

                                                  public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                      if (start >= 0 && s.length() >= 1 && s.charAt(start) == ' ') {
                                                          addFeeling(s.toString());
                                                          feelings_input.setText("");
                                                      }
                                                  }
                                              }
        );

        info_button = (ImageButton) findViewById(R.id.infobutton);
    }

    private TextView addFeeling(String feeling){
        TextView f = new TextView(this);
        f.setText(feeling);
        f.setTextSize(18);
        f.setClickable(true);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feelings_wall.removeView(v);
            }
        });

        LinearLayout.LayoutParams f_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        f.setLayoutParams(f_params);
        f_params.setMargins(20, 20, 20, 20);
        feelings_wall.addView(f,f_params);
        return f;
    }


    //Event Handlers
    public void addFeeling(View v){
        //enable sliders
        //append input field to label new feeling
        //generateFeeling with the string
        LinearLayout t = (LinearLayout) findViewById(R.id.new_feeling_section);
        t.setVisibility(View.VISIBLE);

        if(feelings_input.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(feelings_input, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    public void endPage(View v) {
        // save list of selected feelings, insert to database
        // save new / custom feeling if exists, insert to database

        startActivity(new Intent(this, EntryDescriptionActivity.class));
    }

    public void showInfo(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Explanation")
                .setTitle("Help");

        AlertDialog dialog = builder.create();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_feeling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
