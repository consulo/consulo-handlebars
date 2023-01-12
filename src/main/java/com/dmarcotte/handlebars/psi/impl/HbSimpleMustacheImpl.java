package com.dmarcotte.handlebars.psi.impl;

import com.dmarcotte.handlebars.psi.HbData;
import com.dmarcotte.handlebars.psi.HbPath;
import com.dmarcotte.handlebars.psi.HbPsiElement;
import com.dmarcotte.handlebars.psi.HbSimpleMustache;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;

import javax.annotation.Nonnull;

public class HbSimpleMustacheImpl extends HbMustacheImpl implements HbSimpleMustache {
  public HbSimpleMustacheImpl(@Nonnull ASTNode astNode) {
    super(astNode);
  }

  @Override
  public String getName() {
    for (PsiElement childElement : getChildren()) {
      // use the name of the first path or data to represent this mustache
      if (childElement instanceof HbPath
        || childElement instanceof HbData) {
        return ((HbPsiElement)childElement).getName();
      }
    }

    return null;
  }
}
