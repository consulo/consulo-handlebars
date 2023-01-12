package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import consulo.language.Language;
import consulo.language.impl.file.MultiplePsiFilesPerDocumentFileViewProvider;
import consulo.language.impl.psi.PsiFileImpl;
import consulo.language.impl.psi.template.TemplateDataElementType;
import consulo.language.parser.ParserDefinition;
import consulo.language.psi.LanguageSubstitutors;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.template.ConfigurableTemplateLanguageFileViewProvider;
import consulo.language.template.TemplateDataLanguageMappings;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;
import java.util.Set;

public class HbFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider
  implements ConfigurableTemplateLanguageFileViewProvider {

  private final PsiManager myManager;
  private final VirtualFile myFile;

  public HbFileViewProvider(PsiManager manager, VirtualFile file, boolean physical) {
    super(manager, file, physical);

    myManager = manager;
    myFile = file;

    getTemplateDataLanguage(myManager, myFile);
  }

  private Language getTemplateDataLanguage(PsiManager manager, VirtualFile file) {
    Language dataLang = TemplateDataLanguageMappings.getInstance(manager.getProject()).getMapping(file);
    if (dataLang == null) {
      dataLang = HbLanguage.getDefaultTemplateFileType().getLanguage();
    }

    Language substituteLang = LanguageSubstitutors.substituteLanguage(dataLang, file, manager.getProject());

    // only use a substituted language if it's templateable
    if (TemplateDataLanguageMappings.getTemplateableLanguages().contains(substituteLang)) {
      dataLang = substituteLang;
    }

    return dataLang;
  }

  @Nonnull
  @Override
  public Language getBaseLanguage() {
    return HbLanguage.INSTANCE;
  }

  @Nonnull
  @Override
  public Language getTemplateDataLanguage() {
    return getTemplateDataLanguage(myManager, myFile);
  }

  @Nonnull
  @Override
  public Set<Language> getLanguages() {
    return Set.of(HbLanguage.INSTANCE, getTemplateDataLanguage(myManager, myFile));
  }

  @Override
  protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile virtualFile) {
    return new HbFileViewProvider(getManager(), virtualFile, false);
  }

  @Override
  protected PsiFile createFile(@Nonnull Language lang) {
    ParserDefinition parserDefinition = ParserDefinition.forLanguage(lang);
    if (parserDefinition == null) {
      return null;
    }

    Language templateDataLanguage = getTemplateDataLanguage(myManager, myFile);
    if (lang == templateDataLanguage) {
      PsiFileImpl file = (PsiFileImpl)parserDefinition.createFile(this);
      file.setContentElementType(
        new TemplateDataElementType("HB_TEMPLATE_DATA", templateDataLanguage, HbTokenTypes.CONTENT, HbTokenTypes.OUTER_ELEMENT_TYPE));
      return file;
    }
    else if (lang == HbLanguage.INSTANCE) {
      return parserDefinition.createFile(this);
    }
    else {
      return null;
    }
  }
}

