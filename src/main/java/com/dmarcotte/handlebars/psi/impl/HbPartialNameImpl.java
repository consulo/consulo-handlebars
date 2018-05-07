package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbPartialName;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

public class HbPartialNameImpl extends HbPsiElementImpl implements HbPartialName {
  public HbPartialNameImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    return getText();
  }
}
