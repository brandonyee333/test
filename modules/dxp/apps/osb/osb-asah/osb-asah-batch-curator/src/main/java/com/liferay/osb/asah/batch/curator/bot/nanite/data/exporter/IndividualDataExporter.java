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

import java.io.OutputStream;

import java.util.Date;

import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class IndividualDataExporter extends BaseReportDataExporter {

	public IndividualDataExporter(
			Date fromDate, JsonFactory jsonFactory, OutputStream outputStream,
			ReportHttp reportHttp, Date toDate)
		throws Exception {

		super(fromDate, jsonFactory, outputStream, reportHttp, toDate);
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String afterId) {
		return reportHttp.getIndividualsJSONObject(afterId, fromDate, toDate);
	}

}