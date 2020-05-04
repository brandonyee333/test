/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Marcellus Tavares
 */
public class AccountDataExporterTest {

	@Test
	public void testExport() throws Exception {
		ReportHttp reportHttp = Mockito.mock(ReportHttp.class);

		Mockito.when(
			reportHttp.getAccountsJSONObject(Matchers.eq("0"))
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("accounts-report.json", this)
		);

		Mockito.when(
			reportHttp.getAccountsJSONObject(Matchers.eq("388160510048870753"))
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("empty-report.json", this)
		);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		AccountDataExporter accountDataExporter = new AccountDataExporter(
			new JsonFactory(), byteArrayOutputStream, reportHttp);

		accountDataExporter.export();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"expected-accounts-export.json", this),
			byteArrayOutputStream.toString("UTF-8"), false);

		byteArrayOutputStream.close();
	}

}