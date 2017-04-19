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


public class AmplTypeNameTest {
    protected <E extends Enum<E>> E generic(E[] values) {
        return values[0];
    }

    protected static class TestGeneric<T> {
        class Inner {        }

        class InnerGeneric<T2> {        }

        static class NestedNonGeneric {        }
    }

    protected static TestGeneric<String>.Inner testGenericStringInner() {
        return null;
    }

    protected static TestGeneric<Integer>.Inner testGenericIntInner() {
        return null;
    }

    protected static TestGeneric<Short>.InnerGeneric<Long> testGenericInnerLong() {
        return null;
    }

    protected static TestGeneric<Short>.InnerGeneric<Integer> testGenericInnerInt() {
        return null;
    }

    protected static TestGeneric.NestedNonGeneric testNestedNonGeneric() {
        return null;
    }

    @org.junit.Test
    public void genericType() throws Exception {
        java.lang.reflect.Method recursiveEnum = getClass().getDeclaredMethod("generic", Enum[].class);
        TypeName.get(recursiveEnum.getReturnType());
        TypeName.get(recursiveEnum.getGenericReturnType());
        TypeName genericTypeName = TypeName.get(recursiveEnum.getParameterTypes()[0]);
        TypeName.get(recursiveEnum.getGenericParameterTypes()[0]);
        // Make sure the generic argument is present
        com.google.common.truth.Truth.assertThat(genericTypeName.toString()).contains("Enum");
    }

    @org.junit.Test
    public void innerClassInGenericType() throws Exception {
        java.lang.reflect.Method genericStringInner = getClass().getDeclaredMethod("testGenericStringInner");
        TypeName.get(genericStringInner.getReturnType());
        TypeName genericTypeName = TypeName.get(genericStringInner.getGenericReturnType());
        org.junit.Assert.assertNotEquals(TypeName.get(genericStringInner.getGenericReturnType()), TypeName.get(getClass().getDeclaredMethod("testGenericIntInner").getGenericReturnType()));
        // Make sure the generic argument is present
        com.google.common.truth.Truth.assertThat(genericTypeName.toString()).isEqualTo(((TestGeneric.class.getCanonicalName()) + "<java.lang.String>.Inner"));
    }

    @org.junit.Test
    public void innerGenericInGenericType() throws Exception {
        java.lang.reflect.Method genericStringInner = getClass().getDeclaredMethod("testGenericInnerLong");
        TypeName.get(genericStringInner.getReturnType());
        TypeName genericTypeName = TypeName.get(genericStringInner.getGenericReturnType());
        org.junit.Assert.assertNotEquals(TypeName.get(genericStringInner.getGenericReturnType()), TypeName.get(getClass().getDeclaredMethod("testGenericInnerInt").getGenericReturnType()));
        // Make sure the generic argument is present
        com.google.common.truth.Truth.assertThat(genericTypeName.toString()).isEqualTo(((TestGeneric.class.getCanonicalName()) + "<java.lang.Short>.InnerGeneric<java.lang.Long>"));
    }

    @org.junit.Test
    public void innerStaticInGenericType() throws Exception {
        java.lang.reflect.Method staticInGeneric = getClass().getDeclaredMethod("testNestedNonGeneric");
        TypeName.get(staticInGeneric.getReturnType());
        TypeName typeName = TypeName.get(staticInGeneric.getGenericReturnType());
        // Make sure there are no generic arguments
        com.google.common.truth.Truth.assertThat(typeName.toString()).isEqualTo(((TestGeneric.class.getCanonicalName()) + ".NestedNonGeneric"));
    }

    @org.junit.Test
    public void equalsAndHashCodePrimitive() {
        assertEqualsHashCodeAndToString(TypeName.BOOLEAN, TypeName.BOOLEAN);
        assertEqualsHashCodeAndToString(TypeName.BYTE, TypeName.BYTE);
        assertEqualsHashCodeAndToString(TypeName.CHAR, TypeName.CHAR);
        assertEqualsHashCodeAndToString(TypeName.DOUBLE, TypeName.DOUBLE);
        assertEqualsHashCodeAndToString(TypeName.FLOAT, TypeName.FLOAT);
        assertEqualsHashCodeAndToString(TypeName.INT, TypeName.INT);
        assertEqualsHashCodeAndToString(TypeName.LONG, TypeName.LONG);
        assertEqualsHashCodeAndToString(TypeName.SHORT, TypeName.SHORT);
        assertEqualsHashCodeAndToString(TypeName.VOID, TypeName.VOID);
    }

    @org.junit.Test
    public void equalsAndHashCodeArrayTypeName() {
        assertEqualsHashCodeAndToString(ArrayTypeName.of(Object.class), ArrayTypeName.of(Object.class));
        assertEqualsHashCodeAndToString(TypeName.get(Object[].class), ArrayTypeName.of(Object.class));
    }

