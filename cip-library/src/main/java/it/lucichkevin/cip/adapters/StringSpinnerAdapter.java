package it.lucichkevin.cip.adapters;

import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class StringSpinnerAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {

	private final ThemedSpinnerAdapter.Helper mDropDownHelper;

	public StringSpinnerAdapter(Context context, ArrayList<String> dates_list ){
		super( context, android.R.layout.simple_list_item_1, dates_list );
		mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent ){
		View view;

		if( convertView == null ){
			// Inflate the drop down using the helper's LayoutInflater
			LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
			view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		} else {
			view = convertView;
		}

		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(getItem(position));

		return view;
	}

	@Override
	public Resources.Theme getDropDownViewTheme(){
		return mDropDownHelper.getDropDownViewTheme();
	}

	@Override
	public void setDropDownViewTheme( Resources.Theme theme ){
		mDropDownHelper.setDropDownViewTheme(theme);
	}
}