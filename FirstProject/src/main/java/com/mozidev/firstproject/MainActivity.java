package com.mozidev.firstproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*View v = findViewById(R.id.context_menu);
        registerForContextMenu(v);*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 1, Menu.NONE, "ButtonContextMenu1");
        menu.add(Menu.NONE, 2, Menu.NONE, "ButtonContextMenu2");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Нажата кнопка Item1", Toast.LENGTH_LONG).show();

            case R.id.item2:
                Toast.makeText(this, "Нажата кнопка Item2", Toast.LENGTH_LONG).show();

            case R.id.item3:
                Toast.makeText(this, "Нажата кнопка Item3", Toast.LENGTH_LONG).show();

            case R.id.item4:
                Toast.makeText(this, "Нажата кнопка Item4", Toast.LENGTH_LONG).show();

            case R.id.menu_settings:
                Toast.makeText(this, "Нажата кнопка Setting", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                Toast.makeText(this, "Нажата кнопка ButtonContextMenu1", Toast.LENGTH_LONG).show();
                break;

            case 2:
                Toast.makeText(this, "Нажата кнопка ButtonContextMenu2", Toast.LENGTH_LONG).show();
                break;

            default: return super.onContextItemSelected(item);
        }
        return true;
    }*/
}


