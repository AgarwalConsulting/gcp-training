/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2021 CoderMana Technologies Pvt Ltd
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package com.algogrit.functions;

import java.util.logging.Logger;

import com.algogrit.functions.SimpleGCS.GCSEvent;
import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class SimpleGCS implements BackgroundFunction<GCSEvent> {
  private static final Logger logger = Logger.getLogger(SimpleGCS.class.getName());
  private static Storage storage = StorageOptions.getDefaultInstance().getService();

  @Override
  public void accept(GCSEvent event, Context context) throws Exception {
    String BUCKET_NAME = System.getenv("BUCKET_NAME");

    logger.info("Processing file: " + event.name);
    // throw new UnsupportedOperationException("Not supported yet.");

    // Content credits: https://www.baeldung.com/java-google-cloud-storage

    //// Read file from GCS Bucket
    Blob blob = storage.get(BUCKET_NAME, event.name);

    String targetString = new String(blob.getContent());
    logger.info("File contents:" + targetString);

    //// Write file to GCS bucket
    // Bucket bucket = storage.create(BucketInfo.of(BUCKET_NAME));
    // String value = "Hello, World!";
    // byte[] bytes = value.getBytes(UTF_8);
    // Blob blob = bucket.create("my-first-blob", bytes);
  }

  public static class GCSEvent {
    String bucket;
    String name;
    String metageneration;
  }
}
