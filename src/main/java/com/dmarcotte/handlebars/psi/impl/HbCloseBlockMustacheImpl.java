package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbCloseBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

public class HbCloseBlockMustacheImpl extends HbBlockMustacheImpl implements HbCloseBlockMustache {
  public HbCloseBlockMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public HbOpenBlockMustache getPairedElement() {
    PsiElement openBlockElement = getParent().getFirstChild();
    if (openBlockElement instanceof HbOpenBlockMustache) {
      return (HbOpenBlockMustache)openBlockElement;
    }

    return null;
  }
}
