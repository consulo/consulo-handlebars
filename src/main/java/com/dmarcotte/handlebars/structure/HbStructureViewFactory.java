package com.dmarcotte.handlebars.structure;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.psi.HbPsiFile;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.fileEditor.structureView.StructureViewBuilder;
import consulo.fileEditor.structureView.StructureViewModel;
import consulo.fileEditor.structureView.TreeBasedStructureViewBuilder;
import consulo.language.Language;
import consulo.language.editor.structureView.PsiStructureViewFactory;
import consulo.language.psi.PsiFile;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@ExtensionImpl
public class HbStructureViewFactory implements PsiStructureViewFactory {
  @Nullable
  @Override
  public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
    return new TreeBasedStructureViewBuilder() {
      @Nonnull
      @Override
      public StructureViewModel createStructureViewModel(Editor editor) {
        return new HbStructureViewModel((HbPsiFile)psiFile);
      }
    };
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}
