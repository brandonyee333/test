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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.entity.Preference;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataRetentionNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject jsonObject) throws Exception {
		Preference preference = _preferenceDog.getPreference(
			"data-retention-period");

		new Date(
			System.currentTimeMillis() - Long.parseLong(preference.getValue()));

		// TODO Delete

		// TODO delete BQIdentities created before date

	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(DataRetentionNanite.class);
	}

	private static final String[] _COLLECTION_NAMES = {
		"blogs", "blog-clicks", "blog-traffic-sources", "custom-assets",
		"document-libraries", "forms", "journals", "journal-clicks",
		"page-referrers", "pages", "user-sessions"
	};

	@Autowired
	private PreferenceDog _preferenceDog;

}