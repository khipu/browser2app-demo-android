package com.browser2app.demo;

import android.app.Application;

import com.browser2app.khenshin.Khenshin;
import com.browser2app.khenshin.KhenshinInterface;
import com.browser2app.khenshin.KhenshinApplication;

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
				.setTaskAPIUrl("https://cmr.browser2app.com/api/automata/")
				.setDumpAPIUrl("https://cmr.browser2app.com/api/automata/")
				.setMainButtonStyle(Khenshin.CONTINUE_BUTTON_IN_FORM)
				.setAllowCredentialsSaving(true)
				.setHideWebAddressInformationInForm(false)
				.setAutomatonTimeout(10)
				.build();
	}

}
