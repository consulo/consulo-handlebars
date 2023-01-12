package com.dmarcotte.handlebars.pages;

import com.dmarcotte.handlebars.HbBundle;
import com.dmarcotte.handlebars.HbHighlighter;
import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.TextAttributesKey;
import consulo.colorScheme.setting.AttributesDescriptor;
import consulo.colorScheme.setting.ColorDescriptor;
import consulo.language.editor.colorScheme.setting.ColorSettingsPage;
import consulo.language.editor.highlight.SyntaxHighlighter;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

@ExtensionImpl
public class HbColorsPage implements ColorSettingsPage {
  private static final AttributesDescriptor[] ATTRS;

  static {
    ATTRS = new AttributesDescriptor[HbHighlighter.DISPLAY_NAMES.size()];
    Set<TextAttributesKey> textAttributesKeys = HbHighlighter.DISPLAY_NAMES.keySet();
    TextAttributesKey[] keys = textAttributesKeys.toArray(new TextAttributesKey[textAttributesKeys.size()]);
    for (int i = 0; i < keys.length; i++) {
      TextAttributesKey key = keys[i];
      String name = HbHighlighter.DISPLAY_NAMES.get(key).getFirst();
      ATTRS[i] = new AttributesDescriptor(name, key);
    }
  }

  @Nonnull
  public String getDisplayName() {
    return HbBundle.message("hb.files.file.type.description");
  }

  @Nonnull
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @Nonnull
  public SyntaxHighlighter getHighlighter() {
    return new HbHighlighter();
  }

  @Nonnull
  public String getDemoText() {
    return "{{identifier my-val=true my-other-val=42 my-string-val=\"a string\"}}\n" +
      "{{! this is a comment }}\n" +
      "{{!--\n" +
      "    this is a Handlebars block comment,\n" +
      "    which can comment out mustache expressions: {{ignored}}\n" +
      "--}}\n" +
      "{{@data}}\n" +
      "\\{{escaped}}\n"
      ;
  }

  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }
}