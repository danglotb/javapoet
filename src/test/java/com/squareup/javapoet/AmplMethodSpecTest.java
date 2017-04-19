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


public final class AmplMethodSpecTest {
    @org.junit.Rule
    public final com.google.testing.compile.CompilationRule compilation = new com.google.testing.compile.CompilationRule();

    private javax.lang.model.util.Elements elements;

    private javax.lang.model.util.Types types;

    @org.junit.Before
    public void setUp() {
        elements = compilation.getElements();
        types = compilation.getTypes();
    }

    private javax.lang.model.element.TypeElement getElement(Class<?> clazz) {
        return elements.getTypeElement(clazz.getCanonicalName());
    }

    private javax.lang.model.element.ExecutableElement findFirst(java.util.Collection<javax.lang.model.element.ExecutableElement> elements, String name) {
        for (javax.lang.model.element.ExecutableElement executableElement : elements) {
            if (executableElement.getSimpleName().toString().equals(name)) {
                return executableElement;
            }
        }
        throw new IllegalArgumentException(((name + " not found in ") + elements));
    }

    @org.junit.Test
    public void nullAnnotationsAddition() {
        try {
            MethodSpec.methodBuilder("doSomething").addAnnotations(null);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("annotationSpecs == null");
        }
    }

    @org.junit.Test
    public void nullTypeVariablesAddition() {
        try {
            MethodSpec.methodBuilder("doSomething").addTypeVariables(null);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("typeVariables == null");
        }
    }

    @org.junit.Test
    public void nullParametersAddition() {
        try {
            MethodSpec.methodBuilder("doSomething").addParameters(null);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("parameterSpecs == null");
        }
    }

    @org.junit.Test
    public void nullExceptionsAddition() {
        try {
            MethodSpec.methodBuilder("doSomething").addExceptions(null);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("exceptions == null");
        }
    }

    @java.lang.annotation.Target(value = java.lang.annotation.ElementType.PARAMETER)
    @interface Nullable {    }

    abstract static class Everything {
        @Deprecated
        protected abstract <T extends Runnable & java.io.Closeable> Runnable everything(@Nullable
        String thing, java.util.List<? extends T> things) throws java.io.IOException, SecurityException;
    }

    abstract static class HasAnnotation {
        @Override
        public abstract String toString();
    }

    interface ExtendsOthers extends Comparable<Long> , java.util.concurrent.Callable<Integer> {    }

    interface ExtendsIterableWithDefaultMethods extends Iterable<Object> {    }

    @org.junit.Test
    public void overrideEverything() {
        javax.lang.model.element.TypeElement classElement = getElement(Everything.class);
        javax.lang.model.element.ExecutableElement methodElement = com.google.common.collect.Iterables.getOnlyElement(javax.lang.model.util.ElementFilter.methodsIn(classElement.getEnclosedElements()));
        MethodSpec method = MethodSpec.overriding(methodElement).build();
        com.google.common.truth.Truth.assertThat(method.toString()).isEqualTo(("" + ((((("@java.lang.Override\n" + "protected <T extends java.lang.Runnable & java.io.Closeable> java.lang.Runnable ") + "everything(java.lang.String arg0,\n") + "    java.util.List<? extends T> arg1) throws java.io.IOException, ") + "java.lang.SecurityException {\n") + "}\n")));
    }

    @org.junit.Test
    public void overrideDoesNotCopyOverrideAnnotation() {
        javax.lang.model.element.TypeElement classElement = getElement(HasAnnotation.class);
        javax.lang.model.element.ExecutableElement exec = com.google.common.collect.Iterables.getOnlyElement(javax.lang.model.util.ElementFilter.methodsIn(classElement.getEnclosedElements()));
        MethodSpec method = MethodSpec.overriding(exec).build();
        com.google.common.truth.Truth.assertThat(method.toString()).isEqualTo(("" + (("@java.lang.Override\n" + "public java.lang.String toString() {\n") + "}\n")));
    }

    @org.junit.Test
    public void overrideDoesNotCopyDefaultModifier() {
        javax.lang.model.element.TypeElement classElement = getElement(ExtendsIterableWithDefaultMethods.class);
        javax.lang.model.type.DeclaredType classType = ((javax.lang.model.type.DeclaredType) (classElement.asType()));
        java.util.List<javax.lang.model.element.ExecutableElement> methods = javax.lang.model.util.ElementFilter.methodsIn(elements.getAllMembers(classElement));
        javax.lang.model.element.ExecutableElement exec = findFirst(methods, "iterator");
        com.google.common.truth.TruthJUnit.assume().that(Util.DEFAULT).isNotNull();
        exec = findFirst(methods, "spliterator");
        MethodSpec method = MethodSpec.overriding(exec, classType, types).build();
        com.google.common.truth.Truth.assertThat(method.toString()).isEqualTo(("" + (("@java.lang.Override\n" + "public java.util.Spliterator<java.lang.Object> spliterator() {\n") + "}\n")));
    }

