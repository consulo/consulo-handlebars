package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbPsiElement;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProvider;

import jakarta.annotation.Nonnull;

public class HbPsiElementImpl extends ASTWrapperPsiElement implements HbPsiElement {
  public HbPsiElementImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public ItemPresentation getPresentation() {
    return ItemPresentationProvider.getItemPresentation(this);
  }
}
