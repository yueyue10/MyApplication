package com.encdata.mvp.ui.ticket.storebus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.encdata.mvp.R;

/**
 * 收藏路线
 * Created by zhaoyuejun on 2018/10/16.
 */

public class StoreBusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storebus, container, false);
        return view;
    }

}
