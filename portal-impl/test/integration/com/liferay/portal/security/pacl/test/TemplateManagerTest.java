/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.template.TemplateContextHelper;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;
import com.liferay.portal.test.rule.PACLTestRule;

import org.apache.log4j.Level;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class TemplateManagerTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@BeforeClass
	public static void setUpClass() {
		_captureAppender = Log4JLoggerTestUtil.configureLog4JLogger(
			TemplateContextHelper.class.getName(), Level.OFF);
	}

	@AfterClass
	public static void tearDownClass() {
		_captureAppender.close();
	}

	@Test
	public void test1() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL,
			new StringTemplateResource("123.ftl", "Hello World!"), false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("Hello World!", result);
	}

	@Test
	public void test2() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL,
			new StringTemplateResource(
				"123.ftl", "<#if httpUtil??>FAIL<#else>PASS</#if>"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result);
	}

	@Test
	public void test3() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL,
			new StringTemplateResource(
				"123.ftl", "<#if !httpUtil??>PASS</#if>"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result);
	}

	@Test
	public void test4() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL,
			new StringTemplateResource(
				"123.ftl", "<#if languageUtil??>PASS</#if>"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result);
	}

	@Test
	public void test5() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL,
			new StringTemplateResource(
				"123.ftl", "<#assign sum = (5 + 6)>${sum}"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals(11, GetterUtil.getInteger(result));
	}

	@Test
	public void test6() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM,
			new StringTemplateResource("123.vm", "Hello World!"), false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("Hello World!", result);
	}

	@Test
	public void test7() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM,
			new StringTemplateResource(
				"123.vm", "#if ($httpUtil) FAIL #else PASS #end"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result.trim());
	}

	@Test
	public void test8() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM,
			new StringTemplateResource("123.vm", "#if (!$httpUtil)PASS#end"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result);
	}

	@Test
	public void test9() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM,
			new StringTemplateResource("123.vm", "#if ($languageUtil)PASS#end"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals("PASS", result);
	}

	@Test
	public void test10() throws Exception {
		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_VM,
			new StringTemplateResource("123.vm", "#set($sum = 5 + 6)$sum"),
			false);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		String result = unsyncStringWriter.toString();

		Assert.assertEquals(11, GetterUtil.getInteger(result));
	}

	private static CaptureAppender _captureAppender;

}