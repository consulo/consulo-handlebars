/**
 * @author VISTALL
 * @since 12/01/2023
 */
module com.dmarcotte.handlebars {
    requires consulo.application.api;
    requires consulo.base.icon.library;
    requires consulo.code.editor.api;
    requires consulo.color.scheme.api;
    requires consulo.component.api;
    requires consulo.configurable.api;
    requires consulo.datacontext.api;
    requires consulo.disposer.api;
    requires consulo.document.api;
    requires consulo.file.editor.api;
    requires consulo.language.api;
    requires consulo.language.code.style.api;
    requires consulo.language.editor.api;
    requires consulo.language.impl;
    requires consulo.localize.api;
    requires consulo.navigation.api;
    requires consulo.project.api;
    requires consulo.ui.api;
    requires consulo.util.collection;
    requires consulo.util.lang;
    requires consulo.virtual.file.system.api;

    requires com.intellij.xml.api;
    requires com.intellij.xml.html.api;

    // TODO remove after xml extract all api
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
