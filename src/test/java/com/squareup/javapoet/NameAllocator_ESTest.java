/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 19 09:14:54 GMT 2017
 */

package com.squareup.javapoet;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.squareup.javapoet.NameAllocator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class NameAllocator_ESTest extends NameAllocator_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      NameAllocator nameAllocator0 = new NameAllocator();
      Object object0 = new Object();
      // Undeclared exception!
      try { 
        nameAllocator0.get(object0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // unknown tag: java.lang.Object@1bcdc976
         //
         verifyException("com.squareup.javapoet.NameAllocator", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      NameAllocator nameAllocator0 = new NameAllocator();
      String string0 = nameAllocator0.newName("5q6sMl%f~zoP&O");
      assertEquals("_5q6sMl_f_zoP_O", string0);
      assertNotNull(string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      NameAllocator nameAllocator0 = new NameAllocator();
      String string0 = nameAllocator0.newName(" cannot be used for both '", (Object) " cannot be used for both '");
      assertEquals("_cannot_be_used_for_both__", string0);
      assertNotNull(string0);
  }
}
