package com.dmarcotte.handlebars.psi;

import org.jetbrains.annotations.Nullable;

public interface HbBlockWrapper extends HbPsiElement {
	@Nullable
	HbOpenBlockMustache getInsideElement();
}
