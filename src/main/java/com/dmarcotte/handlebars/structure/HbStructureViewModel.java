package com.dmarcotte.handlebars.structure;

import com.dmarcotte.handlebars.psi.HbBlockWrapper;
import com.dmarcotte.handlebars.psi.HbMustache;
import com.dmarcotte.handlebars.psi.HbPsiFile;
import consulo.codeEditor.Editor;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.language.editor.structureView.TextEditorBasedStructureViewModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

class HbStructureViewModel extends TextEditorBasedStructureViewModel {

  private final HbPsiFile myFile;
  // classes which we construct structure view nodes for
  static final Class[] ourSuitableClasses = new Class[]{HbBlockWrapper.class, HbMustache.class};

  public HbStructureViewModel(@Nullable Editor editor, @Nonnull HbPsiFile psiFile) {
    super(editor, psiFile);
    myFile = psiFile;
  }

  @Nonnull
  @Override
  protected Class[] getSuitableClasses() {
    return ourSuitableClasses;
  }

  @Nonnull
  @Override
  public StructureViewTreeElement getRoot() {
    return new HbTreeElementFile(myFile);
  }
}
