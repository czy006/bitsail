package com.bytedance.bitsail.engine.flink;

import org.apache.flink.client.program.ClusterClient;

/**
 * @param <T>
 */
abstract class AbsFlinkClusterClient<T> {

  private ClusterClient<T> client;
  public AbsFlinkClusterClient(ClusterClient<T> client) {
    this.client = client;
  }
}
