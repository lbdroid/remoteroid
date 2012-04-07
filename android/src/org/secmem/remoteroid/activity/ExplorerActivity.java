package org.secmem.remoteroid.activity;

import org.secmem.remoteroid.R;
import org.secmem.remoteroid.adapter.DataList;
import org.secmem.remoteroid.adapter.ExplorerAdapter;
import org.secmem.remoteroid.expinterface.OnFileSelectedListener;
import org.secmem.remoteroid.expinterface.OnPathChangedListener;
import org.secmem.remoteroid.util.HongUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class ExplorerActivity extends SherlockActivity implements OnScrollListener {
	
	public static boolean SCROLL_STATE = false;
	
	private Button categoryBtn;
	private Button homeBtn;
	private Button topBtn;
	
	private TextView pathTv;
	
	private GridView gridview;
	private ExplorerAdapter adapter;
	
	private DataList dataList;
	
	private String currentPath="";
	private String beforePath="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explorer_activity);	
		
		categoryBtn = (Button)findViewById(R.id.explorer_btn_category);
		topBtn = (Button)findViewById(R.id.explorer_btn_top);
		homeBtn = (Button)findViewById(R.id.explorer_btn_home);
		
		categoryBtn.setOnClickListener(topBtnListener);
		topBtn.setOnClickListener(topBtnListener);
		homeBtn.setOnClickListener(topBtnListener);
		
		
		pathTv = (TextView)findViewById(R.id.explorer_tv_path);
		
		gridview = (GridView)findViewById(R.id.explorer_view_grid);
		gridview.setOnScrollListener(this);
		
		dataList = new DataList(this);
		dataList.setOnPathChangedListener(onPathChanged);
		dataList.setOnFileSelected(onFileSelected);
		
		dataList.setPath("/mnt/sdcard");
		
		Log.i("qq","actSize = "+dataList.getExpList().size());
		
		adapter = new ExplorerAdapter(this, R.layout.grid_explorer_row, dataList);
		gridview.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	OnClickListener topBtnListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			
			case R.id.explorer_btn_home : 
				dataList.setPath("/mnt/sdcard");
				adapter.notifyDataSetChanged();
				break;
			
			case R.id.explorer_btn_top : 
				String backPath = dataList.getBackPathName();
				if(dataList.getPathCount()!=0){
					dataList.setPath(backPath);
					adapter.notifyDataSetChanged();
				}
				else{
					HongUtil.makeToast(ExplorerActivity.this, "최상위입니다.^^");
				}
				break;
			
			case R.id.explorer_btn_category:
				
				break;
			}
		}
	};
	
	public void onBackPressed() {
		String backPath = dataList.getBackPathName();
		if(dataList.getPathCount()==0){
			finish();
		}
		else{
			dataList.setPath(backPath);
			adapter.notifyDataSetChanged();
		}
		
	};
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch(scrollState){
		case OnScrollListener.SCROLL_STATE_IDLE:
			SCROLL_STATE= false;
			break;
			
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			SCROLL_STATE = true;
			break;
			
		case OnScrollListener.SCROLL_STATE_FLING:
			SCROLL_STATE = true;
			break;		
		}
	}
	
	private OnPathChangedListener onPathChanged = new OnPathChangedListener() {
		public void onChanged(String path) {
			pathTv.setText(path);
		}
	};
    
    private OnFileSelectedListener onFileSelected = new OnFileSelectedListener() {
		public void onSelected(String path, String fileName) {
			// TODO
		}
	};
}