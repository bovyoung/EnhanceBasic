package com.bowyoung.enhance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bowyoung.enhancelibrary.utils.DisplayUtils;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MainActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("6 px-dp" + DisplayUtils.px2dip(this, 6));
        System.out.println("6 dp-px" + DisplayUtils.dp2px(this, 6));
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
