package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by y.storchak on 1/22/14.
 */
public class LongListActivity extends FragmentActivity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.long_list_activity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.llLongListAct, new LongListFragment())
                .commit();

    }



    public static class LongListFragment extends Fragment {

        /*public LongListFragment() {
        }*/

        // имена атрибутов для Map
        private final String ATTRIBUTE_NAME_TV1 = "text1";
        private final String ATTRIBUTE_NAME_IV = "image";
        private final String ATTRIBUTE_NAME_TV2 = "text2";
        /*private  static  final int CHANGE_VALUE = 1;*/

        ListView frLVLongList;

        // массив данных
        private String str= "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        private Map<String, Object> m = new HashMap<String, Object>();

        // упаковываем данные в понятную для адаптера структуру
        private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        SimpleAdapter sAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_long_list, container, false);

            frLVLongList = (ListView) view.findViewById(R.id.frLVLongList);
            return view;
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            LinearLayout llItemLL = (LinearLayout) view.findViewById(R.id.llItemLL);

            for (int i = 0; i < 199; i++) {

                m.put(ATTRIBUTE_NAME_TV1, "TITLE " + (i+1));
                m.put(ATTRIBUTE_NAME_IV, R.drawable.ic_launcher);
                m.put(ATTRIBUTE_NAME_TV2, str);

                data.add(m);

                if (i%2==0){
                    llItemLL.setBackgroundColor(0x01060013);
                }
            }

            // массив имен атрибутов, из которых будут читаться данные
            String[] from = { ATTRIBUTE_NAME_TV1, ATTRIBUTE_NAME_IV,
                    ATTRIBUTE_NAME_TV2 };

            // массив ID View-компонентов, в которые будут вставлять данные
            int[] to = { R.id.tvItemLL1, R.id.ivItemLL, R.id.tvItemLL2 };

            // создаем адаптер
            sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_long_list, from, to);

            // Указываем адаптеру свой биндер
           /* sAdapter.setViewBinder(new MyViewBinder());*/

            // определяем список и присваиваем ему адаптер
            frLVLongList.setAdapter(sAdapter);


        }

    }
}