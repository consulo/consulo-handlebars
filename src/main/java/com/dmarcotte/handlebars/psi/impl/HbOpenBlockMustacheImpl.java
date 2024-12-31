package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbCloseBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

public class HbOpenBlockMustacheImpl extends HbBlockMustacheImpl implements HbOpenBlockMustache {
  public HbOpenBlockMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public HbCloseBlockMustache getPairedElement() {
    PsiElement closeBlockElement = getParent().getLastChild();
    if (closeBlockElement instanceof HbCloseBlockMustache) {
      return (HbCloseBlockMustache)closeBlockElement;
    }

    return null;
  }
}
