package com.dmarcotte.handlebars.file;

import com.dmarcotte.handlebars.HbTemplateHighlighter;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.EditorHighlighter;
import consulo.colorScheme.EditorColorsScheme;
import consulo.language.editor.highlight.EditorHighlighterProvider;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 2019-02-27
 */
@ExtensionImpl
public class HbEditorHighlighterProvider implements EditorHighlighterProvider {
  @Override
  public EditorHighlighter getEditorHighlighter(@Nullable Project project,
                                                @Nonnull FileType fileType,
                                                @Nullable VirtualFile virtualFile,
                                                @Nonnull EditorColorsScheme colors) {
    return new HbTemplateHighlighter(project, virtualFile, colors);
  }

  @Nonnull
  @Override
  public FileType getFileType() {
    return HbFileType.INSTANCE;
  }
}
