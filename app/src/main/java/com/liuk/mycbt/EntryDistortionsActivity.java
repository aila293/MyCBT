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
        String[] distortions = {"Filtering", "Dichotomous Thinking", "Overgeneralization",
                "Catastrophizing", "Personalization", "Fallacy of Fairness",
                "Should statements", "Emotional Reasoning", "Heaven’s Reward Fallacy"};

        String[] distortion_descriptions = {"We take the negative details and magnify them while filtering out all positive aspects of a situation. For instance, a person may pick out a single, unpleasant detail and dwell on it exclusively so that their vision of reality becomes darkened or distorted.",
                "We have to be perfect or we’re a failure — there is no middle ground. You place people or situations in 'either/or' categories, with no shades of gray or allowing for the complexity of most people and situations. If your performance falls short of perfect, you see yourself as a total failure.",
                "In this cognitive distortion, we come to a general conclusion based on a single incident or a single piece of evidence. If something bad happens only once, we expect it to happen over and over again. A person may see a single, unpleasant event as part of a never-ending pattern of defeat.",
                "We expect disaster to strike, no matter what. This is also referred to as 'magnifying or minimizing.' We hear about a problem and use what if questions (e.g., 'What if tragedy strikes?' 'What if it happens to me?').\n" +
                        "For example, a person might exaggerate the importance of insignificant events (such as their mistake, or someone else’s achievement). Or they may inappropriately shrink the magnitude of significant events until they appear tiny (for example, a person’s own desirable qualities or someone else’s imperfections).",
                "Personalization is a distortion where a person believes that everything others do or say is some kind of direct, personal reaction to the person. We also compare ourselves to others trying to determine who is smarter, better looking, etc.",
                "We feel resentful because we think we know what is fair, but other people won’t agree with us. As our parents tell us when we’re growing up and something doesn’t go our way, 'Life isn’t always fair.' People who go through life applying a measuring ruler against every situation judging its 'fairness' will often feel badly and negative because of it. Because life isn’t 'fair' — things will not always work out in your favor, even when you think they should.",
                "We have a list of ironclad rules about how others and we should behave. People who break the rules make us angry, and we feel guilty when we violate these rules. A person may often believe they are trying to motivate themselves with shoulds and shouldn'ts, as if they have to be punished before they can do anything.",
                "We believe that what we feel must be true automatically. If we feel stupid and boring, then we must be stupid and boring. You assume that your unhealthy emotions reflect he way things really are — 'I feel it, therefore it must be true.'",
                "We expect our sacrifice and self-denial to pay off, as if someone is keeping score. We feel bitter when the reward doesn’t come."
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
