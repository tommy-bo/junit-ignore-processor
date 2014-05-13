package com.visma.test.apt;

import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static org.truth0.Truth.ASSERT;

public class IgnoreAnnotationProcessorTest {
		
		@Test
		public void shouldCompileClassWithoutIgnoreAnnotationWithoutErrors() {
				ASSERT.about(javaSource())
								.that(JavaFileObjects.forResource("com/visma/test/apt/unittest/ClassWithoutIgnore.java"))
								.processedWith(new IgnoreAnnotationProcessor())
								.compilesWithoutError();
		}
		
		@Test
		public void shouldCompileIgnoreAnnotatedClassWithValueWithoutErrors() {
				ASSERT.about(javaSource())
								.that(JavaFileObjects.forResource("com/visma/test/apt/unittest/IgnoreOnClassWithReason.java"))
								.processedWith(new IgnoreAnnotationProcessor())
								.compilesWithoutError();
		}
		
		@Test
		public void shouldCompileIgnoreAnnotatedMethodWithValueWithoutErrors() {
				ASSERT.about(javaSource())
								.that(JavaFileObjects.forResource("com/visma/test/apt/unittest/IgnoreOnMethodWithReason.java"))
								.processedWith(new IgnoreAnnotationProcessor())
								.compilesWithoutError();
		}
		
		@Test
		public void shouldNotCompileIgnoreAnnotatedClassWithoutValue() {
				ASSERT.about(javaSource())
								.that(JavaFileObjects.forResource("com/visma/test/apt/unittest/IgnoreOnClassWithoutReason.java"))
								.processedWith(new IgnoreAnnotationProcessor())
								.failsToCompile()
								.withErrorContaining("Cause missing for @Ignore [com.visma.test.apt.unittest.IgnoreOnClassWithoutReason]");
		}
		
		@Test
		public void shouldNotCompileIgnoreAnnotatedMethodWithoutValue() {
				ASSERT.about(javaSource())
								.that(JavaFileObjects.forResource("com/visma/test/apt/unittest/IgnoreOnMethodWithoutReason.java"))
								.processedWith(new IgnoreAnnotationProcessor())
								.failsToCompile()
								.withErrorContaining("Cause missing for @Ignore [com.visma.test.apt.unittest.IgnoreOnMethodWithoutReason.someTestMethod()]");
		}
}
