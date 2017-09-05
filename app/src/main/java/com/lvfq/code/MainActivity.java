package com.lvfq.code;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lvfq.code.mvp.demo3.LoginPresenter3;
import com.lvfq.myworkingtest.R;
import com.lvfq.code.mvp.demo3.LoginView3;

public class MainActivity extends BaseActivity<LoginView3, LoginPresenter3> implements LoginView3 {

    private TextView tv_symbol;
    private EditText et_content;

    @Override
    public LoginPresenter3 bindPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_symbol = (TextView) findViewById(R.id.tv_symbol);
        et_content = (EditText) findViewById(R.id.et_content);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int width = (int) et_content.getPaint().measureText(s.toString());
                Log.i("lfq", "width: " + width);
                tv_symbol.setPadding(0, 0, width + 5, 0);
            }
        });

        et_content.setText("10000");

    }

    @Override
    public void onResult(String result) {

    }
}
