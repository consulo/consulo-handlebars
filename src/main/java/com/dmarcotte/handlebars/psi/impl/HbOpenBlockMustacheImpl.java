package com.dmarcotte.handlebars.psi.impl;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.psi.HbCloseBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

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
