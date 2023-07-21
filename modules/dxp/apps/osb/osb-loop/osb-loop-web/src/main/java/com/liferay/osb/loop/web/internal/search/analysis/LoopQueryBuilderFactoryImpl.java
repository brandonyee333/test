/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.search.analysis;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.analysis.FieldQueryBuilder;
import com.liferay.portal.search.analysis.FieldQueryBuilderFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(
	immediate = true,
	property = "substring.fields=emailAddress_sortable|skypeSn_sortable|topicName_sortable",
	service = FieldQueryBuilderFactory.class
)
public class LoopQueryBuilderFactoryImpl implements FieldQueryBuilderFactory {

	@Override
	public FieldQueryBuilder getQueryBuilder(String field) {
		if (_substringFields.contains(field)) {
			return loopSubstringFieldQueryBuilder;
		}

		return null;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_substringFields = getFields(properties, "substring.fields");
	}

	protected Collection<String> getFields(
		Map<String, Object> properties, String key) {

		String[] values = StringUtil.split(
			GetterUtil.getString(properties.get(key)), CharPool.PIPE);

		return new HashSet<>(Arrays.asList(values));
	}

	@Reference
	protected LoopSubstringFieldQueryBuilder loopSubstringFieldQueryBuilder;

	private volatile Collection<String> _substringFields =
		Collections.singleton("substring");

}