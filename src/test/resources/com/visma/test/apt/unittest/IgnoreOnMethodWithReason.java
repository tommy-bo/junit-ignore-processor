package com.visma.test.apt.unittest;

import org.junit.Test;
import org.junit.Ignore;

public class IgnoreOnMethodWithReason {
		@Test
		@Ignore("This is a valid reason to ignore")
		public void someTestMethod() {}
}
		