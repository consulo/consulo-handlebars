package com.dmarcotte.handlebars.psi.impl;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.psi.HbPsiElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;

public class HbPsiElementImpl extends ASTWrapperPsiElement implements HbPsiElement {
  public HbPsiElementImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public ItemPresentation getPresentation() {
    return ItemPresentationProviders.getItemPresentation(this);
  }
}
