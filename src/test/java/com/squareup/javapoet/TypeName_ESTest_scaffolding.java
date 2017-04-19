/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Wed Apr 19 08:46:14 GMT 2017
 */

package com.squareup.javapoet;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class TypeName_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) System.getProperties().clone();

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.squareup.javapoet.TypeName"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    System.setProperties((java.util.Properties) defaultProperties.clone());
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    System.setProperties((java.util.Properties) defaultProperties.clone());
    System.setProperty("os.name", "Linux");
    System.setProperty("java.awt.headless", "true");
    System.setProperty("user.home", "/home/spirals/danglot");
    System.setProperty("java.home", "/home/spirals/danglot/jdk1.8.0_121/jre");
    System.setProperty("user.dir", "/tmp/dspot-experiments/dataset/javapoet");
    System.setProperty("java.io.tmpdir", "/tmp");
    System.setProperty("line.separator", "\n");
    System.setProperty("java.specification.version", "1.8");
    System.setProperty("awt.toolkit", "sun.awt.X11.XToolkit");
    System.setProperty("file.encoding", "ANSI_X3.4-1968");
    System.setProperty("file.separator", "/");
    System.setProperty("java.awt.graphicsenv", "sun.awt.X11GraphicsEnvironment");
    System.setProperty("java.awt.printerjob", "sun.print.PSPrinterJob");
    System.setProperty("java.class.path", "/tmp/EvoSuite_pathingJar6320316446589730845.jar");
    System.setProperty("java.class.version", "52.0");
    System.setProperty("java.endorsed.dirs", "/home/spirals/danglot/jdk1.8.0_121/jre/lib/endorsed");
    System.setProperty("java.ext.dirs", "/home/spirals/danglot/jdk1.8.0_121/jre/lib/ext:/usr/java/packages/lib/ext");
    System.setProperty("java.library.path", "lib");
    System.setProperty("java.runtime.name", "Java(TM) SE Runtime Environment");
    System.setProperty("java.runtime.version", "1.8.0_121-b13");
    System.setProperty("java.specification.name", "Java Platform API Specification");
    System.setProperty("java.specification.vendor", "Oracle Corporation");
    System.setProperty("java.vendor", "Oracle Corporation");
    System.setProperty("java.vendor.url", "http://java.oracle.com/");
    System.setProperty("java.version", "1.8.0_121");
    System.setProperty("java.vm.info", "mixed mode");
    System.setProperty("java.vm.name", "Java HotSpot(TM) 64-Bit Server VM");
    System.setProperty("java.vm.specification.name", "Java Virtual Machine Specification");
    System.setProperty("java.vm.specification.vendor", "Oracle Corporation");
    System.setProperty("java.vm.specification.version", "1.8");
    System.setProperty("java.vm.vendor", "Oracle Corporation");
    System.setProperty("java.vm.version", "25.121-b13");
    System.setProperty("os.arch", "amd64");
    System.setProperty("os.version", "3.16.0-4-amd64");
    System.setProperty("path.separator", ":");
    System.setProperty("user.country", "US");
    System.setProperty("user.language", "en");
    System.setProperty("user.name", "danglot");
    System.setProperty("user.timezone", "Europe/Paris");
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(TypeName_ESTest_scaffolding.class.getClassLoader() ,
      "org.eclipse.jdt.internal.compiler.lookup.MethodVerifier",
      "org.eclipse.jdt.internal.compiler.lookup.BinaryTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.FalseLiteral",
      "org.eclipse.jdt.internal.compiler.ast.Receiver",
      "org.eclipse.jdt.internal.compiler.env.IBinaryTypeAnnotation",
      "org.eclipse.jdt.internal.compiler.lookup.AnnotationHolder",
      "org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext",
      "org.eclipse.jdt.internal.compiler.ast.CompoundAssignment",
      "org.eclipse.jdt.internal.compiler.lookup.ConstraintFormula",
      "org.eclipse.jdt.internal.compiler.parser.Parser$1",
      "org.eclipse.jdt.internal.compiler.lookup.MethodScope",
      "org.eclipse.jdt.internal.compiler.ast.IntLiteral",
      "org.eclipse.jdt.internal.compiler.problem.ShouldNotImplement",
      "org.eclipse.jdt.internal.compiler.ast.SuperReference",
      "org.eclipse.jdt.internal.compiler.CompilationResult$1",
      "org.eclipse.jdt.internal.compiler.ast.NameReference",
      "org.eclipse.jdt.internal.compiler.apt.model.PrimitiveTypeImpl",
      "org.eclipse.jdt.internal.compiler.lookup.MethodBinding",
      "org.eclipse.jdt.internal.compiler.util.Util$Displayable",
      "org.eclipse.jdt.internal.compiler.ast.ConstructorDeclaration",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredField",
      "org.eclipse.jdt.internal.compiler.ast.InnerInferenceHelper",
      "org.eclipse.jdt.internal.compiler.lookup.SyntheticMethodBinding",
      "org.eclipse.jdt.internal.compiler.ast.MemberValuePair$1",
      "org.eclipse.jdt.internal.compiler.CompilationResult",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemPackageBinding",
      "org.eclipse.jdt.internal.compiler.ast.TypeReference",
      "org.eclipse.jdt.internal.compiler.ast.NumberLiteral",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredLocalVariable",
      "org.eclipse.jdt.internal.compiler.lookup.ReferenceBinding$2",
      "org.eclipse.jdt.internal.compiler.lookup.ReferenceBinding$1",
      "org.eclipse.jdt.internal.compiler.impl.Constant",
      "org.eclipse.jdt.internal.compiler.lookup.ReferenceBinding$3",
      "org.eclipse.jdt.internal.compiler.ast.ForeachStatement",
      "org.eclipse.jdt.internal.compiler.AbstractAnnotationProcessorManager",
      "org.eclipse.jdt.internal.compiler.lookup.TypeBinding$1",
      "org.eclipse.jdt.internal.compiler.ast.ThisReference",
      "org.eclipse.jdt.internal.compiler.lookup.ConstraintExpressionFormula",
      "org.eclipse.jdt.internal.compiler.lookup.Scope$Substitutor",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.BaseAnnotationProcessorManager",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$BoundedType",
      "org.eclipse.jdt.internal.compiler.lookup.BlockScope",
      "org.eclipse.jdt.internal.compiler.ast.AssertStatement",
      "org.eclipse.jdt.internal.compiler.flow.FlowInfo",
      "org.eclipse.jdt.internal.compiler.ast.LongLiteral",
      "org.eclipse.jdt.internal.compiler.ast.ArrayQualifiedTypeReference",
      "org.eclipse.jdt.internal.compiler.lookup.SyntheticArgumentBinding",
      "org.eclipse.jdt.internal.compiler.lookup.ImportConflictBinding",
      "org.eclipse.jdt.internal.compiler.lookup.Binding",
      "com.squareup.javapoet.Util",
      "org.eclipse.jdt.internal.compiler.ast.BreakStatement",
      "org.eclipse.jdt.internal.compiler.lookup.ClassScope",
      "org.eclipse.jdt.internal.compiler.impl.IrritantSet",
      "org.eclipse.jdt.internal.compiler.ast.Wildcard",
      "org.eclipse.jdt.internal.compiler.parser.Scanner$VanguardScanner",
      "org.eclipse.jdt.internal.compiler.ast.ReferenceExpression",
      "org.eclipse.jdt.internal.compiler.ast.IfStatement",
      "org.eclipse.jdt.internal.compiler.parser.Parser",
      "org.eclipse.jdt.internal.compiler.lookup.LookupEnvironment",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemMethodBinding",
      "org.eclipse.jdt.internal.compiler.parser.Scanner$VanguardParser",
      "org.eclipse.jdt.internal.compiler.lookup.TypeBound",
      "org.eclipse.jdt.internal.compiler.util.SimpleLookupTable",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredBlock",
      "org.eclipse.jdt.internal.compiler.lookup.LocalTypeBinding",
      "org.eclipse.jdt.internal.compiler.codegen.CodeStream",
      "org.eclipse.jdt.internal.compiler.lookup.InferenceContext18",
      "org.eclipse.jdt.internal.compiler.lookup.Scope$MethodClashException",
      "com.squareup.javapoet.ParameterizedTypeName",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemReferenceBinding",
      "com.squareup.javapoet.TypeSpec",
      "org.eclipse.jdt.internal.compiler.classfmt.NonNullDefaultAwareTypeAnnotationWalker",
      "org.eclipse.jdt.core.compiler.CharOperation",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredMethod",
      "org.eclipse.jdt.internal.compiler.codegen.StackMapFrameCodeStream",
      "org.eclipse.jdt.internal.compiler.lookup.ParameterizedGenericMethodBinding",
      "org.eclipse.jdt.internal.compiler.ast.NullLiteral",
      "com.squareup.javapoet.AnnotationSpec",
      "org.eclipse.jdt.internal.compiler.apt.model.ElementsImpl",
      "com.squareup.javapoet.ArrayTypeName",
      "org.eclipse.jdt.internal.compiler.ast.CastExpression",
      "org.eclipse.jdt.internal.compiler.parser.Parser$1MethodVisitor",
      "org.eclipse.jdt.internal.compiler.ast.LambdaExpression$1LambdaTypeBinding",
      "org.eclipse.jdt.internal.compiler.codegen.TypeAnnotationCodeStream",
      "org.eclipse.jdt.internal.compiler.ast.QualifiedNameReference",
      "org.eclipse.jdt.internal.compiler.lookup.ConstraintExpressionFormula$1",
      "com.squareup.javapoet.ClassName",
      "org.eclipse.jdt.internal.compiler.lookup.InferenceFailureException",
      "org.eclipse.jdt.internal.compiler.problem.ProblemSeverities",
      "org.eclipse.jdt.internal.compiler.ast.AnnotationMethodDeclaration",
      "org.eclipse.jdt.internal.compiler.ast.IntersectionCastTypeReference",
      "org.eclipse.jdt.internal.compiler.lookup.UnresolvedReferenceBinding",
      "org.eclipse.jdt.internal.compiler.ast.Annotation$1LocationCollector",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredAnnotation",
      "org.eclipse.jdt.internal.compiler.ast.Statement",
      "org.eclipse.jdt.internal.compiler.lookup.ParameterizedTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.OperatorExpression",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredType",
      "org.eclipse.jdt.internal.compiler.codegen.VerificationTypeInfo",
      "org.eclipse.jdt.internal.compiler.ast.QualifiedSuperReference",
      "org.eclipse.jdt.internal.compiler.lookup.VariableBinding",
      "com.squareup.javapoet.TypeName$2",
      "org.eclipse.jdt.internal.compiler.lookup.ReductionResult",
      "org.eclipse.jdt.internal.compiler.apt.model.TypesImpl",
      "org.eclipse.jdt.internal.compiler.ClassFile",
      "org.eclipse.jdt.internal.compiler.flow.SwitchFlowContext",
      "com.squareup.javapoet.TypeName$1",
      "org.eclipse.jdt.internal.compiler.env.IDependent",
      "org.eclipse.jdt.internal.compiler.ast.CombinedBinaryExpression",
      "org.eclipse.jdt.internal.compiler.ast.CaseStatement",
      "org.eclipse.jdt.internal.compiler.lookup.AnnotationHolder$MethodHolder",
      "com.squareup.javapoet.CodeBlock$Builder",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$TypeVarBoundedType",
      "org.eclipse.jdt.internal.compiler.ast.NormalAnnotation",
      "org.eclipse.jdt.internal.compiler.ast.BranchStatement",
      "org.eclipse.jdt.internal.compiler.ast.StringLiteralConcatenation",
      "org.eclipse.jdt.internal.compiler.ast.MessageSend",
      "org.eclipse.jdt.internal.compiler.lookup.ParameterizedTypeBinding$1MentionListener",
      "org.eclipse.jdt.internal.compiler.ast.Annotation",
      "org.eclipse.jdt.internal.compiler.ast.ParameterizedSingleTypeReference",
      "org.eclipse.jdt.internal.compiler.flow.UnconditionalFlowInfo$AssertionFailedException",
      "org.eclipse.jdt.internal.compiler.ast.TypeDeclaration",
      "org.eclipse.jdt.core.compiler.IProblem",
      "org.eclipse.jdt.internal.compiler.ast.QualifiedAllocationExpression",
      "org.eclipse.jdt.internal.compiler.ast.ArrayReference",
      "org.eclipse.jdt.internal.compiler.env.ICompilationUnit",
      "org.eclipse.jdt.internal.compiler.codegen.AnnotationContext",
      "org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants",
      "org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.ConditionalExpression",
      "org.eclipse.jdt.internal.compiler.ast.ReturnStatement",
      "org.eclipse.jdt.internal.compiler.ast.DoubleLiteral",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$NotGenericReturnTypeSupport",
      "org.eclipse.jdt.internal.compiler.codegen.Label",
      "org.eclipse.jdt.internal.compiler.lookup.ReductionResult$2",
      "com.squareup.javapoet.WildcardTypeName",
      "org.eclipse.jdt.internal.compiler.lookup.ReductionResult$1",
      "org.eclipse.jdt.internal.compiler.ast.ASTNode",
      "org.eclipse.jdt.internal.compiler.problem.AbortType",
      "org.eclipse.jdt.internal.compiler.ast.MagicLiteral",
      "org.eclipse.jdt.internal.compiler.lookup.CaptureBinding",
      "org.eclipse.jdt.internal.compiler.lookup.ImportBinding",
      "org.mockito.exceptions.base.MockitoException",
      "org.eclipse.jdt.internal.compiler.problem.AbortCompilationUnit",
      "org.eclipse.jdt.internal.compiler.codegen.BranchLabel",
      "org.eclipse.jdt.internal.compiler.codegen.StackMapFrame",
      "org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier",
      "org.eclipse.jdt.internal.compiler.ast.TypeReference$AnnotationCollector",
      "org.eclipse.jdt.internal.compiler.impl.ReferenceContext",
      "org.eclipse.jdt.internal.compiler.impl.IntConstant",
      "org.eclipse.jdt.internal.compiler.ast.ParameterizedQualifiedTypeReference",
      "org.eclipse.jdt.internal.compiler.lookup.SyntheticFactoryMethodBinding",
      "org.eclipse.jdt.internal.compiler.lookup.TypeBindingVisitor",
      "org.eclipse.jdt.internal.compiler.lookup.ElementValuePair",
      "org.eclipse.jdt.internal.compiler.env.IBinaryMethod",
      "org.eclipse.jdt.internal.compiler.problem.AbortCompilation",
      "org.eclipse.jdt.internal.compiler.lookup.TypeVariableBinding",
      "com.squareup.javapoet.TypeVariableName",
      "org.eclipse.jdt.internal.compiler.ast.Clinit",
      "org.eclipse.jdt.internal.compiler.ast.UnaryExpression",
      "org.eclipse.jdt.internal.compiler.ast.ImportReference",
      "org.eclipse.jdt.internal.compiler.lookup.RawTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration$1",
      "org.eclipse.jdt.internal.compiler.lookup.CaptureBinding18",
      "org.eclipse.jdt.internal.compiler.ast.UnionTypeReference",
      "org.eclipse.jdt.internal.compiler.lookup.TypeConstants",
      "org.eclipse.jdt.internal.compiler.codegen.CaseLabel",
      "org.eclipse.jdt.internal.compiler.apt.model.AnnotationMirrorImpl",
      "org.eclipse.jdt.internal.compiler.flow.ExceptionHandlingFlowContext",
      "org.eclipse.jdt.internal.compiler.lookup.UnresolvedAnnotationBinding",
      "org.eclipse.jdt.internal.compiler.parser.TerminalTokens",
      "org.eclipse.jdt.internal.compiler.ast.Initializer",
      "org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope",
      "org.eclipse.jdt.internal.compiler.lookup.ConstraintTypeFormula",
      "org.eclipse.jdt.internal.compiler.ast.ClassLiteralAccess",
      "org.eclipse.jdt.internal.compiler.lookup.LocalVariableBinding",
      "org.eclipse.jdt.internal.compiler.lookup.MostSpecificExceptionMethodBinding",
      "org.eclipse.jdt.internal.compiler.lookup.FieldBinding",
      "org.eclipse.jdt.internal.compiler.lookup.TypeSystem",
      "org.eclipse.jdt.internal.compiler.lookup.PolymorphicMethodBinding",
      "org.eclipse.jdt.internal.compiler.ast.MemberValuePair",
      "org.eclipse.jdt.internal.compiler.flow.ConditionalFlowInfo",
      "org.eclipse.jdt.internal.compiler.flow.FlowContext",
      "org.eclipse.jdt.internal.compiler.lookup.TypeBinding",
      "org.eclipse.jdt.internal.compiler.flow.TryFlowContext",
      "org.eclipse.jdt.internal.compiler.ast.CharLiteral",
      "org.eclipse.jdt.internal.compiler.parser.CommitRollbackParser",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$TypeVariableReturnType",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemReasons",
      "org.eclipse.jdt.internal.compiler.lookup.TypeConstants$CloseMethodRecord",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.BaseProcessingEnvImpl",
      "org.eclipse.jdt.internal.compiler.lookup.ArrayBinding",
      "org.eclipse.jdt.internal.compiler.lookup.NestedTypeBinding",
      "org.eclipse.jdt.internal.compiler.util.Util",
      "org.eclipse.jdt.internal.compiler.lookup.AnnotationBinding",
      "org.eclipse.jdt.internal.compiler.ast.SingleNameReference",
      "com.squareup.javapoet.TypeName",
      "org.eclipse.jdt.internal.compiler.ast.TryStatement",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredStatement",
      "org.mockito.internal.util.reflection.GenericMetadataSupport",
      "org.eclipse.jdt.internal.compiler.impl.CompilerOptions",
      "org.eclipse.jdt.internal.compiler.ast.BinaryExpression",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.BatchProcessingEnvImpl",
      "org.eclipse.jdt.internal.compiler.flow.LoopingFlowContext",
      "org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration",
      "org.eclipse.jdt.internal.compiler.ast.StringLiteral",
      "org.eclipse.jdt.internal.compiler.ast.Invocation",
      "org.eclipse.jdt.internal.compiler.ast.InstanceOfExpression",
      "org.eclipse.jdt.internal.compiler.lookup.WildcardBinding",
      "org.eclipse.jdt.internal.compiler.ast.LambdaExpression",
      "org.eclipse.jdt.internal.compiler.ast.ArrayTypeReference",
      "org.eclipse.jdt.internal.compiler.ast.SubRoutineStatement",
      "org.eclipse.jdt.internal.compiler.lookup.AnnotatableTypeSystem",
      "org.eclipse.jdt.internal.compiler.lookup.NullTypeBinding",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.IProcessorProvider",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredUnit",
      "org.eclipse.jdt.internal.compiler.ast.FunctionalExpression",
      "org.eclipse.jdt.internal.compiler.lookup.InferenceContext",
      "org.eclipse.jdt.internal.compiler.problem.ProblemHandler",
      "org.eclipse.jdt.internal.compiler.ast.TrueLiteral",
      "org.eclipse.jdt.internal.compiler.apt.model.TypeMirrorImpl",
      "org.eclipse.jdt.internal.compiler.ast.SwitchStatement",
      "org.eclipse.jdt.internal.compiler.ast.MethodDeclaration",
      "com.squareup.javapoet.LineWrapper",
      "com.squareup.javapoet.CodeBlock",
      "org.eclipse.jdt.internal.compiler.ast.SingleTypeReference",
      "org.eclipse.jdt.internal.compiler.parser.NLSTag",
      "org.eclipse.jdt.internal.compiler.lookup.Scope",
      "org.eclipse.jdt.internal.compiler.impl.DoubleConstant",
      "org.eclipse.jdt.internal.compiler.lookup.PolyTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.AllocationExpression",
      "org.eclipse.jdt.internal.compiler.ast.ArrayInitializer",
      "org.eclipse.jdt.internal.compiler.parser.Scanner",
      "org.eclipse.jdt.core.compiler.InvalidInputException",
      "org.eclipse.jdt.internal.compiler.ast.ExplicitConstructorCall",
      "org.eclipse.jdt.internal.compiler.ast.Reference",
      "org.eclipse.jdt.internal.compiler.ast.FloatLiteral",
      "org.eclipse.jdt.internal.compiler.ast.ContainerAnnotation",
      "org.eclipse.jdt.internal.compiler.classfmt.TypeAnnotationWalker",
      "org.eclipse.jdt.internal.compiler.lookup.PackageBinding",
      "org.eclipse.jdt.internal.compiler.flow.UnconditionalFlowInfo",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.BatchAnnotationProcessorManager",
      "org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext",
      "org.eclipse.jdt.internal.compiler.ast.Literal",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.ProcessorInfo",
      "org.eclipse.jdt.internal.compiler.classfmt.TypeAnnotationWalker$1",
      "org.eclipse.jdt.internal.compiler.ast.Block",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$3",
      "org.eclipse.jdt.internal.compiler.lookup.InvocationSite",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$2",
      "org.eclipse.jdt.internal.compiler.problem.ProblemReporter",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$FromClassGenericMetadataSupport",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$1",
      "org.eclipse.jdt.internal.compiler.ast.LambdaExpression$1ShapeComputer",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$4",
      "org.eclipse.jdt.internal.compiler.ast.QualifiedThisReference",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemBinding",
      "org.eclipse.jdt.internal.compiler.lookup.SyntheticFieldBinding",
      "org.eclipse.jdt.internal.compiler.ASTVisitor",
      "org.eclipse.jdt.internal.compiler.ast.Expression",
      "org.eclipse.jdt.internal.compiler.ast.OperatorIds",
      "org.eclipse.jdt.internal.compiler.ast.FieldDeclaration",
      "org.eclipse.jdt.internal.compiler.lookup.MissingTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.ContinueStatement",
      "org.eclipse.jdt.internal.compiler.lookup.AnnotationHolder$AnnotationMethodHolder",
      "org.eclipse.jdt.internal.compiler.ast.Argument",
      "org.eclipse.jdt.internal.compiler.lookup.CatchParameterBinding",
      "org.eclipse.jdt.internal.compiler.ast.EmptyStatement",
      "org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy",
      "org.eclipse.jdt.internal.compiler.lookup.IntersectionCastTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.MarkerAnnotation",
      "org.eclipse.jdt.core.compiler.CategorizedProblem",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$FromParameterizedTypeGenericMetadataSupport",
      "org.eclipse.jdt.internal.compiler.env.IGenericMethod",
      "org.eclipse.jdt.internal.compiler.lookup.ProblemFieldBinding",
      "org.eclipse.jdt.internal.compiler.ast.SingleMemberAnnotation",
      "org.eclipse.jdt.internal.compiler.lookup.VoidTypeBinding",
      "org.eclipse.jdt.internal.compiler.ast.TypeParameter",
      "org.eclipse.jdt.internal.compiler.lookup.TypeIds",
      "org.eclipse.jdt.internal.compiler.lookup.InferenceVariable",
      "org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression",
      "org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$WildCardBoundedType",
      "org.eclipse.jdt.internal.compiler.ast.QualifiedTypeReference",
      "org.mockito.internal.util.reflection.GenericMetadataSupport$ParameterizedReturnType",
      "org.eclipse.jdt.internal.compiler.ast.FieldReference",
      "org.eclipse.jdt.internal.compiler.apt.model.Factory",
      "org.eclipse.jdt.internal.compiler.ast.Assignment",
      "org.eclipse.jdt.internal.compiler.lookup.MemberTypeBinding",
      "org.eclipse.jdt.internal.compiler.lookup.Substitution",
      "org.eclipse.jdt.internal.compiler.parser.ConflictedParser",
      "org.eclipse.jdt.internal.compiler.lookup.MethodVerifier15",
      "org.eclipse.jdt.internal.compiler.ast.LongLiteralMinValue",
      "com.squareup.javapoet.CodeWriter",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredImport",
      "org.eclipse.jdt.internal.compiler.codegen.ExceptionLabel",
      "org.eclipse.jdt.internal.compiler.ast.FakedTrackingVariable",
      "org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies$5",
      "org.eclipse.jdt.internal.compiler.apt.model.NoTypeImpl",
      "org.eclipse.jdt.internal.compiler.codegen.MultiCatchExceptionLabel",
      "org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext",
      "org.eclipse.jdt.internal.compiler.lookup.ReferenceBinding",
      "org.eclipse.jdt.internal.compiler.lookup.ParameterizedMethodBinding",
      "org.eclipse.jdt.internal.compiler.ast.ForStatement",
      "org.eclipse.jdt.internal.compiler.ast.ThrowStatement",
      "org.eclipse.jdt.internal.compiler.env.IBinaryAnnotation",
      "org.eclipse.jdt.internal.compiler.lookup.BaseTypeBinding",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredElement",
      "org.eclipse.jdt.internal.compiler.ast.LocalDeclaration",
      "org.eclipse.jdt.internal.compiler.parser.RecoveredInitializer",
      "org.eclipse.jdt.internal.compiler.parser.RecoveryScanner",
      "org.eclipse.jdt.internal.compiler.ast.AbstractVariableDeclaration",
      "org.eclipse.jdt.internal.compiler.lookup.SourceTypeCollisionException",
      "org.eclipse.jdt.internal.compiler.util.SuffixConstants",
      "org.eclipse.jdt.internal.compiler.problem.AbortMethod",
      "org.eclipse.jdt.internal.compiler.flow.InitializationFlowContext",
      "org.eclipse.jdt.internal.compiler.ast.IntLiteralMinValue",
      "org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration",
      "org.eclipse.jdt.internal.compiler.codegen.ConstantPool",
      "org.eclipse.jdt.internal.compiler.parser.Parser$1TypeVisitor"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(TypeName_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.squareup.javapoet.Util",
      "com.squareup.javapoet.ClassName",
      "com.squareup.javapoet.TypeName",
      "org.eclipse.jdt.internal.compiler.apt.model.NoTypeImpl",
      "org.eclipse.jdt.internal.compiler.lookup.Binding",
      "org.eclipse.jdt.internal.compiler.lookup.TypeConstants",
      "org.eclipse.jdt.internal.compiler.lookup.ReferenceBinding",
      "org.eclipse.jdt.internal.compiler.lookup.BaseTypeBinding",
      "org.eclipse.jdt.internal.compiler.lookup.TypeBinding",
      "org.eclipse.jdt.internal.compiler.apt.model.PrimitiveTypeImpl",
      "com.squareup.javapoet.TypeName$2",
      "com.squareup.javapoet.CodeWriter",
      "org.eclipse.jdt.internal.compiler.apt.model.Factory",
      "org.eclipse.jdt.core.compiler.CharOperation",
      "org.eclipse.jdt.internal.compiler.util.Util",
      "org.eclipse.jdt.internal.compiler.CompilationResult",
      "org.eclipse.jdt.internal.compiler.codegen.CodeStream",
      "org.eclipse.jdt.internal.compiler.ast.ASTNode",
      "org.eclipse.jdt.internal.compiler.ast.Statement",
      "org.eclipse.jdt.internal.compiler.ast.FunctionalExpression",
      "org.eclipse.jdt.internal.compiler.ast.LambdaExpression",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$1",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$2",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$3",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext$4",
      "org.eclipse.jdt.internal.compiler.ast.ExpressionContext",
      "org.eclipse.jdt.internal.compiler.ast.TypeReference",
      "org.eclipse.jdt.internal.compiler.ast.Wildcard",
      "org.eclipse.jdt.internal.compiler.ast.OperatorExpression",
      "org.eclipse.jdt.internal.compiler.impl.CompilerOptions",
      "org.eclipse.jdt.internal.compiler.ClassFile",
      "org.eclipse.jdt.internal.compiler.codegen.ConstantPool",
      "org.eclipse.jdt.internal.compiler.ast.TryStatement",
      "com.squareup.javapoet.CodeBlock",
      "org.eclipse.jdt.internal.compiler.ast.Annotation",
      "org.eclipse.jdt.internal.compiler.apt.dispatch.BatchAnnotationProcessorManager",
      "org.eclipse.jdt.internal.compiler.apt.model.ElementsImpl",
      "org.eclipse.jdt.internal.compiler.apt.model.TypesImpl",
      "org.eclipse.jdt.internal.compiler.ast.Clinit",
      "org.eclipse.jdt.internal.compiler.parser.Scanner",
      "org.eclipse.jdt.internal.compiler.ast.StringLiteralConcatenation",
      "org.eclipse.jdt.internal.compiler.flow.FlowInfo",
      "org.eclipse.jdt.internal.compiler.flow.UnconditionalFlowInfo",
      "org.eclipse.jdt.internal.compiler.ast.AbstractVariableDeclaration",
      "org.eclipse.jdt.internal.compiler.ast.FakedTrackingVariable",
      "org.eclipse.jdt.internal.compiler.ast.ExplicitConstructorCall",
      "org.eclipse.jdt.internal.compiler.ast.Argument",
      "org.eclipse.jdt.internal.compiler.ast.ForeachStatement"
    );
  }
}
