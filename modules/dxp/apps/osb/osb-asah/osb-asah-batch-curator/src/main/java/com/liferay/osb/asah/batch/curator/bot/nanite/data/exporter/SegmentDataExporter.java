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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.http.ReportHttp;

import java.io.OutputStream;

import java.util.Date;
import java.util.Optional;

import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class SegmentDataExporter extends BaseDataExporter {

	public SegmentDataExporter(
			Date fromDate, JsonFactory jsonFactory, OutputStream outputStream,
			ReportHttp reportHttp, Date toDate)
		throws Exception {

		super(jsonFactory, outputStream, reportHttp);

		_fromDate = DateUtil.toUTCString(
			Optional.ofNullable(
				fromDate
			).orElse(
				_defaultDate
			));

		_toDate = DateUtil.toUTCString(
			Optional.ofNullable(
				toDate
			).orElse(
				_defaultDate
			));
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String afterId) {
		return reportHttp.getSegmentsJSONObject(afterId, _fromDate, _toDate);
	}

	private static final Date _defaultDate = DateUtil.toUTCDate(
		"1970-01-01T00:00:00.000Z");

	private final String _fromDate;
	private final String _toDate;

}