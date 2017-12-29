package com.vip.abnerming.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 图片加载缓存管理
 * 
 *  Created by AbnerMing
 * 
 */
public class ImageLoaderManager {
	/**
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
				context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remov
		ImageLoader.getInstance().init(config.build());
	}

	public static DisplayImageOptions options;

	/**
	 * init displayImageOptions this.options = new DisplayImageOptions.Builder()
	 * .showImageOnLoading(R.drawable.answerloading)
	 * .showImageOnFail(R.drawable.answerloadfailed)
	 * .showImageForEmptyUri(R.drawable.answerloadfailed)
	 * .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
	 * .bitmapConfig(Bitmap.Config.RGB_565).build();
	 */
	public static DisplayImageOptions initDisplayImageOptions(int onLoadingImg,
                                                              int forEmptyUriImg, int onFailImg) {
		return new DisplayImageOptions.Builder()
				.showImageOnLoading(onLoadingImg)
				.showImageForEmptyUri(forEmptyUriImg)
				.showImageOnFail(onFailImg).cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	public static DisplayImageOptions initDisplayImageOptionsScaleTypeIsNone(
			int onLoadingImg, int forEmptyUriImg, int onFailImg) {
		return new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.EXACTLY)
				// default
				.showImageOnLoading(onLoadingImg)
				.showImageForEmptyUri(forEmptyUriImg)
				.showImageOnFail(onFailImg).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}


}
