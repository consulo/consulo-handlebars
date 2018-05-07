package com.dmarcotte.handlebars.psi.impl;

import javax.annotation.Nonnull;

import com.dmarcotte.handlebars.psi.HbBlockWrapper;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;

public class HbBlockWrapperImpl extends HbPsiElementImpl implements HbBlockWrapper {
	public HbBlockWrapperImpl(@Nonnull ASTNode astNode) {
		super(astNode);
	}

	@Override
	public String getName() {
		HbOpenBlockMustache openBlockMustache = getInsideElement();
		return openBlockMustache == null ? null : openBlockMustache.getName();
	}

	@Override
	public HbOpenBlockMustache getInsideElement() {
		return PsiTreeUtil.findChildOfType(this, HbOpenBlockMustache.class);
	}
}
