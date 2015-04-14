package com.zhangmin.coconews.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhangmin.coconews.util.DensityUtil;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private final int COLUMNWIDTHPX = 55;
	private final int FLINGVELOCITYPX = 800;// ��������
	private int mColumnWidthDip;
	private int mFlingVelocityDip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// ��pxת����dip
		mColumnWidthDip = DensityUtil.px2dip(this, COLUMNWIDTHPX)+90;
		mFlingVelocityDip = DensityUtil.px2dip(this, FLINGVELOCITYPX)+90;
		// �@�������
		String[] categoryArray = getResources().getStringArray(
				R.array.categories);
		// ��������浽List��
		List<HashMap<String, String>> categories = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < categoryArray.length; i++) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("category_title", categoryArray[i]);
			categories.add(hashMap);
		}
		// ����Adapter��ָ��ӳ���ֶ�
		SimpleAdapter categoryAdapter = new SimpleAdapter(this, categories,
				R.layout.category_title, new String[] { "category_title" },
				new int[] { R.id.category_title });
		GridView category = new GridView(this);
		category.setColumnWidth(mColumnWidthDip);// ÿ����Ԫ��Č���
		category.setNumColumns(GridView.AUTO_FIT);// ��Ԫ��Ŀ
		category.setGravity(Gravity.CENTER);// �O�Ì��䷽ʽ
		// �O�Æ�Ԫ���x���Ǳ���ɫλ͸�����@���x��r�Ͳ����F�Sɫ����
		 category.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// ������Ԫ�񌒶Ⱥ͔�ĿӋ�㿂����
		int width = mColumnWidthDip * categories.size();
		System.out.println(width);
		LayoutParams params = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		// ����category���Ⱥ͸߶ȣ��@��category��һ���@ʾ
		category.setLayoutParams(params);
		// �O���m����
		category.setAdapter(categoryAdapter);
		// ��category���뵽������
		LinearLayout categoryList = (LinearLayout) findViewById(R.id.category_layout);
		categoryList.addView(category);
		// ��ͷ
		final HorizontalScrollView categoryScrollview = (HorizontalScrollView) findViewById(R.id.category_scrollview);
		Button categoryArrowRight = (Button) findViewById(R.id.category_arrow_right);
		categoryArrowRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				categoryScrollview.fling(mFlingVelocityDip);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
