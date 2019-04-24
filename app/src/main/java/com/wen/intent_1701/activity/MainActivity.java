package com.wen.intent_1701.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wen.intent_1701.R;
import com.wen.intent_1701.util.StringUtils;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_account;
    private TextInputLayout til_pwd;
    private Button login_btn;
    private int count = 0;//记录错误次数
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent(this);
    }


    /*
    初始化view
     */
    private void initView() {
        til_account = findViewById(R.id.til_account);
        til_pwd = findViewById(R.id.til_pwd);
        login_btn = findViewById(R.id.login_btn);
    }

    /*
    绑定事件
     */
    private void initEvent(final Context mContext) {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {
                //获取输入的值
                String account = til_account.getEditText().getText().toString();
                String password = til_pwd.getEditText().getText().toString();

                til_account.setErrorEnabled(false);
                til_pwd.setErrorEnabled(false);

                //验证用户名和密码
                if (validateAccount(account) && validatePassword(password)) {
                    if (account.equals("user")) {
                        if (!password.equals("123456")) {
                            showError(til_pwd, "密码错误");
                            count++;
                            Snackbar.make(v, "你还有" + (3 - count) + "次输入机会", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        showError(til_account, "账号错误");
                        count++;
                        Snackbar.make(v, "你还有" + (3 - count) + "次输入机会", Snackbar.LENGTH_LONG).show();
                    }
                    //判断输入错误次数
                    if (count == 3) {
                        finish();//退出程序
                    }

                    if (account.equals("user") && password.equals("123456")) {
                        Snackbar.make(v, "登录成功～等待跳转", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable(){
                            public void run() {
                                startActivity(new Intent(mContext, LoginSuccessActivity.class));//登录成功跳转
                                finish();
                            }
                        }, 2500);

                    }

                }
            }
        });
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        Objects.requireNonNull(textInputLayout.getEditText()).setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            showError(til_account, "用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(til_pwd, "密码不能为空");
            return false;
        }
        return true;
    }
}
