package com.bytedance.bitsail.engine.flink;

import org.apache.flink.client.program.ClusterClient;

public class FlinkClusterClient<T> extends AbsFlinkClusterClient<T> {

  public FlinkClusterClient(ClusterClient<T> client) {
    super(client);
  }
}
