package com.dmarcotte.handlebars.pages;

import com.dmarcotte.handlebars.HbLanguage;
import com.dmarcotte.handlebars.config.HbConfig;
import consulo.annotation.component.ExtensionImpl;
import consulo.configurable.ApplicationConfigurable;
import consulo.configurable.SearchableConfigurable;
import consulo.configurable.SimpleConfigurableByProperties;
import consulo.disposer.Disposable;
import consulo.handlebars.localize.HbLocalize;
import consulo.language.Language;
import consulo.language.file.LanguageFileType;
import consulo.language.template.TemplateDataLanguageMappings;
import consulo.localize.LocalizeValue;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.ui.CheckBox;
import consulo.ui.ComboBox;
import consulo.ui.Component;
import consulo.ui.TextAttribute;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.layout.VerticalLayout;
import consulo.ui.util.LabeledBuilder;
import consulo.util.lang.StringUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

@ExtensionImpl
public class HbConfigurationPage extends SimpleConfigurableByProperties implements SearchableConfigurable, ApplicationConfigurable {
    @Nonnull
    @Override
    public String getId() {
        return "editor.hb";
    }

    @Nullable
    @Override
    public String getParentId() {
        return "editor";
    }

    @Nonnull
    @Override
    public LocalizeValue getDisplayName() {
        return HbLocalize.hbPagesOptionsTitle();
    }

    @Nonnull
    @Override
    @RequiredUIAccess
    protected Component createLayout(@Nonnull PropertyBuilder propertyBuilder, @Nonnull Disposable disposable) {
        VerticalLayout root = VerticalLayout.create();

        CheckBox autoInsertCloseTagBox = CheckBox.create(HbLocalize.hbPagesOptionsGenerateClosingTag());
        root.add(autoInsertCloseTagBox);
        propertyBuilder.add(autoInsertCloseTagBox, HbConfig::isAutoGenerateCloseTagEnabled, HbConfig::setAutoGenerateCloseTagEnabled);

        CheckBox enableFormatterBox = CheckBox.create(HbLocalize.hbPagesOptionsFormatter());
        root.add(enableFormatterBox);
        propertyBuilder.add(enableFormatterBox, HbConfig::isFormattingEnabled, HbConfig::setFormattingEnabled);

        List<Language> languages = TemplateDataLanguageMappings.getTemplateableLanguages();
        // add using the native Handlebars commenter as an option
        languages.add(HbLanguage.INSTANCE);

        List<String> languageStrings = new ArrayList<>(languages.size());

        for (Language language : languages) {
            languageStrings.add(language.getID());
        }

        languageStrings.sort(String::compareTo);

        String commenterLanguageID = HbConfig.getCommenterLanguageID();
        if (!StringUtil.isEmptyOrSpaces(commenterLanguageID)) {
            languageStrings.add(0, commenterLanguageID);
        }

        ComboBox<String> commentLanguageBox = ComboBox.create(languageStrings);
        commentLanguageBox.setRenderer((presentation, index, langId) -> {
            System.out.println(langId);
            if (langId == null) {
                presentation.append("");
            }
            else {
                Language languageByID = Language.findLanguageByID(langId);
                if (languageByID != null) {
                    presentation.append(languageByID.getDisplayName());

                    LanguageFileType type = languageByID.getAssociatedFileType();
                    if (type != null) {
                        presentation.withIcon(type.getIcon());
                    }
                }
                else {
                    presentation.withIcon(PlatformIconGroup.actionsHelp());
                    presentation.append(langId, TextAttribute.ERROR);
                }
            }
        });
        root.add(LabeledBuilder.sided(HbLocalize.hbPageOptionsCommenterLanguage(), commentLanguageBox));
        propertyBuilder.add(commentLanguageBox, HbConfig::getCommenterLanguageID, HbConfig::setCommenterLanguageID);

        return root;
    }
}
