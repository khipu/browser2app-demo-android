package com.browser2app.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.browser2app.khenshin.KhenshinConstants;
import com.browser2app.khenshin.activities.StartPaymentActivity;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

	private static final int START_PAYMENT_REQUEST_CODE = 101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void doPay(View view) {
		Intent intent = new Intent(MainActivity.this, StartPaymentActivity.class);
		intent.putExtra(KhenshinConstants.EXTRA_AUTOMATON_ID, "dfFbF");
		Bundle params = new Bundle();

		params.putString("subject", "Prueba");
		params.putString("amount", "2000");
		params.putString("paymentId", UUID.randomUUID().toString());
		params.putString("khipu_account_name", "CUENTAPRUEBA");
		params.putString("khipu_account_number", "123123123");
		params.putString("khipu_alias", "CUENTAPRUEBA");
		params.putString("payer_name", "Emilio Davis");
		params.putString("payer_email", "demo@gmail.com");
		params.putString("useremail", "demo@gmail.com");
		params.putString("khipu_rut", "12.345.678-9");
		params.putString("khipu_email", "transferencias@khipu.com");

		intent.putExtra(KhenshinConstants.EXTRA_AUTOMATON_PARAMETERS, params);
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
