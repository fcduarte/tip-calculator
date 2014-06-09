package com.fcduarte.tipcalculator;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.fcduarte.tipcalculator.model.Bill;

public class MainActivity extends Activity {

	private Bill mBill;
	private EditText etSubTotal;
	private TextView tvTotal;
	private TextView tvTip;
	private TextView tvTipPercentage;
	private SeekBar sbTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etSubTotal = (EditText) findViewById(R.id.etSubTotal);
		tvTotal = (TextView) findViewById(R.id.tvTotal);
		tvTip = (TextView) findViewById(R.id.tvTip);
		tvTipPercentage = (TextView) findViewById(R.id.tvTipPercentage);
		sbTip = (SeekBar) findViewById(R.id.sbTip);

		mBill = new Bill();

		bindDataToViews();

		TextWatcher watcher = getSubTotalTextWatcher();
		etSubTotal.addTextChangedListener(watcher);

		OnSeekBarChangeListener listener = getTipSeekBarListener();
		sbTip.setOnSeekBarChangeListener(listener);
	}

	private TextWatcher getSubTotalTextWatcher() {
		return new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String subTotal = "0.00";

				if (s.length() > 0) {
					subTotal = s.toString();
				}

				mBill.setSubTotal(new BigDecimal(subTotal));
				bindDataToViews();
			}
		};
	}

	private OnSeekBarChangeListener getTipSeekBarListener() {
		return new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mBill.setTipPercentage(Double.valueOf(progress) / 10000);
				bindDataToViews();
			}
		};
	}

	private void bindDataToViews() {
		tvTipPercentage.setText(String.valueOf(mBill.getTipPercentageFormatted()));
		tvTotal.setText(String.valueOf(mBill.getTotalFormatted()));
		tvTip.setText(String.valueOf(mBill.getTotalTipFormatted()));
	}
}
