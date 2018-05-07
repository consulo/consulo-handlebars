package com.dmarcotte.handlebars.structure;

import com.dmarcotte.handlebars.psi.HbPsiFile;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Collection;

class HbTreeElementFile extends PsiTreeElementBase<HbPsiFile> {

  private final HbPsiFile myFile;

  public HbTreeElementFile(@Nonnull HbPsiFile psiFile) {
    super(psiFile);
    this.myFile = psiFile;
  }

  @Nonnull
  @Override
  public Collection<StructureViewTreeElement> getChildrenBase() {
    return HbTreeElement.getStructureViewTreeElements(myFile);
  }

  @Nullable
  @Override
  public String getPresentableText() {
    return myFile.getName();
  }
}
