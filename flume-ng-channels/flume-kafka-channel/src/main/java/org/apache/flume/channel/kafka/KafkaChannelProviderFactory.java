/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flume.channel.kafka;

import java.util.HashSet;
import java.util.Set;

import org.apache.flume.Context;
import org.apache.flume.channel.kafka.impl.KafkaChannelProviderImpl;

public final class KafkaChannelProviderFactory {

  private static Set<String> INSTANCES = new HashSet<String>();
  private static KafkaChannelProvider PROVIDER;

  public static synchronized KafkaChannelProvider getProvider(
      Context context, String name) {
    if (PROVIDER == null) {
      PROVIDER = new KafkaChannelProviderImpl();
      PROVIDER.initialize(context);
    }

    if (!INSTANCES.add(name)) {
      throw new KafkaChannelException("Attempt to initialize multiple "
           + "channels with same name: " + name);
    }

    return PROVIDER;
  }

  public static synchronized void releaseProvider(String name) {
    if (!INSTANCES.remove(name)) {
      throw new KafkaChannelException("Attempt to release non-existent channel: "
          + name);
    }

    if (INSTANCES.size() == 0) {
      // Deinitialize the provider
      PROVIDER.close();
      PROVIDER = null;
    }
  }

  private KafkaChannelProviderFactory() {
    // disable explicit object creation
  }
}
