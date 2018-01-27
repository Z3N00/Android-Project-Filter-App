package com.codingblock.main.filters.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Transformation;

import com.codingblock.main.filters.ControlActivity;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

/**
 * Created by kartik on 18-01-2018.
 */

public class TransformImage {

    public static final int MAX_BRIGHTNESS=100;
    public static final int MAX_SATURATION=5;
    public static final int MAX_VIGNETTE=255;
    public static final int MAX_CONTRAST=100;

    public static final int DEFAULT_BRIGHTNESS = 70;
    public static final int DEFAULT_CONTRAST = 60;
    public static final int DEFAULT_VIGNETTE = 100;
    public static final int DEFAULT_SATURATION = 5;


    private String mFilename;
    private Bitmap mBitmap;
    private Context mContext;

    private Bitmap brightnessFilteredBitmap;
    private Bitmap vignetteFilteredBitmap;
    private Bitmap saturationFilteredBitmap;
    private Bitmap contrastFilteredBitmap;

    public static int FILTER_BRIGHTNESS=0;
    public static int FILTER_VIGNETTE=1;
    public static int FILTER_SATURATION=2;
    public static int Filter_CONTRAST=3;

    public String getFilename(int filter){

        if(filter==FILTER_BRIGHTNESS){
            return mFilename+"_brightness";

        }else if(filter==FILTER_VIGNETTE){
            return mFilename+"_vignette";

        }else if(filter==FILTER_SATURATION){
            return mFilename+"_saturation";

        }else if(filter==Filter_CONTRAST){
            return mFilename+"_contrast";

        }
        return mFilename;

    }

    public Bitmap getBitmap(int filter){

        if(filter==FILTER_BRIGHTNESS){
            return brightnessFilteredBitmap;

        }else if(filter==FILTER_VIGNETTE){
            return vignetteFilteredBitmap;

        }else if(filter==FILTER_SATURATION){
            return saturationFilteredBitmap;

        }else if(filter==Filter_CONTRAST){
            return contrastFilteredBitmap;

        }
        return mBitmap;

    }

    public TransformImage(Context context,Bitmap bitmap){

     mContext=context;
     mBitmap=bitmap;
     mFilename=System.currentTimeMillis() +"";
    }

    public Bitmap applyBrightnessSubFilter(int brightness){

        Filter myFilterBrightness = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        myFilterBrightness.addSubFilter(new BrightnessSubfilter(brightness));
        Bitmap outputImageBrightness = myFilterBrightness.processFilter(mutableBitmap);

        brightnessFilteredBitmap=outputImageBrightness;

        return outputImageBrightness;


    }

    public Bitmap applyVignetteSubFilter(int vignette){

        Filter myFilterVignette = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        myFilterVignette.addSubFilter(new VignetteSubfilter(mContext,vignette));
        Bitmap outputImageVignette = myFilterVignette.processFilter(mutableBitmap);

        vignetteFilteredBitmap=outputImageVignette;

        return outputImageVignette;



    }

    public Bitmap applySaturationSubFilter(int saturation){

        Filter myFilterSaturation = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        myFilterSaturation.addSubFilter(new SaturationSubfilter(saturation));
        Bitmap outputImageSaturation = myFilterSaturation.processFilter(mutableBitmap);

        saturationFilteredBitmap=outputImageSaturation;

        return outputImageSaturation;

    }

    public Bitmap applyContrastSubFilter(int contrast){

        Filter myFilterContrast = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        myFilterContrast.addSubFilter(new ContrastSubfilter(contrast));
        Bitmap outputImageContrast = myFilterContrast.processFilter(mutableBitmap);

        contrastFilteredBitmap=outputImageContrast;

        return outputImageContrast;

    }
}
