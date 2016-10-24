package com.browser2app.demo;

import android.app.Application;

import com.browser2app.khenshin.Khenshin;
import com.browser2app.khenshin.KhenshinInterface;
import com.browser2app.khenshin.activities.KhenshinApplication;

public class Demo extends Application implements KhenshinApplication {

	private KhenshinInterface khenshin;

	@Override
	public KhenshinInterface getKhenshin() {
		return khenshin;
	}


	public Demo() {
		super();
		khenshin = new Khenshin.KhenshinBuilder()
				.setApplication(this)
				.setAutomatonApiUrl("https://khipu.com/app/2.0/")
				.setCerebroApiUrl("https://khipu.com/cerebro/")
				.build();
	}

}
