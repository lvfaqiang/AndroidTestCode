package com.lvfq.code.uploadfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lvfq.code.R;
import com.lvfq.code.http.HttpClient;
import com.lvfq.library.utils.LvLog;

import java.io.File;
import java.util.List;

import me.lvfq.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * UploadFileActivity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/10/19 上午10:22
 * @desc :
 */

public class UploadFileActivity extends AppCompatActivity {

    private Button btn_upload;
    private Button btn_choose;
    private ImageView iv_upload;
    private String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        btn_upload = (Button) findViewById(R.id.upload_btn_confirm);
        btn_choose = (Button) findViewById(R.id.upload_btn_choose);

        iv_upload = (ImageView) findViewById(R.id.upload_iv);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadFileActivity.this, MultiImageSelectorActivity.class);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                UploadFileActivity.this.startActivityForResult(intent, 2);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(imgUrl)) {
                    return;
                }
                http_Upload();
            }
        });
    }


    private void http_Upload() {
        File file = new File(imgUrl);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("fileName", file.getName(), body);
        HttpClient.getService().uploadFile("测试数据", part).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    LvLog.i("success");
                } else {
                    LvLog.i("Error");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LvLog.i("Failure");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 2) {
            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (list != null && list.size() >= 0) {
                imgUrl = list.get(0);
                Glide.with(this).load(imgUrl).into(iv_upload);
            }
        }
    }
}
