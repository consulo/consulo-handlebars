package com.dmarcotte.handlebars.parsing;

import javax.annotation.Nonnull;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import consulo.lang.LanguageVersion;

class HbParser implements PsiParser {

  @Nonnull
  public ASTNode parse(IElementType root, PsiBuilder builder, LanguageVersion languageVersion) {
    final PsiBuilder.Marker rootMarker = builder.mark();

    new HbParsing(builder).parse();

    rootMarker.done(root);

    return builder.getTreeBuilt();
  }
}
