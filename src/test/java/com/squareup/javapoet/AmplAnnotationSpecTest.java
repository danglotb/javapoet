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


public final class AmplAnnotationSpecTest {
    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface AnnotationA {    }

    @java.lang.annotation.Inherited
    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface AnnotationB {    }

    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface AnnotationC {
        String value();
    }

    public enum Breakfast {
WAFFLES, PANCAKES;
        public String toString() {
            return (name()) + " with cherries!";
        }
    }

    @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface HasDefaultsAnnotation {
        byte a() default 5;

        short b() default 6;

        int c() default 7;

        long d() default 8;

        float e() default 9.0F;

        double f() default 10.0;

        char[] g() default { 0 , 51966 , 'z' , '€', 'ℕ', '"' , '\'' , '\t' , '\n' };

        boolean h() default true;

        Breakfast i() default Breakfast.WAFFLES;

        AnnotationA j() default @AnnotationA
        ;

        String k() default "maple";

        Class<? extends java.lang.annotation.Annotation> l() default AnnotationB.class;

        int[] m() default { 1 , 2 , 3 };

        Breakfast[] n() default { Breakfast.WAFFLES , Breakfast.PANCAKES };

        Breakfast o();

        int p();

        AnnotationC q() default @AnnotationC(value = "foo")
        ;

        Class<? extends Number>[] r() default { Byte.class , Short.class , Integer.class , Long.class };
    }

    // empty
    @HasDefaultsAnnotation(o = Breakfast.PANCAKES, p = 1701, f = 11.1, m = { 9 , 8 , 1 }, l = Override.class, j = @AnnotationA
    , q = @AnnotationC(value = "bar")
    , r = { Float.class , Double.class })
    public class IsAnnotated {    }

    @org.junit.Rule
    public final com.google.testing.compile.CompilationRule compilation = new com.google.testing.compile.CompilationRule();

