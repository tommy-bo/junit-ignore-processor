package com.visma.test.apt.unittest;

import org.junit.Test;
import org.junit.Ignore;

public class IgnoreOnMethodWithoutReason {
		@Test
		@Ignore
		public void someTestMethod() {}
}
		