    @org.junit.Test
    public void equalsAndHashCodeClassName() {
        assertEqualsHashCodeAndToString(ClassName.get(Object.class), ClassName.get(Object.class));
        assertEqualsHashCodeAndToString(TypeName.get(Object.class), ClassName.get(Object.class));
        assertEqualsHashCodeAndToString(ClassName.bestGuess("java.lang.Object"), ClassName.get(Object.class));
    }

    @org.junit.Test
    public void equalsAndHashCodeParameterizedTypeName() {
        assertEqualsHashCodeAndToString(ParameterizedTypeName.get(Object.class), ParameterizedTypeName.get(Object.class));
        assertEqualsHashCodeAndToString(ParameterizedTypeName.get(java.util.Set.class, java.util.UUID.class), ParameterizedTypeName.get(java.util.Set.class, java.util.UUID.class));
        org.junit.Assert.assertNotEquals(ClassName.get(java.util.List.class), ParameterizedTypeName.get(java.util.List.class, String.class));
    }

    @org.junit.Test
    public void equalsAndHashCodeTypeVariableName() {
        assertEqualsHashCodeAndToString(TypeVariableName.get(Object.class), TypeVariableName.get(Object.class));
        TypeVariableName typeVar1 = TypeVariableName.get("T", java.util.Comparator.class, java.io.Serializable.class);
        TypeVariableName typeVar2 = TypeVariableName.get("T", java.util.Comparator.class, java.io.Serializable.class);
        assertEqualsHashCodeAndToString(typeVar1, typeVar2);
    }

    @org.junit.Test
    public void equalsAndHashCodeWildcardTypeName() {
        assertEqualsHashCodeAndToString(WildcardTypeName.subtypeOf(Object.class), WildcardTypeName.subtypeOf(Object.class));
        assertEqualsHashCodeAndToString(WildcardTypeName.subtypeOf(java.io.Serializable.class), WildcardTypeName.subtypeOf(java.io.Serializable.class));
        assertEqualsHashCodeAndToString(WildcardTypeName.supertypeOf(String.class), WildcardTypeName.supertypeOf(String.class));
    }

