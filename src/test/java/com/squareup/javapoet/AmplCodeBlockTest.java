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


public final class AmplCodeBlockTest {
    @org.junit.Test
    public void equalsAndHashCode() {
        CodeBlock a = CodeBlock.builder().build();
        CodeBlock b = CodeBlock.builder().build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
        a = CodeBlock.builder().add("$L", "taco").build();
        b = CodeBlock.builder().add("$L", "taco").build();
        com.google.common.truth.Truth.assertThat(a.equals(b)).isTrue();
        com.google.common.truth.Truth.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @org.junit.Test
    public void of() {
        CodeBlock a = CodeBlock.of("$L taco", "delicious");
        com.google.common.truth.Truth.assertThat(a.toString()).isEqualTo("delicious taco");
    }

    @org.junit.Test
    public void indentCannotBeIndexed() {
        try {
            CodeBlock.builder().add("$1>", "taco").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException exp) {
            com.google.common.truth.Truth.assertThat(exp).hasMessage("$$, $>, $<, $[, $], and $W may not have an index");
        }
    }

    @org.junit.Test
    public void deindentCannotBeIndexed() {
        try {
            CodeBlock.builder().add("$1<", "taco").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException exp) {
            com.google.common.truth.Truth.assertThat(exp).hasMessage("$$, $>, $<, $[, $], and $W may not have an index");
        }
    }

    @org.junit.Test
    public void dollarSignEscapeCannotBeIndexed() {
        try {
            CodeBlock.builder().add("$1$", "taco").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException exp) {
            com.google.common.truth.Truth.assertThat(exp).hasMessage("$$, $>, $<, $[, $], and $W may not have an index");
        }
    }

    @org.junit.Test
    public void statementBeginningCannotBeIndexed() {
        try {
            CodeBlock.builder().add("$1[", "taco").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException exp) {
            com.google.common.truth.Truth.assertThat(exp).hasMessage("$$, $>, $<, $[, $], and $W may not have an index");
        }
    }

    @org.junit.Test
    public void statementEndingCannotBeIndexed() {
        try {
            CodeBlock.builder().add("$1]", "taco").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException exp) {
            com.google.common.truth.Truth.assertThat(exp).hasMessage("$$, $>, $<, $[, $], and $W may not have an index");
        }
    }

    @org.junit.Test
    public void nameFormatCanBeIndexed() {
        CodeBlock block = CodeBlock.builder().add("$1N", "taco").build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("taco");
    }

    @org.junit.Test
    public void literalFormatCanBeIndexed() {
        CodeBlock block = CodeBlock.builder().add("$1L", "taco").build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("taco");
    }

    @org.junit.Test
    public void stringFormatCanBeIndexed() {
        CodeBlock block = CodeBlock.builder().add("$1S", "taco").build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("\"taco\"");
    }

    @org.junit.Test
    public void typeFormatCanBeIndexed() {
        CodeBlock block = CodeBlock.builder().add("$1T", String.class).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("java.lang.String");
    }

    @org.junit.Test
    public void simpleNamedArgument() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("text", "taco");
        CodeBlock block = CodeBlock.builder().addNamed("$text:S", map).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("\"taco\"");
    }

