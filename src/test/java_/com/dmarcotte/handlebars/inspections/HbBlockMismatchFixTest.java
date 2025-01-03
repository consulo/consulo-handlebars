package com.dmarcotte.handlebars.inspections;

import com.dmarcotte.handlebars.util.HbTestUtils;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import consulo.language.editor.WriteCommandAction;
import consulo.language.editor.intention.IntentionAction;

import jakarta.annotation.Nonnull;

public abstract class HbBlockMismatchFixTest extends LightPlatformCodeInsightFixtureTestCase {
  public HbBlockMismatchFixTest() {
  }

  @Override
  protected boolean isWriteActionRequired() {
    return false;
  }

  public void testWrongCloseBlock1() {
    doTest("Change block end");
  }

  public void testWrongCloseBlock2() {
    doTest("Change block end");
  }

  public void testWrongOpenBlock1() {
    doTest("Change block start");
  }

  public void testWrongOpenBlock2() {
    doTest("Change block start");
  }

  private void doTest(String intentionHint) {
    myFixture.configureByFile("inspections/before" + getTestName(false) + ".hbs");
    final IntentionAction intention = myFixture.findSingleIntention(intentionHint);
    new WriteCommandAction.Simple(myFixture.getProject()) {
      @Override
      protected void run() throws Throwable {
        myFixture.launchAction(intention);
      }
    }.execute();
    myFixture.checkResultByFile("inspections/after" + getTestName(false) + ".hbs");
  }


  @Override
  protected String getBasePath() {
    return "/inspections";
  }

  @Nonnull
  @Override
  protected String getTestDataPath() {
    return HbTestUtils.BASE_TEST_DATA_PATH;
  }
}
