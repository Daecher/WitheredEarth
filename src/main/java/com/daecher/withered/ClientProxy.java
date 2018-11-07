package com.daecher.withered;

import net.minecraft.client.resources.I18n;

public class ClientProxy extends CommonProxy{

	public String localize(String unlocalized, Object... args)
	{
		return I18n.format(unlocalized, args);
	}
}
