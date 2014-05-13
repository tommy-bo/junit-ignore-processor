package com.visma.test.apt;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import static com.visma.test.apt.IgnoreAnnotatedMethodOrClass.fromRoundEnvironment;

@SupportedAnnotationTypes("org.junit.Ignore")
public class IgnoreAnnotationProcessor extends AbstractProcessor {

    private static final boolean WILL_NOT_CLAIM_ANNOTATIONS = false;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotationElements, RoundEnvironment roundEnv) {
        for (IgnoreAnnotatedMethodOrClass ignoredMethodOrClass : fromRoundEnvironment(roundEnv)) {
						if(!ignoredMethodOrClass.hasCauseDescription()) {
								ignoredMethodOrClass.printErrorMessageTo(processingEnv);
						}
        }
        return WILL_NOT_CLAIM_ANNOTATIONS;
    }
}
