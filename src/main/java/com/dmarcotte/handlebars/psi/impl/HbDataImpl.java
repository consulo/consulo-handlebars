package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbData;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

public class HbDataImpl extends HbPsiElementImpl implements HbData {
  public HbDataImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    return getText();
  }
}
