package com.dmarcotte.handlebars.editor.actions;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.config.HbConfig;
import com.dmarcotte.handlebars.file.HbFileViewProvider;
import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.dmarcotte.handlebars.psi.*;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.CaretModel;
import consulo.codeEditor.Editor;
import consulo.document.util.TextRange;
import consulo.language.ast.ASTNode;
import consulo.language.codeStyle.CodeStyleManager;
import consulo.language.editor.action.TypedHandlerDelegate;
import consulo.language.file.FileViewProvider;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.project.Project;
import consulo.util.lang.function.Condition;

import jakarta.annotation.Nonnull;

/**
 * Handler for custom plugin actions on chars typed by the user.  See {@link HbEnterHandler} for custom actions
 * on Enter.
 */
@ExtensionImpl
public class HbTypedHandler extends TypedHandlerDelegate {

  @Override
  public Result charTyped(char c, Project project, Editor editor, @Nonnull PsiFile file) {
    int offset = editor.getCaretModel().getOffset();
    FileViewProvider provider = file.getViewProvider();

    if (offset < 2 || offset > editor.getDocument().getTextLength()) {
      return Result.CONTINUE;
    }

    String previousChar = editor.getDocument().getText(new TextRange(offset - 2, offset - 1));

    if (provider instanceof HbFileViewProvider) {
      if (c == '}' && !previousChar.equals("}")) {
        // seems like we can complete the second brace
        PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
        PsiElement elementAt = provider.findElementAt(offset - 1, HbLanguage.class);
        ASTNode node = elementAt != null ? elementAt.getNode() : null;
        if (node != null && node.getElementType() == HbTokenTypes.INVALID) {
          // yes we can!
          previousChar = "}";
          editor.getDocument().insertString(offset, previousChar);
          editor.getCaretModel().moveToOffset(++offset);
        }
      }
      // if we're looking at a close stache, we may have some business too attend to
      if (c == '}' && previousChar.equals("}")) {
        autoInsertCloseTag(project, offset, editor, provider);
        adjustMustacheFormatting(project, offset, editor, file, provider);
      }
    }

    return Result.CONTINUE;
  }

  /**
   * When appropriate, auto-inserts Handlebars close tags.  i.e.  When "{{#tagId}}" or "{{^tagId}} is typed,
   * {{/tagId}} is automatically inserted
   */
  private static void autoInsertCloseTag(Project project, int offset, Editor editor, FileViewProvider provider) {
    if (!HbConfig.isAutoGenerateCloseTagEnabled()) {
      return;
    }

    PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());

    PsiElement elementAtCaret = provider.findElementAt(offset - 1, HbLanguage.class);

    PsiElement openTag = HbPsiUtil.findParentOpenTagElement(elementAtCaret);

    if (openTag != null && openTag.getChildren().length > 1) {
      // we've got an open block type stache... find its "name" (its first path element)
      HbPsiElement pathElem = (HbPsiElement)openTag.getChildren()[1];

      if (pathElem != null
        && pathElem instanceof HbPath) {
        // insert the corresponding close tag
        editor.getDocument().insertString(offset, "{{/" + pathElem.getText() + "}}");
      }
    }
  }

  /**
   * When appropriate, adjusts the formatting for some 'staches, particularily close 'staches
   * and simple inverses ("{{^}}" and "{{else}}")
   */
  private static void adjustMustacheFormatting(Project project, int offset, Editor editor, PsiFile file, FileViewProvider provider) {
    if (!HbConfig.isFormattingEnabled()) {
      // formatting disabled; nothing to do
      return;
    }

    PsiElement elementAtCaret = provider.findElementAt(offset - 1, HbLanguage.class);
    PsiElement closeOrSimpleInverseParent = PsiTreeUtil.findFirstParent(elementAtCaret, true, new Condition<PsiElement>() {
      @Override
      public boolean value(PsiElement element) {
        return element != null
          && (element instanceof HbSimpleInverse
          || element instanceof HbCloseBlockMustache);
      }
    });

    // run the formatter if the user just completed typing a SIMPLE_INVERSE or a CLOSE_BLOCK_STACHE
    if (closeOrSimpleInverseParent != null) {
      // grab the current caret position (AutoIndentLinesHandler is about to mess with it)
      PsiDocumentManager.getInstance(project).commitAllDocuments();
      CaretModel caretModel = editor.getCaretModel();
      CodeStyleManager codeStyleManager = CodeStyleManager.getInstance(project);
      codeStyleManager.adjustLineIndent(file, editor.getDocument().getLineStartOffset(caretModel.getLogicalPosition().line));
    }
  }
}