    @org.junit.Test
    public void equalsAndHashCode() {
        AnnotationSpec a = AnnotationSpec.builder(AnnotationC.class).build();
        AnnotationSpec b = AnnotationSpec.builder(AnnotationC.class).build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
        a = AnnotationSpec.builder(AnnotationC.class).addMember("value", "$S", "123").build();
        b = AnnotationSpec.builder(AnnotationC.class).addMember("value", "$S", "123").build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @org.junit.Test
    public void defaultAnnotation() {
        String name = IsAnnotated.class.getCanonicalName();
        javax.lang.model.element.TypeElement element = compilation.getElements().getTypeElement(name);
        AnnotationSpec annotation = AnnotationSpec.get(element.getAnnotationMirrors().get(0));
        TypeSpec taco = TypeSpec.classBuilder("Taco").addAnnotation(annotation).build();
        com.google.common.truth.Truth.assertThat(toString(taco)).isEqualTo(("" + ((((((((((((((((((((((((("package com.squareup.tacos;\n" + "\n") + "import com.squareup.javapoet.AmplAnnotationSpecTest;\n") + "import java.lang.Double;\n") + "import java.lang.Float;\n") + "import java.lang.Override;\n") + "\n") + "@AmplAnnotationSpecTest.HasDefaultsAnnotation(\n") + "    o = AmplAnnotationSpecTest.Breakfast.PANCAKES,\n") + "    p = 1701,\n") + "    f = 11.1,\n") + "    m = {\n") + "        9,\n") + "        8,\n") + "        1\n") + "    },\n") + "    l = Override.class,\n") + "    j = @AmplAnnotationSpecTest.AnnotationA,\n") + "    q = @AmplAnnotationSpecTest.AnnotationC(\"bar\"),\n") + "    r = {\n") + "        Float.class,\n") + "        Double.class\n") + "    }\n") + ")\n") + "class Taco {\n") + "}\n")));
    }

    @org.junit.Test
    public void defaultAnnotationWithImport() {
        String name = IsAnnotated.class.getCanonicalName();
        javax.lang.model.element.TypeElement element = compilation.getElements().getTypeElement(name);
        AnnotationSpec annotation = AnnotationSpec.get(element.getAnnotationMirrors().get(0));
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(IsAnnotated.class.getSimpleName());
        typeBuilder.addAnnotation(annotation);
        JavaFile file = JavaFile.builder("com.squareup.javapoet", typeBuilder.build()).build();
        com.google.common.truth.Truth.assertThat(file.toString()).isEqualTo(("package com.squareup.javapoet;\n" + ((((((((((((((((((((((("\n" + "import java.lang.Double;\n") + "import java.lang.Float;\n") + "import java.lang.Override;\n") + "\n") + "@AmplAnnotationSpecTest.HasDefaultsAnnotation(\n") + "    o = AmplAnnotationSpecTest.Breakfast.PANCAKES,\n") + "    p = 1701,\n") + "    f = 11.1,\n") + "    m = {\n") + "        9,\n") + "        8,\n") + "        1\n") + "    },\n") + "    l = Override.class,\n") + "    j = @AmplAnnotationSpecTest.AnnotationA,\n") + "    q = @AmplAnnotationSpecTest.AnnotationC(\"bar\"),\n") + "    r = {\n") + "        Float.class,\n") + "        Double.class\n") + "    }\n") + ")\n") + "class IsAnnotated {\n") + "}\n")));
    }

    @org.junit.Test
    public void emptyArray() {
        AnnotationSpec.Builder builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
        builder.addMember("n", "$L", "{}");
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = {}" + ")")));
        builder.addMember("m", "$L", "{}");
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = {}, m = {}" + ")")));
    }

    @org.junit.Test
    public void dynamicArrayOfEnumConstants() {
        AnnotationSpec.Builder builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
        builder.addMember("n", "$T.$L", Breakfast.class, Breakfast.PANCAKES.name());
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES" + ")")));
        // builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
        builder.addMember("n", "$T.$L", Breakfast.class, Breakfast.WAFFLES.name());
        builder.addMember("n", "$T.$L", Breakfast.class, Breakfast.PANCAKES.name());
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + (((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + "})")));
        builder = builder.build().toBuilder();// idempotent
        
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + (((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + "})")));
        builder.addMember("n", "$T.$L", Breakfast.class, Breakfast.WAFFLES.name());
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ((((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + "})")));
    }

    @org.junit.Test
    public void defaultAnnotationToBuilder() {
        String name = IsAnnotated.class.getCanonicalName();
        javax.lang.model.element.TypeElement element = compilation.getElements().getTypeElement(name);
        AnnotationSpec.Builder builder = AnnotationSpec.get(element.getAnnotationMirrors().get(0)).toBuilder();
        builder.addMember("m", "$L", 123);
        com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + (((((((("o = com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES" + ", p = 1701") + ", f = 11.1") + ", m = {9, 8, 1, 123}") + ", l = java.lang.Override.class") + ", j = @com.squareup.javapoet.AmplAnnotationSpecTest.AnnotationA") + ", q = @com.squareup.javapoet.AmplAnnotationSpecTest.AnnotationC(\"bar\")") + ", r = {java.lang.Float.class, java.lang.Double.class}") + ")")));
    }

    @org.junit.Test
    public void reflectAnnotation() {
        HasDefaultsAnnotation annotation = IsAnnotated.class.getAnnotation(HasDefaultsAnnotation.class);
        AnnotationSpec spec = AnnotationSpec.get(annotation);
        TypeSpec taco = TypeSpec.classBuilder("Taco").addAnnotation(spec).build();
        com.google.common.truth.Truth.assertThat(toString(taco)).isEqualTo(("" + (((((((((((((((((((((((("package com.squareup.tacos;\n" + "\n") + "import com.squareup.javapoet.AmplAnnotationSpecTest;\n") + "import java.lang.Double;\n") + "import java.lang.Float;\n") + "import java.lang.Override;\n") + "\n") + "@AmplAnnotationSpecTest.HasDefaultsAnnotation(\n") + "    f = 11.1,\n") + "    l = Override.class,\n") + "    m = {\n") + "        9,\n") + "        8,\n") + "        1\n") + "    },\n") + "    o = AmplAnnotationSpecTest.Breakfast.PANCAKES,\n") + "    p = 1701,\n") + "    q = @AmplAnnotationSpecTest.AnnotationC(\"bar\"),\n") + "    r = {\n") + "        Float.class,\n") + "        Double.class\n") + "    }\n") + ")\n") + "class Taco {\n") + "}\n")));
    }

    @org.junit.Test
    public void reflectAnnotationWithDefaults() {
        HasDefaultsAnnotation annotation = IsAnnotated.class.getAnnotation(HasDefaultsAnnotation.class);
        AnnotationSpec spec = AnnotationSpec.get(annotation, true);
        TypeSpec taco = TypeSpec.classBuilder("Taco").addAnnotation(spec).build();
        com.google.common.truth.Truth.assertThat(toString(taco)).isEqualTo(("" + (((((((((((((((((((((((((((((((((((((((((((((((("package com.squareup.tacos;\n" + "\n") + "import com.squareup.javapoet.AmplAnnotationSpecTest;\n") + "import java.lang.Double;\n") + "import java.lang.Float;\n") + "import java.lang.Override;\n") + "\n") + "@AmplAnnotationSpecTest.HasDefaultsAnnotation(\n") + "    a = 5,\n") + "    b = 6,\n") + "    c = 7,\n") + "    d = 8,\n") + "    e = 9.0f,\n") + "    f = 11.1,\n") + "    g = {\n") + "        \'\\u0000\',\n") + "        \'\ucafe\',\n") + "        \'z\',\n") + "        \'\u20ac\',\n") + "        \'\u2115\',\n") + "        \'\"\',\n") + "        \'\\\'\',\n") + "        \'\\t\',\n") + "        \'\\n\'\n") + "    },\n") + "    h = true,\n") + "    i = AmplAnnotationSpecTest.Breakfast.WAFFLES,\n") + "    j = @AmplAnnotationSpecTest.AnnotationA,\n") + "    k = \"maple\",\n") + "    l = Override.class,\n") + "    m = {\n") + "        9,\n") + "        8,\n") + "        1\n") + "    },\n") + "    n = {\n") + "        AmplAnnotationSpecTest.Breakfast.WAFFLES,\n") + "        AmplAnnotationSpecTest.Breakfast.PANCAKES\n") + "    },\n") + "    o = AmplAnnotationSpecTest.Breakfast.PANCAKES,\n") + "    p = 1701,\n") + "    q = @AmplAnnotationSpecTest.AnnotationC(\"bar\"),\n") + "    r = {\n") + "        Float.class,\n") + "        Double.class\n") + "    }\n") + ")\n") + "class Taco {\n") + "}\n")));
    }

    private String toString(TypeSpec typeSpec) {
        return JavaFile.builder("com.squareup.tacos", typeSpec).build().toString();
    }

    /* amplification of com.squareup.javapoet.AmplAnnotationSpecTest#dynamicArrayOfEnumConstants */
    /* amplification of com.squareup.javapoet.AmplAnnotationSpecTest#dynamicArrayOfEnumConstants_add8 */
    @org.junit.Test(timeout = 10000)
    public void dynamicArrayOfEnumConstants_add8_cf735_failAssert36() {
        // AssertGenerator generate try/catch block with fail statement
        try {
            AnnotationSpec.Builder builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_dynamicArrayOfEnumConstants_add8__3 = builder.addMember("n", "$T.$L", AmplAnnotationSpecTest.Breakfast.class, AmplAnnotationSpecTest.Breakfast.PANCAKES.name());
            // MethodAssertGenerator build local variable
            Object o_5_0 = o_dynamicArrayOfEnumConstants_add8__3.equals(builder);
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES" + ")")));
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_dynamicArrayOfEnumConstants_add8__9 = // builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
builder.addMember("n", "$T.$L", AmplAnnotationSpecTest.Breakfast.class, AmplAnnotationSpecTest.Breakfast.WAFFLES.name());
            // MethodAssertGenerator build local variable
            Object o_12_0 = o_dynamicArrayOfEnumConstants_add8__9.equals(builder);
            // MethodAssertGenerator build local variable
            Object o_14_0 = o_dynamicArrayOfEnumConstants_add8__9.equals(o_dynamicArrayOfEnumConstants_add8__3);
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_dynamicArrayOfEnumConstants_add8__12 = builder.addMember("n", "$T.$L", AmplAnnotationSpecTest.Breakfast.class, AmplAnnotationSpecTest.Breakfast.PANCAKES.name());
            // MethodAssertGenerator build local variable
            Object o_18_0 = o_dynamicArrayOfEnumConstants_add8__12.equals(o_dynamicArrayOfEnumConstants_add8__3);
            // MethodAssertGenerator build local variable
            Object o_20_0 = o_dynamicArrayOfEnumConstants_add8__12.equals(o_dynamicArrayOfEnumConstants_add8__9);
            // MethodAssertGenerator build local variable
            Object o_22_0 = o_dynamicArrayOfEnumConstants_add8__12.equals(builder);
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + (((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + "})")));
            builder = builder.build().toBuilder();// idempotent
            
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + (((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + "})")));
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_dynamicArrayOfEnumConstants_add8__26 = builder.addMember("n", "$T.$L", AmplAnnotationSpecTest.Breakfast.class, AmplAnnotationSpecTest.Breakfast.WAFFLES.name());
            // StatementAdderOnAssert create null value
            ClassName vc_559 = (ClassName)null;
            // StatementAdderOnAssert create null value
            AnnotationSpec vc_557 = (AnnotationSpec)null;
            // StatementAdderMethod cloned existing statement
            vc_557.builder(vc_559);
            // MethodAssertGenerator build local variable
            Object o_42_0 = o_dynamicArrayOfEnumConstants_add8__26.equals(builder);
            // MethodCallAdder
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ((((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + "})")));
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ((((("n = {" + "com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.PANCAKES") + ", com.squareup.javapoet.AmplAnnotationSpecTest.Breakfast.WAFFLES") + "})")));
            org.junit.Assert.fail("dynamicArrayOfEnumConstants_add8_cf735 should have thrown NullPointerException");
        } catch (NullPointerException eee) {
        }
    }

    /* amplification of com.squareup.javapoet.AmplAnnotationSpecTest#emptyArray */
    /* amplification of com.squareup.javapoet.AmplAnnotationSpecTest#emptyArray_add2697 */
    @org.junit.Test(timeout = 10000)
    public void emptyArray_add2697_cf2850_failAssert87() {
        // AssertGenerator generate try/catch block with fail statement
        try {
            AnnotationSpec.Builder builder = AnnotationSpec.builder(HasDefaultsAnnotation.class);
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_emptyArray_add2697__3 = builder.addMember("n", "$L", "{}");
            // MethodAssertGenerator build local variable
            Object o_5_0 = o_emptyArray_add2697__3.equals(builder);
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = {}" + ")")));
            // AssertGenerator replace invocation
            AnnotationSpec.Builder o_emptyArray_add2697__8 = builder.addMember("m", "$L", "{}");
            // MethodAssertGenerator build local variable
            Object o_12_0 = o_emptyArray_add2697__8.equals(builder);
            // StatementAdderOnAssert create null value
            ClassName vc_631 = (ClassName)null;
            // StatementAdderOnAssert create null value
            AnnotationSpec vc_629 = (AnnotationSpec)null;
            // StatementAdderMethod cloned existing statement
            vc_629.builder(vc_631);
            // MethodAssertGenerator build local variable
            Object o_20_0 = o_emptyArray_add2697__8.equals(o_emptyArray_add2697__3);
            // MethodCallAdder
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = {}, m = {}" + ")")));
            com.google.common.truth.Truth.assertThat(builder.build().toString()).isEqualTo(("@com.squareup.javapoet.AmplAnnotationSpecTest.HasDefaultsAnnotation(" + ("n = {}, m = {}" + ")")));
            org.junit.Assert.fail("emptyArray_add2697_cf2850 should have thrown NullPointerException");
        } catch (NullPointerException eee) {
        }
    }
}

