package com.dmarcotte.handlebars.parsing;

import consulo.testFramework.ParsingTestCase;

/**
 * ParsingTestCase test are created by placing a MyTestName.hbs file in the test/data/parsing directory with the syntax
 * you would like to validate, and a MyTestName.txt file representing the expected Psi structure.
 * <p/>
 * You then create a test of the following form to validate the parser (note how the test name corresponds
 * to the name of the .hbs and .txt files above):
 * <p/>
 * <pre>{@code
 * public void testMyTestName() { doTest(true); }
 * }</pre>
 * <p/>
 * <b>TIP:</b> if you create a .hbs file without a .txt file, the test will autogenerate .txt file from the current
 * parser. So, in practice when creating parser tests, you create the .hbs file, run the test, validate that
 * the .txt represents the desired Psi structure, then call it a day.
 */
public abstract class HbParserTest extends ParsingTestCase
{
	public HbParserTest()
	{
		super("parser", "hbs");
	}

	@Override
	protected String getTestDataPath()
	{
		return "/test/data";
	}

	@Override
	protected boolean checkAllPsiRoots()
	{
		return false;
	}

}
