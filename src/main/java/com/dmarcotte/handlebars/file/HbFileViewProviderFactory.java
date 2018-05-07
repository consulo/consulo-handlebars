package com.dmarcotte.handlebars.file;

import javax.annotation.Nonnull;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;


public class HbFileViewProviderFactory implements FileViewProviderFactory {
  @Override
  public FileViewProvider createFileViewProvider(@Nonnull VirtualFile virtualFile,
                                                 Language language,
                                                 @Nonnull PsiManager psiManager,
                                                 boolean physical) {
    return new HbFileViewProvider(psiManager, virtualFile, physical);
  }
}

