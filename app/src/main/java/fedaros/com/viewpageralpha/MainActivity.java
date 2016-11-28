package fedaros.com.viewpageralpha;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {


    ViewPager pager;
    ArrayList words = new ArrayList();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.viewPager);
//        mContainer.setViewPager(pager);
        words.add("hi1");
        words.add("hi2");
        words.add("hi3");
        words.add("hi4");
        words.add("hi5");
        words.add("hi6");
        words.add("hi7");
        words.add("hi8");
        words.add("hi9");

        PagerAdapter adapter = new MyPagerAdapter(words);
        InfinitePager infinitePager = new InfinitePager(adapter);
        pager.setPageTransformer(true, new ZoomOutPageTranformer(true));
        pager.setAdapter(infinitePager);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
//        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setOffscreenPageLimit(3);
        //A little space between pages
        pager.setPageMargin((int) getResources().getDimension(R.dimen.dimen_20));
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        pager.setCurrentItem(infinitePager.getCount()/2);
    }


    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends PagerAdapter {

        ArrayList items = new ArrayList();

        public MyPagerAdapter(ArrayList list){
            this.items = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            TextView view = new TextView(MainActivity.this);
//            view.setText(items.get(position).toString() + position);
//            view.setTextColor(Color.WHITE);
//            view.setGravity(Gravity.CENTER);
//            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
            View fragment = inflater.inflate(R.layout.view_pager_item, null);
            TextView txt = (TextView) fragment.findViewById(R.id.hello_txt);
            txt.setText(words.get(position).toString());
            container.addView(fragment);
            return fragment;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }



    }
}
