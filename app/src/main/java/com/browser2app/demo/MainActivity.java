package com.browser2app.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.browser2app.khenshin.KhenshinConstants;
import com.browser2app.khenshin.activities.StartPaymentActivity;

public class MainActivity extends AppCompatActivity {

	private static final int START_PAYMENT_REQUEST_CODE = 101;
	private EditText paymentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		paymentId = (EditText) findViewById(R.id.paymentId);
	}

	public void doPay(View view) {
		Intent intent = new Intent(MainActivity.this, StartPaymentActivity.class);
		intent.putExtra(KhenshinConstants.EXTRA_PAYMENT_ID, paymentId.getText().toString());
		intent.putExtra(KhenshinConstants.EXTRA_FORCE_UPDATE_PAYMENT, false);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, START_PAYMENT_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == START_PAYMENT_REQUEST_CODE) {
			String exitUrl = data.getStringExtra(KhenshinConstants.EXTRA_INTENT_URL);
			if (resultCode == RESULT_OK) {
				Toast.makeText(MainActivity.this, "PAYMENT OK, exit url: " + exitUrl,
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this, "PAYMENT FAILED, exit url: " + exitUrl,
						Toast.LENGTH_LONG).show();
			}
		}

	}
}
