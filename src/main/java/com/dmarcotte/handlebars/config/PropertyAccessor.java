package com.dmarcotte.handlebars.config;

import consulo.component.PropertiesComponent;

/**
 * Class responsible for reads and writes of properties
 */
class PropertyAccessor {

  private final PropertiesComponent myPropertiesComponent;

  PropertyAccessor(PropertiesComponent propertiesComponent) {
    myPropertiesComponent = propertiesComponent;
  }

  String getPropertyValue(Property property) {

    // We getOrInit to ensure that the default is written for this user the first time it is fetched
    // This will ensure that users preferences stay stable in the future, even if defaults change
    return myPropertiesComponent.getOrInit(property.getStringName(), property.getDefault());
  }

  void setPropertyValue(Property property, String propertyValue) {
    myPropertiesComponent.setValue(property.getStringName(),
                                   propertyValue);
  }
}
