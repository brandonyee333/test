/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.search.facet.collector.DefaultTermCollector;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class SolrFacetQueryCollector implements FacetCollector {

	public SolrFacetQueryCollector(
		String fieldName, Map<String, Integer> facetQueries) {

		_fieldName = fieldName;

		for (Map.Entry<String, Integer> entry : facetQueries.entrySet()) {
			String term = _getTerm(entry.getKey());
			Integer count = entry.getValue();

			_counts.put(term, count);
		}
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public TermCollector getTermCollector(String term) {
		return new DefaultTermCollector(
			term, GetterUtil.getInteger(_counts.get(term)));
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		if (_termCollectors != null) {
			return _termCollectors;
		}

		List<TermCollector> termCollectors = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : _counts.entrySet()) {
			Integer count = entry.getValue();

			TermCollector termCollector = new DefaultTermCollector(
				entry.getKey(), count.intValue());

			termCollectors.add(termCollector);
		}

		_termCollectors = termCollectors;

		return _termCollectors;
	}

	private String _getTerm(String term) {
		return term.substring(_fieldName.length() + 1);
	}

	private final Map<String, Integer> _counts = new HashMap<>();
	private final String _fieldName;
	private List<TermCollector> _termCollectors;

}