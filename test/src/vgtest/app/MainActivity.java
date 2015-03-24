// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.app;

import vgtest.testview.ViewFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;

public class MainActivity extends ListActivity {
    private static final String TAG = "vgtest";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<ViewFactory.DummyItem>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ViewFactory.items()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(this, DummyActivity.class);
        i.putExtra("className", ViewFactory.items().get(position).getId());
        i.putExtra("title", ViewFactory.items().get(position).toString());
        i.putExtra("flags", ViewFactory.items().get(position).getFlags());
        startActivity(i);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "ORIENTATION_PORTRAIT");
        }
    }
}
