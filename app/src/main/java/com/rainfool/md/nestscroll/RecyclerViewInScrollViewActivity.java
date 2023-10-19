package com.rainfool.md.nestscroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rainfool.md.R;
import com.rainfool.md.recyclerview.GalleryLayoutManager;
import com.rainfool.md.recyclerview.SimpleNumberAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rainfool
 */
public class RecyclerViewInScrollViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewInScrollVie";

    Button mDisableButton, mEnableButton;
    LockableScrollView mScrollView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_in_scroll_view);

        initViews();
        setupViewPager();
        setupButtons();
    }

    private void initViews() {
        mDisableButton = findViewById(R.id.btn_disable_scrolling);
        mEnableButton = findViewById(R.id.btn_enable_scrolling);
        mScrollView = findViewById(R.id.sv_container);
        viewPager = findViewById(R.id.view_pager);
    }

    private void setupViewPager() {
        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.app_color);
        colorList.add(R.color.cardview_dark_background);
        colorList.add(R.color.colorAccent);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                LayoutInflater inflater = LayoutInflater.from(container.getContext());
                View view = inflater.inflate(R.layout.view_recycler_view_in_scroll_view_inner, container, false);
                View headView = view.findViewById(R.id.head_in_scroll);
                headView.setBackgroundResource(colorList.get(position));
                RecyclerView recyclerView = view.findViewById(R.id.rv_recycler_in_scroll);
                setupRecyclerView(recyclerView);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
//        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.setAdapter(new SimpleNumberAdapter(1000));
        recyclerView.scrollToPosition(500);
        GalleryLayoutManager galleryLayoutManager = new GalleryLayoutManager(RecyclerView.HORIZONTAL);
        galleryLayoutManager.setItemTransformer(new GalleryLayoutManager.ItemTransformer() {
            @Override
            public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
                int r = 600;
                double radians = Math.toRadians(fraction * 45);
                Log.i(TAG, "transformItem: fraction = " + fraction + " radians = " + radians);
                int translateY = (int) (r - r * Math.cos(radians));
                item.setTranslationY(translateY);
                int translateX = (int) (r * Math.sin(radians) - item.getWidth() * fraction);
                item.setTranslationX(translateX);
                item.setRotation(fraction * 45);
            }
        });
        galleryLayoutManager.attach(recyclerView);
        galleryLayoutManager.setOverlap(0);
    }

    private void setupButtons() {
        mDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.setScrollingEnabled(false);
            }
        });
        mEnableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.setScrollingEnabled(true);
            }
        });
    }


}
