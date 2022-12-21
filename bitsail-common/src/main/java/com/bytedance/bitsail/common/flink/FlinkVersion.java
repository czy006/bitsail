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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FlinkVersion implements Comparable<FlinkVersion> {

  private static final Pattern FLINK_VERSION_PATTERN =
      Pattern.compile(
          "^(?<major>\\d+)\\.(?<minor>\\d+)(\\.(?<patch>\\d+))?$");

  /**
   * Flink Version Main 1.x.x 2.x.x
   */
  @NonNull
  private final Integer major;

  /**
   * Flink Version Minor 1.x.0 1.x.0
   */
  @NonNull
  private final Integer minor;

  /**
   * Flink Version Patch 1.16.x
   */
  private final Integer patch;

  public static FlinkVersion parse(String flinkVersionString) {
    Matcher matcher = FLINK_VERSION_PATTERN.matcher(Objects.requireNonNull(flinkVersionString));
    if (!matcher.matches()) {
      throw new IllegalArgumentException(
          String.format(
              "Invalid Flink version string '%s'. Expected pattern: %s",
              flinkVersionString, FLINK_VERSION_PATTERN));
    } else {
      try {
        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));
        String patchGroup = matcher.group("patch");
        Integer patch = patchGroup != null ? Integer.parseInt(patchGroup) : null;
        return new FlinkVersion(major, minor, patch);
      } catch (NumberFormatException numberFormatException) {
        throw new IllegalArgumentException(numberFormatException);
      }
    }
  }

  @NonNull
  public Integer getMajor() {
    return this.major;
  }

  @NonNull
  public Integer getMinor() {
    return this.minor;
  }

  public Integer getPatch() {
    return this.isPatchVersion() ? this.patch : 0;
  }

  public boolean isPatchVersion() {
    return this.patch != null;
  }

  @Override
  public int compareTo(FlinkVersion other) {
    return FlinkVersionComparator.ALL.compare(this, other);
  }

  public String getMajorMinor() {
    return String.format("%d.%d", this.major, this.minor);
  }

  public FlinkVersion toMinor() {
    return new FlinkVersion(this.major, this.minor, null);
  }
}