    @org.junit.Test
    public void isPrimitive() throws Exception {
        com.google.common.truth.Truth.assertThat(TypeName.INT.isPrimitive()).isTrue();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "Integer").isPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "String").isPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(TypeName.VOID.isPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "Void").isPrimitive()).isFalse();
    }

    @org.junit.Test
    public void isBoxedPrimitive() throws Exception {
        com.google.common.truth.Truth.assertThat(TypeName.INT.isBoxedPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "Integer").isBoxedPrimitive()).isTrue();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "String").isBoxedPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(TypeName.VOID.isBoxedPrimitive()).isFalse();
        com.google.common.truth.Truth.assertThat(ClassName.get("java.lang", "Void").isBoxedPrimitive()).isFalse();
    }

    private void assertEqualsHashCodeAndToString(TypeName a, TypeName b) {
        org.junit.Assert.assertEquals(a.toString(), b.toString());
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    /* amplification of com.squareup.javapoet.TypeNameTest#equalsAndHashCodeParameterizedTypeName */
    @org.junit.Test(timeout = 10000)
    public void equalsAndHashCodeParameterizedTypeName_cf140_failAssert9() {
        // AssertGenerator generate try/catch block with fail statement
        try {
            // MethodAssertGenerator build local variable
            Object o_13_1 = ParameterizedTypeName.get(java.util.List.class, String.class);
            assertEqualsHashCodeAndToString(ParameterizedTypeName.get(Object.class), ParameterizedTypeName.get(Object.class));
            assertEqualsHashCodeAndToString(ParameterizedTypeName.get(java.util.Set.class, java.util.UUID.class), ParameterizedTypeName.get(java.util.Set.class, java.util.UUID.class));
            // StatementAdderOnAssert create null value
            java.lang.reflect.Type vc_65385 = (java.lang.reflect.Type)null;
            // StatementAdderOnAssert create null value
            TypeName vc_65383 = (TypeName)null;
            // StatementAdderMethod cloned existing statement
            vc_65383.get(vc_65385);
            // MethodAssertGenerator build local variable
            Object o_13_0 = ClassName.get(java.util.List.class);
            org.junit.Assert.fail("equalsAndHashCodeParameterizedTypeName_cf140 should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException eee) {
        }
    }

    /* amplification of com.squareup.javapoet.TypeNameTest#genericType */
    /* amplification of com.squareup.javapoet.TypeNameTest#genericType_literalMutation1692 */
    /* amplification of com.squareup.javapoet.TypeNameTest#genericType_literalMutation1692_failAssert3_add1735 */
    @org.junit.Test(timeout = 10000)
    public void genericType_literalMutation1692_failAssert3_add1735_literalMutation2287() throws Exception {
        // AssertGenerator generate try/catch block with fail statement
        try {
            java.lang.reflect.Method recursiveEnum = getClass().getDeclaredMethod("generic", Enum[].class);
            TypeName.get(recursiveEnum.getReturnType());
            TypeName.get(recursiveEnum.getGenericReturnType());
            TypeName genericTypeName = TypeName.get(recursiveEnum.getParameterTypes()[0]);
            // AssertGenerator replace invocation
            TypeName o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13 = // MethodCallAdder
TypeName.get(recursiveEnum.getGenericParameterTypes()[0]);
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).withoutAnnotations()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box()).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).box()).isPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations()).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box()).isBoxedPrimitive());
            // AssertGenerator add assertion
            org.junit.Assert.assertFalse(((ArrayTypeName)((ArrayTypeName)((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box()).box()).isAnnotated());
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).withoutAnnotations().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            // AssertGenerator add assertion
            org.junit.Assert.assertTrue(((ArrayTypeName)o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13).box().equals(o_genericType_literalMutation1692_failAssert3_add1735_literalMutation2287__13));
            TypeName.get(recursiveEnum.getGenericParameterTypes()[1]);
            // Make sure the generic argument is present
            com.google.common.truth.Truth.assertThat(genericTypeName.toString()).contains("Enum");
            org.junit.Assert.fail("genericType_literalMutation1692 should have thrown ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException eee) {
        }
    }

    /* amplification of com.squareup.javapoet.TypeNameTest#innerClassInGenericType */
    @org.junit.Test(timeout = 10000)
    public void innerClassInGenericType_cf2812_failAssert21() throws Exception {
        // AssertGenerator generate try/catch block with fail statement
        try {
            // MethodAssertGenerator build local variable
            Object o_15_1 = TypeName.get(getClass().getDeclaredMethod("testGenericIntInner").getGenericReturnType());
            java.lang.reflect.Method genericStringInner = getClass().getDeclaredMethod("testGenericStringInner");
            TypeName.get(genericStringInner.getReturnType());
            TypeName genericTypeName = TypeName.get(genericStringInner.getGenericReturnType());
            // StatementAdderOnAssert create null value
            java.util.Map<java.lang.reflect.Type, TypeVariableName> vc_65463 = (java.util.Map)null;
            // StatementAdderOnAssert create null value
            java.lang.reflect.Type vc_65461 = (java.lang.reflect.Type)null;
            // StatementAdderMethod cloned existing statement
            genericTypeName.get(vc_65461, vc_65463);
            // MethodAssertGenerator build local variable
            Object o_15_0 = TypeName.get(genericStringInner.getGenericReturnType());
            // Make sure the generic argument is present
            com.google.common.truth.Truth.assertThat(genericTypeName.toString()).isEqualTo(((TestGeneric.class.getCanonicalName()) + "<java.lang.String>.Inner"));
            org.junit.Assert.fail("innerClassInGenericType_cf2812 should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException eee) {
        }
    }

    /* amplification of com.squareup.javapoet.TypeNameTest#innerGenericInGenericType */
    @org.junit.Test(timeout = 10000)
    public void innerGenericInGenericType_cf3306_failAssert19() throws Exception {
        // AssertGenerator generate try/catch block with fail statement
        try {
            // MethodAssertGenerator build local variable
            Object o_15_1 = TypeName.get(getClass().getDeclaredMethod("testGenericInnerInt").getGenericReturnType());
            java.lang.reflect.Method genericStringInner = getClass().getDeclaredMethod("testGenericInnerLong");
            TypeName.get(genericStringInner.getReturnType());
            TypeName genericTypeName = TypeName.get(genericStringInner.getGenericReturnType());
            // StatementAdderOnAssert create null value
            java.lang.reflect.Type vc_65601 = (java.lang.reflect.Type)null;
            // StatementAdderOnAssert create null value
            TypeName vc_65599 = (TypeName)null;
            // StatementAdderMethod cloned existing statement
            vc_65599.get(vc_65601);
            // MethodAssertGenerator build local variable
            Object o_15_0 = TypeName.get(genericStringInner.getGenericReturnType());
            // Make sure the generic argument is present
            com.google.common.truth.Truth.assertThat(genericTypeName.toString()).isEqualTo(((TestGeneric.class.getCanonicalName()) + "<java.lang.Short>.InnerGeneric<java.lang.Long>"));
            org.junit.Assert.fail("innerGenericInGenericType_cf3306 should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException eee) {
        }
    }
}

