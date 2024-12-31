package com.dmarcotte.handlebars.psi;

import jakarta.annotation.Nullable;

public interface HbBlockWrapper extends HbPsiElement {
  @Nullable
  HbOpenBlockMustache getInsideElement();
}
