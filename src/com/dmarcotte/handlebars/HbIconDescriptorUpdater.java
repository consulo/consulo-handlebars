package com.dmarcotte.handlebars;

import org.jetbrains.annotations.NotNull;
import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.dmarcotte.handlebars.psi.HbBlockWrapper;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenInverseBlockMustache;
import com.dmarcotte.handlebars.psi.HbPartial;
import com.dmarcotte.handlebars.psi.HbSimpleInverse;
import com.dmarcotte.handlebars.psi.HbSimpleMustache;
import com.intellij.psi.PsiElement;
import consulo.ide.IconDescriptor;
import consulo.ide.IconDescriptorUpdater;

/**
 * @author VISTALL
 * @since 09.09.13.
 */
public class HbIconDescriptorUpdater implements IconDescriptorUpdater
{
	@Override
	public void updateIcon(@NotNull IconDescriptor iconDescriptor, @NotNull PsiElement element, int flags)
	{
		if(element instanceof HbPartial)
		{
			iconDescriptor.setMainIcon(HbIcons.OPEN_PARTIAL);
		}
		else if(element instanceof HbSimpleInverse)
		{
			iconDescriptor.setMainIcon(((HbSimpleInverse) element).hasElseNode() ? HbIcons.OPEN_MUSTACHE : HbIcons.OPEN_INVERSE);
		}
		else if(element instanceof HbOpenInverseBlockMustache)
		{
			iconDescriptor.setMainIcon(HbIcons.OPEN_INVERSE);
		}
		else if(element instanceof HbOpenBlockMustache)
		{
			iconDescriptor.setMainIcon(HbIcons.OPEN_BLOCK);
		}
		else if(element instanceof HbBlockWrapper)
		{
			HbOpenBlockMustache insideElement = ((HbBlockWrapper) element).getInsideElement();
			if(insideElement != null)
			{
				updateIcon(iconDescriptor, insideElement, flags);
			}
		}
		else if(element instanceof HbSimpleMustache)
		{
			PsiElement openStacheElem = element.getFirstChild();
			if(openStacheElem == null)
			{
				return;
			}

			if(openStacheElem.getNode().getElementType() == HbTokenTypes.OPEN_UNESCAPED)
			{
				iconDescriptor.setMainIcon(HbIcons.OPEN_UNESCAPED);
			}

			iconDescriptor.setMainIcon(HbIcons.OPEN_MUSTACHE);
		}
	}
}
