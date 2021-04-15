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

package com.liferay.osb.asah.common.dog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import org.springframework.data.domain.Sort;

/**
 * @author Marcellus Tavares
 */
public class SortUtil {

	public static Sort getSort(Sort defaultSort, String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return defaultSort;
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < (sorts.length - 1); i = i + 2) {
			String sort = sorts[i];

			if (Objects.equals(sorts[i + 1], "asc")) {
				orders.add(Sort.Order.asc(sort));
			}
			else {
				orders.add(Sort.Order.desc(sort));
			}
		}

		return Sort.by(orders);
	}

	public static Sort getSort(String[] sorts) {
		return getSort(Sort.by(Sort.Order.desc("id")), sorts);
	}

}