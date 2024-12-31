package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbBlockMustache;
import com.dmarcotte.handlebars.psi.HbPath;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

abstract class HbBlockMustacheImpl extends HbPsiElementImpl implements HbBlockMustache {
  protected HbBlockMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  @Nullable
  public HbPath getBlockMainPath() {
    return PsiTreeUtil.findChildOfType(this, HbPath.class);
  }

  @Override
  @Nullable
  public String getName() {
    HbPath mainPath = getBlockMainPath();
    return mainPath == null ? null : mainPath.getName();
  }
}
