package com.rainfool.md.crop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rainfool.md.R;

import java.io.File;
import java.io.IOException;

public class CropImageDemoActivity extends AppCompatActivity {

    private static final String TAG = "CropImageDemoActivity";

    public static final int GET_IMAGE = 1000;
    public static final int CAMERA = 1001;
    public static final int CROP = 1002;

    private String mExternalDirPath = Environment.getExternalStorageDirectory().getPath();
    private File mCropOutputFile = new File(mExternalDirPath, TAG + "_croped");

    private Uri mCameraUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image_demo);

        File uriFile = new File(mExternalDirPath, TAG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mCameraUri = FileProvider.getUriForFile(getApplicationContext(), "com.duowan.kiwi.fileprovider", uriFile);
        } else {
            mCameraUri = Uri.fromFile(uriFile);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Log.i(TAG, "onActivityResult requestCode:%s" + requestCode);
        switch (requestCode) {
            case GET_IMAGE:
                if (data != null) {
                    startCropActivity(data.getData());
                }
                break;
            case CAMERA:
                startCropActivity(mCameraUri);
                break;
            case CROP:

                break;
            default:
                break;
        }
    }


    /**
     * 提供给外部，跳转到系统剪切的方法
     *
     * @param mImageUri 图片URI地址，一般为拍照结果或图片选取结果
     */
    public void startCropActivity(Uri mImageUri) {
        if (mCropOutputFile.exists()) {
            mCropOutputFile.delete();
        }
        try {
            mCropOutputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri cropOutputUri = Uri.fromFile(mCropOutputFile);


        final int portraitSize = getFitPortraitSize(mImageUri);

        Intent intent = new Intent();
        intent.setDataAndType(mImageUri, "image/*");
        // fix xiaomi android N+ phone  bug:
        // W ActivityManager: Permission Denial: opening provider android.support.v4.content.FileProvider from ProcessRecord{30bbeb 23218:com.miui.gallery/u0a21} (pid=23218, uid=10021) that is not exported from uid 10575
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
//        }

        intent.putExtra(Portrait.PORTRAIT_KEY_CROP, "true");
        intent.putExtra(Portrait.PORTRAIT_KEY_ASPECT_X, 1);
        intent.putExtra(Portrait.PORTRAIT_KEY_ASPECT_Y, 1);
        intent.putExtra(Portrait.PORTRAIT_KEY_OUTPUT_X, portraitSize);
        intent.putExtra(Portrait.PORTRAIT_KEY_OUTPUT_Y, portraitSize);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropOutputUri);
        intent.putExtra(Portrait.PORTRAIT_KEY_OUTPUT_FORMAT, Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra(Portrait.PORTRAIT_KEY_RETURN_DATA, false);
        crop(this, intent);
    }

    public static void crop(Activity activity, Intent intent) {
        intent.setAction("com.android.camera.action.CROP");
        activity.startActivityForResult(intent, CROP);
    }

    public void onPickButtonClicked(View view) {
        getImage(this);
    }

    public static void getImage(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK
                , android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                activity.startActivityForResult(intent, GET_IMAGE);
            } catch (Exception e) {
            }

        }
    }

    class Portrait {

        public static final String PORTRAIT_KEY_CROP = "crop";
        public static final String PORTRAIT_KEY_ASPECT_X = "aspectX";
        public static final String PORTRAIT_KEY_ASPECT_Y = "aspectY";
        public static final String PORTRAIT_KEY_OUTPUT_X = "outputX";
        public static final String PORTRAIT_KEY_OUTPUT_Y = "outputY";
        public static final String PORTRAIT_KEY_OUTPUT_FORMAT = "outputFormat";
        public static final String PORTRAIT_KEY_RETURN_DATA = "return-data";


        public static final int PORTRAIT_OUTPUT_SIZE = 640;
    }

    private int getFitPortraitSize(Uri uri) {
//        final String path = PortraitUtil.getRealPathFromURI(mActivity, uri);
//        if (!FP.empty(path)) {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(path, options);
//
//            if (options.outHeight > 0 && options.outWidth > 0) {
//                final int matrixSize = Math.min(options.outHeight, options.outWidth);
//                if (matrixSize < IUserInfoModel.Portrait.PORTRAIT_OUTPUT_SIZE) {
//                    return matrixSize;
//                }
//            }
//        }
//        return IUserInfoModel.Portrait.PORTRAIT_OUTPUT_SIZE;
        return 200;
    }

}
