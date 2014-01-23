package com.mozidev.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LongListActivity extends FragmentActivity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.long_list_activity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.llLongListAct, new LongListFragment())
                .commit();
    }

    public static class LongListFragment extends Fragment { //почему выдает ошибку при изменениии класса на ListFragment?

        //ИМЕЕТ ЛИ СМЫСЛ СОЗДАВАТЬ ЭТИ КОНСТАНТЫ ИЛИ МОЖНО УКАЗЫВАТЬ KEYS В HASHMAP НЕПОСРЕДСТВЕННО?
        private final String ATTRIBUTE_NAME_TV1 = "text1";
        private final String ATTRIBUTE_NAME_IV = "image";
        private final String ATTRIBUTE_NAME_TV2 = "text2";

        private String str= "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua" ;
        private Map<String, Object> item = new HashMap<String, Object>();
        private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        private ListView lv_container;
        private ArrayAdapter myAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_long_list, container, false);

            lv_container = (ListView) view.findViewById(R.id.frLVLongList);
            return view;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            for (int i = 0; i < 199; i++) {

                item.put(ATTRIBUTE_NAME_TV1, "TITLE " + (i+1));
                item.put(ATTRIBUTE_NAME_IV, R.drawable.ic_launcher);
                item.put(ATTRIBUTE_NAME_TV2, str);

                data.add(item);
            }

            myAdapter = new MyArrayAdapter(getActivity(), R.layout.item_long_list, data);
            lv_container.setAdapter(myAdapter);
        }

        public class MyArrayAdapter extends ArrayAdapter  {

            private Context context;
            private ArrayList<Map<String, Object>> data;
            private Map<String, Object> item;
            private LayoutInflater lInflater;
            private int resource;

            public MyArrayAdapter (Context context, int resource, ArrayList<Map<String, Object>> data) {

                // ПОЧЕМУ ТРЕБУЕТСЯ ВЫЗОВ КОНСТРУКТОРА СУПЕРКЛАССА?
                // ЧТО ЗА ПРЕДУПРЕЖДЕНИЕ ВЫДАЕТСЯ ПРИ ВЫЗОВЕ КОНСТРУКТОРА СУПЕРКЛАССА?
                super(context, resource, data);

                this.context = context;
                this.data = data;
                this.resource = resource;
                lInflater = getActivity().getLayoutInflater();// ПОЧЕМУ ВМЕСТО getActivity() МЫ НЕ МОЖЕМ ИСПОЛЬЗОВАТЬ ПЕРЕМЕННУЮ context?
            }
            @Override
            public int getCount(){
                return data.size();
            }
            @Override
            public Map<String, Object> getItem (int position){
                return data.get(position);
            }

            public View getView (int position, View convertview, ViewGroup parent){

                View v = convertview;

                if (v == null){
                    v = lInflater.inflate(resource, parent, false);
                }

                item = data.get(position);

                String text1 = item.get("text1").toString();
                String text2 = item.get("text2").toString();
                Integer image = (Integer)item.get("image");

                ((TextView)v.findViewById(R.id.tvItemLL1)).setText(text1);
                ((ImageView)v.findViewById(R.id.ivItemLL)).setImageResource(image);
                ((TextView)v.findViewById(R.id.tvItemLL2)).setText(text2);

                return v;
            }
        }
    }
}