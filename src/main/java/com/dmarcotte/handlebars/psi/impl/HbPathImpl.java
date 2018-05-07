package com.dmarcotte.handlebars.psi.impl;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.psi.HbPath;
import com.intellij.lang.ASTNode;

public class HbPathImpl extends HbPsiElementImpl implements HbPath {
  public HbPathImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    return getText();
  }
}
