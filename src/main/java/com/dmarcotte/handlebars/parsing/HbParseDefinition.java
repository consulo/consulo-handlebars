package com.dmarcotte.handlebars.parsing;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.psi.HbPsiFile;
import com.dmarcotte.handlebars.psi.impl.*;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;

import jakarta.annotation.Nonnull;

@ExtensionImpl
public class HbParseDefinition implements ParserDefinition {
  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }

  @Nonnull
  public Lexer createLexer(LanguageVersion languageVersion) {
    return new _HbLexer();
  }

  public PsiParser createParser(LanguageVersion languageVersion) {
    return new HbParser();
  }

  public IFileElementType getFileNodeType() {
    return HbTokenTypes.FILE;
  }

  @Nonnull
  public TokenSet getWhitespaceTokens(LanguageVersion languageVersion) {
    return HbTokenTypes.WHITESPACES;
  }

  @Nonnull
  public TokenSet getCommentTokens(LanguageVersion languageVersion) {
    return HbTokenTypes.COMMENTS;
  }

  @Nonnull
  public TokenSet getStringLiteralElements(LanguageVersion languageVersion) {
    return HbTokenTypes.STRING_LITERALS;
  }

  @Nonnull
  public PsiElement createElement(ASTNode node) {
    if (node.getElementType() == HbTokenTypes.BLOCK_WRAPPER) {
      return new HbBlockWrapperImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.OPEN_BLOCK_STACHE) {
      return new HbOpenBlockMustacheImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.OPEN_INVERSE_BLOCK_STACHE) {
      return new HbOpenInverseBlockMustacheImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.CLOSE_BLOCK_STACHE) {
      return new HbCloseBlockMustacheImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.MUSTACHE) {
      return new HbSimpleMustacheImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.PATH) {
      return new HbPathImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.DATA) {
      return new HbDataImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.PARAM) {
      return new HbParamImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.PARTIAL_STACHE) {
      return new HbPartialImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.PARTIAL_NAME) {
      return new HbPartialNameImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.SIMPLE_INVERSE) {
      return new HbSimpleInverseImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.STATEMENTS) {
      return new HbStatementsImpl(node);
    }

    if (node.getElementType() == HbTokenTypes.COMMENT) {
      return new HbCommentImpl(node);
    }

    return new HbPsiElementImpl(node);
  }

  public PsiFile createFile(FileViewProvider viewProvider) {
    return new HbPsiFile(viewProvider);
  }

  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
}
