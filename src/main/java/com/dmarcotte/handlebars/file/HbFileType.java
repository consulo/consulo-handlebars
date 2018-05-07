package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbBundle;
import com.dmarcotte.handlebars.HbIcons;
import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.HbTemplateHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import javax.swing.*;
import java.nio.charset.Charset;

public class HbFileType extends LanguageFileType implements TemplateLanguageFileType {
  public static final LanguageFileType INSTANCE = new HbFileType();

  @NonNls
  public static final String DEFAULT_EXTENSION = "handlebars;hbs;mustache";

  private HbFileType() {
    super(HbLanguage.INSTANCE);

    FileTypeEditorHighlighterProviders.INSTANCE.addExplicitExtension(this, new EditorHighlighterProvider() {
      public EditorHighlighter getEditorHighlighter(@Nullable Project project,
                                                    @Nonnull FileType fileType,
                                                    @Nullable VirtualFile virtualFile,
                                                    @Nonnull EditorColorsScheme editorColorsScheme) {
        return new HbTemplateHighlighter(project, virtualFile, editorColorsScheme);
      }
    });
  }

  @Nonnull
  public String getName() {
    return "Handlebars/Mustache";
  }

  @Nonnull
  public String getDescription() {
    return HbBundle.message("hb.files.file.type.description");
  }

  @Nonnull
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  public Icon getIcon() {
    return HbIcons.FILE_ICON;
  }

  public Charset extractCharsetFromFileContent(@Nullable final Project project,
                                               @Nullable final VirtualFile file,
                                               @Nonnull final String content) {
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
