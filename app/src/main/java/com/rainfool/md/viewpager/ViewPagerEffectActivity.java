package com.rainfool.md.viewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainfool.md.R;

public class ViewPagerEffectActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_effect);
        mViewPager = findViewById(R.id.banner_viewpager);

        mViewPager.setPageMargin(13);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new ScaleInTransformer());
        mViewPager.setCurrentItem(1);

        mViewPager.setAdapter(new ViewPagerAdapter());
    }


    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            View view = inflater.inflate(R.layout.view_pager_item, container, false);
            container.addView(view);
            return view;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    public class ScaleInTransformer implements ViewPager.PageTransformer {
        private static final float DEFAULT_MIN_SCALE = 0.89f;
        private float mMinScale = DEFAULT_MIN_SCALE;
        public static final float DEFAULT_CENTER = 0.5f;

        public ScaleInTransformer() {
            this(DEFAULT_MIN_SCALE);
        }

        public ScaleInTransformer(float minScale) {
            mMinScale = minScale;
        }


        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void pageTransform(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            view.setPivotY(pageHeight / 2);
            view.setPivotX(pageWidth / 2);
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setScaleX(mMinScale);
                view.setScaleY(mMinScale);
                view.setPivotX(pageWidth);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                if (position < 0) //1-2:1[0,-1] ;2-1:1[-1,0]
                {

                    float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    view.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));

                } else //1-2:2[1,0] ;2-1:2[0,1]
                {
                    float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    view.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
                }


            } else { // (1,+Infinity]
                view.setPivotX(0);
                view.setScaleX(mMinScale);
                view.setScaleY(mMinScale);
            }
        }

        @Override
        public void transformPage(View page, float position) {
            page.setScaleX(0.999f);//hack
            pageTransform(page, position);
        }
    }
}
