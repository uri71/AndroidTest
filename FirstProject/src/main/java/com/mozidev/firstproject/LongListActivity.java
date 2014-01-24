package com.mozidev.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LongListActivity extends FragmentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.long_list_activity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.llLongListAct, new LongListFragment())
                .commit();
    }

    public class LongListFragment extends ListFragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_long_list, container, false);

            return view;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            String str = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";

            List<Item> items = new ArrayList<Item>();
            for (int i = 0; i < 199; i++) {

                items.add(new Item(R.drawable.ic_launcher, str, "TITLE " + (i + 1)));
            }

            MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), R.layout.item_long_list, items);
            setListAdapter(adapter);
        }

        private final class MyArrayAdapter extends ArrayAdapter {

            private List<Item> items;
            private LayoutInflater inflater;

            public MyArrayAdapter(Context context, int resource, List<Item> data) {

                super(context, resource, data);

                this.items = data;
                inflater = LayoutInflater.from(context);
            }

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Item getItem(int position) {
                return items.get(position);
            }

            public View getView(int position, View convertView, ViewGroup parent) {

                View v = convertView;

                if (v == null) {
                    v = inflater.inflate(R.layout.item_long_list, parent, false);
                }

                Item item = items.get(position);

                String text1 = item.getName();
                String text2 = item.getDescription();
                int image = item.getIconRes();

                TextView tvItemLL1 = (TextView) v.findViewById(R.id.tvItemLL1);
                tvItemLL1.setText(text1);

                ImageView ivItemLL = (ImageView) v.findViewById(R.id.ivItemLL);
                ivItemLL.setImageResource(image);

                TextView tvItemLL2 = (TextView) v.findViewById(R.id.tvItemLL2);
                tvItemLL2.setText(text2);

                return v;
            }
        }
    }

    public class Item {
        private String name;
        private String description;
        private int iconRes;

        public Item(int iconRes, String description, String name) {
            this.iconRes = iconRes;
            this.description = description;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getIconRes() {
            return iconRes;
        }

        public void setIconRes(int iconRes) {
            this.iconRes = iconRes;
        }
    }
}