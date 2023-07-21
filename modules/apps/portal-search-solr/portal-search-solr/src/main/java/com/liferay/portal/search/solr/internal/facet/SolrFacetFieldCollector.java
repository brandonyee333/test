/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.search.facet.collector.DefaultTermCollector;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;

/**
 * @author Raymond Augé
 */
public class SolrFacetFieldCollector implements FacetCollector {

	public SolrFacetFieldCollector(String fieldName, FacetField facetField) {
		_fieldName = fieldName;

		List<FacetField.Count> counts = facetField.getValues();

		if (ListUtil.isNotEmpty(counts)) {
			for (FacetField.Count count : counts) {
				if (count.getCount() > 0) {
					_counts.put(count.getName(), count);
				}
			}
		}
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public TermCollector getTermCollector(String term) {
		FacetField.Count count = _counts.get(term);

		int occurences = 0;

		if (count != null) {
			occurences = (int)count.getCount();
		}

		return new DefaultTermCollector(term, occurences);
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		if (_termCollectors != null) {
			return _termCollectors;
		}

		List<TermCollector> termCollectors = new ArrayList<>();

		for (Map.Entry<String, FacetField.Count> entry : _counts.entrySet()) {
			FacetField.Count count = entry.getValue();

			TermCollector termCollector = new DefaultTermCollector(
				entry.getKey(), (int)count.getCount());

			termCollectors.add(termCollector);
		}

		_termCollectors = termCollectors;

		return _termCollectors;
	}

	private final Map<String, FacetField.Count> _counts = new LinkedHashMap<>();
	private final String _fieldName;
	private List<TermCollector> _termCollectors;

}