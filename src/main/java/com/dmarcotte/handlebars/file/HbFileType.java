package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbIcons;
import com.dmarcotte.handlebars.HbLanguage;
import consulo.handlebars.localize.HbLocalize;
import consulo.language.Language;
import consulo.language.file.LanguageFileType;
import consulo.language.template.TemplateDataLanguageMappings;
import consulo.language.template.TemplateLanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import consulo.ui.image.Image;
import consulo.virtualFileSystem.VirtualFile;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.nio.charset.Charset;

public class HbFileType extends LanguageFileType implements TemplateLanguageFileType {
  public static final LanguageFileType INSTANCE = new HbFileType();

  @NonNls
  public static final String DEFAULT_EXTENSION = "handlebars;hbs;mustache";

  private HbFileType() {
    super(HbLanguage.INSTANCE);
  }

  @Nonnull
  public String getId() {
    return "Handlebars/Mustache";
  }

  @Nonnull
  public LocalizeValue getDescription() {
    return HbLocalize.hbFilesFileTypeDescription();
  }

  @Nonnull
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  public Image getIcon() {
    return HbIcons.FILE_ICON;
  }

  @Override
  public Charset extractCharsetFromFileContent(@Nullable final Project project,
                                               @Nullable final VirtualFile file,
                                               @Nonnull final CharSequence content) {
    LanguageFileType associatedFileType = getAssociatedFileType(file, project);

    if (associatedFileType == null) {
      return null;
    }

    return associatedFileType.extractCharsetFromFileContent(project, file, content);
  }

  private static LanguageFileType getAssociatedFileType(VirtualFile file, Project project) {
    if (project == null) {
      return null;
    }
    Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(file);

    LanguageFileType associatedFileType = null;
    if (language != null) {
      associatedFileType = language.getAssociatedFileType();
    }

    if (language == null || associatedFileType == null) {
      associatedFileType = HbLanguage.getDefaultTemplateFileType();
    }
    return associatedFileType;
  }
}
