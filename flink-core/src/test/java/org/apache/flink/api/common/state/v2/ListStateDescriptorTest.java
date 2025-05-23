/*
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

package org.apache.flink.api.common.state.v2;

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.core.testutils.CommonTestUtils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Tests for {@link ListStateDescriptor}. */
public class ListStateDescriptorTest {

    @Test
    void testHashCodeAndEquals() throws Exception {
        final String name = "testName";

        ListStateDescriptor<Integer> original =
                new ListStateDescriptor<>(name, BasicTypeInfo.INT_TYPE_INFO);
        ListStateDescriptor<Integer> same =
                new ListStateDescriptor<>(name, BasicTypeInfo.INT_TYPE_INFO);
        ListStateDescriptor<Integer> sameBySerializer =
                new ListStateDescriptor<>(name, BasicTypeInfo.INT_TYPE_INFO);

        // test that hashCode() works on state descriptors with initialized and uninitialized
        // serializers
        assertThat(same).hasSameHashCodeAs(original);
        assertThat(sameBySerializer).hasSameHashCodeAs(original);

        assertThat(same).isEqualTo(original);
        assertThat(sameBySerializer).isEqualTo(original);

        // equality with a clone
        ListStateDescriptor<Integer> clone = CommonTestUtils.createCopySerializable(original);
        assertThat(clone).isEqualTo(original);
    }
}
