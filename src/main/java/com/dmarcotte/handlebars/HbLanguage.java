package com.dmarcotte.handlebars;

import consulo.language.Language;
import consulo.language.file.LanguageFileType;
import consulo.language.template.TemplateLanguage;
import consulo.xml.ide.highlighter.HtmlFileType;

public class HbLanguage extends Language implements TemplateLanguage {
  public static final HbLanguage INSTANCE = new HbLanguage();

  @SuppressWarnings("SameReturnValue") // ideally this would be public static, but the static inits in the tests get cranky when we do that
  public static LanguageFileType getDefaultTemplateFileType() {
    return HtmlFileType.INSTANCE;
  }

  public HbLanguage() {
    super("Handlebars", "text/x-handlebars-template", "text/x-handlebars");
  }
}
