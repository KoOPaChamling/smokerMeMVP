package com.example.sujanrai.smokerme.dashboard;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sujanrai.smokerme.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {


    RecyclerView infoRv;
    InfoAdapter infoAdapter;
    DashboardContract.Presenter presenter;
    DashboardPresenter dashboardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        infoRv = (RecyclerView) findViewById(R.id.infoRv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        infoRv.setLayoutManager(layoutManager);
        infoRv.setItemAnimator(new DefaultItemAnimator());
        itemTouchHelper.attachToRecyclerView(infoRv);
        infoAdapter = new InfoAdapter(new ArrayList<String>(0));
        infoRv.setAdapter(infoAdapter);

        dashboardPresenter = new DashboardPresenter(this);
    }

    InfoItemListener infoItemListener = new InfoItemListener() {
        @Override
        public void onInfoTouch(String info) {
            Toast.makeText(DashboardActivity.this, "Info Clicked", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showInfo(ArrayList<String> infoList) {
        infoAdapter.replaceData(infoList);
    }

    @Override
    public void showAddInfo() {

    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private static class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

        private ArrayList<String> infoList;

        public InfoAdapter(ArrayList<String> infoList) {
            setData(infoList);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(infoList.get(position));
        }

        @Override
        public int getItemCount() {
            return infoList.size();
        }

        public void replaceData(ArrayList<String> infoList) {
            setData(infoList);
        }

        private void setData(ArrayList<String> infoList) {
            this.infoList = infoList;
            notifyDataSetChanged();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView infoTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                infoTv = (TextView) itemView.findViewById(android.R.id.text1);
            }

            public void bind(final String s) {
                infoTv.setText(s);
                /*itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        infoItemListener.onInfoTouch(s);
                        return false;
                    }
                });*/
            }
        }

    }

    public interface InfoItemListener {
        void onInfoTouch(String info);
    }

    ItemTouchHelper.SimpleCallback simpleCallBackItemTouch = new ItemTouchHelper.SimpleCallback(1, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Toast.makeText(DashboardActivity.this, "Swiped", Toast.LENGTH_SHORT).show();

        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallBackItemTouch);

}