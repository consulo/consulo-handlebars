package com.dmarcotte.handlebars.completion;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.parsing.HbTokenTypes;
import com.dmarcotte.handlebars.psi.HbPath;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.editor.completion.*;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.ProcessingContext;

import jakarta.annotation.Nonnull;

import static consulo.language.pattern.PlatformPatterns.psiElement;

@ExtensionImpl
public class HbKeywordCompletionContributor extends CompletionContributor {
  public HbKeywordCompletionContributor() {
    extend(CompletionType.BASIC, psiElement(HbTokenTypes.ID).withSuperParent(2, psiElement(HbTokenTypes.PATH)), new CompletionProvider() {
      @Override
      public void addCompletions(@Nonnull CompletionParameters parameters, ProcessingContext context, @Nonnull CompletionResultSet result) {
        PsiElement position = PsiTreeUtil.getParentOfType(parameters.getPosition(), HbPath.class);
        PsiElement prevSibling = position != null ? position.getPrevSibling() : null;
        ASTNode prevSiblingNode = prevSibling != null ? prevSibling.getNode() : null;
        if (prevSiblingNode != null && prevSiblingNode.getElementType() == HbTokenTypes.OPEN_BLOCK) {
          result.addElement(LookupElementBuilder.create("if"));
          result.addElement(LookupElementBuilder.create("each"));
        }
      }
    });
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}
