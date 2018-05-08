package com.dmarcotte.handlebars;

import com.intellij.openapi.util.IconLoader;
import consulo.ui.image.Image;

public interface HbIcons
{
	Image FILE_ICON = IconLoader.getIcon("/icons/handlebars_icon.png");

	Image OPEN_BLOCK = IconLoader.getIcon("/icons/elements/openBlock.png");
	Image OPEN_INVERSE = IconLoader.getIcon("/icons/elements/openInverse.png");
	Image OPEN_MUSTACHE = IconLoader.getIcon("/icons/elements/openMustache.png");
	Image OPEN_UNESCAPED = IconLoader.getIcon("/icons/elements/openUnescaped.png");
	Image OPEN_PARTIAL = IconLoader.getIcon("/icons/elements/openPartial.png");
}
