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

package com.liferay.osb.loop.web.internal.search.analysis;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.QueryTermImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.analysis.FieldQueryBuilder;
import com.liferay.portal.search.analysis.KeywordTokenizer;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 * @author Rodrigo Paulino
 */
@Component(immediate = true, service = LoopSubstringFieldQueryBuilder.class)
public class LoopSubstringFieldQueryBuilder implements FieldQueryBuilder {

	@Override
	public Query build(String field, String keywords) {
		BooleanQueryImpl booleanQueryImpl = new BooleanQueryImpl();

		List<String> tokens = keywordTokenizer.tokenize(keywords);

		for (String token : tokens) {
			booleanQueryImpl.add(
				createQuery(field, token), BooleanClauseOccur.SHOULD);
		}

		return booleanQueryImpl;
	}

	protected Query createQuery(String field, String value) {
		if (StringUtil.startsWith(value, CharPool.QUOTE)) {
			value = StringUtil.unquote(value);
		}

		value = StringUtil.replace(value, CharPool.PERCENT, StringPool.BLANK);

		if (value.isEmpty()) {
			value = StringPool.STAR;
		}
		else {
			value = StringUtil.quote(
				StringUtil.toLowerCase(value), StringPool.STAR);
		}

		return new WildcardQueryImpl(new QueryTermImpl(field, value));
	}

	@Reference
	protected KeywordTokenizer keywordTokenizer;

}