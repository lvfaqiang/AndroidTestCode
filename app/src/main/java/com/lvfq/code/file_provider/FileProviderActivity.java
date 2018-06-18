package com.lvfq.code.file_provider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lvfq.code.R;
import com.lvfq.library.utils.LvToastUtil;

import java.io.File;
import java.util.Date;

/**
 * FileProviderActivity
 *
 * @author FaQiang on 2018/6/18 上午9:49
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class FileProviderActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 100;


    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider);


        imageView = findViewById(R.id.fp_iv);

        findViewById(R.id.fp_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(FileProviderActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FileProviderActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    showCameraAction();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showCameraAction();
            } else {
                LvToastUtil.showToast(this, "You denied the permission");
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA) {
            Glide.with(this).load(mCaptureUri).into(imageView);
        }

    }

    /**
     * 选择相机
     */
    private String imagePath = "";
    private Uri mCaptureUri;

    private void showCameraAction() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            imagePath = new Date().getTime() + ".png";
            File tempFile = new File(Environment.getExternalStorageDirectory(), imagePath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mCaptureUri = FileProvider.getUriForFile(this, this.getPackageName() + ".fileprovider", tempFile);

            } else {
                mCaptureUri = Uri.fromFile(tempFile);
            }
            /**
             * 调用系统照相机
             */
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureUri);
            startActivityForResult(intent, REQUEST_CAMERA);
//            mTmpFile = FileUtils.createTmpFile(getActivity());
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
//            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } else {
            Toast.makeText(this, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

}
