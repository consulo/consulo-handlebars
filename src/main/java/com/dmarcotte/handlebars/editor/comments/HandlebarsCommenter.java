package com.dmarcotte.handlebars.editor.comments;

import com.dmarcotte.handlebars.HbLanguage;
import consulo.language.Commenter;
import consulo.language.Language;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Commenter for native Handlebars comments: <pre>{{!-- comment --}}</pre>
 */
class HandlebarsCommenter implements Commenter {
  @Nullable
  @Override
  public String getLineCommentPrefix() {
    return null;
  }

  @Nullable
  @Override
  public String getBlockCommentPrefix() {
    return "{{!--";
  }

  @Nullable
  @Override
  public String getBlockCommentSuffix() {
    return "--}}";
  }

  @Nullable
  @Override
  public String getCommentedBlockCommentPrefix() {
    return null;
  }

  @Nullable
  @Override
  public String getCommentedBlockCommentSuffix() {
    return null;
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return HbLanguage.INSTANCE;
  }
}
