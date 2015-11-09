package com.liuk.mycbt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_feeling);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, FEELINGS);
        feelings_input = (AutoCompleteTextView)findViewById(R.id.feelings_input);
        feelings_input.setAdapter(adapter);

        feelings_wall = (LinearLayout) findViewById(R.id.feeling_list);

//        TextView.OnEditorActionListener exampleListener = new TextView.OnEditorActionListener(){
//            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_NULL
//                        && event.getAction() == KeyEvent.ACTION_DOWN) {
//
//                    //on space/enter, convert most recent word to a tile and append to feelings_wall
//
//                }
//                return true;
//            }
//        };


        feelings_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Log.i("tag", text);
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

    }


    private void populateFeelings(){
        RelativeLayout.LayoutParams f_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        f_params.setMargins(20, 0, 0, 0);

        feelings_wall.addView(addFeeling(FEELINGS[0]), f_params);

        for (int i=1;i<FEELINGS.length;i++){
            TextView f = addFeeling(FEELINGS[i]);

            f_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            f_params.addRule(RelativeLayout.BELOW, i-3);
            f_params.setMargins(20,0,0,0);

            if (i%4!=0) {
                f_params.addRule(RelativeLayout.RIGHT_OF, i);
            }
            feelings_wall.addView(f, f_params);
        }

    }

    private TextView addFeeling(String feeling){
        TextView f = new TextView(this);
        f.setText(feeling);
        f.setTextSize(18);
        f.setClickable(true);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //allow deletion
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

       // t.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(t, InputMethodManager.SHOW_IMPLICIT);


        if(feelings_input.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(feelings_input, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    public void endPage(View v) {
        // save list of selected feelings, insert to database
        // save new / custom feeling if exists, insert to database

        Intent next = new Intent(this, EntryDescriptionActivity.class);
        startActivity(next);
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
