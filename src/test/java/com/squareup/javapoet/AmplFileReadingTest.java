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


@org.junit.runner.RunWith(value = org.junit.runners.JUnit4.class)
public class AmplFileReadingTest {
    // Used for storing compilation output.
    @org.junit.Rule
    public final org.junit.rules.TemporaryFolder temporaryFolder = new org.junit.rules.TemporaryFolder();

    @org.junit.Test
    public void javaFileObjectUri() {
        TypeSpec type = TypeSpec.classBuilder("Test").build();
        com.google.common.truth.Truth.assertThat(JavaFile.builder("", type).build().toJavaFileObject().toUri()).isEqualTo(java.net.URI.create("Test.java"));
        com.google.common.truth.Truth.assertThat(JavaFile.builder("foo", type).build().toJavaFileObject().toUri()).isEqualTo(java.net.URI.create("foo/Test.java"));
        com.google.common.truth.Truth.assertThat(JavaFile.builder("com.example", type).build().toJavaFileObject().toUri()).isEqualTo(java.net.URI.create("com/example/Test.java"));
    }

    @org.junit.Test
    public void javaFileObjectKind() {
        JavaFile javaFile = JavaFile.builder("", TypeSpec.classBuilder("Test").build()).build();
        com.google.common.truth.Truth.assertThat(javaFile.toJavaFileObject().getKind()).isEqualTo(javax.tools.JavaFileObject.Kind.SOURCE);
    }

    @org.junit.Test
    public void javaFileObjectCharacterContent() throws java.io.IOException {
        TypeSpec type = TypeSpec.classBuilder("Test").addJavadoc("Pi\u00f1ata\u00a1").addMethod(MethodSpec.methodBuilder("fooBar").build()).build();
        JavaFile javaFile = JavaFile.builder("foo", type).build();
        javax.tools.JavaFileObject javaFileObject = javaFile.toJavaFileObject();
        // We can never have encoding issues (everything is in process)
        com.google.common.truth.Truth.assertThat(javaFileObject.getCharContent(true)).isEqualTo(javaFile.toString());
        com.google.common.truth.Truth.assertThat(javaFileObject.getCharContent(false)).isEqualTo(javaFile.toString());
    }

    @org.junit.Test
    public void javaFileObjectInputStreamIsUtf8() throws java.io.IOException {
        JavaFile javaFile = JavaFile.builder("foo", TypeSpec.classBuilder("Test").build()).addFileComment("Pi\u00f1ata\u00a1").build();
        byte[] bytes = com.google.common.io.ByteStreams.toByteArray(javaFile.toJavaFileObject().openInputStream());
        // JavaPoet always uses UTF-8.
        com.google.common.truth.Truth.assertThat(bytes).isEqualTo(javaFile.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @org.junit.Test
    public void compileJavaFile() throws Exception {
        final String value = "Hello World!";
        TypeSpec type = TypeSpec.classBuilder("Test").addModifiers(javax.lang.model.element.Modifier.PUBLIC).addSuperinterface(ParameterizedTypeName.get(java.util.concurrent.Callable.class, String.class)).addMethod(MethodSpec.methodBuilder("call").returns(String.class).addModifiers(javax.lang.model.element.Modifier.PUBLIC).addStatement("return $S", value).build()).build();
        JavaFile javaFile = JavaFile.builder("foo", type).build();
        javax.tools.JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
        javax.tools.DiagnosticCollector<javax.tools.JavaFileObject> diagnosticCollector = new javax.tools.DiagnosticCollector<>();
        javax.tools.StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, java.util.Locale.getDefault(), java.nio.charset.StandardCharsets.UTF_8);
        fileManager.setLocation(javax.tools.StandardLocation.CLASS_OUTPUT, java.util.Collections.singleton(temporaryFolder.newFolder()));
        javax.tools.JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, java.util.Collections.<String>emptySet(), java.util.Collections.<String>emptySet(), java.util.Collections.singleton(javaFile.toJavaFileObject()));
        com.google.common.truth.Truth.assertThat(task.call()).isTrue();
        com.google.common.truth.Truth.assertThat(diagnosticCollector.getDiagnostics()).isEmpty();
        ClassLoader loader = fileManager.getClassLoader(javax.tools.StandardLocation.CLASS_OUTPUT);
        java.util.concurrent.Callable<?> test = Class.forName("foo.Test", true, loader).asSubclass(java.util.concurrent.Callable.class).newInstance();
        com.google.common.truth.Truth.assertThat(java.util.concurrent.Callable.class.getMethod("call").invoke(test)).isEqualTo(value);
    }
}

