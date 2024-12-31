package com.dmarcotte.handlebars;

import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import consulo.colorScheme.EditorColorsScheme;
import consulo.language.Language;
import consulo.language.editor.highlight.LayerDescriptor;
import consulo.language.editor.highlight.LayeredLexerEditorHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.language.plain.PlainTextFileType;
import consulo.language.template.TemplateDataLanguageMappings;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class HbTemplateHighlighter extends LayeredLexerEditorHighlighter {
  public HbTemplateHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @Nonnull EditorColorsScheme colors) {
    // create main highlighter
    super(new HbHighlighter(), colors);

    // highlighter for outer lang
    FileType type = null;
    if (project == null || virtualFile == null) {
      type = PlainTextFileType.INSTANCE;
    }
    else {
      Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile);
      if (language != null) type = language.getAssociatedFileType();
      if (type == null) type = HbLanguage.getDefaultTemplateFileType();
    }
    SyntaxHighlighter outerHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(type, project, virtualFile);
    registerLayer(HbTokenTypes.CONTENT, new LayerDescriptor(outerHighlighter, ""));
  }
}

