package com.dmarcotte.handlebars.psi;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.file.HbFileType;
import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.PsiFileBase;
import consulo.language.psi.PsiFileEx;
import consulo.virtualFileSystem.fileType.FileType;

import javax.annotation.Nonnull;

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
