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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;

import com.liferay.osb.asah.common.dog.BlockedKeywordDog;
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.BlockedKeyword;
import com.liferay.osb.asah.common.model.InterestTopic;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Victor Oliveira
 * @author Marcellus Tavares
 */
@Component
public class InterestTopicsNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		ParallelTopicModel parallelTopicModel = new ParallelTopicModel(
			_ldaTopicsCount, _ldaAlphaSum, _ldaBeta);

		parallelTopicModel.addInstances(_createTrainingInstanceList());
		parallelTopicModel.setNumIterations(_ldaIterations);
		parallelTopicModel.setNumThreads(_ldaThreads);

		parallelTopicModel.estimate();

		_saveModel(parallelTopicModel);
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(InterestTopicsNanite.class);
	}

	private InterestTopic _createInterestTopic(
		Alphabet alphabet, IDSorter idSorter, int topicTermsLength, int topic,
		double topicWeight) {

		InterestTopic interestTopic = new InterestTopic();

		String value = String.valueOf(alphabet.lookupObject(idSorter.getID()));

		String[] valueParts = value.split(_SEPARATOR);

		interestTopic.setTerm(valueParts[0]);
		interestTopic.setTermType(valueParts[1]);

		interestTopic.setTermWeight(idSorter.getWeight() / topicTermsLength);
		interestTopic.setTopic(topic);
		interestTopic.setTopicWeight(topicWeight);

		return interestTopic;
	}

	private List<InterestTopic> _createInterestTopics(
		ParallelTopicModel parallelTopicModel) {

		List<InterestTopic> interestTopics = new ArrayList<>();

		ArrayList<TreeSet<IDSorter>> sortedWords =
			parallelTopicModel.getSortedWords();

		for (int i = 0; i < _ldaTopicsCount; i++) {
			TreeSet<IDSorter> idSorters = sortedWords.get(i);

			for (IDSorter idSorter : idSorters) {
				interestTopics.add(
					_createInterestTopic(
						parallelTopicModel.getAlphabet(), idSorter,
						parallelTopicModel.getTokensPerTopic()[i], i,
						parallelTopicModel.alpha[i]));
			}
		}

		return interestTopics;
	}

	private List<Pipe> _createPreprocessPipes() {
		List<Pipe> pipes = new ArrayList<>();

		pipes.add(new CharSequence2TokenSequence(Pattern.compile("([^,]+)")));
		pipes.add(new TokenSequence2FeatureSequence());

		return pipes;
	}

	private InstanceList _createTrainingInstanceList() {
		InstanceList instanceList = new InstanceList(
			new SerialPipes(_createPreprocessPipes()));

		Iterator<Instance> instanceIterator = new InstanceIterator(
			SetUtil.map(
				_blockedKeywordDog.getBlockedKeywords(),
				BlockedKeyword::getKeyword),
			_faroInfoElasticsearchInvoker);

		instanceList.addThruPipe(instanceIterator);

		return instanceList;
	}

	private void _saveModel(ParallelTopicModel parallelTopicModel) {
		_interestTopicDog.deleteInterestTopics();

		_interestTopicDog.addInterestTopics(
			_createInterestTopics(parallelTopicModel));
	}

	private static final String _SEPARATOR = "_SEPARATOR_";

	@Autowired
	private BlockedKeywordDog _blockedKeywordDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private InterestTopicDog _interestTopicDog;

	@Value("${lda.alpha.sum:1.0}")
	private double _ldaAlphaSum;

	@Value("${lda.beta:0.01}")
	private double _ldaBeta;

	@Value("${lda.iterations:2000}")
	private int _ldaIterations;

	@Value("${lda.terms.count:10}")
	private int _ldaTermsCount;

	@Value("${lda.threads:1}")
	private int _ldaThreads;

	@Value("${lda.topics.count:10}")
	private int _ldaTopicsCount;

	private static class InstanceIterator implements Iterator<Instance> {

		public InstanceIterator(
			Set<String> blockedKeywords,
			ElasticsearchInvoker elasticsearchInvoker) {

			_blockedKeywords = blockedKeywords;
			_elasticsearchInvoker = elasticsearchInvoker;

			_totalCount = elasticsearchInvoker.count(
				"assets", _createQueryBuilder());
		}

		@Override
		public boolean hasNext() {
			if (_currentCount < _totalCount) {
				return true;
			}

			return false;
		}

		@Override
		public Instance next() {
			if (_assetsQueue.isEmpty()) {
				_fetchAssetsBatch();
			}

			_currentCount++;

			Map<String, Object> asset = _assetsQueue.poll();

			return new Instance(
				_toKeywordsString(
					(List<Map<String, String>>)asset.get("keywords")),
				"", asset.get("id"), null);
		}

		private void _cacheAssetsBatch(SearchHits searchHits) {
			List<Map<String, Object>> assets = _toAssets(searchHits);

			_assetsQueue.clear();

			_assetsQueue.addAll(assets);

			Map<String, Object> lastAsset = assets.get(assets.size() - 1);

			_lastAssetId = (String)lastAsset.get("id");
		}

		private QueryBuilder _createQueryBuilder() {
			return BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", "Page")
			).filter(
				QueryBuilders.existsQuery("keywords")
			);
		}

		private void _fetchAssetsBatch() {
			SearchResponse searchResponse = _elasticsearchInvoker.search(
				"assets",
				searchSourceBuilder -> {
					searchSourceBuilder.query(_createQueryBuilder());
					searchSourceBuilder.searchAfter(
						new Object[] {_lastAssetId});
					searchSourceBuilder.size(50);
					searchSourceBuilder.sort("id");
				});

			_cacheAssetsBatch(searchResponse.getHits());
		}

		private List<Map<String, Object>> _toAssets(SearchHits searchHits) {
			List<Map<String, Object>> assets = new ArrayList<>();

			for (SearchHit searchHit : searchHits) {
				assets.add(searchHit.getSourceAsMap());
			}

			return assets;
		}

		private String _toKeywordsString(List<Map<String, String>> keywords) {
			Stream<Map<String, String>> stream = keywords.stream();

			return stream.filter(
				map -> !_blockedKeywords.contains(map.get("keyword"))
			).map(
				map -> map.get("keyword") + _SEPARATOR + map.get("type")
			).collect(
				Collectors.joining(",")
			);
		}

		private final Queue<Map<String, Object>> _assetsQueue =
			new LinkedList<>();
		private final Set<String> _blockedKeywords;
		private long _currentCount;
		private final ElasticsearchInvoker _elasticsearchInvoker;
		private String _lastAssetId = "0";
		private final long _totalCount;

	}

}