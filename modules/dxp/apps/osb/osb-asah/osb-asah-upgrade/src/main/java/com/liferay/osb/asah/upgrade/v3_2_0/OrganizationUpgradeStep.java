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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class OrganizationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		int page = 0;

		while (true) {
			JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
				"organizations", page++, QueryBuilders.matchAllQuery(), 500);

			if (jsonArray.length() == 0) {
				break;
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String dateModified = jsonObject.getString("dateModified");

				if (StringUtils.isNumeric(dateModified)) {
					jsonObject.put(
						"dateModified",
						DateUtil.toUTCString(
							new Date(Long.parseLong(dateModified))));
				}
			}

			_faroInfoElasticsearchInvoker.save("organizations", jsonArray);
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}