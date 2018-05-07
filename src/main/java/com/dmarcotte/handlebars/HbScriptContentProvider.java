package com.dmarcotte.handlebars;

import javax.annotation.Nullable;

import com.intellij.lang.HtmlScriptContentProvider;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;

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
}
