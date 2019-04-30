package com.danielburgnerjr.appume2019.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.danielburgnerjr.appume2019.R;
import java.util.List;

public class CompanyTimePeriodAdapter implements ListAdapter {
    private List<String> strCompanyNameList;
    private List<String> strTimePeriodList;
    private Context context;

    public CompanyTimePeriodAdapter(List<String> strCoList, List<String> strTPList) {
        this.strCompanyNameList = strCoList;
        this.strTimePeriodList = strTPList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            //Only creates new view when recycling isn't possible
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.company_time_period_list, null);
        }

        TextView companyView = (TextView) view.findViewById(R.id.txtCompanyName);
        companyView.setText(strCompanyNameList.get(position));

        TextView timePeriodView = (TextView) view.findViewById(R.id.txtTimePeriod);
        timePeriodView.setText(strTimePeriodList.get(position));

        return view;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

}
