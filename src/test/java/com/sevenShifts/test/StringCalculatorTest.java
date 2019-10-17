package com.sevenShifts.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StringCalculatorTest {

	StringCalculator calTest = null;

	@BeforeClass
	public void intClassObject() throws Exception {
		calTest = new StringCalculator();

	}

	@Test
	public void assertEmptyInput() throws Exception {
		Assert.assertEquals(0, calTest.Add(""));
	}

	@Test
	public void assertSingleDigitInput() throws Exception {
		Assert.assertEquals(1, calTest.Add("1"));
	}

	@Test
	public void assertTwoDigitInput() throws Exception {
		Assert.assertEquals(3, calTest.Add("1,2"));
	}

	@Test
	public void assertNegativeOneDigitInput() throws Exception {
		try {
			calTest.Add("-1");
			Assert.fail("Negatives not allowed.");
		} catch (Exception e) {
			Assert.assertEquals("Negatives not allowed.", e.getMessage());
		}
	}

	@Test
	public void assertNegativeOneTwoDigitInput() throws Exception {
		try {
			calTest.Add("-1,-2");
			Assert.fail("Negatives not allowed.");
		} catch (Exception e) {
			Assert.assertEquals("Negatives not allowed.", e.getMessage());
		}
	}
	
	@Test
	public void assertSingleDelimitertInput() throws Exception {
		Assert.assertEquals(3, calTest.Add("//#\n1#2"));
	}
	
	@Test
	public void assertArbitraryDelimitertInput() throws Exception {
		Assert.assertEquals(3, calTest.Add("//$$$\n1$$$2"));
	}
	
	@Test
	public void assertMultipleDelimitertInput() throws Exception {
		Assert.assertEquals(15, calTest.Add("//$,@\n1$2$3@4@5"));
	}
	
	@Test
	public void assertArbitraryMultipleDelimitertInput() throws Exception {
		Assert.assertEquals(15, calTest.Add("//$$$,@@@\n1$$$2$$$3@@@4@@@5"));
	}
	
	@Test
	public void assertPlusDelimitertInput() throws Exception {
		Assert.assertEquals(15, calTest.Add("//+\n1+2+3+4+5"));
	}
	
	@Test
	public void assertInvalidDelimitertInput() throws Exception {
		Assert.assertEquals(0, calTest.Add("//\n1$2$3$4@5"));
	}
	
	@Test
	public void assertIgnoreMorethan1KDelimitertInput() throws Exception {
		Assert.assertEquals(2, calTest.Add("2,1002"));
	}
	
	
	@Test
	public void assertTwoDigitIgnoreMorethan1KDelimitertInput() throws Exception {
		Assert.assertEquals(5, calTest.Add("2,1002,3"));
	}
}
