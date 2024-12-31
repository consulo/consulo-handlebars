package com.dmarcotte.handlebars.file;

import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import jakarta.annotation.Nonnull;

@ExtensionImpl
public class HbFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@Nonnull FileTypeConsumer consumer) {
    consumer.consume(HbFileType.INSTANCE, HbFileType.DEFAULT_EXTENSION);
  }
}
