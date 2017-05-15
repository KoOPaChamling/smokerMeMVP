package com.example.sujanrai.smokerme.dashboard;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sujan.rai on 5/9/2017.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    DashboardContract.View mView;

    DashboardPresenter(DashboardContract.View infoView) {
        mView = infoView;
        mView.setPresenter(this);
    }

    @Override
    public void addInfo() {
    }

    @Override
    public void deleteInfo() {

    }

    @Override
    public void loadInfo() {
        getInfo();
    }

    private void getInfo() {
        ArrayList<String> infoList = new ArrayList<>();
        infoList.add("Hello");
        infoList.add("Hello");
        infoList.add("Hello");
        infoList.add("Hello");
        infoList.add("Hello");
        processInfo(infoList);
    }

    private void processInfo(ArrayList<String> infoList) {
        mView.showInfo(infoList);
    }

    @Override
    public void start() {
        loadInfo();
    }
}