    @org.junit.Test
    public void overrideExtendsOthersWorksWithActualTypeParameters() {
        javax.lang.model.element.TypeElement classElement = getElement(ExtendsOthers.class);
        javax.lang.model.type.DeclaredType classType = ((javax.lang.model.type.DeclaredType) (classElement.asType()));
        java.util.List<javax.lang.model.element.ExecutableElement> methods = javax.lang.model.util.ElementFilter.methodsIn(elements.getAllMembers(classElement));
        javax.lang.model.element.ExecutableElement exec = findFirst(methods, "call");
        MethodSpec method = MethodSpec.overriding(exec, classType, types).build();
        com.google.common.truth.Truth.assertThat(method.toString()).isEqualTo(("" + (("@java.lang.Override\n" + "public java.lang.Integer call() throws java.lang.Exception {\n") + "}\n")));
        exec = findFirst(methods, "compareTo");
        method = MethodSpec.overriding(exec, classType, types).build();
        com.google.common.truth.Truth.assertThat(method.toString()).isEqualTo(("" + (("@java.lang.Override\n" + "public int compareTo(java.lang.Long arg0) {\n") + "}\n")));
    }

    @org.junit.Test
    public void overrideInvalidModifiers() {
        javax.lang.model.element.ExecutableElement method = org.mockito.Mockito.mock(javax.lang.model.element.ExecutableElement.class);
        org.mockito.Mockito.when(method.getModifiers()).thenReturn(com.google.common.collect.ImmutableSet.of(javax.lang.model.element.Modifier.FINAL));
        javax.lang.model.element.Element element = org.mockito.Mockito.mock(javax.lang.model.element.Element.class);
        org.mockito.Mockito.when(element.asType()).thenReturn(org.mockito.Mockito.mock(javax.lang.model.type.DeclaredType.class));
        org.mockito.Mockito.when(method.getEnclosingElement()).thenReturn(element);
        try {
            MethodSpec.overriding(method);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("cannot override method with modifiers: [final]");
        }
        org.mockito.Mockito.when(method.getModifiers()).thenReturn(com.google.common.collect.ImmutableSet.of(javax.lang.model.element.Modifier.PRIVATE));
        try {
            MethodSpec.overriding(method);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("cannot override method with modifiers: [private]");
        }
        org.mockito.Mockito.when(method.getModifiers()).thenReturn(com.google.common.collect.ImmutableSet.of(javax.lang.model.element.Modifier.STATIC));
        try {
            MethodSpec.overriding(method);
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("cannot override method with modifiers: [static]");
        }
    }

    @org.junit.Test
    public void equalsAndHashCode() {
        MethodSpec a = MethodSpec.constructorBuilder().build();
        MethodSpec b = MethodSpec.constructorBuilder().build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
        a = MethodSpec.methodBuilder("taco").build();
        b = MethodSpec.methodBuilder("taco").build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
        javax.lang.model.element.TypeElement classElement = getElement(Everything.class);
        javax.lang.model.element.ExecutableElement methodElement = com.google.common.collect.Iterables.getOnlyElement(javax.lang.model.util.ElementFilter.methodsIn(classElement.getEnclosedElements()));
        a = MethodSpec.overriding(methodElement).build();
        b = MethodSpec.overriding(methodElement).build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @org.junit.Test
    public void duplicateExceptionsIgnored() {
        ClassName ioException = ClassName.get(java.io.IOException.class);
        ClassName timeoutException = ClassName.get(java.util.concurrent.TimeoutException.class);
        MethodSpec methodSpec = MethodSpec.methodBuilder("duplicateExceptions").addException(ioException).addException(timeoutException).addException(timeoutException).addException(ioException).build();
        com.google.common.truth.Truth.assertThat(methodSpec.exceptions).isEqualTo(java.util.Arrays.asList(ioException, timeoutException));
        com.google.common.truth.Truth.assertThat(methodSpec.toBuilder().addException(ioException).build().exceptions).isEqualTo(java.util.Arrays.asList(ioException, timeoutException));
    }
}

