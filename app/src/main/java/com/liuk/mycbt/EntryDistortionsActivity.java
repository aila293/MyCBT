package com.liuk.mycbt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryDistortionsActivity extends Activity {

    private String description;
    private ExpandableListView distortion_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_distortions);

        //show description from previous page
        description = getIntent().getStringExtra(EntryDescriptionActivity.EXTRA_DESCRIPTION);
        TextView description_view = (TextView) findViewById(R.id.prev_description);
        description_view.setText(description);

        setDistortionlist();
    }

    private void setDistortionlist(){
        String[] distortions = {"Overgeneralization", "Dichotomous Thinking", "Mind-reading",
                "Disqualifying the Positive", "Emotional Reasoning", "Magnifying / Minifying",
                "Catastrophizing"};

        String[] distortion_descriptions = {"overgeneralization definition",
                "dichotomous thinking definition",
                "Mind-Reading definition",
                "Disqualifying the Positive",
                "Emotional Reasoning",
                "Magnifying / Minifying",
                "Catastrophizing"
        };

        //setting the parent data
        List<Map<String,String>> groupData = new ArrayList<Map<String, String>>();
        for (int i=0;i<distortions.length;i++){
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("ROOT", distortions[i]);
            groupData.add(item);
        }
        int groupLayout = android.R.layout.simple_expandable_list_item_1;
        String[] groupFrom = {"ROOT"};
        int[] groupTo = {android.R.id.text1};

        //setting the child data
        List<List<Map<String,String>>> listofChildGroups = new ArrayList();
        for (int i=0;i<distortions.length;i++){
            List<Map<String,String>> childGroup = new ArrayList();
            HashMap<String, String> childItem = new HashMap();
            childItem.put("CHILD", distortion_descriptions[i]);
            childGroup.add(childItem);
            listofChildGroups.add(childGroup);
        }
        int childLayout = android.R.layout.simple_expandable_list_item_2;
        String[] childFrom = {"CHILD"};
        int[] childTo = {android.R.id.text1};

        SimpleExpandableListAdapter list = new SimpleExpandableListAdapter (this,
                groupData, groupLayout, groupFrom, groupTo,
                listofChildGroups, childLayout, childFrom, childTo);

        distortion_list = (ExpandableListView) findViewById(R.id.distortion_list);
        distortion_list.setAdapter(list);
    }

    public void endPage(View v){
        Intent next = new Intent(this, MainActivity.class);
        startActivity(next);
    }

    private void nextActivity(View view){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_distortions, menu);
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
