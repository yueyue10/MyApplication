package com.encdata.catchcraycat.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.encdata.catchcraycat.R;
import com.encdata.catchcraycat.ui.view.dateview.DatePickerController;
import com.encdata.catchcraycat.ui.view.dateview.DayPickerView;
import com.encdata.catchcraycat.ui.view.dateview.SimpleMonthAdapter;
import com.socks.library.KLog;

/**
 * Created by zhaoyuejun on 2018/7/26.
 */

public class CalendarActivity extends AppCompatActivity implements DatePickerController {

    DayPickerView pickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claendar);
        setTitle("自定义日历");
        pickerView = (DayPickerView) findViewById(R.id.pickerView);
        pickerView.setStartEndMonth(7, 11);//设置起止年份下可用的起始月份
        pickerView.setController(this,"2018-08-10","2018-10-20",false);
    }

    /**
     * 设置显示的最大年数，必填。
     */
    @Override
    public int getMaxYear() {
        return 2019;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        KLog.d("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        KLog.d("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}
