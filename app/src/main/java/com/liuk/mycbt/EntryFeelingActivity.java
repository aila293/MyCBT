package com.liuk.mycbt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EntryFeelingActivity extends Activity {

    RelativeLayout feelings_wall;
    Button next_button;
    String[] feelings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_feeling);

        feelings_wall = (RelativeLayout) findViewById(R.id.feelings_wall);
        next_button = (Button) findViewById(R.id.to_description);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // save list of selected feelings, insert to database
                // save new / custom feeling, insert to database
                nextActivity(v);
            }
        });

        populateFeelings();

    }

    private void populateFeelings(){
        String[] feelings = {"Happy", "Sad", "Depressed", "Anxious",
                "Guilty", "Calm", "Lonely", "Tired", "Nervous", "Content",
                "Hopeful", "Hopeless", "Hollow", "In Pain", "Inadequate",
                "Worthless", "Nervous", "Overjoyed", "Excited", "Proud",
                "Panicked", "Angry", "Frustrated", "Neutral"};

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i=0;i<feelings.length;i++){
            TextView f = new TextView(this);
            f.setText(feelings[i]);
            f.setId(i);
            if (i>0) {
                params.addRule(RelativeLayout.RIGHT_OF, i-1);
                f.setLayoutParams(params);
                f.setPadding(0, 0, 20, 0);
            } else {
                f.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }

            feelings_wall.addView(f);
        }
    }

    private void nextActivity(View view){
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
