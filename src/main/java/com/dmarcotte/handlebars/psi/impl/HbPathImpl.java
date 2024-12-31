package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbPath;
import consulo.language.ast.ASTNode;

import jakarta.annotation.Nonnull;

public class HbPathImpl extends HbPsiElementImpl implements HbPath {
  public HbPathImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    return getText();
  }
}
