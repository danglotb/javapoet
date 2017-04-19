/**
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.squareup.javapoet;


public final class AmplNameAllocatorTest {
    @org.junit.Test
    public void usage() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo", 1)).isEqualTo("foo");
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("bar", 2)).isEqualTo("bar");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(1)).isEqualTo("foo");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(2)).isEqualTo("bar");
    }

    @org.junit.Test
    public void nameCollision() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo")).isEqualTo("foo");
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo")).isEqualTo("foo_");
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo")).isEqualTo("foo__");
    }

    @org.junit.Test
    public void nameCollisionWithTag() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo", 1)).isEqualTo("foo");
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo", 2)).isEqualTo("foo_");
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("foo", 3)).isEqualTo("foo__");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(1)).isEqualTo("foo");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(2)).isEqualTo("foo_");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(3)).isEqualTo("foo__");
    }

    @org.junit.Test
    public void characterMappingSubstitute() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("a-b", 1)).isEqualTo("a_b");
    }

    @org.junit.Test
    public void characterMappingSurrogate() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("a\ud83c\udf7ab", 1)).isEqualTo("a_b");
    }

    @org.junit.Test
    public void characterMappingInvalidStartButValidPart() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("1ab", 1)).isEqualTo("_1ab");
    }

    @org.junit.Test
    public void characterMappingInvalidStartIsInvalidPart() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("&ab", 1)).isEqualTo("_ab");
    }

    @org.junit.Test
    public void javaKeyword() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        com.google.common.truth.Truth.assertThat(nameAllocator.newName("public", 1)).isEqualTo("public_");
        com.google.common.truth.Truth.assertThat(nameAllocator.get(1)).isEqualTo("public_");
    }

    @org.junit.Test
    public void tagReuseForbidden() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        nameAllocator.newName("foo", 1);
        try {
            nameAllocator.newName("bar", 1);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("tag 1 cannot be used for both 'foo' and 'bar'");
        }
    }

    @org.junit.Test
    public void useBeforeAllocateForbidden() throws Exception {
        NameAllocator nameAllocator = new NameAllocator();
        try {
            nameAllocator.get(1);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("unknown tag: 1");
        }
    }

    @org.junit.Test
    public void cloneUsage() throws Exception {
        NameAllocator outterAllocator = new NameAllocator();
        outterAllocator.newName("foo", 1);
        NameAllocator innerAllocator1 = outterAllocator.clone();
        com.google.common.truth.Truth.assertThat(innerAllocator1.newName("bar", 2)).isEqualTo("bar");
        com.google.common.truth.Truth.assertThat(innerAllocator1.newName("foo", 3)).isEqualTo("foo_");
        NameAllocator innerAllocator2 = outterAllocator.clone();
        com.google.common.truth.Truth.assertThat(innerAllocator2.newName("foo", 2)).isEqualTo("foo_");
        com.google.common.truth.Truth.assertThat(innerAllocator2.newName("bar", 3)).isEqualTo("bar");
    }

    /* amplification of com.squareup.javapoet.NameAllocatorTest#cloneUsage */
    /* amplification of com.squareup.javapoet.NameAllocatorTest#cloneUsage_add39 */
    /* amplification of com.squareup.javapoet.NameAllocatorTest#cloneUsage_add39_failAssert2_add59 */
    @org.junit.Test(timeout = 10000)
    public void cloneUsage_add39_failAssert2_add59_cf1336_failAssert73() throws Exception {
        // AssertGenerator generate try/catch block with fail statement
        try {
            // AssertGenerator generate try/catch block with fail statement
            try {
                NameAllocator outterAllocator = new NameAllocator();
                // AssertGenerator replace invocation
                String o_cloneUsage_add39_failAssert2_add59__5 = outterAllocator.newName("foo", 1);
                // StatementAdderOnAssert create null value
                Object vc_40726 = (Object)null;
                // StatementAdderOnAssert create random local variable
                String vc_40725 = new String();
                // StatementAdderOnAssert create random local variable
                NameAllocator vc_40723 = new NameAllocator();
                // StatementAdderMethod cloned existing statement
                vc_40723.newName(vc_40725, vc_40726);
                // MethodAssertGenerator build local variable
                Object o_15_0 = o_cloneUsage_add39_failAssert2_add59__5;
                NameAllocator innerAllocator1 = outterAllocator.clone();
                com.google.common.truth.Truth.assertThat(innerAllocator1.newName("bar", 2)).isEqualTo("bar");
                // MethodCallAdder
                com.google.common.truth.Truth.assertThat(innerAllocator1.newName("foo", 3)).isEqualTo("foo_");
                com.google.common.truth.Truth.assertThat(innerAllocator1.newName("foo", 3)).isEqualTo("foo_");
                NameAllocator innerAllocator2 = outterAllocator.clone();
                com.google.common.truth.Truth.assertThat(innerAllocator2.newName("foo", 2)).isEqualTo("foo_");
                // MethodCallAdder
                com.google.common.truth.Truth.assertThat(innerAllocator2.newName("bar", 3)).isEqualTo("bar");
                com.google.common.truth.Truth.assertThat(innerAllocator2.newName("bar", 3)).isEqualTo("bar");
                org.junit.Assert.fail("cloneUsage_add39 should have thrown IllegalArgumentException");
            } catch (IllegalArgumentException eee) {
            }
            org.junit.Assert.fail("cloneUsage_add39_failAssert2_add59_cf1336 should have thrown NullPointerException");
        } catch (NullPointerException eee) {
        }
    }
}

