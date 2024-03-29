package com.dmarcotte.handlebars.parsing;

import java.util.ArrayList;
import java.util.List;

import consulo.language.ast.IElementType;
import com.intellij.testFramework.UsefulTestCase;
import consulo.language.lexer.Lexer;
import junit.framework.Assert;

public abstract class HbLexerTest extends UsefulTestCase
{

  private Lexer _lexer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    _lexer = new _HbLexer();
  }

  TokenizerResult tokenize(String string) {
    List<Token> tokens = new ArrayList<Token>();
    IElementType currentElement;

    _lexer.start(string);

    while ((currentElement = _lexer.getTokenType()) != null) {
      tokens.add(new Token(currentElement, _lexer.getTokenText()));
      _lexer.advance();
    }

    return new TokenizerResult(tokens);
  }

  static class Token {
    private final IElementType _elementType;
    private final String _elementContent;

    private Token(IElementType elementType, String elementContent) {
      _elementType = elementType;
      _elementContent = elementContent;
    }

    public IElementType getElementType() {
      return _elementType;
    }

    public String getElementContent() {
      return _elementContent;
    }
  }

  static class TokenizerResult {
    private final List<Token> _tokens;

    public TokenizerResult(List<Token> tokens) {
      _tokens = tokens;
    }

    /**
     * @param tokenTypes The token types expected for the tokens in this TokenizerResult, in the order they are expected
     */
    public void shouldMatchTokenTypes(IElementType... tokenTypes) {

      // compare tokens as far as we can (which is ideally all of them).  We'll check that
      // these have the same length next - doing the content compare first yields more illuminating failures
      // in the case of a mis-match
      for (int i = 0; i < Math.min(_tokens.size(), tokenTypes.length); i++) {
        Assert.assertEquals(tokenTypes[i], _tokens.get(i).getElementType());
      }

      Assert.assertEquals(tokenTypes.length, _tokens.size());
    }

    /**
     * @param tokenContent The content string expected for the tokens in this TokenizerResult, in the order they are expected
     */
    public void shouldMatchTokenContent(String... tokenContent) {

      // compare tokens as far as we can (which is ideally all of them).  We'll check that
      // these have the same length next - doing the content compare first yields more illuminating failures
      // in the case of a mis-match
      for (int i = 0; i < Math.min(_tokens.size(), tokenContent.length); i++) {
        Assert.assertEquals(tokenContent[i], _tokens.get(i).getElementContent());
      }

      Assert.assertEquals(tokenContent.length, _tokens.size());
    }

    /**
     * Convenience method for validating a specific token in this TokenizerResult
     */
    public void shouldBeToken(int tokenPosition, IElementType tokenType, String tokenContent) {
      Token token = _tokens.get(tokenPosition);

      Assert.assertEquals(tokenType, token.getElementType());
      Assert.assertEquals(tokenContent, token.getElementContent());
    }
  }
}
