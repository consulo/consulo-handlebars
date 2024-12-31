package com.dmarcotte.handlebars.editor.actions;

import com.dmarcotte.handlebars.psi.HbPsiFile;
import com.dmarcotte.handlebars.psi.HbPsiUtil;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.codeEditor.EditorEx;
import consulo.codeEditor.EditorHighlighter;
import consulo.codeEditor.HighlighterIterator;
import consulo.codeEditor.action.EditorActionHandler;
import consulo.dataContext.DataContext;
import consulo.language.editor.action.EnterHandlerDelegateAdapter;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.util.lang.ref.Ref;

import jakarta.annotation.Nonnull;

/**
 * Handler for custom plugin actions when {@code Enter} is typed by the user
 */
@ExtensionImpl
public class HbEnterHandler extends EnterHandlerDelegateAdapter {

  public Result preprocessEnter(@Nonnull final PsiFile file,
                                @Nonnull final Editor editor,
                                @Nonnull final Ref<Integer> caretOffset,
                                @Nonnull final Ref<Integer> caretAdvance,
                                @Nonnull final DataContext dataContext,
                                final EditorActionHandler originalHandler) {
    /**
     * if we are between open and close tags, we ensure the caret ends up in the "logical" place on Enter.
     * i.e. "{{#foo}}<caret>{{/foo}}" becomes the following on Enter:
     *
     * {{#foo}}
     * <caret>
     * {{/foo}}
     *
     * (Note: <caret> may be indented depending on formatter settings.)
     */
    if (file instanceof HbPsiFile
      && isBetweenHbTags(editor, file, caretOffset.get())) {
      originalHandler.execute(editor, dataContext);
      return Result.Default;
    }
    return Result.Continue;
  }

  /**
   * Checks to see if {@code Enter} has been typed while the caret is between an open and close tag pair.
   *
   * @return true if between open and close tags, false otherwise
   */
  private static boolean isBetweenHbTags(Editor editor, PsiFile file, int offset) {
    if (offset == 0) return false;
    CharSequence chars = editor.getDocument().getCharsSequence();
    if (chars.charAt(offset - 1) != '}') return false;

    EditorHighlighter highlighter = ((EditorEx)editor).getHighlighter();
    HighlighterIterator iterator = highlighter.createIterator(offset - 1);

    final PsiElement openerElement = file.findElementAt(iterator.getStart());

    PsiElement openTag = HbPsiUtil.findParentOpenTagElement(openerElement);

    if (openTag == null) {
      return false;
    }

    iterator.advance();

    if (iterator.atEnd()) {
      // no more tokens, so certainly no close tag
      return false;
    }

    final PsiElement closerElement = file.findElementAt(iterator.getStart());

    PsiElement closeTag = HbPsiUtil.findParentCloseTagElement(closerElement);

    // if we got this far, we're between open and close tags iff this is a close tag
    return closeTag != null;
  }
}
