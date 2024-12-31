package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbMustache;
import consulo.language.ast.ASTNode;

import jakarta.annotation.Nonnull;

class HbMustacheImpl extends HbPsiElementImpl implements HbMustache {
  HbMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }
}
