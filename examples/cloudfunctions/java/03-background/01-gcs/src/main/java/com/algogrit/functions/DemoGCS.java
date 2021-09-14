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

// import com.algogrit.functions.eventpojos.GcsEvent;
import com.algogrit.functions.DemoGCS.GcsEvent;
import com.google.cloud.functions.*;
import com.google.cloud.storage.*;

public class DemoGCS implements BackgroundFunction<GcsEvent>{
  public static final Logger logger = Logger.getLogger(DemoGCS.class.getName());

  public static final Storage storage = StorageOptions.getDefaultInstance().getService();

  public void accept(GcsEvent payload, Context context) throws Exception {
    logger.info("Event ID: " + context.eventId());
    logger.info("Event Type: " + context.eventType());
    logger.info("Event Resource: " + context.resource());

    logger.info("File uploaded: " + payload.name + " | File Size: " + payload.size.toString());

    // Bucket bucket = storage.create(BucketInfo.of(payload.bucket));

    // Blob blob = bucket.get(payload.name);
    Blob blob = storage.get(payload.bucket, payload.name);
    String fileContents = new String(blob.getContent());

    logger.info("File contents: " + fileContents);
  };

  public static class GcsEvent {
    String name;
    String bucket;
    Long size;
  }
}
