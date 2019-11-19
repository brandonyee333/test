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

package com.liferay.osb.customer.provisioning.trial.internal.license;

import com.liferay.osb.license.util.KeyGenerator;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Amos Fong
 */
public class DXPTrialLicenseManager {

	public DXPTrialLicenseManager(int version, String versionLabel) {
		_version = version;
		_versionLabel = versionLabel;
	}

	public String getLicenseXML(long dayHash) throws Exception {
		String today = _dateFormat.format(new Date());

		if (!today.equals(_lastRefreshDay)) {
			_refreshDayHashes();
		}

		String day = _dayHashesMap.get(dayHash);

		if (day == null) {
			return null;
		}

		String licenseXML = _licenseXMLMap.get(day);

		if (Validator.isNull(licenseXML)) {
			_clearOldLicenseXMLs();

			licenseXML = _generateLicenseXML(day);

			_licenseXMLMap.put(day, licenseXML);

			if (_log.isInfoEnabled()) {
				_log.info("Generated new license xml for " + day);
			}
		}

		return licenseXML;
	}

	private void _clearOldLicenseXMLs() {
		Set<String> keySet = _licenseXMLMap.keySet();

		Iterator<String> iterator = keySet.iterator();

		while (iterator.hasNext()) {
			String day = iterator.next();

			long dayHash = _generateHash(day);

			if (!_dayHashesMap.containsKey(dayHash)) {
				iterator.remove();
			}
		}
	}

	private long _generateHash(String day) {
		int seed = Integer.valueOf(day);

		double hash = Math.pow(seed, 17) % 51539607551L;

		hash = Math.pow(hash + _version, 17) % 51539607551L;

		return (long)hash;
	}

	private String _generateLicenseXML(String day) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date startDate = dateFormat.parse(day);

		Date expirationDate = new Date(startDate.getTime() + (Time.DAY * 30));

		Map<String, String> properties = KeyGenerator.getProperties(
			"Liferay Trial", "Digital Enterprise Development",
			LicenseEntryConstants.TYPE_DEVELOPER,
			LicenseKeyConstants.getLicenseVersionByMajorProductVersion(
				_version),
			"Digital Enterprise Development",
			ProductEntryConstants.PRODUCT_ID_PORTAL, _versionLabel,
			"Liferay Trial", 0, 5, 0, 0, 0, "Liferay Trial", StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK, new String[0], startDate,
			expirationDate);

		String key = KeyGenerator.generate(properties);

		return LicenseUtil.exportToXML(properties, key);
	}

	private synchronized void _refreshDayHashes() {
		Calendar cal = Calendar.getInstance();

		_lastRefreshDay = _dateFormat.format(cal.getTime());

		cal.add(Calendar.DATE, -30);

		Map<Long, String> newDayHashesMap = new HashMap<>();

		for (int i = 0; i < 31; i++) {
			String day = _dateFormat.format(cal.getTime());

			newDayHashesMap.put(_generateHash(day), day);

			cal.add(Calendar.DATE, 1);
		}

		_dayHashesMap = newDayHashesMap;

		if (_log.isInfoEnabled()) {
			_log.info("Refreshed day hashes on " + _lastRefreshDay);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DXPTrialLicenseManager.class);

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMdd");
	private Map<Long, String> _dayHashesMap = new HashMap<>();
	private String _lastRefreshDay;
	private final Map<String, String> _licenseXMLMap =
		new ConcurrentHashMap<>();
	private final int _version;
	private final String _versionLabel;

}