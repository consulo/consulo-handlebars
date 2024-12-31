package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbData;
import consulo.language.ast.ASTNode;

import jakarta.annotation.Nonnull;

public class HbDataImpl extends HbPsiElementImpl implements HbData {
  public HbDataImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    return getText();
  }
}
