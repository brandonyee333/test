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

/**
 * @author Alejo Ceballos
 */
public abstract class BaseReportDataExporter extends BaseDataExporter {

	public BaseReportDataExporter(
			Date fromDate, JsonFactory jsonFactory, OutputStream outputStream,
			ReportHttp reportHttp, Date toDate)
		throws Exception {

		super(jsonFactory, outputStream, reportHttp);

		if (fromDate != null) {
			this.fromDate = DateUtil.toUTCString(fromDate);
		}

		if (toDate != null) {
			this.toDate = DateUtil.toUTCString(toDate);
		}
	}

	protected String fromDate;
	protected String toDate;

}