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

package com.liferay.osb.asah.upgrade.v2_8_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class KnownIndividualInteractionsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		JSONArrayIterator.of(
			"individuals", _faroInfoElasticsearchInvoker,
			individualJSONObject -> {
				boolean knownIndividual = !Objects.isNull(
					individualJSONObject.optQuery("/demographics/email"));

				_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"individualId", individualJSONObject.get("id"))
					).filter(
						QueryBuilders.termQuery(
							"knownIndividual", !knownIndividual)
					),
					false,
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						"ctx._source.knownIndividual = params.knownIndividual",
						Collections.singletonMap(
							"knownIndividual", knownIndividual)),
					_COLLECTION_NAMES);

				return null;
			}
		).setStopOnExceptions(
			false
		).iterate();
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final String[] _COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals", "page-referrers", "pages"
	};

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}