/**
 * @author VISTALL
 * @since 12/01/2023
 */
module com.dmarcotte.handlebars {
  requires consulo.ide.api;
  requires com.intellij.xml;

  exports com.dmarcotte.handlebars;
  exports com.dmarcotte.handlebars.completion;
  exports com.dmarcotte.handlebars.config;
  exports com.dmarcotte.handlebars.editor.actions;
  exports com.dmarcotte.handlebars.editor.braces;
  exports com.dmarcotte.handlebars.editor.comments;
  exports com.dmarcotte.handlebars.editor.folding;
  exports com.dmarcotte.handlebars.exception;
  exports com.dmarcotte.handlebars.file;
  exports com.dmarcotte.handlebars.format;
  exports com.dmarcotte.handlebars.inspections;
  exports com.dmarcotte.handlebars.pages;
  exports com.dmarcotte.handlebars.parsing;
  exports com.dmarcotte.handlebars.psi;
  exports com.dmarcotte.handlebars.psi.impl;
  exports com.dmarcotte.handlebars.structure;
  exports consulo.handlebars;
  exports consulo.handlebars.icon;
  exports consulo.handlebars.localize;
}