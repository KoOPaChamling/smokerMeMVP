package com.example.sujanrai.smokerme.dashboard;

import com.example.sujanrai.smokerme.BasePresenter;
import com.example.sujanrai.smokerme.BaseView;

import java.util.ArrayList;

/**
 * Created by sujan.rai on 5/9/2017.
 */

public interface DashboardContract {

    interface View extends BaseView<Presenter> {
        void showInfo(ArrayList<String> infoList);

        void showAddInfo();
    }

    interface Presenter extends BasePresenter {
        void addInfo();

        void deleteInfo();

        void loadInfo();
    }
}
