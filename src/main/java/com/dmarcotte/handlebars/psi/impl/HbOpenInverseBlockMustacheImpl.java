package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbOpenInverseBlockMustache;
import consulo.language.ast.ASTNode;

import jakarta.annotation.Nonnull;

public class HbOpenInverseBlockMustacheImpl extends HbOpenBlockMustacheImpl implements HbOpenInverseBlockMustache {
  public HbOpenInverseBlockMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

}
