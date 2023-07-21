/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import org.elasticsearch.search.aggregations.bucket.range.Range;

/**
 * @author André de Oliveira
 */
public class RangeFacetCollector implements FacetCollector {

	public RangeFacetCollector(Range range) {
		_fieldName = range.getName();
		_termCollectorHolder = getTermCollectorHolder(range);
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public TermCollector getTermCollector(String term) {
		return _termCollectorHolder.getTermCollector(term);
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		return _termCollectorHolder.getTermCollectors();
	}

	protected TermCollectorHolder getTermCollectorHolder(Range range) {
		List<? extends Range.Bucket> buckets = range.getBuckets();

		TermCollectorHolder termCollectorHolder = new TermCollectorHolder(
			buckets.size());

		for (Range.Bucket bucket : buckets) {
			String key = StringUtil.replace(
				bucket.getKeyAsString(), CharPool.DASH, _TO_STRING);

			key = StringPool.OPEN_BRACKET.concat(
				key
			).concat(
				StringPool.CLOSE_BRACKET
			);

			termCollectorHolder.add(key, (int)bucket.getDocCount());
		}

		return termCollectorHolder;
	}

	private static final String _TO_STRING = " TO ";

	private final String _fieldName;
	private final TermCollectorHolder _termCollectorHolder;

}