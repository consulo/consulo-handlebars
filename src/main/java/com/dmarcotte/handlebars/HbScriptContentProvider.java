package com.dmarcotte.handlebars;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.lexer.Lexer;
import consulo.xml.lang.HtmlScriptContentProvider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@ExtensionImpl
public class HbScriptContentProvider implements HtmlScriptContentProvider {
  @Override
  public IElementType getScriptElementType() {
    return null;
  }

  @Nullable
  @Override
  public Lexer getHighlightingLexer() {
    return null;
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}
