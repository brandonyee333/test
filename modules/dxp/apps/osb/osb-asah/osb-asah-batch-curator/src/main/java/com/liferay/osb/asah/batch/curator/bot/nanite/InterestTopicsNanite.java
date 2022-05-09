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

import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.BlockedKeywordDog;
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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
		return _log;
	}

	private InterestTopic _createInterestTopic(
		Alphabet alphabet, IDSorter idSorter, int topicTermsLength, int topic,
		double topicWeight) {

		InterestTopic interestTopic = new InterestTopic();

		String value = String.valueOf(alphabet.lookupObject(idSorter.getID()));

		String[] valueParts = value.split(_SEPARATOR);

		interestTopic.setTerm(valueParts[0]);
		interestTopic.setTermType(valueParts[1]);

		interestTopic.setTermWeight(
			idSorter.getWeight() / Math.max(1, topicTermsLength));
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

			int[] tokensPerTopic = parallelTopicModel.getTokensPerTopic();

			if (!idSorters.isEmpty() && ArrayUtils.isNotEmpty(tokensPerTopic) &&
				(tokensPerTopic.length > i) && (tokensPerTopic[i] <= 0) &&
				_log.isWarnEnabled()) {

				_log.warn(
					String.format(
						"Topic terms length is 0. Sorted words index is %d. " +
							"Sorted words size is %d. ID sorters are %s.",
						i, sortedWords.size(), JSONUtil.put(idSorters)));
			}

			for (IDSorter idSorter : idSorters) {
				interestTopics.add(
					_createInterestTopic(
						parallelTopicModel.getAlphabet(), idSorter,
						tokensPerTopic[i], i, parallelTopicModel.alpha[i]));
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
			_assetDog,
			SetUtil.map(
				_blockedKeywordDog.getBlockedKeywords(),
				BlockedKeyword::getKeyword));

		instanceList.addThruPipe(instanceIterator);

		return instanceList;
	}

	private void _saveModel(ParallelTopicModel parallelTopicModel) {
		_interestTopicDog.deleteInterestTopics();

		_interestTopicDog.addInterestTopics(
			_createInterestTopics(parallelTopicModel));
	}

	private static final String _SEPARATOR = "_SEPARATOR_";

	private static final Log _log = LogFactory.getLog(
		InterestTopicsNanite.class);

	@Autowired
	private AssetDog _assetDog;

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

	private static class InstanceIterator implements Iterator<Instance> {

		public InstanceIterator(
			AssetDog assetDog, Set<String> blockedKeywords) {

			_assetDog = assetDog;
			_blockedKeywords = blockedKeywords;
		}

		@Override
		public boolean hasNext() {
			if (_assetsQueue.isEmpty()) {
				_fetchAssetsBatch();
			}

			return !_assetsQueue.isEmpty();
		}

		@Override
		public Instance next() {
			if (_assetsQueue.isEmpty()) {
				_fetchAssetsBatch();
			}

			Tuple2<Long, String> tuple2 = _assetsQueue.poll();

			if (tuple2 == null) {
				return null;
			}

			return new Instance(tuple2.getT2(), "", tuple2.getT1(), null);
		}

		private void _fetchAssetsBatch() {
			_assetsQueue.clear();

			List<Asset> assets = _assetDog.getAssets(
				_currentAssetId, "Page", 500);

			if (assets.isEmpty()) {
				return;
			}

			for (Asset asset : assets) {
				Long assetId = asset.getId();

				if (assetId == null) {
					throw new IllegalArgumentException("Asset ID is null");
				}

				Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

				Stream<AssetKeyword> stream = assetKeywords.stream();

				_assetsQueue.add(
					Tuples.of(
						assetId,
						stream.filter(
							assetKeyword -> !_blockedKeywords.contains(
								assetKeyword.getKeyword())
						).map(
							assetKeyword ->
								assetKeyword.getKeyword() + _SEPARATOR +
									assetKeyword.getType()
						).collect(
							Collectors.joining(",")
						)));
			}

			Asset asset = assets.get(assets.size() - 1);

			_currentAssetId = asset.getId();
		}

		private final AssetDog _assetDog;
		private final Queue<Tuple2<Long, String>> _assetsQueue =
			new LinkedList<>();
		private final Set<String> _blockedKeywords;
		private Long _currentAssetId;

	}

}