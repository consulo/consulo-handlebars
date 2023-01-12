package com.dmarcotte.handlebars.editor.actions;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.file.HbFileType;
import consulo.ide.impl.idea.codeInsight.generation.CommentByBlockCommentHandler;
import consulo.ide.impl.idea.codeInsight.generation.CommentByLineCommentHandler;
import consulo.project.Project;
import consulo.ui.ex.action.IdeActions;
import consulo.application.ApplicationManager;
import consulo.undoRedo.CommandProcessor;
import consulo.codeEditor.action.EditorActionHandler;
import consulo.codeEditor.action.EditorActionManager;
import consulo.codeEditor.action.TypedAction;
import consulo.codeEditor.EditorEx;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

/**
 * Base test for plugin action handlers<br/>
 * <br/>
 * In general, all the tests here work on the same principles: run an action on the given "before" text and compare
 * the result to the given "expected" text.<br/>
 * <br/>
 * Both "before" and "expected" text must include substring "&lt;caret&gt;" to indicate the caret position in the text.<br/>
 * <br/>
 * Optionally, a chunk of the given text may be marked with "&lt;selection&gt;SELECTED TEXT&lt;/selection&gt;"
 * to indicate a selection.
 */
public abstract class HbActionHandlerTest extends LightPlatformCodeInsightFixtureTestCase {

  protected HbActionHandlerTest() {
  }

  private void performWriteAction(final Project project, final Runnable action) {
    ApplicationManager.getApplication().runWriteAction(new Runnable() {
      @Override
      public void run() {
        CommandProcessor.getInstance().executeCommand(project, action, "test command", null);
      }
    });
  }

  private void validateTestStrings(@Nonnull String before, @Nonnull String expected) {
    if (!before.contains("<caret>")
        || !expected.contains("<caret>")) {
      throw new IllegalArgumentException("Test strings must contain \"<caret>\" to indicate caret position");
    }
  }

  /**
   * Call this method to test behavior when the given charToType is typed at the &lt;caret&gt;.
   * See class documentation for more info: {@link HbActionHandlerTest}
   */
  void doCharTest(final char charToType, @Nonnull String before, @Nonnull String expected) {
    final TypedAction typedAction = EditorActionManager.getInstance().getTypedAction();
    doExecuteActionTest(before, expected, new Runnable() {
      @Override
      public void run() {
        typedAction.actionPerformed(myFixture.getEditor(), charToType, ((EditorEx)myFixture.getEditor()).getDataContext());
      }
    });
  }

  /**
   * Call this method to test behavior when Enter is typed.
   * See class documentation for more info: {@link HbActionHandlerTest}
   */
  protected void doEnterTest(@Nonnull String before, @Nonnull String expected) {
    final EditorActionHandler enterActionHandler = EditorActionManager.getInstance().getActionHandler(IdeActions.ACTION_EDITOR_ENTER);
    doExecuteActionTest(before, expected, new Runnable() {
      @Override
      public void run() {
        enterActionHandler.execute(myFixture.getEditor(), ((EditorEx)myFixture.getEditor()).getDataContext());
      }
    });
  }

  /**
   * Call this method to test behavior when the "Comment with Line Comment" action is executed.
   * See class documentation for more info: {@link HbActionHandlerTest}
   */
  void doLineCommentTest(@Nonnull String before, @Nonnull String expected) {
    doExecuteActionTest(before, expected, new Runnable() {
      @Override
      public void run() {
        new consulo.ide.impl.idea.codeInsight.generation.CommentByLineCommentHandler().invoke(myFixture.getProject(), myFixture.getEditor(), myFixture.getEditor().getCaretModel().getCurrentCaret(), myFixture.getFile());
      }
    });
  }

  /**
   * Call this method to test behavior when the "Comment with Block Comment" action is executed.
   * See class documentation for more info: {@link HbActionHandlerTest}
   */
  void doBlockCommentTest(@Nonnull String before, @Nonnull String expected) {
    doExecuteActionTest(before, expected, new Runnable() {
      @Override
      public void run() {
        new consulo.ide.impl.idea.codeInsight.generation.CommentByBlockCommentHandler().invoke(myFixture.getProject(), myFixture.getEditor(), myFixture.getEditor().getCaretModel().getCurrentCaret(), myFixture.getFile());
      }
    });
  }

  private void doExecuteActionTest(@Nonnull String before, @Nonnull String expected, @Nonnull Runnable action) {
    validateTestStrings(before, expected);

    myFixture.configureByText(HbFileType.INSTANCE, before);
    performWriteAction(myFixture.getProject(), action);
    myFixture.checkResult(expected);
  }
}
