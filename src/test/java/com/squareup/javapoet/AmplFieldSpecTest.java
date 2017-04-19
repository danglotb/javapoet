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


public class AmplFieldSpecTest {
    @org.junit.Test
    public void equalsAndHashCode() {
        FieldSpec a = FieldSpec.builder(int.class, "foo").build();
        FieldSpec b = FieldSpec.builder(int.class, "foo").build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
        a = FieldSpec.builder(int.class, "FOO", javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.STATIC).build();
        b = FieldSpec.builder(int.class, "FOO", javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.STATIC).build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @org.junit.Test
    public void nullAnnotationsAddition() {
        try {
            FieldSpec.builder(int.class, "foo").addAnnotations(null);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected.getMessage()).isEqualTo("annotationSpecs == null");
        }
    }
}

