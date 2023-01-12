package com.dmarcotte.handlebars.config;

import com.dmarcotte.handlebars.HbBundle;
import consulo.annotation.component.ExtensionImpl;
import consulo.configurable.ApplicationConfigurable;
import consulo.configurable.BeanConfigurable;
import org.jetbrains.annotations.Nls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@ExtensionImpl
public class HbFoldingConfigurable extends BeanConfigurable<HbFoldingConfigurable.HbCodeFoldingOptionsBean> implements ApplicationConfigurable {
  @SuppressWarnings("UnusedDeclaration") // the properties in this class are accessed using reflection by the parent
  public static class HbCodeFoldingOptionsBean {

    public boolean isAutoCollapseBlocks() {
      return HbConfig.isAutoCollapseBlocksEnabled();
    }

    public void setAutoCollapseBlocks(boolean value) {
      HbConfig.setAutoCollapseBlocks(value);
    }
  }

  public HbFoldingConfigurable() {
    super(new HbCodeFoldingOptionsBean());

    checkBox("autoCollapseBlocks", HbBundle.message("hb.pages.folding.auto.collapse.blocks"));
  }

  @Nonnull
  @Override
  public String getId() {
    return "editor.preferences.folding.hb";
  }

  @Nullable
  @Override
  public String getParentId() {
    return "editor.preferences.folding";
  }

  @Nls
  @Override
  public String getDisplayName() {
    return HbBundle.message("hb.pages.options.title");
  }
}
