package com.dmarcotte.handlebars.inspections;

import com.dmarcotte.handlebars.HbLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.AnnotatorFactory;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 12/01/2023
 */
@ExtensionImpl
public class HbBlockMismatchInspectionFactory implements AnnotatorFactory {
  @Nullable
  @Override
  public Annotator createAnnotator() {
    return new HbBlockMismatchInspection();
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}
