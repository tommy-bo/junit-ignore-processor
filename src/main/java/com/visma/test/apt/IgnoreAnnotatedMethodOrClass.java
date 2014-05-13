package com.visma.test.apt;

import com.google.common.base.Optional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.junit.Ignore;

import static java.lang.String.format;
import static javax.lang.model.element.ElementKind.METHOD;

class IgnoreAnnotatedMethodOrClass {
		
		private static final String errorMessage = "Cause missing for @Ignore [%1$s]";

		private final Element methodOrClass;

		private IgnoreAnnotatedMethodOrClass(Element methodOrClass) {
				this.methodOrClass = methodOrClass;
		}

		static Set<IgnoreAnnotatedMethodOrClass> fromRoundEnvironment(RoundEnvironment roundEnv) {
				Set<IgnoreAnnotatedMethodOrClass> ignoredElements = new HashSet<IgnoreAnnotatedMethodOrClass>();
				for (Element methodOrClass : roundEnv.getElementsAnnotatedWith(Ignore.class)) {
						ignoredElements.add(new IgnoreAnnotatedMethodOrClass(methodOrClass));
				}
				return ignoredElements;
		}

		boolean hasCauseDescription() {
				return getCauseForIgnore().isPresent();
		}

		void printErrorMessageTo(ProcessingEnvironment processingEnv) {
				String elementName = toString();
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, format(errorMessage, elementName));
		}

		@Override
		public String toString() {
				return (methodOrClass.getKind() == METHOD ? methodOrClass.getEnclosingElement() + "." : "")
								+ methodOrClass.toString();
		}

		@Override
		public int hashCode() {
				return Objects.hashCode(methodOrClass);
		}

		@Override
		public boolean equals(Object obj) {
				return obj instanceof IgnoreAnnotatedMethodOrClass
								&& Objects.equals(methodOrClass, ((IgnoreAnnotatedMethodOrClass) obj).methodOrClass);
		}

		@SuppressWarnings("unchecked")
		private Cause getCauseForIgnore() {
				Ignore ignoreAnnotation = methodOrClass.getAnnotation(Ignore.class);
				return Cause.fromNullableDescription(ignoreAnnotation.value());
		}

		static class Cause {

				private final Optional<String> causeDescription;

				private Cause(Optional<String> causeDescription) {
						this.causeDescription = causeDescription;
				}

				private static Cause fromNullableDescription(String causeDescription) {
						return new Cause(Optional.fromNullable(causeDescription));
				}
				
				private boolean isPresent() {
						return causeDescription.isPresent() && !causeDescription.get().isEmpty();
				}
		}
}
