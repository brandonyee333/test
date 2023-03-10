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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.entity.BQSessionInterestScore;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.BQIdentityInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQSessionInterestScoreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class InterestScoreIngestionNanite {

	public void run() {
		Date date = DateUtil.newDayDate();

		List<BQIdentityInterestScore> bqIdentityInterestScores =
			new ArrayList<>();
		List<BQSessionInterestScore> bqSessionInterestScores = new ArrayList();
		Set<String> userIds = new HashSet<>();

		for (Map<String, String> keywordsMap :
				_bqEventRepository.getKeywordsGroupedBySessionIdAndUserId()) {

			String userId = keywordsMap.get("userId");

			if (!userIds.contains(userId)) {
				bqIdentityInterestScores.addAll(
					_getBQIdentityInterestScores(keywordsMap, date));
				userIds.add(userId);
			}

			bqSessionInterestScores.addAll(
				_getBQSessionInterestScores(keywordsMap, date));
		}

		_bqIdentityInterestScoreRepository.deleteAll();

		_bqIdentityInterestScoreRepository.insertAll(bqIdentityInterestScores);

		_bqSessionInterestScoreRepository.deleteByRecordedDate(date);

		_bqSessionInterestScoreRepository.insertAll(bqSessionInterestScores);
	}

	private List<BQIdentityInterestScore> _getBQIdentityInterestScores(
		Map<String, String> keywordsMap, Date recordedDate) {

		List<BQIdentityInterestScore> bqIdentityInterestScores =
			new ArrayList();

		for (String keyword : _getKeywords(keywordsMap.get("keywords"))) {
			double interestScore = Math.random();
			boolean interested = false;

			if (interestScore > 0.2) {
				interested = true;
			}

			BQIdentityInterestScore bqIdentityInterestScore =
				new BQIdentityInterestScore();

			bqIdentityInterestScore.setIdentityId(keywordsMap.get("userId"));
			bqIdentityInterestScore.setInterestScore(interestScore);
			bqIdentityInterestScore.setInterested(interested);
			bqIdentityInterestScore.setKeyword(keyword);
			bqIdentityInterestScore.setRecordedDate(recordedDate);

			bqIdentityInterestScores.add(bqIdentityInterestScore);
		}

		return bqIdentityInterestScores;
	}

	private List<BQSessionInterestScore> _getBQSessionInterestScores(
		Map<String, String> keywordsMap, Date recordedDate) {

		List<BQSessionInterestScore> bqSessionInterestScores = new ArrayList();

		for (String keyword : _getKeywords(keywordsMap.get("keywords"))) {
			double interestScore = Math.random();
			boolean interested = false;

			if (interestScore > 0.2) {
				interested = true;
			}

			BQSessionInterestScore bqSessionInterestScore =
				new BQSessionInterestScore();

			bqSessionInterestScore.setIdentityId(keywordsMap.get("userId"));
			bqSessionInterestScore.setInterestScore(interestScore);
			bqSessionInterestScore.setInterested(interested);
			bqSessionInterestScore.setKeyword(keyword);
			bqSessionInterestScore.setRecordedDate(recordedDate);
			bqSessionInterestScore.setSessionId(keywordsMap.get("sessionId"));

			bqSessionInterestScores.add(bqSessionInterestScore);
		}

		return bqSessionInterestScores;
	}

	private Set<String> _getKeywords(String keywords) {
		return new HashSet<>(Arrays.asList(keywords.split(" ")));
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private BQIdentityInterestScoreRepository
		_bqIdentityInterestScoreRepository;

	@Autowired
	private BQSessionInterestScoreRepository _bqSessionInterestScoreRepository;

}