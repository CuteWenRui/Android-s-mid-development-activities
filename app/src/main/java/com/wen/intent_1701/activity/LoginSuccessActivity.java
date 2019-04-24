package com.wen.intent_1701.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wen.intent_1701.R;


public class LoginSuccessActivity extends AppCompatActivity {
    private Button callPhone, sendSMS, openBrowser;
    private Context context=LoginSuccessActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        initView();
        initEvent();
    }

    private void initView() {
        callPhone = findViewById(R.id.callPhone);
        sendSMS = findViewById(R.id.sendSMS);
        openBrowser = findViewById(R.id.openBrowser);


    }

    private void initEvent() {
        callPhone.setOnClickListener(new OnclickLister());
        sendSMS.setOnClickListener(new OnclickLister());
        openBrowser.setOnClickListener(new OnclickLister());
    }

    class OnclickLister implements View.OnClickListener {
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            switch (arg0.getId()) {
                case R.id.callPhone://打电话
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + 10086);
                    intent1.setData(data);
                    context.startActivity(intent1);
                    break;
                case R.id.sendSMS://发短信
                    Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+10086));
                    intent2.putExtra("sms_body", "");
                    startActivity(intent2);
                    break;
                case R.id.openBrowser://打开浏览器
                    Uri uri = Uri.parse("https://www.baidu.com");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    break;
            }
        }
    }



}