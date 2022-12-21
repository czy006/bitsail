package com.bytedance.bitsail.engine.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkStreamExecutionEnvInitializer {

  private volatile static StreamExecutionEnvironment streamExecutionEnvironment;

  private FlinkStreamExecutionEnvInitializer(){}

  public static StreamExecutionEnvironment getStreamExecutionEnvironment() {
    if (streamExecutionEnvironment == null){
      synchronized (FlinkStreamExecutionEnvInitializer.class){
        if (streamExecutionEnvironment == null){
          streamExecutionEnvironment = new StreamExecutionEnvironment();
          return streamExecutionEnvironment;
        }
      }
    }
    return streamExecutionEnvironment;
  }
}
