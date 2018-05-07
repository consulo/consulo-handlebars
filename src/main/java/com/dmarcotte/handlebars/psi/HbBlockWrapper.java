package com.dmarcotte.handlebars.psi;

import javax.annotation.Nullable;

public interface HbBlockWrapper extends HbPsiElement {
	@Nullable
	HbOpenBlockMustache getInsideElement();
}
