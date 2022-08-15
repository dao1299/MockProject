package com.example.mockproject.model;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeElementModel {

    private String title;
    private boolean haveViewAll;
//    private RecyclerView recyclerView;
    private Object adapter;


    public HomeElementModel(String title, boolean haveViewAll, Object adapter) {
        this.title = title;
        this.haveViewAll = haveViewAll;
        this.adapter = adapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHaveViewAll() {
        return haveViewAll;
    }

    public void setHaveViewAll(boolean haveViewAll) {
        this.haveViewAll = haveViewAll;
    }


    @Override
    public String toString() {
        return "HomeElementModel{" +
                "title='" + title + '\'' +
                ", haveViewAll=" + haveViewAll +
                '}';
    }

    public Object getAdapter() {
        return adapter;
    }

    public void setAdapter(Object adapter) {
        this.adapter = adapter;
    }
}
