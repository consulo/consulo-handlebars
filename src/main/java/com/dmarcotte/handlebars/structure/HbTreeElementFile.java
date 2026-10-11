package com.dmarcotte.handlebars.structure;

import com.dmarcotte.handlebars.psi.HbPsiFile;
import consulo.annotation.access.RequiredReadAction;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.language.editor.structureView.PsiTreeElementBase;
import consulo.localize.LocalizeValue;
import jakarta.annotation.Nonnull;

import java.util.Collection;

class HbTreeElementFile extends PsiTreeElementBase<HbPsiFile> {
    private final HbPsiFile myFile;

    public HbTreeElementFile(@Nonnull HbPsiFile psiFile) {
        super(psiFile);
        this.myFile = psiFile;
    }

    @Nonnull
    @Override
    @RequiredReadAction
    public Collection<StructureViewTreeElement> getChildrenBase() {
        return HbTreeElement.getStructureViewTreeElements(myFile);
    }

    @Override
    @RequiredReadAction
    public LocalizeValue getPresentableText() {
        return LocalizeValue.ofNullable(myFile.getName());
    }
}
