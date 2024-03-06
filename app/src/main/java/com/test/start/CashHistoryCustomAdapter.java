package com.test.start;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CashHistoryCustomAdapter extends ArrayAdapter<AddUserDetails> {

    private Activity context1;
    private List<AddUserDetails> IncomeDataList;

    public CashHistoryCustomAdapter(Activity context1, List<AddUserDetails> IncomeDataList) {
        super(context1, R.layout.history_sample_view, IncomeDataList);
        this.context1 = context1;
        this.IncomeDataList = IncomeDataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater1 = context1.getLayoutInflater();
        View view1 = layoutInflater1.inflate(R.layout.history_sample_view, null,true);

        AddUserDetails CashIncomeHistoryData = IncomeDataList.get(position);

        TextView CashIncomeType = view1.findViewById(R.id.TypeSampleTextView);
        TextView CashIncomeAmount = view1.findViewById(R.id.AmountSampleTextView);
        TextView CashIncomeComment = view1.findViewById(R.id.CommentSampleTextView);
        TextView Date = view1.findViewById(R.id.DateView);


        CashIncomeType.setText("Income Type: "+CashIncomeHistoryData.getType());
        CashIncomeAmount.setText("Amount: "+CashIncomeHistoryData.getAmount());
        CashIncomeComment.setText("Comment: "+CashIncomeHistoryData.getComment());
        Date.setText("Date: 07 September 2022");

        return view1;
    }
}
