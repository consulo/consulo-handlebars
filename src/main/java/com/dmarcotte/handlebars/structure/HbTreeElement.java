package com.dmarcotte.handlebars.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.dmarcotte.handlebars.psi.HbPsiElement;
import com.dmarcotte.handlebars.psi.HbStatements;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiElement;
import com.intellij.util.ReflectionUtil;
import consulo.ide.IconDescriptorUpdaters;
import consulo.ui.image.Image;

class HbTreeElement extends PsiTreeElementBase<HbPsiElement> {

  private final HbPsiElement myElement;

  private HbTreeElement(HbPsiElement psiElement) {
    super(psiElement);
    myElement = psiElement;
  }

  @Nonnull
  @Override
  public Collection<StructureViewTreeElement> getChildrenBase() {
    return getStructureViewTreeElements(myElement);
  }

  static List<StructureViewTreeElement> getStructureViewTreeElements(PsiElement psiElement) {
    List<StructureViewTreeElement> children = new ArrayList<StructureViewTreeElement>();
    for (PsiElement childElement : psiElement.getChildren()) {
      if (!(childElement instanceof HbPsiElement)) {
        continue;
      }

      if (childElement instanceof HbStatements) {
        // HbStatments elements transparently wrap other elements, so we don't add
        // this element to the tree, but we add its children
        children.addAll(new HbTreeElement((HbPsiElement)childElement).getChildrenBase());
      }

      for (Class suitableClass : HbStructureViewModel.ourSuitableClasses) {
        if (ReflectionUtil.isAssignable(suitableClass, childElement.getClass())) {
          children.add(new HbTreeElement((HbPsiElement)childElement));
          break;
        }
      }
    }
    return children;
  }

  @Nullable
  @Override
  public String getPresentableText() {
    return myElement.getName();
  }

  @Override
  public Image getIcon() {
    return IconDescriptorUpdaters.getIcon(myElement, 0);
  }
}
