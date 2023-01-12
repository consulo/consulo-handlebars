package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbStatements;
import consulo.language.ast.ASTNode;

import javax.annotation.Nonnull;

public class HbStatementsImpl extends HbPsiElementImpl implements HbStatements {
  public HbStatementsImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }
}
