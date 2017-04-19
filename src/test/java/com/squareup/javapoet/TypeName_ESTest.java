/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 19 08:46:14 GMT 2017
 */

package com.squareup.javapoet;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import java.lang.reflect.Type;
import java.util.HashMap;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.UnknownTypeException;
import org.eclipse.jdt.internal.compiler.apt.model.NoTypeImpl;
import org.eclipse.jdt.internal.compiler.apt.model.PrimitiveTypeImpl;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TypeName_ESTest extends TypeName_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      PrimitiveTypeImpl primitiveTypeImpl0 = PrimitiveTypeImpl.CHAR;
      TypeName typeName0 = TypeName.get((TypeMirror) primitiveTypeImpl0);
      ArrayTypeName arrayTypeName0 = ArrayTypeName.of((TypeName) typeName0.OBJECT);
      ClassName className0 = (ClassName)TypeName.arrayComponent(arrayTypeName0);
      assertEquals("Object", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Type[] typeArray0 = new Type[6];
      HashMap<Type, TypeVariableName> hashMap0 = new HashMap<Type, TypeVariableName>(0);
      // Undeclared exception!
      try { 
        TypeName.list(typeArray0, hashMap0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // unexpected type: null
         //
         verifyException("com.squareup.javapoet.TypeName", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Class<TypeName> class0 = TypeName.class;
      TypeName typeName0 = TypeName.get((Type) class0);
      boolean boolean0 = typeName0.isPrimitive();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      NoTypeImpl noTypeImpl0 = (NoTypeImpl)NoTypeImpl.NO_TYPE_PACKAGE;
      // Undeclared exception!
      try { 
        TypeName.get((TypeMirror) noTypeImpl0);
        fail("Expecting exception: UnknownTypeException");
      
      } catch(UnknownTypeException e) {
         //
         // Unknown type: package
         //
         verifyException("javax.lang.model.util.AbstractTypeVisitor6", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TypeName typeName0 = TypeName.DOUBLE;
      String string0 = typeName0.toString();
      assertEquals("double", string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      PrimitiveTypeImpl primitiveTypeImpl0 = PrimitiveTypeImpl.CHAR;
      TypeName typeName0 = TypeName.get((TypeMirror) primitiveTypeImpl0);
      ArrayTypeName arrayTypeName0 = ArrayTypeName.of((TypeName) typeName0.OBJECT);
      boolean boolean0 = arrayTypeName0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      TypeName typeName0 = TypeName.VOID;
      TypeName typeName1 = typeName0.VOID.withoutAnnotations();
      // Undeclared exception!
      try { 
        typeName1.box();
        fail("Expecting exception: AssertionError");
      
      } catch(AssertionError e) {
         //
         // void
         //
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      TypeVariableName typeVariableName0 = TypeVariableName.get("cXG?Q3K o%a@");
      TypeName typeName0 = typeVariableName0.CHAR.unbox();
      TypeName typeName1 = typeName0.unbox();
      ClassName className0 = (ClassName)typeName1.box();
      assertEquals("Character", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Class<Boolean> class0 = Boolean.TYPE;
      TypeName typeName0 = TypeName.get((Type) class0);
      ClassName className0 = (ClassName)typeName0.VOID.box();
      assertTrue(typeName0.isPrimitive());
      assertEquals("Void", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      TypeName typeName0 = TypeName.SHORT;
      ClassName className0 = (ClassName)typeName0.box();
      assertEquals("Short", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      TypeName typeName0 = TypeName.CHAR;
      ClassName className0 = (ClassName)typeName0.OBJECT.LONG.box();
      assertEquals("Long", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      TypeName typeName0 = TypeName.INT;
      ClassName className0 = (ClassName)typeName0.box();
      assertEquals("Integer", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      TypeName typeName0 = TypeName.DOUBLE;
      ClassName className0 = (ClassName)typeName0.box();
      TypeName typeName1 = className0.unbox();
      assertSame(typeName1, typeName0);
      assertEquals("Double", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      TypeName typeName0 = TypeName.FLOAT;
      ClassName className0 = (ClassName)typeName0.box();
      assertEquals("Float", className0.simpleName());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      PrimitiveTypeImpl primitiveTypeImpl0 = PrimitiveTypeImpl.CHAR;
      TypeName typeName0 = TypeName.get((TypeMirror) primitiveTypeImpl0);
      TypeName typeName1 = typeName0.BOOLEAN.box();
      boolean boolean0 = typeName1.isBoxedPrimitive();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      PrimitiveTypeImpl primitiveTypeImpl0 = PrimitiveTypeImpl.CHAR;
      TypeName typeName0 = TypeName.get((TypeMirror) primitiveTypeImpl0);
      boolean boolean0 = typeName0.OBJECT.isBoxedPrimitive();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      PrimitiveTypeImpl primitiveTypeImpl0 = PrimitiveTypeImpl.CHAR;
      TypeName typeName0 = TypeName.get((TypeMirror) primitiveTypeImpl0);
      boolean boolean0 = typeName0.isPrimitive();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      TypeName[] typeNameArray0 = new TypeName[1];
      TypeName typeName0 = TypeName.VOID;
      typeNameArray0[0] = typeName0;
      // Undeclared exception!
      try { 
        TypeVariableName.get("6Yx>tO%T(Ei)tU||Im", typeNameArray0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // invalid bound: void
         //
         verifyException("com.squareup.javapoet.Util", e);
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Class<GenericMetadataSupport.TypeVarBoundedType> class0 = GenericMetadataSupport.TypeVarBoundedType.class;
      ClassName className0 = ClassName.get(class0);
      boolean boolean0 = className0.isAnnotated();
      assertFalse(boolean0);
  }
}
