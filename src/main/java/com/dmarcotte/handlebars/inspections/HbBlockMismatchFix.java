package com.dmarcotte.handlebars.inspections;

import com.dmarcotte.handlebars.psi.HbBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import com.dmarcotte.handlebars.psi.HbPath;
import consulo.annotation.access.RequiredWriteAction;
import consulo.codeEditor.Editor;
import consulo.document.Document;
import consulo.document.util.TextRange;
import consulo.handlebars.localize.HbLocalize;
import consulo.language.editor.FileModificationService;
import consulo.language.editor.intention.SyntheticIntentionAction;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiWhiteSpace;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.IncorrectOperationException;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import jakarta.annotation.Nonnull;

class HbBlockMismatchFix implements SyntheticIntentionAction {
    private final boolean myUpdateOpenMustache;
    private final String myCorrectedName;
    private final String myOriginalName;

    /**
     * @param correctedName      The name this action will update a block element to
     * @param originalName       The original name of the element this action corrects
     * @param updateOpenMustache Whether or not this updates the open mustache of this block
     */
    public HbBlockMismatchFix(String correctedName, String originalName, boolean updateOpenMustache) {
        myUpdateOpenMustache = updateOpenMustache;
        myCorrectedName = correctedName;
        myOriginalName = originalName;
    }

    @Nonnull
    @Override
    public LocalizeValue getText() {
        return myUpdateOpenMustache
            ? HbLocalize.hbBlockMismatchIntentionRenameOpen(myOriginalName, myCorrectedName)
            : HbLocalize.hbBlockMismatchIntentionRenameClose(myOriginalName, myCorrectedName);
    }

    @Override
    public boolean isAvailable(@Nonnull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    @RequiredWriteAction
    public void invoke(@Nonnull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        int offset = editor.getCaretModel().getOffset();
        PsiElement psiElement = file.findElementAt(offset);

        if (psiElement == null || !psiElement.isValid()) {
            return;
        }
        if (!FileModificationService.getInstance().prepareFileForWrite(psiElement.getContainingFile())) {
            return;
        }

        if (psiElement instanceof PsiWhiteSpace) {
            psiElement = PsiTreeUtil.prevLeaf(psiElement);
        }

        HbBlockMustache blockMustache =
            (HbBlockMustache) PsiTreeUtil.findFirstParent(psiElement, true, elem -> elem instanceof HbBlockMustache);

        if (blockMustache == null) {
            return;
        }

        HbBlockMustache targetBlockMustache = blockMustache;

        // ensure we update the open or close mustache for this block appropriately
        if (myUpdateOpenMustache != (targetBlockMustache instanceof HbOpenBlockMustache)) {
            targetBlockMustache = blockMustache.getPairedElement();
        }

        HbPath path = PsiTreeUtil.findChildOfType(targetBlockMustache, HbPath.class);
        Document document = PsiDocumentManager.getInstance(project).getDocument(file);
        if (path != null && document != null) {
            TextRange textRange = path.getTextRange();
            document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), myCorrectedName);
        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
