package com.dmarcotte.handlebars.psi;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.file.HbFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.PsiFileEx;

public class HbPsiFile extends PsiFileBase implements PsiFileEx {

  public HbPsiFile(@Nonnull FileViewProvider viewProvider) {
    super(viewProvider, HbLanguage.INSTANCE);
  }

  @Nonnull
  public FileType getFileType() {
    return HbFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "HbFile:" + getName();
  }
}
