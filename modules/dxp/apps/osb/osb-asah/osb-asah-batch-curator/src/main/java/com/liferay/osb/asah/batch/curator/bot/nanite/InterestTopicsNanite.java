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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.BlockedKeywordDog;
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.util.SetUtil;

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

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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
			_assetRepository,
			SetUtil.map(
				_blockedKeywordDog.getBlockedKeywords(),
				BlockedKeyword::getKeyword),
			_objectMapper);

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
	private AssetRepository _assetRepository;

	@Autowired
	private BlockedKeywordDog _blockedKeywordDog;

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

	@Autowired
	private ObjectMapper _objectMapper;

	private static class InstanceIterator implements Iterator<Instance> {

		public InstanceIterator(
			AssetRepository assetRepository, Set<String> blockedKeywords,
			ObjectMapper objectMapper) {

			_assetRepository = assetRepository;
			_blockedKeywords = blockedKeywords;
			_objectMapper = objectMapper;

			_totalCount = assetRepository.countByAssetTypeAndKeywordNotNull(
				"Page");
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

		private void _fetchAssetsBatch() {
			List<Asset> assets =
				_assetRepository.findByAssetTypeAndKeywordNotNull(
					"Page", PageRequest.of(_currentPage, 50, Sort.asc("id")));

			Stream<Asset> stream = assets.stream();

			List<Map<String, Object>> assetsMap = stream.map(
				asset -> (Map<String, Object>)_objectMapper.convertValue(
					asset, Map.class)
			).collect(
				Collectors.toList()
			);

			_assetsQueue.clear();

			_assetsQueue.addAll(assetsMap);

			_currentPage++;
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

		private final AssetRepository _assetRepository;
		private final Queue<Map<String, Object>> _assetsQueue =
			new LinkedList<>();
		private final Set<String> _blockedKeywords;
		private long _currentCount;
		private int _currentPage;
		private final ObjectMapper _objectMapper;
		private final long _totalCount;

	}

}