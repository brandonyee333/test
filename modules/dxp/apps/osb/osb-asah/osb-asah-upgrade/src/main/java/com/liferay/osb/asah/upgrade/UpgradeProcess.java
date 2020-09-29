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

package com.liferay.osb.asah.upgrade;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcellus Tavares
 */
public class UpgradeProcess {

	public void addUpgradeSteps(
		String fromVersionString, String toVersionString,
		UpgradeStep... upgradeSteps) {

		_upgradeStepsMap.put(
			fromVersionString,
			Pair.of(toVersionString, Arrays.asList(upgradeSteps)));
	}

	public String getMaxVersionString() {
		Collection<Pair<String, List<UpgradeStep>>> values =
			_upgradeStepsMap.values();

		Stream<Pair<String, List<UpgradeStep>>> stream = values.stream();

		return stream.map(
			Pair::getLeft
		).max(
			new VersionComparator()
		).orElseThrow(
			IllegalStateException::new
		);
	}

	public String getToVersionString(String fromVersionString) {
		if (fromVersionString == null) {
			fromVersionString = "0.0.0";
		}

		return Optional.ofNullable(
			_upgradeStepsMap.get(fromVersionString)
		).map(
			Pair::getLeft
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	public List<UpgradeStep> getUpgradeSteps(String fromVersionString) {
		if (fromVersionString == null) {
			fromVersionString = "0.0.0";
		}

		return Optional.ofNullable(
			_upgradeStepsMap.get(fromVersionString)
		).map(
			Pair::getRight
		).orElseGet(
			Collections::emptyList
		);
	}

	private final Map<String, Pair<String, List<UpgradeStep>>>
		_upgradeStepsMap = new HashMap<>();

	private static class VersionComparator
		implements Comparator<String>, Serializable {

		@Override
		public int compare(String versionString1, String versionString2) {
			int value = 0;

			int[] versionParts1 = _split(versionString1, 3);
			int[] versionParts2 = _split(versionString2, 3);

			if (versionParts1[0] > versionParts2[0]) {
				value = 1;
			}
			else if (versionParts1[0] < versionParts2[0]) {
				value = -1;
			}
			else if (versionParts1[1] > versionParts2[1]) {
				value = 1;
			}
			else if (versionParts1[1] < versionParts2[1]) {
				value = -1;
			}
			else if (versionParts1[2] > versionParts2[2]) {
				value = 1;
			}
			else if (versionParts1[2] < versionParts2[2]) {
				value = -1;
			}

			return value;
		}

		private int[] _split(String versionString, int arrayLength) {
			String[] array = StringUtils.split(versionString, ".");

			int[] newArray = new int[arrayLength];

			for (int i = 0; i < newArray.length; i++) {
				int value = 0;

				if (i < array.length) {
					try {
						value = Integer.parseInt(array[i]);
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to parse " + array[i] + " to integer");
						}
					}
				}

				newArray[i] = value;
			}

			return newArray;
		}

		private static final Log _log = LogFactory.getLog(
			VersionComparator.class);

	}

}