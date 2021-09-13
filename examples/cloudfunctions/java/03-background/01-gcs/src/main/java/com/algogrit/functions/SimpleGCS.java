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

import com.algogrit.functions.SimpleGCS.GCSEvent;
import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import java.util.logging.Logger;

public class SimpleGCS implements BackgroundFunction<GCSEvent> {
  private static final Logger logger = Logger.getLogger(SimpleGCS.class.getName());
  // private static Storage storage = StorageOptions.getDefaultInstance().getService();

  @Override
  public void accept(GCSEvent event, Context context) {
    logger.info("Processing file: " + event.name);
    // throw new UnsupportedOperationException("Not supported yet.");

    //// Read file from GCS Bucket
    // String BUCKET_NAME = System.getenv("BUCKET_NAME");
    // Blob blob = storage.get(BUCKET_NAME, event.name);
    // try (ReadChannel reader = blob.reader()) {
    //   ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
    //   StringBuilder buffer = new StringBuilder();
    //    int numCharsRead;
    //   while ((numCharsRead = reader.read(bytes)) > 0) {
    //    bytes.flip();
    //    // Content credits: https://www.baeldung.com/java-convert-reader-to-string
    //    // do something with bytes; currently reading into a buffer
    //    buffer.append(bytes, 0, numCharsRead);
    //    bytes.clear();
    //   }
    // String targetString = buffer.toString();
    //  }

    //// Write file to GCS bucket
    // Create a bucket - Optional
    // String bucketName = "my_unique_bucket"; // Change this to something unique
    // Bucket bucket = storage.create(BucketInfo.of(bucketName));

    // // Upload a blob to the newly created bucket
    // BlobId blobId = BlobId.of(bucketName, "my_blob_name");
    // BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
    // Blob blob = storage.create(blobInfo, "a simple blob".getBytes(UTF_8));
  }

  public static class GCSEvent {
    String bucket;
    String name;
    String metageneration;
  }
}
