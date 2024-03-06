package com.test.start;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ExpanseHistoryCustomAdapter extends ArrayAdapter<AddUserDetails> {

    private Activity context2;
    private List<AddUserDetails> ExpanseDataList;

    public ExpanseHistoryCustomAdapter(Activity context2, List<AddUserDetails> ExpanseDataList) {
        super(context2, R.layout.history_sample_view, ExpanseDataList);
        this.context2 = context2;
        this.ExpanseDataList = ExpanseDataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater2 = context2.getLayoutInflater();
        View view2 = layoutInflater2.inflate(R.layout.history_sample_view, null,true);

        AddUserDetails ExpanseHistoryCustomAdapter = ExpanseDataList.get(position);

        TextView ExpanseType = view2.findViewById(R.id.TypeSampleTextView);
        TextView ExpanseAmount = view2.findViewById(R.id.AmountSampleTextView);
        TextView ExpanseComment = view2.findViewById(R.id.CommentSampleTextView);

        ExpanseType.setText("Expanse Type: "+ExpanseHistoryCustomAdapter.getType());
        ExpanseAmount.setText("Amount: "+ExpanseHistoryCustomAdapter.getAmount());
        ExpanseComment.setText("Comment: "+ExpanseHistoryCustomAdapter.getComment());

        return view2;
    }
}
