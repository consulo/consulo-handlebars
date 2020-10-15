package com.dmarcotte.handlebars;

import consulo.handlebars.icon.HandlebarsIconGroup;
import consulo.ui.image.Image;

public interface HbIcons
{
	Image FILE_ICON = HandlebarsIconGroup.handlebars_icon();

	Image OPEN_BLOCK = HandlebarsIconGroup.elementsOpenBlock();
	Image OPEN_INVERSE = HandlebarsIconGroup.elementsOpenInverse();
	Image OPEN_MUSTACHE = HandlebarsIconGroup.elementsOpenMustache();
	Image OPEN_UNESCAPED = HandlebarsIconGroup.elementsOpenUnescaped();
	Image OPEN_PARTIAL = HandlebarsIconGroup.elementsOpenPartial();
}
