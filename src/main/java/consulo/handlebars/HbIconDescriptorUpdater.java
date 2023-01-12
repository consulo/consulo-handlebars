package consulo.handlebars;

import com.dmarcotte.handlebars.HbIcons;
import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.dmarcotte.handlebars.psi.*;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.icon.IconDescriptor;
import consulo.language.icon.IconDescriptorUpdater;
import consulo.language.psi.PsiElement;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 09.09.13.
 */
@ExtensionImpl
public class HbIconDescriptorUpdater implements IconDescriptorUpdater {
  @RequiredReadAction
  @Override
  public void updateIcon(@Nonnull IconDescriptor iconDescriptor, @Nonnull PsiElement element, int flags) {
    if (element instanceof HbPartial) {
      iconDescriptor.setMainIcon(HbIcons.OPEN_PARTIAL);
    }
    else if (element instanceof HbSimpleInverse) {
      iconDescriptor.setMainIcon(((HbSimpleInverse)element).hasElseNode() ? HbIcons.OPEN_MUSTACHE : HbIcons.OPEN_INVERSE);
    }
    else if (element instanceof HbOpenInverseBlockMustache) {
      iconDescriptor.setMainIcon(HbIcons.OPEN_INVERSE);
    }
    else if (element instanceof HbOpenBlockMustache) {
      iconDescriptor.setMainIcon(HbIcons.OPEN_BLOCK);
    }
    else if (element instanceof HbBlockWrapper) {
      HbOpenBlockMustache insideElement = ((HbBlockWrapper)element).getInsideElement();
      if (insideElement != null) {
        updateIcon(iconDescriptor, insideElement, flags);
      }
    }
    else if (element instanceof HbSimpleMustache) {
      PsiElement openStacheElem = element.getFirstChild();
      if (openStacheElem == null) {
        return;
      }

      if (openStacheElem.getNode().getElementType() == HbTokenTypes.OPEN_UNESCAPED) {
        iconDescriptor.setMainIcon(HbIcons.OPEN_UNESCAPED);
      }

      iconDescriptor.setMainIcon(HbIcons.OPEN_MUSTACHE);
    }
  }
}
