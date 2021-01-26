package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class IconActivity extends AppCompatActivity {
    ImageView imageView;

    int[] imagelist = new int[]{R.drawable.ic_add_circle_black_24dp, R.drawable.ic_arrow_forward_black_24dp,
            R.drawable.ic_check_black_24dp, R.drawable.ic_cloud_black_24dp, R.drawable.ic_menu_camera};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnData();

            }
        });


    }




    public void returnData() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageId", imagelist[0]);
        setResult(RESULT_OK, resultIntent);
        finish();

    }
}
