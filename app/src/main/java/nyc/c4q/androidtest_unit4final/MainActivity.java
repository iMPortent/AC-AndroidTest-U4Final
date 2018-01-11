package nyc.c4q.androidtest_unit4final;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static nyc.c4q.androidtest_unit4final.InfoFragment.hideAllButton;
import static nyc.c4q.androidtest_unit4final.InfoFragment.moreInfoText;

public class MainActivity extends AppCompatActivity {

    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict, holdTemp;
    protected List<String> colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDict = new HashMap<>();
        holdTemp = new HashMap<>();

        colorDict.put("indigo", "#4b0082");
        colorDict.put("green", "#00ff00");
        colorDict.put("blue", "#0000ff");
        colorDict.put("red", "#ff0000");


        // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
        // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json
        makeRequestWithHttp("https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json");
        addTo(holdTemp);

        colorsList = new ArrayList<>();
        String[] names = new String[]{"blue", "red", "purple", "indigo", "orange", "brown", "black", "green"};
        for (String n : names) colorsList.add(n);

        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new ColorAdapter(colorsList, colorDict);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void makeRequestWithHttp(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                Log.d("Response", "onFailure: ");

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();

                holdTemp = new Gson().fromJson(result, new TypeToken<HashMap<String,String>>(){}.getType());

            }
        });
    }

    public void addTo(HashMap<String,String> test) {
        for (String s : test.keySet()) {
            colorDict.put(s, test.get(s));
        }
    }


    public void showMore(View view) {
        moreInfoText.setVisibility(View.VISIBLE);
        hideAllButton.setVisibility(View.VISIBLE);

    }

    public void hideAll(View view) {
        moreInfoText.setVisibility(View.INVISIBLE);
        hideAllButton.setVisibility(View.INVISIBLE);
    }

    // TODO: Add options menu with the item "Info" which is always visible
    // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
    // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
    // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_option:
                //if()
                break;
        }
        return true;
    }
}
