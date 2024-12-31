package com.dmarcotte.handlebars.parsing;

import com.dmarcotte.handlebars.HbLanguage;
import consulo.language.ast.IElementType;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;

/**
 * Distinct interface to distinguish the leaf elements we get from the lexer from the synthetic
 * composite elements we create in the parser
 */
class HbCompositeElementType extends IElementType {
  public HbCompositeElementType(@Nonnull @NonNls String debugName) {
    super(debugName, HbLanguage.INSTANCE);
  }
}
