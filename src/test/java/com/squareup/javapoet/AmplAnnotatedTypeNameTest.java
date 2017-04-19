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


public class AmplAnnotatedTypeNameTest {
    private static final String NN = NeverNull.class.getCanonicalName();

    private final AnnotationSpec NEVER_NULL = AnnotationSpec.builder(NeverNull.class).build();

    public @interface NeverNull {    }

    @org.junit.Test(expected = NullPointerException.class)
    public void nullAnnotationArray() {
        TypeName.BOOLEAN.annotated(((AnnotationSpec[]) (null)));
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void nullAnnotationList() {
        TypeName.DOUBLE.annotated(((java.util.List<AnnotationSpec>) (null)));
    }

    @org.junit.Test
    public void annotated() {
        TypeName simpleString = TypeName.get(String.class);
        org.junit.Assert.assertFalse(simpleString.isAnnotated());
        org.junit.Assert.assertEquals(simpleString, TypeName.get(String.class));
        TypeName annotated = simpleString.annotated(NEVER_NULL);
        org.junit.Assert.assertTrue(annotated.isAnnotated());
        org.junit.Assert.assertEquals(annotated, annotated.annotated());
    }

    @org.junit.Test
    public void annotatedType() {
        String expected = ("@" + (AmplAnnotatedTypeNameTest.NN)) + " java.lang.String";
        TypeName type = TypeName.get(String.class);
        String actual = type.annotated(NEVER_NULL).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedTwice() {
        String expected = ("@" + (AmplAnnotatedTypeNameTest.NN)) + " @java.lang.Override java.lang.String";
        TypeName type = TypeName.get(String.class);
        String actual = type.annotated(NEVER_NULL).annotated(AnnotationSpec.builder(Override.class).build()).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedParameterizedType() {
        String expected = ("@" + (AmplAnnotatedTypeNameTest.NN)) + " java.util.List<java.lang.String>";
        TypeName type = ParameterizedTypeName.get(java.util.List.class, String.class);
        String actual = type.annotated(NEVER_NULL).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedArgumentOfParameterizedType() {
        String expected = ("java.util.List<@" + (AmplAnnotatedTypeNameTest.NN)) + " java.lang.String>";
        TypeName type = TypeName.get(String.class).annotated(NEVER_NULL);
        ClassName list = ClassName.get(java.util.List.class);
        String actual = ParameterizedTypeName.get(list, type).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedWildcardTypeNameWithSuper() {
        String expected = ("? super @" + (AmplAnnotatedTypeNameTest.NN)) + " java.lang.String";
        TypeName type = TypeName.get(String.class).annotated(NEVER_NULL);
        String actual = WildcardTypeName.supertypeOf(type).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedWildcardTypeNameWithExtends() {
        String expected = ("? extends @" + (AmplAnnotatedTypeNameTest.NN)) + " java.lang.String";
        TypeName type = TypeName.get(String.class).annotated(NEVER_NULL);
        String actual = WildcardTypeName.subtypeOf(type).toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void annotatedEquivalence() {
        annotatedEquivalence(TypeName.VOID);
        annotatedEquivalence(ArrayTypeName.get(Object[].class));
        annotatedEquivalence(ClassName.get(Object.class));
        annotatedEquivalence(ParameterizedTypeName.get(java.util.List.class, Object.class));
        annotatedEquivalence(TypeVariableName.get(Object.class));
        annotatedEquivalence(WildcardTypeName.get(Object.class));
    }

    private void annotatedEquivalence(TypeName type) {
        org.junit.Assert.assertFalse(type.isAnnotated());
        org.junit.Assert.assertEquals(type, type);
        org.junit.Assert.assertEquals(type.annotated(NEVER_NULL), type.annotated(NEVER_NULL));
        org.junit.Assert.assertNotEquals(type, type.annotated(NEVER_NULL));
        org.junit.Assert.assertEquals(type.hashCode(), type.hashCode());
        org.junit.Assert.assertEquals(type.annotated(NEVER_NULL).hashCode(), type.annotated(NEVER_NULL).hashCode());
        org.junit.Assert.assertNotEquals(type.hashCode(), type.annotated(NEVER_NULL).hashCode());
    }

    // https://github.com/square/javapoet/issues/431
    public @interface TypeUseAnnotation {    }

    // https://github.com/square/javapoet/issues/431
    @org.junit.Ignore
    @org.junit.Test
    public void annotatedNestedType() {
        String expected = ("java.util.Map.@" + (TypeUseAnnotation.class.getCanonicalName())) + " Entry";
        AnnotationSpec typeUseAnnotation = AnnotationSpec.builder(TypeUseAnnotation.class).build();
        TypeName type = TypeName.get(java.util.Map.Entry.class).annotated(typeUseAnnotation);
        String actual = type.toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    // https://github.com/square/javapoet/issues/431
    @org.junit.Ignore
    @org.junit.Test
    public void annotatedNestedParameterizedType() {
        String expected = ("java.util.Map.@" + (TypeUseAnnotation.class.getCanonicalName())) + " Entry<java.lang.Byte, java.lang.Byte>";
        AnnotationSpec typeUseAnnotation = AnnotationSpec.builder(TypeUseAnnotation.class).build();
        TypeName type = ParameterizedTypeName.get(java.util.Map.Entry.class, Byte.class, Byte.class).annotated(typeUseAnnotation);
        String actual = type.toString();
        org.junit.Assert.assertEquals(expected, actual);
    }

    /* amplification of com.squareup.javapoet.AnnotatedTypeNameTest#annotated */
    @org.junit.Test(timeout = 10000)
    public void annotated_cf52_failAssert12() {
        // AssertGenerator generate try/catch block with fail statement
        try {
            TypeName simpleString = TypeName.get(String.class);
            // MethodAssertGenerator build local variable
            Object o_3_0 = simpleString.isAnnotated();
            // MethodAssertGenerator build local variable
            Object o_5_0 = TypeName.get(String.class);
            TypeName annotated = simpleString.annotated(NEVER_NULL);
            // MethodAssertGenerator build local variable
            Object o_9_0 = annotated.isAnnotated();
            // StatementAdderOnAssert create null value
            java.util.Map<java.lang.reflect.Type, TypeVariableName> vc_38 = (java.util.Map)null;
            // StatementAdderOnAssert create null value
            java.lang.reflect.Type vc_36 = (java.lang.reflect.Type)null;
            // StatementAdderOnAssert create null value
            TypeName vc_34 = (TypeName)null;
            // StatementAdderMethod cloned existing statement
            vc_34.get(vc_36, vc_38);
            // MethodAssertGenerator build local variable
            Object o_19_0 = annotated.annotated();
            org.junit.Assert.fail("annotated_cf52 should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException eee) {
        }
    }

    /* amplification of com.squareup.javapoet.AnnotatedTypeNameTest#annotated */
    @org.junit.Test(timeout = 10000)
    public void annotated_cf3() {
        TypeName simpleString = TypeName.get(String.class);
        org.junit.Assert.assertFalse(simpleString.isAnnotated());
        org.junit.Assert.assertEquals(simpleString, TypeName.get(String.class));
        TypeName annotated = simpleString.annotated(NEVER_NULL);
        org.junit.Assert.assertTrue(annotated.isAnnotated());
        // StatementAdderOnAssert create null value
        Object vc_2 = (Object)null;
        // AssertGenerator replace invocation
        boolean o_annotated_cf3__13 = // StatementAdderMethod cloned existing statement
annotated.equals(vc_2);
        // AssertGenerator add assertion
        org.junit.Assert.assertFalse(o_annotated_cf3__13);
        org.junit.Assert.assertEquals(annotated, annotated.annotated());
    }
}

