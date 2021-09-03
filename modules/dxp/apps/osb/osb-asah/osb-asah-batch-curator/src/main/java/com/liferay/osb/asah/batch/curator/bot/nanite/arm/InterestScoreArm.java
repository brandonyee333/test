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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 * @author Michael Bowerman
 */
@Component
public class InterestScoreArm {

	public static final int DAYS = 30;

	public static final double DECAY = 0.9;

	public static final double MINIMUM_THRESHOLD_SCORE = 0.1;

	public double computeScore(
		double individualKeywordViews, double individualViews, double score,
		double totalKeywordViews, double totalViews) {

		double computeDayScore = _computeDayScore(
			individualKeywordViews, individualViews, totalKeywordViews,
			totalViews);

		return score + computeDayScore;
	}

	public double computeThresholdScore(String keyword) {
		double totalKeywordViews = 0.0;
		double totalViews = 0.0;

		for (int i = 0; i < DAYS; i++) {
			String previousDayDateString = DateUtil.addDays(
				DateUtil.newDayDateString(), -i);

			totalKeywordViews += _urlArm.getTotalKeywordViews(
				previousDayDateString, keyword);

			totalViews += _faroInfoActivityDog.getTotalPageViews(
				previousDayDateString);
		}

		double score = 0.0;

		if (totalKeywordViews > 0) {
			score =
				Math.log(totalViews / totalKeywordViews) * Math.pow(DECAY, 7);
		}

		return Math.max(score, MINIMUM_THRESHOLD_SCORE);
	}

	private double _computeDayScore(
		double individualKeywordViews, double individualViews,
		double totalKeywordViews, double totalViews) {

		if (individualKeywordViews == 0) {
			return 0;
		}

		double idf = _computeIDF(
			individualKeywordViews, individualViews, totalKeywordViews,
			totalViews);
		double tf = individualKeywordViews / individualViews;

		return tf * idf;
	}

	private double _computeIDF(
		double individualKeywordViews, double individualViews,
		double totalKeywordViews, double totalViews) {

		double weightedViews =
			(individualViews * _WEIGHT_INDIVIDUAL_VIEWS) +
				(totalViews * _WEIGHT_TOTAL_VIEWS);

		double weightedKeywordViews =
			(individualKeywordViews * _WEIGHT_INDIVIDUAL_VIEWS) +
				(totalKeywordViews * _WEIGHT_TOTAL_VIEWS);

		return Math.log(weightedViews / weightedKeywordViews);
	}

	private static final double _WEIGHT_INDIVIDUAL_VIEWS = 1.0;

	private static final double _WEIGHT_TOTAL_VIEWS = 2.0;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private URLArm _urlArm;

}