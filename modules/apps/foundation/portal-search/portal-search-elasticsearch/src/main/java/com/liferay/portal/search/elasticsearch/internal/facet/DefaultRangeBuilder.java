/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.aggregations.bucket.range.AbstractRangeBuilder;
import org.elasticsearch.search.aggregations.bucket.range.InternalRange;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;

/**
 * @author Michael C. Han
 */
public class DefaultRangeBuilder extends AbstractRangeBuilder<RangeBuilder> {

	public DefaultRangeBuilder(String name) {
		super(name, InternalRange.TYPE.name());
	}

	public DefaultRangeBuilder addRange(String from, String to) {
		return addRange(null, from, to);
	}

	public DefaultRangeBuilder addRange(String key, String from, String to) {
		ranges.add(new Range(key, from, to));

		return this;
	}

	public DefaultRangeBuilder addUnboundedFrom(String from) {
		return addUnboundedFrom(null, from);
	}

	public DefaultRangeBuilder addUnboundedFrom(String key, String from) {
		ranges.add(new Range(key, from, null));

		return this;
	}

	public DefaultRangeBuilder addUnboundedTo(String to) {
		return addUnboundedTo(null, to);
	}

	public DefaultRangeBuilder addUnboundedTo(String key, String to) {
		ranges.add(new Range(key, null, to));

		return this;
	}

	public DefaultRangeBuilder format(String format) {
		_format = format;

		return this;
	}

	public boolean hasRanges() {
		return !ranges.isEmpty();
	}

	@Override
	protected XContentBuilder doInternalXContent(
			XContentBuilder builder, Params params)
		throws IOException {

		super.doInternalXContent(builder, params);

		if (_format != null) {
			builder.field("format", _format);
		}

		return builder;
	}

	private String _format;

}