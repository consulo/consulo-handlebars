package com.dmarcotte.handlebars.config;

import com.dmarcotte.handlebars.HbBundle;
import consulo.annotation.component.ExtensionImpl;
import consulo.configurable.ApplicationConfigurable;
import consulo.configurable.SimpleConfigurableByProperties;
import consulo.disposer.Disposable;
import consulo.ui.CheckBox;
import consulo.ui.Component;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.layout.VerticalLayout;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@ExtensionImpl
public class HbFoldingConfigurable extends SimpleConfigurableByProperties implements ApplicationConfigurable {
  @RequiredUIAccess
  @Nonnull
  @Override
  protected Component createLayout(@Nonnull PropertyBuilder propertyBuilder,
                                   @Nonnull Disposable disposable) {
    VerticalLayout root = VerticalLayout.create();

    CheckBox collapseBlocksBox = CheckBox.create(HbBundle.message("hb.pages.folding.auto.collapse.blocks"));
    root.add(collapseBlocksBox);
    propertyBuilder.add(collapseBlocksBox, HbConfig::isAutoCollapseBlocksEnabled, HbConfig::setAutoCollapseBlocks);
    return root;
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

  @Nonnull
  @Override
  public String getDisplayName() {
    return HbBundle.message("hb.pages.options.title");
  }
}
