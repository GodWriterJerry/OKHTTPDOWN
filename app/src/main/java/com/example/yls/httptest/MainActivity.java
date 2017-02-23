package com.example.yls.httptest;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    String urlstr = "https://downpack.baidu.com/appsearch_AndroidPhone_1012271b.apk" ;
    private Handler mHandler=new Handler();
    private ProgressBar mProgressBar;
    private Button btn;
    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btn = (Button) findViewById(R.id.downLoad);
        txt = (TextView) findViewById(R.id.webText);
        mProgressBar= (ProgressBar) findViewById(R.id.progress_bar);

        // CTRL+SHIFT+空格
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                getDatas();

                            }
                        }
                ).start();


            }
        });


    }

    public void getDatas() {
        OkHttpUtils
                .get()
                .url(urlstr)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"com.ttpicture.android_295.apk") {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }

                    @Override
                    public void inProgress(final float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress((int) (100 * progress));
                            }
                        });
                    }
                });
    }
}
