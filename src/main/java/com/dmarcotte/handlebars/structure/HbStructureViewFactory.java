package com.dmarcotte.handlebars.structure;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.dmarcotte.handlebars.psi.HbPsiFile;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

public class HbStructureViewFactory implements PsiStructureViewFactory
{
	@Nullable
	@Override
	public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile)
	{
		return new TreeBasedStructureViewBuilder()
		{
			@Nonnull
			@Override
			public StructureViewModel createStructureViewModel(Editor editor)
			{
				return new HbStructureViewModel((HbPsiFile) psiFile);
			}
		};
	}
}
