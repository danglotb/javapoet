/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 19 08:48:11 GMT 2017
 */

package com.squareup.javapoet;

import org.junit.Test;
import static org.junit.Assert.*;
import com.squareup.javapoet.CodeWriter;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import java.lang.reflect.Type;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ParameterizedTypeName_ESTest extends ParameterizedTypeName_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Class<TypeVariableName> class0 = TypeVariableName.class;
      Type[] typeArray0 = new Type[2];
      typeArray0[0] = (Type) class0;
      typeArray0[1] = (Type) class0;
      ParameterizedTypeName parameterizedTypeName0 = ParameterizedTypeName.get(class0, typeArray0);
      StringBuffer stringBuffer0 = new StringBuffer(4032);
      CodeWriter codeWriter0 = new CodeWriter(stringBuffer0);
      parameterizedTypeName0.emit(codeWriter0);
      assertEquals(118, stringBuffer0.length());
      assertEquals("com.squareup.javapoet.TypeVariableName<com.squareup.javapoet.TypeVariableName, com.squareup.javapoet.TypeVariableName>", stringBuffer0.toString());
  }
}
