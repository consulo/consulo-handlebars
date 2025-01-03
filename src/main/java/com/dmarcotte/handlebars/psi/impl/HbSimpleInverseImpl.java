package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.dmarcotte.handlebars.psi.HbSimpleInverse;
import consulo.language.ast.ASTNode;
import consulo.language.ast.TokenSet;

import jakarta.annotation.Nonnull;

public class HbSimpleInverseImpl extends HbMustacheImpl implements HbSimpleInverse {
  public HbSimpleInverseImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    ASTNode elseNode = getElseNode();
    if (elseNode != null) {
      return elseNode.getText();
    }
    return ""; // no name for "{{^}}" expressions
  }

  @Override
  public boolean hasElseNode() {
    return getElseNode() != null;
  }

  /**
   * If this element was created from an "{{else}}" expression, it will have an {@link HbTokenTypes#ELSE} child.
   * Otherwise, it was created from "{{^}}"
   *
   * @return the {@link HbTokenTypes#ELSE} element if it exists, null otherwise
   */
  private ASTNode getElseNode() {
    ASTNode[] elseChildren = getNode().getChildren(TokenSet.create(HbTokenTypes.ELSE));
    if (elseChildren != null && elseChildren.length > 0) {
      return elseChildren[0];
    }
    return null;
  }
}
