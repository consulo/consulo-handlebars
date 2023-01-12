package com.dmarcotte.handlebars.psi;

import consulo.language.psi.PsiElement;

/**
 * Base for all Handlebars/Mustache elements
 */
public interface HbPsiElement extends PsiElement {
  String getName();
}
