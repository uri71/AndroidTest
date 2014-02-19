package com.mozidev.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button btnTop, btnBot, countries, longList, locales;
    private ProgressBar progress;
    private Class<? extends Activity> target;

    public MainFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         btnTop = (Button) view.findViewById(R.id.btnTop);
        btnTop.setOnClickListener(this);

         btnBot = (Button) view.findViewById(R.id.btnBot);
        btnBot.setOnClickListener(this);

         countries = (Button) view.findViewById(R.id.countries);
        countries.setOnClickListener(this);

         longList = (Button) view.findViewById(R.id.longList);
        longList.setOnClickListener(this);

         locales = (Button) view.findViewById(R.id.locales);
        locales.setOnClickListener(this);

        progress = (ProgressBar) view.findViewById(R.id.progress);
        progress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnTop:
                target = SecondActivity.class;
                break;

            case R.id.countries:
                target = CountriesActivity.class;
                break;

            case R.id.longList:
                target = LongListActivity.class;
                break;

            case R.id.locales:
                target = LocalesActivity.class;
                break;

            default:
                target = WebActivity.class;
                break;

        }

		newTask task= new newTask();
		task.execute();
	}

	class newTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

            btnTop.setVisibility(View.INVISIBLE);
            btnTop.setClickable(false);
			btnBot.setVisibility(View.INVISIBLE);
            btnBot.setClickable(false);
			locales.setVisibility(View.INVISIBLE);
			locales.setClickable(false);
			longList.setVisibility(View.INVISIBLE);
			longList.setClickable(false);
            countries.setVisibility(View.INVISIBLE);
            countries.setClickable(false);

            progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			                                                    //try {
                Intent intent = new Intent(getActivity(), target);
                getActivity().startActivity(intent);
                                                                /*} catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }*/
			return null;
		}

        @Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

            btnTop.setVisibility(View.VISIBLE);
            btnTop.setClickable(true);
            btnBot.setVisibility(View.VISIBLE);
            btnBot.setClickable(true);
            locales.setVisibility(View.VISIBLE);
            locales.setClickable(true);
            longList.setVisibility(View.VISIBLE);
            longList.setClickable(true);
            countries.setVisibility(View.VISIBLE);
            countries.setClickable(true);

            progress.setVisibility(View.INVISIBLE);
		}
	}
}

