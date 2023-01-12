package com.dmarcotte.handlebars.parsing;

import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiParser;
import consulo.language.version.LanguageVersion;

import javax.annotation.Nonnull;

class HbParser implements PsiParser {

  @Nonnull
  public ASTNode parse(IElementType root, PsiBuilder builder, LanguageVersion languageVersion) {
    final PsiBuilder.Marker rootMarker = builder.mark();

    new HbParsing(builder).parse();

    rootMarker.done(root);

    return builder.getTreeBuilt();
  }
}
