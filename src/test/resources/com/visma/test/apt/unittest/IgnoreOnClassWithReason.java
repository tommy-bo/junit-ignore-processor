package com.visma.test.apt.unittest;

import org.junit.Test;
import org.junit.Ignore;

@Ignore("This is a valid reason to ignore")
public class IgnoreOnClassWithReason {
		@Test
		public void someTestMethod() {}
}
		