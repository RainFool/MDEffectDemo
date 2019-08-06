package com.rainfool.md.imagelibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.image.ImageInfo;

import java.io.File;


/**
 * Created by FrankChan on 2018/6/6.
 * 基于Fresco加载的WebpView，支持直接加载
 */

public abstract class KiwiWebpView<T> extends SimpleDraweeView implements AnimationListener, ControllerListener<ImageInfo> {

    private static final String TAG = "KiwiWebpView";

    public final static int LOOP_TIMES_INFINITE = Integer.MAX_VALUE;

    private int mMaxLoopTimes = 1;
    private int mLoopTimes = 0;

    private AnimatedDrawable2 mAnimatedDrawable;

    private T mItem;
    private Bitmap mBitmap;

    public KiwiWebpView(Context context) {
        super(context);
    }

    public KiwiWebpView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KiwiWebpView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public void start(T item) {
        stopIfRunning();
        mItem = item;
        String path = getFilePath(item);
        if (TextUtils.isEmpty(path)) {
            postErrorCallback("empty file path");
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            postErrorCallback("file not exists");
            return;
        }
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.fromFile(file))
                .setAutoPlayAnimations(true)
                .setControllerListener(this)
                .build();
        setController(controller);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            if (mBitmap != null) {
                setImageBitmap(mBitmap);
            }
        }
    }

    protected abstract String getFilePath(T item);

    private void postErrorCallback(String errorInfo) {
    }

    public void cancel() {
        stopIfRunning();
    }

    private void stopIfRunning() {
        if (isAnimating()) {
            mAnimatedDrawable.stop();
        }
    }

    public boolean isAnimating() {
        return mAnimatedDrawable != null
                && mAnimatedDrawable.isRunning();
    }

    public void setMaxLoopTimes(int maxLoopTimes) {
        if (maxLoopTimes <= 0) {
            throw new IllegalArgumentException("unable to handle zero or negative loop times");
        }
        mMaxLoopTimes = maxLoopTimes;
    }

    @Override
    public void onSubmit(String id, Object callerContext) {
        Log.i(TAG, "onSubmit: ");
    }

    @Override
    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
        Log.i(TAG, "onFinalImageSet: ");
        if (animatable != null) {
            AnimatedDrawable2 animatedDrawable = (AnimatedDrawable2) animatable;
            AnimationBackend origin = animatedDrawable.getAnimationBackend();
            if (origin != null) {
                animatedDrawable.setAnimationBackend(new BackendWrapper(origin));
            }

            GenericDraweeHierarchy hierarchy = getHierarchy();
            if (hierarchy != null) {
                hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            }
            setScaleType(ScaleType.FIT_XY);

            animatedDrawable.setAnimationListener(KiwiWebpView.this);
            animatable.start();
        } else {
            postErrorCallback("null animated drawable");
        }
    }

    @Override
    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        Log.i(TAG, "onIntermediateImageSet: ");
    }

    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {
        Log.i(TAG, "onIntermediateImageFailed: ");
    }

    @Override
    public void onFailure(String id, Throwable throwable) {
        Log.i(TAG, "onFailure: " + throwable.toString());
        setController(null);
        postErrorCallback("on Animate failure");
        mItem = null;
    }

    @Override
    public void onRelease(String id) {
        Log.i(TAG, "onRelease: ");
    }

    @Override
    public void onAnimationStart(AnimatedDrawable2 drawable) {
        Log.i(TAG, "onAnimationStart: ");
        mAnimatedDrawable = drawable;
        mLoopTimes = 0;
    }

    @Override
    public void onAnimationStop(final AnimatedDrawable2 drawable) {
        Log.i(TAG, "onAnimationStop: ");
        setController(null);
//        mAnimatedDrawable = null;
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                drawable.jumpToFrame(drawable.getFrameCount() - 1);
//            }
//        }, 100);
        setImageBitmap(mBitmap);
        mItem = null;
    }

    @Override
    public void onAnimationReset(AnimatedDrawable2 drawable) {
        Log.i(TAG, "onAnimationReset: ");
//        mAnimatedDrawable = null;
        mItem = null;
    }

    @Override
    public void onAnimationRepeat(AnimatedDrawable2 drawable) {
        Log.i(TAG, "onAnimationRepeat: ");
    }


    @Override
    public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {
        Log.i(TAG, "onAnimationFrame: " + frameNumber);
        if (drawable != null) {
            if (mMaxLoopTimes == LOOP_TIMES_INFINITE) {
                return;
            }
            if (drawable.getFrameCount() - 1 == frameNumber && ++mLoopTimes == mMaxLoopTimes) {
//                drawable.jumpToFrame(drawable.getFrameCount() - 1);
//                drawable.stop();
                long start = System.currentTimeMillis();
                mBitmap = drawableToBitmap(drawable);

                long end = System.currentTimeMillis();
                Log.i(TAG, "onAnimationFrame,last frame,create bitmap takes:" + (end - start));
            }

        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;

    }

    public void notifyVisibleToUser() {
        if (mBitmap != null && !mAnimatedDrawable.isRunning()) {
            setImageBitmap(mBitmap);
        }
    }

    /**
     * 针对Fresco的AnimationBackend实例实现的包装器
     * 对{@link #drawFrame(Drawable, Canvas, int)}方法进行异常捕捉，解决线上存在的崩溃
     * 出现问题的版本Fresco是1.9.0，issue未被fix，后续更新库版本，可以考虑去掉此防御
     * <p>
     * 参考方案： https://github.com/facebook/fresco/issues/2093
     */
    private static class BackendWrapper implements AnimationBackend {

        public static final String TAG = "BackendWrapper";
        private final AnimationBackend mBackend;

        BackendWrapper(AnimationBackend backend) {
            mBackend = backend;
        }

        @Override
        public boolean drawFrame(Drawable parent, Canvas canvas, int frameNumber) {
            try {
                return mBackend.drawFrame(parent, canvas, frameNumber);
            } catch (IllegalStateException ex) {
                Log.e(TAG, "drawFrame(%d) error" + frameNumber);
            }
            return false;
        }

        @Override
        public void setAlpha(@IntRange(from = 0L, to = 255L) int alpha) {
            mBackend.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            mBackend.setColorFilter(colorFilter);
        }

        @Override
        public void setBounds(Rect bounds) {
            mBackend.setBounds(bounds);
        }

        @Override
        public int getIntrinsicWidth() {
            return mBackend.getIntrinsicWidth();
        }

        @Override
        public int getIntrinsicHeight() {
            return mBackend.getIntrinsicHeight();
        }

        @Override
        public int getSizeInBytes() {
            return mBackend.getSizeInBytes();
        }

        @Override
        public void clear() {
            mBackend.clear();
        }

        @Override
        public int getFrameCount() {
            return mBackend.getFrameCount();
        }

        @Override
        public int getFrameDurationMs(int frameNumber) {
            return mBackend.getFrameDurationMs(frameNumber);
        }

        @Override
        public int getLoopCount() {
            return 1;
        }
    }
}
