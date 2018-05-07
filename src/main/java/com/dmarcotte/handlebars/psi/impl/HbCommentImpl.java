package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbComment;
import com.intellij.lang.ASTNode;
import javax.annotation.Nonnull;

public class HbCommentImpl extends HbPsiElementImpl implements HbComment {
  public HbCommentImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }
}
