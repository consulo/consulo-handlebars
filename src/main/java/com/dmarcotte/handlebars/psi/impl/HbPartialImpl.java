package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbPartial;
import com.dmarcotte.handlebars.psi.HbPartialName;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;

public class HbPartialImpl extends HbMustacheImpl implements HbPartial {
  public HbPartialImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    HbPartialName partialName = PsiTreeUtil.findChildOfType(this, HbPartialName.class);
    return partialName == null ? null : partialName.getName();
  }
}
