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
public final class AmplJavaFileTest {
    @org.junit.Test
    public void importStaticReadmeExample() {
        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");
        ClassName namedBoards = ClassName.get("com.mattel", "Hoverboard", "Boards");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);
        MethodSpec beyond = MethodSpec.methodBuilder("beyond").returns(listOfHoverboards).addStatement("$T result = new $T<>()", listOfHoverboards, arrayList).addStatement("result.add($T.createNimbus(2000))", hoverboard).addStatement("result.add($T.createNimbus(\"2001\"))", hoverboard).addStatement("result.add($T.createNimbus($T.THUNDERBOLT))", hoverboard, namedBoards).addStatement("$T.sort(result)", java.util.Collections.class).addStatement("return result.isEmpty() ? $T.emptyList() : result", java.util.Collections.class).build();
        TypeSpec hello = TypeSpec.classBuilder("HelloWorld").addMethod(beyond).build();
        JavaFile example = JavaFile.builder("com.example.helloworld", hello).addStaticImport(hoverboard, "createNimbus").addStaticImport(namedBoards, "*").addStaticImport(java.util.Collections.class, "*").build();
        com.google.common.truth.Truth.assertThat(example.toString()).isEqualTo(("" + ((((((((((((((((((("package com.example.helloworld;\n" + "\n") + "import static com.mattel.Hoverboard.Boards.*;\n") + "import static com.mattel.Hoverboard.createNimbus;\n") + "import static java.util.Collections.*;\n") + "\n") + "import com.mattel.Hoverboard;\n") + "import java.util.ArrayList;\n") + "import java.util.List;\n") + "\n") + "class HelloWorld {\n") + "  List<Hoverboard> beyond() {\n") + "    List<Hoverboard> result = new ArrayList<>();\n") + "    result.add(createNimbus(2000));\n") + "    result.add(createNimbus(\"2001\"));\n") + "    result.add(createNimbus(THUNDERBOLT));\n") + "    sort(result);\n") + "    return result.isEmpty() ? emptyList() : result;\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticMixed() {
        JavaFile source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("assert $1T.valueOf(\"BLOCKED\") == $1T.BLOCKED", Thread.State.class).addStatement("$T.gc()", System.class).addStatement("$1T.out.println($1T.nanoTime())", System.class).build()).addMethod(MethodSpec.constructorBuilder().addParameter(Thread.State[].class, "states").varargs(true).build()).build()).addStaticImport(Thread.State.BLOCKED).addStaticImport(System.class, "*").addStaticImport(Thread.State.class, "valueOf").build();
        com.google.common.truth.Truth.assertThat(source.toString()).isEqualTo(("" + ((((((((((((((((("package com.squareup.tacos;\n" + "\n") + "import static java.lang.System.*;\n") + "import static java.lang.Thread.State.BLOCKED;\n") + "import static java.lang.Thread.State.valueOf;\n") + "\n") + "import java.lang.Thread;\n") + "\n") + "class Taco {\n") + "  static {\n") + "    assert valueOf(\"BLOCKED\") == BLOCKED;\n") + "    gc();\n") + "    out.println(nanoTime());\n") + "  }\n") + "\n") + "  Taco(Thread.State... states) {\n") + "  }\n") + "}\n")));
    }

    @org.junit.Ignore(value = "addStaticImport doesn't support members with $L")
    @org.junit.Test
    public void importStaticDynamic() {
        JavaFile source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addMethod(MethodSpec.methodBuilder("main").addStatement("$T.$L.println($S)", System.class, "out", "hello").build()).build()).addStaticImport(System.class, "out").build();
        com.google.common.truth.Truth.assertThat(source.toString()).isEqualTo(("" + (((((((("package com.squareup.tacos;\n" + "\n") + "import static java.lang.System.out;\n") + "\n") + "class Taco {\n") + "  void main() {\n") + "    out.println(\"hello\");\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticNone() {
        com.google.common.truth.Truth.assertThat(JavaFile.builder("readme", importStaticTypeSpec("Util")).build().toString()).isEqualTo(("" + (((((((((("package readme;\n" + "\n") + "import java.lang.System;\n") + "import java.util.concurrent.TimeUnit;\n") + "\n") + "class Util {\n") + "  public static long minutesToSeconds(long minutes) {\n") + "    System.gc();\n") + "    return TimeUnit.SECONDS.convert(minutes, TimeUnit.MINUTES);\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticOnce() {
        com.google.common.truth.Truth.assertThat(JavaFile.builder("readme", importStaticTypeSpec("Util")).addStaticImport(java.util.concurrent.TimeUnit.SECONDS).build().toString()).isEqualTo(("" + (((((((((((("package readme;\n" + "\n") + "import static java.util.concurrent.TimeUnit.SECONDS;\n") + "\n") + "import java.lang.System;\n") + "import java.util.concurrent.TimeUnit;\n") + "\n") + "class Util {\n") + "  public static long minutesToSeconds(long minutes) {\n") + "    System.gc();\n") + "    return SECONDS.convert(minutes, TimeUnit.MINUTES);\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticTwice() {
        com.google.common.truth.Truth.assertThat(JavaFile.builder("readme", importStaticTypeSpec("Util")).addStaticImport(java.util.concurrent.TimeUnit.SECONDS).addStaticImport(java.util.concurrent.TimeUnit.MINUTES).build().toString()).isEqualTo(("" + (((((((((((("package readme;\n" + "\n") + "import static java.util.concurrent.TimeUnit.MINUTES;\n") + "import static java.util.concurrent.TimeUnit.SECONDS;\n") + "\n") + "import java.lang.System;\n") + "\n") + "class Util {\n") + "  public static long minutesToSeconds(long minutes) {\n") + "    System.gc();\n") + "    return SECONDS.convert(minutes, MINUTES);\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticUsingWildcards() {
        com.google.common.truth.Truth.assertThat(JavaFile.builder("readme", importStaticTypeSpec("Util")).addStaticImport(java.util.concurrent.TimeUnit.class, "*").addStaticImport(System.class, "*").build().toString()).isEqualTo(("" + (((((((((("package readme;\n" + "\n") + "import static java.lang.System.*;\n") + "import static java.util.concurrent.TimeUnit.*;\n") + "\n") + "class Util {\n") + "  public static long minutesToSeconds(long minutes) {\n") + "    gc();\n") + "    return SECONDS.convert(minutes, MINUTES);\n") + "  }\n") + "}\n")));
    }

    private TypeSpec importStaticTypeSpec(String name) {
        MethodSpec method = MethodSpec.methodBuilder("minutesToSeconds").addModifiers(javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.STATIC).returns(long.class).addParameter(long.class, "minutes").addStatement("$T.gc()", System.class).addStatement("return $1T.SECONDS.convert(minutes, $1T.MINUTES)", java.util.concurrent.TimeUnit.class).build();
        return TypeSpec.classBuilder(name).addMethod(method).build();
    }

    @org.junit.Test
    public void noImports() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((("package com.squareup.tacos;\n" + "\n") + "class Taco {\n") + "}\n")));
    }

    @org.junit.Test
    public void singleImport() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addField(java.util.Date.class, "madeFreshDate").build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((("package com.squareup.tacos;\n" + "\n") + "import java.util.Date;\n") + "\n") + "class Taco {\n") + "  Date madeFreshDate;\n") + "}\n")));
    }

    @org.junit.Test
    public void conflictingImports() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addField(java.util.Date.class, "madeFreshDate").addField(ClassName.get("java.sql", "Date"), "madeFreshDatabaseDate").build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((("package com.squareup.tacos;\n" + "\n") + "import java.util.Date;\n") + "\n") + "class Taco {\n") + "  Date madeFreshDate;\n") + "\n") + "  java.sql.Date madeFreshDatabaseDate;\n") + "}\n")));
    }

    @org.junit.Test
    public void skipJavaLangImportsWithConflictingClassLast() throws Exception {
        // Whatever is used first wins! In this case the Float in java.lang is imported.
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addField(ClassName.get("java.lang", "Float"), "litres").addField(ClassName.get("com.squareup.soda", "Float"), "beverage").build()).skipJavaLangImports(true).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((("package com.squareup.tacos;\n" + "\n") + "class Taco {\n") + "  Float litres;\n") + "\n") + "  com.squareup.soda.Float beverage;\n") + "}\n")));
    }

    @org.junit.Test
    public void skipJavaLangImportsWithConflictingClassFirst() throws Exception {
        // Whatever is used first wins! In this case the Float in com.squareup.soda is imported.
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addField(ClassName.get("com.squareup.soda", "Float"), "beverage").addField(ClassName.get("java.lang", "Float"), "litres").build()).skipJavaLangImports(true).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((("package com.squareup.tacos;\n" + "\n") + "import com.squareup.soda.Float;\n") + "\n") + "class Taco {\n") + "  Float beverage;\n") + "\n") + "  java.lang.Float litres;\n") + "}\n")));
    }

    @org.junit.Test
    public void conflictingParentName() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("A").addType(TypeSpec.classBuilder("B").addType(TypeSpec.classBuilder("Twin").build()).addType(TypeSpec.classBuilder("C").addField(ClassName.get("com.squareup.tacos", "A", "Twin", "D"), "d").build()).build()).addType(TypeSpec.classBuilder("Twin").addType(TypeSpec.classBuilder("D").build()).build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((((((((((("package com.squareup.tacos;\n" + "\n") + "class A {\n") + "  class B {\n") + "    class Twin {\n") + "    }\n") + "\n") + "    class C {\n") + "      A.Twin.D d;\n") + "    }\n") + "  }\n") + "\n") + "  class Twin {\n") + "    class D {\n") + "    }\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void conflictingChildName() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("A").addType(TypeSpec.classBuilder("B").addType(TypeSpec.classBuilder("C").addField(ClassName.get("com.squareup.tacos", "A", "Twin", "D"), "d").addType(TypeSpec.classBuilder("Twin").build()).build()).build()).addType(TypeSpec.classBuilder("Twin").addType(TypeSpec.classBuilder("D").build()).build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((((((((((("package com.squareup.tacos;\n" + "\n") + "class A {\n") + "  class B {\n") + "    class C {\n") + "      A.Twin.D d;\n") + "\n") + "      class Twin {\n") + "      }\n") + "    }\n") + "  }\n") + "\n") + "  class Twin {\n") + "    class D {\n") + "    }\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void conflictingNameOutOfScope() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("A").addType(TypeSpec.classBuilder("B").addType(TypeSpec.classBuilder("C").addField(ClassName.get("com.squareup.tacos", "A", "Twin", "D"), "d").addType(TypeSpec.classBuilder("Nested").addType(TypeSpec.classBuilder("Twin").build()).build()).build()).build()).addType(TypeSpec.classBuilder("Twin").addType(TypeSpec.classBuilder("D").build()).build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((((((((((((("package com.squareup.tacos;\n" + "\n") + "class A {\n") + "  class B {\n") + "    class C {\n") + "      Twin.D d;\n") + "\n") + "      class Nested {\n") + "        class Twin {\n") + "        }\n") + "      }\n") + "    }\n") + "  }\n") + "\n") + "  class Twin {\n") + "    class D {\n") + "    }\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void nestedClassAndSuperclassShareName() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").superclass(ClassName.get("com.squareup.wire", "Message")).addType(TypeSpec.classBuilder("Builder").superclass(ClassName.get("com.squareup.wire", "Message", "Builder")).build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((((((("package com.squareup.tacos;\n" + "\n") + "import com.squareup.wire.Message;\n") + "\n") + "class Taco extends Message {\n") + "  class Builder extends Message.Builder {\n") + "  }\n") + "}\n")));
    }

    /**
     * * https://github.com/square/javapoet/issues/366
     */
    @org.junit.Test
    public void annotationIsNestedClass() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("TestComponent").addAnnotation(ClassName.get("dagger", "Component")).addType(TypeSpec.classBuilder("Builder").addAnnotation(ClassName.get("dagger", "Component", "Builder")).build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((((((((("package com.squareup.tacos;\n" + "\n") + "import dagger.Component;\n") + "\n") + "@Component\n") + "class TestComponent {\n") + "  @Component.Builder\n") + "  class Builder {\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void defaultPackage() throws Exception {
        String source = JavaFile.builder("", TypeSpec.classBuilder("HelloWorld").addMethod(MethodSpec.methodBuilder("main").addModifiers(javax.lang.model.element.Modifier.PUBLIC, javax.lang.model.element.Modifier.STATIC).addParameter(String[].class, "args").addCode("$T.out.println($S);\n", System.class, "Hello World!").build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((((((("import java.lang.String;\n" + "import java.lang.System;\n") + "\n") + "class HelloWorld {\n") + "  public static void main(String[] args) {\n") + "    System.out.println(\"Hello World!\");\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void defaultPackageTypesAreNotImported() throws Exception {
        String source = JavaFile.builder("hello", TypeSpec.classBuilder("World").addSuperinterface(ClassName.get("", "Test")).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((("package hello;\n" + "\n") + "class World implements Test {\n") + "}\n")));
    }

    @org.junit.Test
    public void topOfFileComment() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").build()).addFileComment("Generated $L by JavaPoet. DO NOT EDIT!", "2015-01-13").build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((("// Generated 2015-01-13 by JavaPoet. DO NOT EDIT!\n" + "package com.squareup.tacos;\n") + "\n") + "class Taco {\n") + "}\n")));
    }

    @org.junit.Test
    public void emptyLinesInTopOfFileComment() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").build()).addFileComment("\nGENERATED FILE:\n\nDO NOT EDIT!\n").build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + (((((((("//\n" + "// GENERATED FILE:\n") + "//\n") + "// DO NOT EDIT!\n") + "//\n") + "package com.squareup.tacos;\n") + "\n") + "class Taco {\n") + "}\n")));
    }

    @org.junit.Test
    public void packageClassConflictsWithNestedClass() throws Exception {
        String source = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addField(ClassName.get("com.squareup.tacos", "A"), "a").addType(TypeSpec.classBuilder("A").build()).build()).build().toString();
        com.google.common.truth.Truth.assertThat(source).isEqualTo(("" + ((((((("package com.squareup.tacos;\n" + "\n") + "class Taco {\n") + "  com.squareup.tacos.A a;\n") + "\n") + "  class A {\n") + "  }\n") + "}\n")));
    }

    @org.junit.Test
    public void importStaticForCrazyFormatsWorks() {
        MethodSpec method = MethodSpec.methodBuilder("method").build();
        // AssertGenerator replace invocation
        String o_importStaticForCrazyFormatsWorks__4 = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("$T", Runtime.class).addStatement("$T.a()", Runtime.class).addStatement("$T.X", Runtime.class).addStatement("$T$T", Runtime.class, Runtime.class).addStatement("$T.$T", Runtime.class, Runtime.class).addStatement("$1T$1T", Runtime.class).addStatement("$1T$2L$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T$3N$1T", Runtime.class, "?", method).addStatement("$T$L", Runtime.class, "?").addStatement("$T$S", Runtime.class, "?").addStatement("$T$N", Runtime.class, method).build()).build()).addStaticImport(Runtime.class, "*").build().toString();
        // AssertGenerator add assertion
        org.junit.Assert.assertEquals(o_importStaticForCrazyFormatsWorks__4, "package com.squareup.tacos;\n\nimport static java.lang.Runtime.*;\n\nimport java.lang.Runtime;\n\nclass Taco {\n  static {\n    Runtime;\n    a();\n    X;\n    RuntimeRuntime;\n    Runtime.Runtime;\n    RuntimeRuntime;\n    Runtime?Runtime;\n    Runtime?\"?\"Runtime;\n    Runtime?\"?\"RuntimemethodRuntime;\n    Runtime?;\n    Runtime\"?\";\n    Runtimemethod;\n  }\n}\n");
    }

    /* amplification of com.squareup.javapoet.JavaFileTest#importStaticForCrazyFormatsWorks */
    @org.junit.Test(timeout = 10000)
    public void importStaticForCrazyFormatsWorks_add112959() {
        MethodSpec method = MethodSpec.methodBuilder("method").build();
        // AssertGenerator replace invocation
        String o_importStaticForCrazyFormatsWorks_add112959__4 = // MethodCallAdder
JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("$T", Runtime.class).addStatement("$T.a()", Runtime.class).addStatement("$T.X", Runtime.class).addStatement("$T$T", Runtime.class, Runtime.class).addStatement("$T.$T", Runtime.class, Runtime.class).addStatement("$1T$1T", Runtime.class).addStatement("$1T$2L$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T$3N$1T", Runtime.class, "?", method).addStatement("$T$L", Runtime.class, "?").addStatement("$T$S", Runtime.class, "?").addStatement("$T$N", Runtime.class, method).build()).build()).addStaticImport(Runtime.class, "*").build().toString();
        // AssertGenerator add assertion
        org.junit.Assert.assertEquals(o_importStaticForCrazyFormatsWorks_add112959__4, "package com.squareup.tacos;\n\nimport static java.lang.Runtime.*;\n\nimport java.lang.Runtime;\n\nclass Taco {\n  static {\n    Runtime;\n    a();\n    X;\n    RuntimeRuntime;\n    Runtime.Runtime;\n    RuntimeRuntime;\n    Runtime?Runtime;\n    Runtime?\"?\"Runtime;\n    Runtime?\"?\"RuntimemethodRuntime;\n    Runtime?;\n    Runtime\"?\";\n    Runtimemethod;\n  }\n}\n");
        // AssertGenerator replace invocation
        String o_importStaticForCrazyFormatsWorks_add112959__27 = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("$T", Runtime.class).addStatement("$T.a()", Runtime.class).addStatement("$T.X", Runtime.class).addStatement("$T$T", Runtime.class, Runtime.class).addStatement("$T.$T", Runtime.class, Runtime.class).addStatement("$1T$1T", Runtime.class).addStatement("$1T$2L$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T$3N$1T", Runtime.class, "?", method).addStatement("$T$L", Runtime.class, "?").addStatement("$T$S", Runtime.class, "?").addStatement("$T$N", Runtime.class, method).build()).build()).addStaticImport(Runtime.class, "*").build().toString();
        // AssertGenerator add assertion
        org.junit.Assert.assertEquals(o_importStaticForCrazyFormatsWorks_add112959__27, "package com.squareup.tacos;\n\nimport static java.lang.Runtime.*;\n\nimport java.lang.Runtime;\n\nclass Taco {\n  static {\n    Runtime;\n    a();\n    X;\n    RuntimeRuntime;\n    Runtime.Runtime;\n    RuntimeRuntime;\n    Runtime?Runtime;\n    Runtime?\"?\"Runtime;\n    Runtime?\"?\"RuntimemethodRuntime;\n    Runtime?;\n    Runtime\"?\";\n    Runtimemethod;\n  }\n}\n");
    }

    /* amplification of com.squareup.javapoet.JavaFileTest#importStaticForCrazyFormatsWorks */
    /* amplification of com.squareup.javapoet.JavaFileTest#importStaticForCrazyFormatsWorks_add112959 */
    @org.junit.Test(timeout = 10000)
    public void importStaticForCrazyFormatsWorks_add112959_cf112977_failAssert13() {
        // AssertGenerator generate try/catch block with fail statement
        try {
            MethodSpec method = MethodSpec.methodBuilder("method").build();
            // AssertGenerator replace invocation
            String o_importStaticForCrazyFormatsWorks_add112959__4 = // MethodCallAdder
JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("$T", Runtime.class).addStatement("$T.a()", Runtime.class).addStatement("$T.X", Runtime.class).addStatement("$T$T", Runtime.class, Runtime.class).addStatement("$T.$T", Runtime.class, Runtime.class).addStatement("$1T$1T", Runtime.class).addStatement("$1T$2L$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T$3N$1T", Runtime.class, "?", method).addStatement("$T$L", Runtime.class, "?").addStatement("$T$S", Runtime.class, "?").addStatement("$T$N", Runtime.class, method).build()).build()).addStaticImport(Runtime.class, "*").build().toString();
            // MethodAssertGenerator build local variable
            Object o_6_0 = o_importStaticForCrazyFormatsWorks_add112959__4;
            // AssertGenerator replace invocation
            String o_importStaticForCrazyFormatsWorks_add112959__27 = JavaFile.builder("com.squareup.tacos", TypeSpec.classBuilder("Taco").addStaticBlock(CodeBlock.builder().addStatement("$T", Runtime.class).addStatement("$T.a()", Runtime.class).addStatement("$T.X", Runtime.class).addStatement("$T$T", Runtime.class, Runtime.class).addStatement("$T.$T", Runtime.class, Runtime.class).addStatement("$1T$1T", Runtime.class).addStatement("$1T$2L$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T", Runtime.class, "?").addStatement("$1T$2L$2S$1T$3N$1T", Runtime.class, "?", method).addStatement("$T$L", Runtime.class, "?").addStatement("$T$S", Runtime.class, "?").addStatement("$T$N", Runtime.class, method).build()).build()).addStaticImport(Runtime.class, "*").build().toString();
            // StatementAdderOnAssert create null value
            TypeSpec vc_28964 = (TypeSpec)null;
            // StatementAdderOnAssert create literal from method
            String String_vc_1063 = "package com.squareup.tacos;\n\nimport static java.lang.Runtime.*;\n\nimport java.lang.Runtime;\n\nclass Taco {\n  static {\n    Runtime;\n    a();\n    X;\n    RuntimeRuntime;\n    Runtime.Runtime;\n    RuntimeRuntime;\n    Runtime?Runtime;\n    Runtime?\"?\"Runtime;\n    Runtime?\"?\"RuntimemethodRuntime;\n    Runtime?;\n    Runtime\"?\";\n    Runtimemethod;\n  }\n}\n";
            // StatementAdderOnAssert create null value
            JavaFile vc_28960 = (JavaFile)null;
            // StatementAdderMethod cloned existing statement
            vc_28960.builder(String_vc_1063, vc_28964);
            // MethodAssertGenerator build local variable
            Object o_18_0 = o_importStaticForCrazyFormatsWorks_add112959__27;
            org.junit.Assert.fail("importStaticForCrazyFormatsWorks_add112959_cf112977 should have thrown NullPointerException");
        } catch (NullPointerException eee) {
        }
    }
}

