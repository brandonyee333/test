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

package com.liferay.osb.asah.dxp.extractor.dog;

import com.liferay.osb.asah.dxp.extractor.client.ExtractorDXPClient;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 */
@Component
public class AuditEventDog {

	public JSONArray getAuditEventsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		Date createDateGT, int start, int end) {

		StringBuilder sb = new StringBuilder();

		sb.append("/api/jsonws/audit.auditevent/get-audit-events/company-id/");
		sb.append(companyId);
		sb.append("/user-id/0/-user-name/create-date-gt/");
		sb.append(_getCreateDateGTLong(createDateGT));
		sb.append("/create-date-lt/");
		sb.append(_getCreateDateLTLong());
		sb.append("/-event-type/-class-name/-class-pk/-client-host/-client-ip");
		sb.append("/-server-name/server-port/0/-session-id/and-search/true");
		sb.append("/start/");
		sb.append(start);
		sb.append("/end/");
		sb.append(end);

		JSONArray auditEventsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, sb.toString());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + auditEventsJSONArray.length() + " audit events");
		}

		return auditEventsJSONArray;
	}

	public JSONObject getLatestAuditEventJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId) {

		JSONArray auditEventsJSONArray = getAuditEventsJSONArray(
			dxpExtractorConfiguration, companyId, null, 0, 1);

		if (auditEventsJSONArray.length() == 0) {
			return null;
		}

		JSONObject auditEventJSONObject = auditEventsJSONArray.getJSONObject(0);

		if (_log.isInfoEnabled()) {
			_log.info("Return latest audit event " + auditEventJSONObject);
		}

		return auditEventJSONObject;
	}

	private long _getCreateDateGTLong(Date createDateGT) {
		if (createDateGT != null) {
			return createDateGT.getTime();
		}

		return 0;
	}

	private long _getCreateDateLTLong() {
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(System.currentTimeMillis());

		// Account for timezones by adding 2 days

		calendar.add(Calendar.DATE, 2);

		return calendar.getTimeInMillis();
	}

	private static final Log _log = LogFactory.getLog(AuditEventDog.class);

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

}