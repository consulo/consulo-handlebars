package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbBlockWrapper;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;

public class HbBlockWrapperImpl extends HbPsiElementImpl implements HbBlockWrapper {
  public HbBlockWrapperImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    HbOpenBlockMustache openBlockMustache = getInsideElement();
    return openBlockMustache == null ? null : openBlockMustache.getName();
  }

  @Override
  public HbOpenBlockMustache getInsideElement() {
    return PsiTreeUtil.findChildOfType(this, HbOpenBlockMustache.class);
  }
}
