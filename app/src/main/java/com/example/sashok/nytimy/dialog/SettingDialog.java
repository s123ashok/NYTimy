package com.example.sashok.nytimy.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sashok.nytimy.R;
import com.example.sashok.nytimy.activities.SearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asmurthy on 2/14/16.
 */
public class SettingDialog extends DialogFragment {

    private EditText mEditText;

    public SettingDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static SettingDialog newInstance(String title) {
        SettingDialog frag = new SettingDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.article_settings, container);
    }

    @Bind(R.id.begin_date)
    TextView begin_date;
    @Bind(R.id.sortChkOld)
    RadioButton sortOld;
    @Bind(R.id.sortChkNew)
    RadioButton sortNew;
    @Bind(R.id.newsArt)
    CheckBox newsArt;
    @Bind(R.id.newsSport)
    CheckBox newsSport;
    @Bind(R.id.newsFashion)
    CheckBox newsFashion;
    @Bind(R.id.btnSave)
    Button btnSave;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.beginDate = begin_date.getText().toString();
                if (sortNew.isChecked())
                    SearchActivity.sort = "Newest";
                if (sortOld.isChecked())
                    SearchActivity.sort = "Oldest";

                Boolean arts = newsArt.isChecked();
                Boolean sports = newsSport.isChecked();
                Boolean fashion = newsFashion.isChecked();

                String newsDesk = "";
                if (arts || sports || fashion)
                    newsDesk = "news_desk:(";
                if(arts)
                    newsDesk = newsDesk + "\"" + "Arts" + "\" ";
                if(sports)
                    newsDesk = newsDesk + "\"" + "Sports" + "\" ";
                if(fashion)
                    newsDesk = newsDesk + "\"" + "Fashion & Style" + "\" ";
                if(!TextUtils.isEmpty(newsDesk))
                    newsDesk = newsDesk + "}";
                SearchActivity.newsDesk = newsDesk;

            }
        });
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Settings");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        begin_date.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}