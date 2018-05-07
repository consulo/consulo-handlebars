package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbMustache;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

class HbMustacheImpl extends HbPsiElementImpl implements HbMustache {
  HbMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }
}
