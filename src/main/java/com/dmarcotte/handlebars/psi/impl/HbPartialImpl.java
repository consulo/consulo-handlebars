package com.dmarcotte.handlebars.psi.impl;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.psi.HbPartial;
import com.dmarcotte.handlebars.psi.HbPartialName;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;

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
