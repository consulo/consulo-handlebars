package com.dmarcotte.handlebars.config;

import org.jetbrains.annotations.Nls;
import javax.annotation.Nullable;
import com.dmarcotte.handlebars.HbBundle;
import com.intellij.openapi.options.BeanConfigurable;
import com.intellij.openapi.options.Configurable;

public class HbFoldingConfigurable extends BeanConfigurable<HbFoldingConfigurable.HbCodeFoldingOptionsBean> implements Configurable
{
	@SuppressWarnings("UnusedDeclaration") // the properties in this class are accessed using reflection by the parent
	public static class HbCodeFoldingOptionsBean
	{

		public boolean isAutoCollapseBlocks()
		{
			return HbConfig.isAutoCollapseBlocksEnabled();
		}

		public void setAutoCollapseBlocks(boolean value)
		{
			HbConfig.setAutoCollapseBlocks(value);
		}
	}

	public HbFoldingConfigurable()
	{
		super(new HbCodeFoldingOptionsBean());

		checkBox("autoCollapseBlocks", HbBundle.message("hb.pages.folding.auto.collapse.blocks"));
	}

	@Nls
	@Override
	public String getDisplayName()
	{
		return null;
	}

	@Nullable
	@Override
	public String getHelpTopic()
	{
		return null;
	}
}