    @org.junit.Test
    public void repeatedNamedArgument() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("text", "tacos");
        CodeBlock block = CodeBlock.builder().addNamed("\"I like \" + $text:S + \". Do you like \" + $text:S + \"?\"", map).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("\"I like \" + \"tacos\" + \". Do you like \" + \"tacos\" + \"?\"");
    }

    @org.junit.Test
    public void namedAndNoArgFormat() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("text", "tacos");
        CodeBlock block = CodeBlock.builder().addNamed("$>\n$text:L for $$3.50", map).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("\n  tacos for $3.50");
    }

    @org.junit.Test
    public void missingNamedArgument() {
        try {
            java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
            CodeBlock.builder().addNamed("$text:S", map).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("Missing named argument for $text");
        }
    }

    @org.junit.Test
    public void lowerCaseNamed() {
        try {
            java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("Text", "tacos");
            CodeBlock block = CodeBlock.builder().addNamed("$Text:S", map).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("argument 'Text' must start with a lowercase character");
        }
    }

    @org.junit.Test
    public void multipleNamedArguments() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("pipe", System.class);
        map.put("text", "tacos");
        CodeBlock block = CodeBlock.builder().addNamed("$pipe:T.out.println(\"Let\'s eat some $text:L\");", map).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("java.lang.System.out.println(\"Let\'s eat some tacos\");");
    }

    @org.junit.Test
    public void namedNewline() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("clazz", Integer.class);
        CodeBlock block = CodeBlock.builder().addNamed("$clazz:T\n", map).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("java.lang.Integer\n");
    }

    @org.junit.Test
    public void danglingNamed() {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        map.put("clazz", Integer.class);
        try {
            CodeBlock.builder().addNamed("$clazz:T$", map).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("dangling $ at end");
        }
    }

    @org.junit.Test
    public void indexTooHigh() {
        try {
            CodeBlock.builder().add("$2T", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("index 2 for '$2T' not in range (received 1 arguments)");
        }
    }

    @org.junit.Test
    public void indexIsZero() {
        try {
            CodeBlock.builder().add("$0T", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("index 0 for '$0T' not in range (received 1 arguments)");
        }
    }

    @org.junit.Test
    public void indexIsNegative() {
        try {
            CodeBlock.builder().add("$-1T", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("invalid format string: '$-1T'");
        }
    }

    @org.junit.Test
    public void indexWithoutFormatType() {
        try {
            CodeBlock.builder().add("$1", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("dangling format characters in '$1'");
        }
    }

    @org.junit.Test
    public void indexWithoutFormatTypeNotAtStringEnd() {
        try {
            CodeBlock.builder().add("$1 taco", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("invalid format string: '$1 taco'");
        }
    }

    @org.junit.Test
    public void indexButNoArguments() {
        try {
            CodeBlock.builder().add("$1T").build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("index 1 for '$1T' not in range (received 0 arguments)");
        }
    }

    @org.junit.Test
    public void formatIndicatorAlone() {
        try {
            CodeBlock.builder().add("$", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("dangling format characters in '$'");
        }
    }

    @org.junit.Test
    public void formatIndicatorWithoutIndexOrFormatType() {
        try {
            CodeBlock.builder().add("$ tacoString", String.class).build();
            org.junit.Assert.fail();
        } catch (IllegalArgumentException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("invalid format string: '$ tacoString'");
        }
    }

    @org.junit.Test
    public void sameIndexCanBeUsedWithDifferentFormats() {
        CodeBlock block = CodeBlock.builder().add("$1T.out.println($1S)", ClassName.get(System.class)).build();
        com.google.common.truth.Truth.assertThat(block.toString()).isEqualTo("java.lang.System.out.println(\"java.lang.System\")");
    }

    @org.junit.Test
    public void tooManyStatementEnters() {
        CodeBlock codeBlock = CodeBlock.builder().add("$[$[").build();
        try {
            // We can't report this error until rendering type because code blocks might be composed.
            codeBlock.toString();
            org.junit.Assert.fail();
        } catch (IllegalStateException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("statement enter $[ followed by statement enter $[");
        }
    }

    @org.junit.Test
    public void statementExitWithoutStatementEnter() {
        CodeBlock codeBlock = CodeBlock.builder().add("$]").build();
        try {
            // We can't report this error until rendering type because code blocks might be composed.
            codeBlock.toString();
            org.junit.Assert.fail();
        } catch (IllegalStateException expected) {
            com.google.common.truth.Truth.assertThat(expected).hasMessage("statement exit $] has no matching statement enter $[");
        }
    }
}

