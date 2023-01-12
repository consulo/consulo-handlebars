package com.dmarcotte.handlebars.config;

import consulo.xml.lang.html.HTMLLanguage;

import javax.annotation.Nonnull;

/**
 * Formalizes the properties which we will persist using {@link consulo.application.ApplicationPropertiesComponent}
 */
enum Property {
  AUTO_GENERATE_CLOSE_TAG {
    @Nonnull
    @Override
    public String getStringName() {
      // please excuse the "disabled" in this name.  This is an artifact from an earlier approach
      //      to properties, which we keep for backwards compatibility
      return "HbDisableAutoGenerateCloseTag";
    }

    @Nonnull
    @Override
    public String getDefault() {
      return ENABLED;
    }
  },

  FORMATTER {
    @Nonnull
    @Override
    public String getStringName() {
      return "HbFormatter";
    }

    @Nonnull
    @Override
    public String getDefault() {
      return ENABLED;
    }
  },

  AUTO_COLLAPSE_BLOCKS {
    @Nonnull
    @Override
    public String getStringName() {
      return "HbAutoCollapseBlocks";
    }

    @Nonnull
    @Override
    public String getDefault() {
      return DISABLED;
    }
  },

  COMMENTER_LANGUAGE_ID {
    @Nonnull
    @Override
    public String getStringName() {
      return "HbCommenterLanguageId";
    }

    @Nonnull
    @Override
    public String getDefault() {
      return HTMLLanguage.INSTANCE.getID();
    }
  };

  public static final String ENABLED = "enabled";
  public static final String DISABLED = "disabled";

  /**
   * The String which will actually be persisted in a user's properties using {@link consulo.application.ApplicationPropertiesComponent}.
   * <p/>
   * This value must be unique amongst Property entries
   * <p/>
   * IMPORTANT: these should probably never change so that we don't lose a user's preferences between releases.
   */
  @Nonnull
  public abstract String getStringName();

  /**
   * The default/initial value for a user
   */
  @Nonnull
  public abstract String getDefault();
}
