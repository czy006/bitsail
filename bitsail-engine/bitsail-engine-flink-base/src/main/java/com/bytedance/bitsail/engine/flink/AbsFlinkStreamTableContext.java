package com.bytedance.bitsail.engine.flink;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

abstract class AbsFlinkStreamTableContext implements StreamTableEnvironment {

  private final StreamExecutionEnvironment streamExecutionEnvironment;
  private final StreamTableEnvironment streamTableEnvironment;

  public final ParameterTool parameterTool;

  public AbsFlinkStreamTableContext(
      ParameterTool parameterTool,
      StreamExecutionEnvironment streamExecutionEnvironment,
      StreamTableEnvironment streamTableEnvironment) {
    this.parameterTool = parameterTool;
    this.streamExecutionEnvironment = streamExecutionEnvironment;
    this.streamTableEnvironment = streamTableEnvironment;
  }

  public StreamExecutionEnvironment getStreamExecutionEnvironment() {
    return this.streamExecutionEnvironment;
  }

}
