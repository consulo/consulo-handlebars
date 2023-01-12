package com.dmarcotte.handlebars;

import consulo.handlebars.icon.HandlebarsIconGroup;
import consulo.ui.image.Image;

public interface HbIcons {
  Image FILE_ICON = HandlebarsIconGroup.handlebars_icon();

  Image OPEN_BLOCK = HandlebarsIconGroup.elementsOpenblock();
  Image OPEN_INVERSE = HandlebarsIconGroup.elementsOpeninverse();
  Image OPEN_MUSTACHE = HandlebarsIconGroup.elementsOpenmustache();
  Image OPEN_UNESCAPED = HandlebarsIconGroup.elementsOpenunescaped();
  Image OPEN_PARTIAL = HandlebarsIconGroup.elementsOpenpartial();
}
