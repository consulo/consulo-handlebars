package com.dmarcotte.handlebars.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import javax.annotation.Nonnull;

public class HbFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@Nonnull FileTypeConsumer consumer) {
    consumer.consume(HbFileType.INSTANCE, HbFileType.DEFAULT_EXTENSION);
  }
}
