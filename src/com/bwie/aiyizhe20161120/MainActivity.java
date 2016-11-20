package com.bwie.aiyizhe20161120;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	
	private ViewPager mPager;
	private int id[] = new int[]{R.drawable.aa,R.drawable.ab,R.drawable.ac,R.drawable.ad};
	private int count = 1000;
	private boolean flag = true;
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 0){
				if(flag){
					count++;
					mPager.setCurrentItem(count);
					mHandler.sendEmptyMessageDelayed(0, 1000);
				}else{
					flag = true;
					mPager.setCurrentItem(count);
					mHandler.sendEmptyMessageDelayed(0, 1000);
				}
			}
			
			
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPager = (ViewPager) findViewById(R.id.vp_ViewPager);
        initViews();
        mPager.setAdapter(new MyPagerAdapter());
    }
    /**
     * Ð¡Ô²µã
     */
    private void initViews() {
    	LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
    	final ArrayList<ImageView> point = new ArrayList<ImageView>();
    	for (int i = 0; i < 4; i++) {
    		ImageView imView = new ImageView(this);
    		imView.setBackgroundResource(R.drawable.select);
    		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    		point.add(imView);
    		params.setMargins(10, 0, 10, 0);
			imView.setLayoutParams(params );
			ll.addView(imView);
		}
    	
    	
    	
    	point.get(0).setSelected(true);
    	mHandler.sendEmptyMessageDelayed(0, 1000);
    	
    	mPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				count = position;
				for (int i = 0; i < 4 ; i++){
					
					point.get(i).setSelected(false);
					
				}
				point.get(position%4).setSelected(true);
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				flag = false;
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
    	
	}
    
    class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View v = View.inflate(MainActivity.this, R.layout.pager, null);
			v.setBackgroundResource(id[position%4]);
			container.addView(v);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
    	
    }
    
}
