package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.file.FileViewProvider;
import consulo.language.file.LanguageFileViewProviderFactory;
import consulo.language.psi.PsiManager;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

@ExtensionImpl
public class HbFileViewProviderFactory implements LanguageFileViewProviderFactory {
  @Override
  public FileViewProvider createFileViewProvider(@Nonnull VirtualFile virtualFile,
                                                 Language language,
                                                 @Nonnull PsiManager psiManager,
                                                 boolean physical) {
    return new HbFileViewProvider(psiManager, virtualFile, physical);
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}

