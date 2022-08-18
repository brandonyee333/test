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

import com.liferay.osb.asah.common.json.JSONUtil;

import java.io.OutputStream;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class PageDataExporter extends BaseReportDataExporter {

	public PageDataExporter(
			Date fromDate, JsonFactory jsonFactory, OutputStream outputStream,
			Date toDate)
		throws Exception {

		super(fromDate, jsonFactory, outputStream, null, toDate);
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String afterId) {

		// TODO Implement Page data export

		return JSONUtil.put("results", new JSONArray());
	}

}