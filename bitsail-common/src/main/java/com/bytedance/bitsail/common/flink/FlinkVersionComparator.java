/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytedance.bitsail.common.flink;

import java.util.Comparator;

public enum FlinkVersionComparator implements Comparator<FlinkVersion> {

  /**
   * Compare Flink all version num
   */
  ALL(true, true, true),
  /**
   * Compare Flink all minor num
   */
  MINOR(true, true, false);

  private final boolean compareMajor;
  private final boolean compareMinor;
  private final boolean comparePatch;

  private FlinkVersionComparator(
          final boolean compareMajor,
          final boolean compareMinor,
          final boolean comparePatch) {
    this.compareMajor = compareMajor;
    this.compareMinor = compareMinor;
    this.comparePatch = comparePatch;
  }

  @Override
  public int compare(FlinkVersion first, FlinkVersion second) {
    if (first == null && second != null) {
      return -1;
    } else if (second == null && first != null) {
      return 1;
    } else {
      int result;
      if (this.compareMajor) {
        result = first.getMajor().compareTo(second.getMajor());
        if (result != 0) {
          return result;
        }
      }

      if (this.compareMinor) {
        result = first.getMinor().compareTo(second.getMinor());
        if (result != 0) {
          return result;
        }
      }

      if (this.comparePatch) {
        result = first.getPatch().compareTo(second.getPatch());
        if (result != 0) {
          return result;
        }
      }
    }

    return 0;
  }

}
