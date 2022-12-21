package com.bytedance.bitsail.engine.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkStreamExecutionEnv {

  public static StreamExecutionEnvironment getFlinkStreamExecutionEnv() {
    return FlinkStreamExecutionEnvInitializer.getStreamExecutionEnvironment();
  }

}
