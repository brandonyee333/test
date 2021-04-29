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

package com.liferay.osb.asah.upgrade.v2_13_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SalesforceAuditEventUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		JSONArrayIterator.of(
			"audit-events", _salesforceRawElasticsearchInvoker,
			salesforceAuditEventJSONObject ->
				_salesforceRawElasticsearchInvoker.replace(
					"audit-events",
					_upgradeSalesforceAuditEventJSONObject(
						salesforceAuditEventJSONObject))
		).iterate();
	}

	private JSONObject _upgradeSalesforceAuditEventJSONObject(
		JSONObject salesforceEntityJSONObject) {

		String typeName = salesforceEntityJSONObject.optString(
			"typeName", null);

		if (typeName == null) {
			return salesforceEntityJSONObject;
		}

		try {
			SalesforceEntity.Type salesforceEntityType =
				SalesforceEntity.Type.of(typeName);

			salesforceEntityJSONObject.put(
				"typeName", salesforceEntityType.toString());
		}
		catch (IllegalArgumentException iae) {
		}

		return salesforceEntityJSONObject;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}