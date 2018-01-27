package com.codingblock.main.filters;


        import android.Manifest;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.SeekBar;

        import com.afollestad.materialdialogs.DialogAction;
        import com.afollestad.materialdialogs.MaterialDialog;
        import com.codingblock.main.filters.utility.Helper;

        import com.codingblock.main.filters.utility.TransformImage;
        import com.squareup.picasso.Picasso;
        import com.squareup.picasso.Target;
        import com.zomato.photofilters.imageprocessors.Filter;
        import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
        import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
        import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
        import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

public class ControlActivity extends AppCompatActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    Toolbar mControlToolbar;



    ImageView mCenterImageView;

    TransformImage mtransformImage;

    int mCurrentFilter;

    Uri mSelectedImageUri;

    SeekBar mSeekbar;
    ImageView mTickImageView;
    ImageView cancelImageView;

    int mScreenHeight;
    int mScreenWidth;

    Target mApplySingleFilter=new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            int CurrentFilterValue=mSeekbar.getProgress();

            if(mCurrentFilter==TransformImage.FILTER_BRIGHTNESS){
                mtransformImage.applyBrightnessSubFilter(CurrentFilterValue);
                Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS),mtransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS)));
                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenHeight/2).into(mCenterImageView);

            }else if(mCurrentFilter==TransformImage.FILTER_SATURATION){
                mtransformImage.applySaturationSubFilter(CurrentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_SATURATION),mtransformImage.getBitmap(TransformImage.FILTER_SATURATION));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_SATURATION)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }else if(mCurrentFilter==TransformImage.FILTER_VIGNETTE){
                mtransformImage.applyVignetteSubFilter(CurrentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE),mtransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }else if(mCurrentFilter==TransformImage.Filter_CONTRAST){
                mtransformImage.applyContrastSubFilter(CurrentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.Filter_CONTRAST),mtransformImage.getBitmap(TransformImage.Filter_CONTRAST));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.Filter_CONTRAST)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.Filter_CONTRAST))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    Target mSmallTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

             mtransformImage=new TransformImage(ControlActivity.this,bitmap);
         //************************************************************************************************************************************
            mtransformImage.applyBrightnessSubFilter(TransformImage.DEFAULT_BRIGHTNESS);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_BRIGHTNESS),mtransformImage.getBitmap(mtransformImage.FILTER_BRIGHTNESS));
            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_BRIGHTNESS))).fit().centerInside().into(mFirstFilterPreviewImageView);

        //************************************************************************************************************************************

            mtransformImage.applySaturationSubFilter(TransformImage.DEFAULT_SATURATION);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_SATURATION),mtransformImage.getBitmap(mtransformImage.FILTER_SATURATION));
            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_SATURATION))).fit().centerInside().into(mSecondFilterPreviewImageView);

         //************************************************************************************************************************************

           mtransformImage.applyVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_VIGNETTE),mtransformImage.getBitmap(mtransformImage.FILTER_VIGNETTE));
            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.FILTER_VIGNETTE))).fit().centerInside().into(mThirdFilterPreviewImageView);

         //************************************************************************************************************************************


            mtransformImage.applyContrastSubFilter(TransformImage.DEFAULT_CONTRAST);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.Filter_CONTRAST),mtransformImage.getBitmap(mtransformImage.Filter_CONTRAST));
            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(mtransformImage.Filter_CONTRAST))).fit().centerInside().into(mFourthFilterPreviewImageView);

         //**************************************************************************************************************************************
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    final static int PICK_IMAGE = 2;
    final static  int MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION = 3;

    ImageView mFirstFilterPreviewImageView;
    ImageView mSecondFilterPreviewImageView;
    ImageView mThirdFilterPreviewImageView;
    ImageView mFourthFilterPreviewImageView;

    private static final String TAG = ControlActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mSeekbar= (SeekBar) findViewById(R.id.seekBar);

        mControlToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCenterImageView = (ImageView) findViewById(R.id.CenterimageView);

        mControlToolbar.setTitle(getString(R.string.app_name));
        mControlToolbar.setNavigationIcon(R.drawable.icon);
        mControlToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        mFirstFilterPreviewImageView = (ImageView) findViewById(R.id.imageView4);
        mSecondFilterPreviewImageView = (ImageView) findViewById(R.id.imageView5);
        mThirdFilterPreviewImageView = (ImageView) findViewById(R.id.imageView6);
        mFourthFilterPreviewImageView = (ImageView) findViewById(R.id.imageView7);

        mTickImageView = (ImageView) findViewById(R.id.imageView3);
        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this,ImagePreviewActivity.class);
                startActivity(intent);
            }
        });

        mCenterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestStoragePermissions();

                if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        mFirstFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_BRIGHTNESS);
                mSeekbar.setProgress(TransformImage.DEFAULT_BRIGHTNESS);
                mSeekbar.setMax(TransformImage.MAX_BRIGHTNESS);

                mCurrentFilter=TransformImage.FILTER_BRIGHTNESS;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenHeight/2).into(mCenterImageView);
            }
        });

        mSecondFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_SATURATION);
                mSeekbar.setProgress(TransformImage.DEFAULT_SATURATION);


                mCurrentFilter=TransformImage.FILTER_SATURATION;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }
        });

        mThirdFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_VIGNETTE);
                mSeekbar.setProgress(TransformImage.DEFAULT_VIGNETTE);


                mCurrentFilter=TransformImage.FILTER_VIGNETTE;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }
        });

        mFourthFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_CONTRAST);

                mSeekbar.setProgress(TransformImage.DEFAULT_CONTRAST);

                mCurrentFilter=TransformImage.Filter_CONTRAST;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,mtransformImage.getFilename(TransformImage.Filter_CONTRAST ))).resize(0,mScreenHeight/2).into(mCenterImageView);


            }
        });

        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(ControlActivity.this).load(mSelectedImageUri).into(mApplySingleFilter);
            }
        });

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenHeight=displayMetrics.heightPixels;
        mScreenWidth=displayMetrics.widthPixels;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new MaterialDialog.Builder(ControlActivity.this).title("Permission Granted")
                            .content("Thank you for providing storage permission")
                            .positiveText("Ok").canceledOnTouchOutside(true).show();
                } else {
                    Log.d(TAG,"Permission denied!");
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
             mSelectedImageUri = data.getData();

            Picasso.with(ControlActivity.this).load(mSelectedImageUri).fit().centerInside().into(mCenterImageView);

            Picasso.with(ControlActivity.this).load(mSelectedImageUri).into(mSmallTarget);

        }
    }

    public void requestStoragePermissions() {
        if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_title)
                        .content(R.string.permission_content)
                        .negativeText(R.string.permission_cancel)
                        .positiveText(R.string.permission_agree_setting)
                        .canceledOnTouchOutside(true)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(ControlActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION);
            }
            return;
        }
    }

}
