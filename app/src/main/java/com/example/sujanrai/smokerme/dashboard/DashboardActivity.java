package com.example.sujanrai.smokerme.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sujanrai.smokerme.BasePresenter;
import com.example.sujanrai.smokerme.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {

    ListView infoLv;
    InfoAdapter infoAdapter;
    DashboardContract.Presenter presenter;
    DashboardPresenter dashboardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        infoLv = (ListView) findViewById(R.id.infoLv);
        infoLv.setEmptyView(findViewById(android.R.id.empty));

        infoAdapter = new InfoAdapter(new ArrayList<String>(0));

        Log.w("BEFORE CALLING:::", "CALLING");
        dashboardPresenter = new DashboardPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showInfo(ArrayList<String> infoList) {
        Log.w("CALLED", "CALLED");
        infoAdapter.replaceData(infoList);
    }

    @Override
    public void showAddInfo() {

    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private static class InfoAdapter extends BaseAdapter {

        private ArrayList<String> infoList;

        public InfoAdapter(ArrayList<String> infoList) {
            setList(infoList);
        }

        public void replaceData(ArrayList<String> infoList) {
            setList(infoList);
            notifyDataSetChanged();
        }

        private void setList(ArrayList<String> infoList) {
            this.infoList = infoList;
        }

        @Override
        public int getCount() {
            return infoList.size();
        }

        @Override
        public Object getItem(int position) {
            return infoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent);
            }

            Log.w("INSIDE ADAPTER", "inside");

            String info = (String) getItem(position);

            ((TextView) view.findViewById(android.R.id.text1)).setText(info);

            return view;
        }
    }
}
