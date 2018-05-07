package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbParam;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

public class HbParamImpl extends HbPsiElementImpl implements HbParam {
  public HbParamImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }
}
