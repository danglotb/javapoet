/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 19 08:50:16 GMT 2017
 */

package com.squareup.javapoet;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.time.chrono.MockHijrahDate;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class MethodSpec_ESTest extends MethodSpec_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.constructorBuilder();
      Object[] objectArray0 = new Object[3];
      CodeBlock codeBlock0 = CodeBlock.of("", objectArray0);
      MethodSpec.Builder methodSpec_Builder1 = methodSpec_Builder0.defaultValue(codeBlock0);
      // Undeclared exception!
      try { 
        methodSpec_Builder1.defaultValue(codeBlock0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // defaultValue was already set
         //
         verifyException("com.squareup.javapoet.Util", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.constructorBuilder();
      TypeName typeName0 = TypeName.INT;
      // Undeclared exception!
      try { 
        methodSpec_Builder0.returns(typeName0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // constructor cannot have return type.
         //
         verifyException("com.squareup.javapoet.Util", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.constructorBuilder();
      MethodSpec methodSpec0 = methodSpec_Builder0.build();
      boolean boolean0 = methodSpec0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.methodBuilder("Constructor");
      MethodSpec methodSpec0 = methodSpec_Builder0.build();
      MockHijrahDate mockHijrahDate0 = new MockHijrahDate();
      boolean boolean0 = methodSpec0.equals(mockHijrahDate0);
      assertFalse(boolean0);
      assertFalse(methodSpec0.isConstructor());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.methodBuilder("Constructor");
      MethodSpec methodSpec0 = methodSpec_Builder0.build();
      methodSpec0.hashCode();
      assertFalse(methodSpec0.isConstructor());
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      MethodSpec.Builder methodSpec_Builder0 = MethodSpec.constructorBuilder();
      MethodSpec methodSpec0 = methodSpec_Builder0.build();
      methodSpec0.hashCode();
  }
}
