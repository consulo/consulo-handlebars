package com.dmarcotte.handlebars;

import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

