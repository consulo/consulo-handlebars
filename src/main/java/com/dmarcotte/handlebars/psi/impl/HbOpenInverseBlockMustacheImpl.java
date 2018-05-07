package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbOpenInverseBlockMustache;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

public class HbOpenInverseBlockMustacheImpl extends HbOpenBlockMustacheImpl implements HbOpenInverseBlockMustache {
  public HbOpenInverseBlockMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

}
