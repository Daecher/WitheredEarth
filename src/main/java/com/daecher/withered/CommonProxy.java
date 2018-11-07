package com.daecher.withered;

import net.minecraft.util.text.translation.I18n;

public class CommonProxy {

	public String localize(String unlocalized, Object... args)
	{
		return I18n.translateToLocalFormatted(unlocalized, args);
	}
}
