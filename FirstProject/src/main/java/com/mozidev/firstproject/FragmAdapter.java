package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by y.storchak on 1/21/14.
 */
public class FragmAdapter extends Fragment {

    // имена атрибутов для Map
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_PB = "pb";
    private final String ATTRIBUTE_NAME_LL = "ll";
    /*private  static  final int CHANGE_VALUE = 1;*/

    ListView lvSimple;

    // массив данных
    private int load[]= { 41, 48, 22, 35, 30, 67, 51, 88 };

    private Map<String, Object> m = new HashMap<String, Object>();

    // упаковываем данные в понятную для адаптера структуру
    private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(load.length);

    SimpleAdapter sAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adapter, container, false);

        lvSimple = (ListView) v.findViewById(R.id.lvSimple);

       /* registerForContextMenu(lvSimple);*/

        return v;

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < load.length; i++) {



            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i+1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);

            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        // создаем адаптер
        sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item, from, to);

        // Указываем адаптеру свой биндер
        sAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        lvSimple.setAdapter(sAdapter);
    }

   /* public void onCreateContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0, CHANGE_VALUE, 0, "ИЗМЕНИТЕ ПРОЦЕНТ");

    }

    public boolean onContextItemSelected (MenuItem item) {

        if (item.getItemId() == CHANGE_VALUE){
            View v = inflater.inflate(R.layout.fragment_adapter_edit_text, container, false);

            // достаем информацию из EditText
            EditText etFrAdVal = (EditText) v.findViewById(R.id.etFrAdVal);
            int val =  Integer.getInteger(etFrAdVal.getText().toString()); // исключение если val не int?

            if (val >=0 & val<=100){
                m.put(ATTRIBUTE_NAME_TEXT, "Day " + (val+1) + ". Load: " + load[val] + "%");
                m.put(ATTRIBUTE_NAME_PB, load[val]);
                m.put(ATTRIBUTE_NAME_LL, load[val]);

                AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                data.set(acmi.position, m);

                item.getItemId()

                sAdapter.notifyDataSetChanged();
                return true;
                }
                else {
                    etFrAdVal.setText("Значение не в допуске");
                    return false;
            }
        }
        return false;
    }*/

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);/**/

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;
            switch (view.getId()) {

                // LinearLayout
                case R.id.llLoad:
                    i = ((Integer) data).intValue();

                    if (i < 40) view.setBackgroundColor(green);

                    else
                        if (i < 70) view.setBackgroundColor(orange);
                    else
                        view.setBackgroundColor(red);
                    return true;

                // ProgressBar
                case R.id.pbLoad:

                    i = ((Integer) data).intValue();
                    ((ProgressBar)view).setProgress(i);

                    return true;
            }
            return false;
        }
    }
}

