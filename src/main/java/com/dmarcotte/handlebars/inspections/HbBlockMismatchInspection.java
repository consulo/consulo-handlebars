package com.dmarcotte.handlebars.inspections;

import com.dmarcotte.handlebars.psi.HbCloseBlockMustache;
import com.dmarcotte.handlebars.psi.HbOpenBlockMustache;
import com.dmarcotte.handlebars.psi.HbPath;
import consulo.handlebars.localize.HbLocalize;
import consulo.language.editor.annotation.Annotation;
import consulo.language.editor.annotation.AnnotationHolder;
import consulo.language.editor.annotation.Annotator;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

public class HbBlockMismatchInspection implements Annotator {
  @Override
  public void annotate(@Nonnull PsiElement element, @Nonnull AnnotationHolder holder) {
    if (element instanceof HbOpenBlockMustache) {
      HbOpenBlockMustache openBlockMustache = (HbOpenBlockMustache)element;
      HbPath openBlockMainPath = openBlockMustache.getBlockMainPath();

      HbCloseBlockMustache closeBlockMustache = openBlockMustache.getPairedElement();
      if (closeBlockMustache != null) {
        HbPath closeBlockMainPath = closeBlockMustache.getBlockMainPath();

        if (openBlockMainPath == null || closeBlockMainPath == null) {
          return;
        }

        String openBlockName = openBlockMainPath.getName();
        String closeBlockName = closeBlockMainPath.getName();
        if (!openBlockName.equals(closeBlockName)) {
          Annotation openBlockAnnotation = holder.createErrorAnnotation(
            openBlockMainPath,
            HbLocalize.hbBlockMismatchInspectionOpenBlock(openBlockName, closeBlockName).get()
          );
          openBlockAnnotation.registerFix(new HbBlockMismatchFix(closeBlockName, openBlockName, true));
          openBlockAnnotation.registerFix(new HbBlockMismatchFix(openBlockName, closeBlockName, false));

          Annotation closeBlockAnnotation = holder.createErrorAnnotation(
            closeBlockMainPath,
            HbLocalize.hbBlockMismatchInspectionCloseBlock(openBlockName, closeBlockName).get()
          );
          closeBlockAnnotation.registerFix(new HbBlockMismatchFix(openBlockName, closeBlockName, false));
          closeBlockAnnotation.registerFix(new HbBlockMismatchFix(closeBlockName, openBlockName, true));
        }
      }
      else {
        holder.createErrorAnnotation(
          openBlockMainPath,
          HbLocalize.hbBlockMismatchInspectionMissingEndBlock(openBlockMustache.getName()).get()
        );
      }
    }

    if (element instanceof HbCloseBlockMustache) {
      HbCloseBlockMustache closeBlockMustache = (HbCloseBlockMustache)element;
      PsiElement openBlockElement = closeBlockMustache.getPairedElement();
      if (openBlockElement == null) {
        HbPath closeBlockMainPath = closeBlockMustache.getBlockMainPath();
        if (closeBlockMainPath == null) {
          return;
        }
        holder.createErrorAnnotation(
          closeBlockMainPath,
          HbLocalize.hbBlockMismatchInspectionMissingStartBlock(closeBlockMustache.getName()).get()
        );
      }
    }
  }
}
