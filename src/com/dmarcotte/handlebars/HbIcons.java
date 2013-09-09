package com.dmarcotte.handlebars;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface HbIcons {
  Icon FILE_ICON = IconLoader.getIcon("/icons/handlebars_icon.png");

  Icon OPEN_BLOCK = IconLoader.getIcon("/icons/elements/openBlock.png");
  Icon OPEN_INVERSE = IconLoader.getIcon("/icons/elements/openInverse.png");
  Icon OPEN_MUSTACHE = IconLoader.getIcon("/icons/elements/openMustache.png");
  Icon OPEN_UNESCAPED = IconLoader.getIcon("/icons/elements/openUnescaped.png");
  Icon OPEN_PARTIAL = IconLoader.getIcon("/icons/elements/openPartial.png");
}
