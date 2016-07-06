package com.ncbi.a3dmgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import com.ncbi.a3dmgame.service.DownLoadService;
import com.ncbi.a3dmgame.utils.NetUtils;

public class WelcomeActivity extends AppCompatActivity {
    private Animation animation;
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //获取欢迎界面的GifImageView控件；
        gifImageView = (GifImageView) findViewById(R.id.welcome_gif);

        //欢迎界面的启动动画
        animation = new AlphaAnimation(0, 1.0f);
        animation.setDuration(3000);
        gifImageView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            NetUtils netUtils = new NetUtils(WelcomeActivity.this);

            @Override
            public void onAnimationStart(Animation animation) {
                if (netUtils.netOk()) {
                    if (netUtils.getNetType() == ConnectivityManager.TYPE_MOBILE) {
                        Toast.makeText(WelcomeActivity.this, "你正在使用手机流量", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(WelcomeActivity.this, DownLoadService.class);
                        startService(intent);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!netUtils.netOk()) {
                    Toast.makeText(WelcomeActivity.this, "请连接网络", Toast.LENGTH_LONG).show();
                }
                isFirstOpen();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void isFirstOpen() {
        //创建SharedPreferences对象；读取是否为首次登录的本地记录
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstOpen", Context.MODE_PRIVATE);
        boolean mark = sharedPreferences.getBoolean("isFirstOpen", false);
        if (!mark) {
            Intent guideIntent = new Intent(WelcomeActivity.this, GuideActivity.class);
            startActivity(guideIntent);
            finish();
        } else {
            Intent mainintent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(mainintent);
            finish();
        }
    }
}
