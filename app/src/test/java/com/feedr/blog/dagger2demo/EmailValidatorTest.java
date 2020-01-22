package com.feedr.blog.dagger2demo;

import com.feedr.blog.dagger2demo.utils.EmailValidator;

import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {

    @Test
    public void testMyEmail(){
        Assert.assertTrue(EmailValidator.Companion.isValidEmail("name@email.com"));
        Assert.assertFalse(EmailValidator.Companion.isValidEmail("name@email..com"));
        Assert.assertFalse(EmailValidator.Companion.isValidEmail(""));
        Assert.assertFalse(EmailValidator.Companion.isValidEmail("@asd.erg"));
    }

    @Test
    public void testMyFalseTest(){
        Assert.assertFalse(EmailValidator.Companion.isValidEmail("@asd.erg.f"));
        Assert.assertEquals("Palak","Pa l ak".replaceAll(" ",""));
        Assert.assertNotNull("Pa");
        Assert.assertNotEquals(2,"r");
    }
}
