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

package com.liferay.osb.asah.common.postgresql.converter.helper.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.BQDataSourceUser;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Rachael Koestartyo
 */
@Disabled
@Import(JDBCTestConfiguration.class)
public class IndividualsFilterStringConverterHelperTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {

		// TODO Add activities

		// TODO Add user session collections

		_setUpDataSources();

		_setUpBQOrganizations();
		_setUpIndividuals();

		_setUpBQMemberships();
		_setUpBQMembershipChanges();
		_setUpSegments();
	}

	@Disabled
	@Test
	public void testActivitiesFilterBetweenDay() {
		testFilterString(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountAtLeastOne() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			346468603851271125L, 346468605699756892L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountAtMostOne() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=1)",
			346468605699756892L, 346468608880878498L, 346468609906122549L,
			346468614337714393L, 346468617541446459L, 346468620407090009L,
			346468629688998133L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468640520316300L, 346468641321212439L,
			346468642072426931L, 346468647886010657L, 346468649722790279L,
			346468650664446754L, 346468651775874817L, 346468652526171339L,
			346468659407593919L, 346468660260465644L, 346468664541254353L,
			346468669276672682L, 346468670578686898L, 346468677661047218L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468683127812925L, 346468699875814972L, 346468700681239480L,
			346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqPositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=1)",
			346468605699756892L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=0)",
			346468608880878498L, 346468609906122549L, 346468614337714393L,
			346468617541446459L, 346468620407090009L, 346468629688998133L,
			346468631552894612L, 346468636854641829L, 346468638249348979L,
			346468640520316300L, 346468641321212439L, 346468642072426931L,
			346468647886010657L, 346468649722790279L, 346468650664446754L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468660260465644L, 346468664541254353L, 346468669276672682L,
			346468670578686898L, 346468677661047218L, 346468678682539385L,
			346468679569640045L, 346468680492094349L, 346468683127812925L,
			346468699875814972L, 346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGeNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=-1)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468699875814972L,
			346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGePositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			346468603851271125L, 346468605699756892L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGeZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=0)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468699875814972L,
			346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=-1)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468699875814972L,
			346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtPositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=1)",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=0)",
			346468603851271125L, 346468605699756892L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLeNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLePositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=2)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468699875814972L,
			346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLeZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=0)",
			346468608880878498L, 346468609906122549L, 346468614337714393L,
			346468617541446459L, 346468620407090009L, 346468629688998133L,
			346468631552894612L, 346468636854641829L, 346468638249348979L,
			346468640520316300L, 346468641321212439L, 346468642072426931L,
			346468647886010657L, 346468649722790279L, 346468650664446754L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468660260465644L, 346468664541254353L, 346468669276672682L,
			346468670578686898L, 346468677661047218L, 346468678682539385L,
			346468679569640045L, 346468680492094349L, 346468683127812925L,
			346468699875814972L, 346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtPositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=2)",
			346468605699756892L, 346468608880878498L, 346468609906122549L,
			346468614337714393L, 346468617541446459L, 346468620407090009L,
			346468629688998133L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468640520316300L, 346468641321212439L,
			346468642072426931L, 346468647886010657L, 346468649722790279L,
			346468650664446754L, 346468651775874817L, 346468652526171339L,
			346468659407593919L, 346468660260465644L, 346468664541254353L,
			346468669276672682L, 346468670578686898L, 346468677661047218L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468683127812925L, 346468699875814972L, 346468700681239480L,
			346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=0)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNeNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=-1)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468699875814972L,
			346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNePositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=1)",
			346468603851271125L, 346468608880878498L, 346468609906122549L,
			346468614337714393L, 346468617541446459L, 346468620407090009L,
			346468629688998133L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468640520316300L, 346468641321212439L,
			346468642072426931L, 346468647886010657L, 346468649722790279L,
			346468650664446754L, 346468651775874817L, 346468652526171339L,
			346468659407593919L, 346468660260465644L, 346468664541254353L,
			346468669276672682L, 346468670578686898L, 346468677661047218L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468683127812925L, 346468699875814972L, 346468700681239480L,
			346468701457781206L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNeZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=0)",
			346468603851271125L, 346468605699756892L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast7Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last7Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast24Hours() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last24Hours''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast28Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last28Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast30Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last30Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L,
			346468609906122549L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast90Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last90Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L,
			346468609906122549L, 346468614337714393L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceYesterday() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994''', operator='eq', " +
					"value=1)",
			"346468699875814972",
			DSL.field(
				"individual.id"
			).in(
				346468699875814972L
			));
	}

	@Disabled
	@Test
	public void testActivitiesFilterGtDay() {
		testFilterString(
			"activities.filter(filter='day gt ''2019-04-09''')",
			346468699875814972L, 346468603851271125L, 346468608880878498L,
			346468609906122549L, 346468614337714393L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterInBetweenDay() {
		testFilterString(
			"(activities.filter(filter='day le ''2019-03-31''') and " +
				"activities.filter(filter='day gt ''2019-03-01'''))",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterLtDay() {
		testFilterString(
			"activities.filter(filter='day lt ''2019-03-07''')",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testActivitiesFilterWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			"346468603851271125",
			DSL.field(
				"individual.id"
			).in(
				346468603851271125L
			));
	}

	@Test
	public void testFreestyle3() {
		StringBuilder sb = new StringBuilder();

		sb.append("sessions.filter(filter='context/browserName eq ''Chrome'' ");
		sb.append("and contains(context/city, ''French'') and ");
		sb.append("(context/region eq ''Indiana'') and (completeDate gt ''");
		sb.append("last90Days'')')");

		testFilterString(sb.toString(), 346468614337714393L);
	}

	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/interests.json"
	)
	@Test
	public void testInterestsFilter() {
		testFilterString(
			"interests.filter(filter='(((name eq ''abc'')) and (score eq " +
				"''true''))')");
		testFilterString(
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''false'')')");

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"IndividualInterestScoresNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.newDayDateString())));

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.newDayDateString()
				).put(
					"score", 0.3
				)));

		testFilterString(
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''true'')')",
			346468699875814972L, 346468700681239480L);
		testFilterString(
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''false'')')",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468629688998133L, 346468631552894612L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468641321212439L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468650664446754L, 346468651775874817L,
			346468652526171339L, 346468659407593919L, 346468660260465644L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468677661047218L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468683127812925L, 346468701457781206L);
	}

	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/interests.json"
	)
	@Test
	public void testInterestsFilterWithIndividual() {
		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"IndividualInterestScoresNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.newDayDateString())));

		_asahMarkerDog.addAsahMarker(
			new AsahMarker(
				"InterestThresholdScoreNanite",
				JSONUtil.put(
					"lastSuccessfulDay", DateUtil.newDayDateString()
				).put(
					"score", 0.2
				)));

		testFilterStringWithIndividual(
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''true'')')",
			"346468700681239480",
			DSL.field(
				"individual.id"
			).in(
				346468700681239480L
			));
	}

	@Disabled
	@Test
	public void testOrganizationFilterGtModifiedDate() {
		testFilterString(
			"(organizations.filter(filter='(modifiedDate gt 1580256740750)'))",
			346468605699756892L, 346468608880878498L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterIdEq() {
		testFilterString(
			"(organizations.filter(filter='(id eq ''402139267512234420'')'))",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterIdNotEq() {
		testFilterString(
			"(organizations.filter(filter='(id ne ''402139267512234420'')'))",
			346468605699756892L, 346468608880878498L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterLtModifiedDate() {
		testFilterString(
			"(organizations.filter(filter='(modifiedDate lt 1580256740750)'))",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameContains() {
		testFilterString(
			"(organizations.filter(filter='(contains(name, ''child''))'))",
			346468605699756892L, 346468608880878498L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameEq() {
		testFilterString(
			"(organizations.filter(filter='(name eq ''parentOrg'')'))",
			346468603851271125L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameNotEq() {
		testFilterString(
			"(organizations.filter(filter='(name ne ''childOrg1'')'))",
			346468603851271125L, 346468608880878498L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathContains() {
		testFilterString(
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))",
			346468605699756892L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathEq() {
		testFilterString(
			"(organizations.filter(filter='(nameTreePath eq ''parentOrg > " +
				"childOrg1 > grandchild'')'))",
			346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathNotContains() {
		testFilterString(
			"(organizations.filter(filter='(not contains(nameTreePath, " +
				"''childOrg1''))'))",
			346468603851271125L, 346468608880878498L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterParentIdEq() {
		testFilterString(
			"(organizations.filter(filter='(parentId eq " +
				"''402139267512234420'')'))",
			346468605699756892L, 346468608880878498L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterParentIdNe() {
		testFilterString(
			"(organizations.filter(filter='(parentId ne " +
				"''402139267512234420'')'))",
			346468603851271125L, 346468609906122549L);
	}

	@Disabled
	@Test
	public void testOrganizationFilterTypeEq() {
		testFilterString(
			"(organizations.filter(filter='(type eq ''organization'')'))",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L);
	}

	@Test
	public void testSessionsFilterBetweenDay() {
		testFilterString(
			"sessions.filter(filter='between(completeDate, ''2090-08-01'', " +
				"''2090-10-01'')')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterBrowserNameContains() {
		testFilterString(
			"sessions.filter(filter='contains(context/browserName, " +
				"''Unknown'')')",
			346468609906122549L);
	}

	@Test
	public void testSessionsFilterBrowserNameEq() {
		testFilterString(
			"sessions.filter(filter='context/browserName eq ''Chrome''')",
			346468603851271125L, 346468614337714393L, 346468608880878498L);
	}

	@Test
	public void testSessionsFilterBrowserNameNotContains() {
		testFilterString(
			"sessions.filter(filter='not contains(context/browserName, " +
				"''Unknown'')')",
			346468603851271125L, 346468614337714393L, 346468608880878498L);
	}

	@Test
	public void testSessionsFilterDeviceTypeEq() {
		testFilterString(
			"sessions.filter(filter='(context/deviceType eq ''Desktop'')')",
			346468603851271125L, 346468609906122549L, 346468614337714393L,
			346468608880878498L);
	}

	@Test
	public void testSessionsFilterGeolocationEq() {
		testFilterString(
			"sessions.filter(filter='(context/city eq ''French Lick'') and " +
				"(context/country eq ''United States'') and (context/region " +
					"eq ''Indiana'')')",
			346468614337714393L);
	}

	@Test
	public void testSessionsFilterInBetweenDay() {
		testFilterString(
			"sessions.filter(filter='(completeDate gt ''2090-08-01'') and " +
				"(completeDate le ''2090-10-01'')')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterReferrerEq() {
		testFilterString(
			"sessions.filter(filter='context/referrer eq " +
				"''https://www.liferay.com/digital-experience-platform''')",
			346468614337714393L);
	}

	@Test
	public void testSessionsFilterReferrerEqEmpty() {
		testFilterString(
			"sessions.filter(filter='context/referrer eq ''''')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterReferrersEqNull() {
		testFilterString(
			"sessions.filter(filter='(context/referrers eq null)')",
			346468609906122549L);
	}

	@Test
	public void testSessionsFilterSinceCustomDate() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''2090-09-01''')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterSinceLast7Days() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''last7Days''')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterSinceLast24Hours() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''last24Hours''')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterSinceLast28Days() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''last28Days''')",
			346468603851271125L, 346468608880878498L);
	}

	@Test
	public void testSessionsFilterSinceLast30Days() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''last30Days''')",
			346468603851271125L, 346468608880878498L, 346468609906122549L);
	}

	@Test
	public void testSessionsFilterSinceLast90Days() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''last90Days''')",
			346468603851271125L, 346468608880878498L, 346468609906122549L,
			346468614337714393L);
	}

	@Test
	public void testSessionsFilterSinceYesterday() {
		testFilterString(
			"sessions.filter(filter='completeDate gt ''yesterday''')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterURLStartsWith() {
		testFilterString(
			"sessions.filter(filter='startsWith(context/url, " +
				"''https://customer.liferay.com/en/documentation'') and " +
					"between(completeDate, ''2090-09-11'', ''2090-09-12'')')",
			346468603851271125L);
	}

	@Test
	public void testSessionsFilterWithIndividual1() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='(context/country eq ''United States'')')",
			"346468614337714393",
			DSL.field(
				"individual.id"
			).in(
				346468614337714393L
			));
	}

	@Disabled
	@Test
	public void testSessionsFilterWithIndividual2() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='(context/country eq ''United States'')')",
			"346468603851271125", DSL.noCondition());
	}

	protected void testFilterString(
		String filterString, Long... expectedIndividualIds) {

		// TODO Search individuals by filterString

		List<Individual> individuals = Collections.emptyList();

		Stream<Individual> stream = individuals.stream();

		List<Long> ids = stream.map(
			Individual::getId
		).map(
			Long::valueOf
		).collect(
			Collectors.toList()
		);

		MatcherAssert.assertThat(
			expectedIndividualIds,
			Matchers.arrayContainingInAnyOrder(ids.toArray(new Long[0])));
	}

	protected void testFilterStringWithIndividual(
		String filterString, String individualId, Condition expectedCondition) {

		try {
			IndividualIdThreadLocal.setIndividualId(individualId);

			Assertions.assertEquals(
				expectedCondition,
				FilterStringToConditionConverter.convert(
					filterString, _individualsFilterStringConverterHelper));
		}
		finally {
			IndividualIdThreadLocal.remove();
		}
	}

	private void _setUpBQMembershipChanges() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/bq_membership_changes.json",
					this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			BQMembershipChange bqMembershipChange = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), BQMembershipChange.class);

			bqMembershipChange.setIsNew(Boolean.TRUE);

			_bqMembershipChangeRepository.save(bqMembershipChange);
		}
	}

	private void _setUpBQMemberships() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/bq_memberships.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			BQMembership bqMembership = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), BQMembership.class);

			bqMembership.setIsNew(Boolean.TRUE);

			_bqMembershipRepository.save(bqMembership);
		}
	}

	private void _setUpBQOrganizations() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/organizations.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			BQOrganization bqOrganization = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), BQOrganization.class);

			bqOrganization.setIsNew(Boolean.TRUE);

			_bqOrganizationRepository.save(bqOrganization);
		}
	}

	private void _setUpDataSources() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/data_sources.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			DataSource dataSource = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), DataSource.class);

			dataSource.setIsNew(Boolean.TRUE);

			_dataSourceRepository.save(dataSource);
		}
	}

	private void _setUpIndividuals() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/individuals.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Map<Long, BQDataSourceUser> bqDataSourceUsers = new HashMap<>();

			JSONArray dataSourceIndividualPKsJSONArray =
				jsonObject.optJSONArray("dataSourceIndividualPKs");

			if (dataSourceIndividualPKsJSONArray != null) {
				for (int j = 0; j < dataSourceIndividualPKsJSONArray.length();
					 j++) {

					JSONObject dataSourceIndividualPKJSONObject =
						dataSourceIndividualPKsJSONArray.getJSONObject(j);

					Long dataSourceId = Long.valueOf(
						dataSourceIndividualPKJSONObject.getString(
							"dataSourceId"));

					if (bqDataSourceUsers.containsKey(dataSourceId)) {
						BQDataSourceUser bqDataSourceUser =
							bqDataSourceUsers.get(dataSourceId);

						bqDataSourceUser.setUserPKs(
							JSONUtil.toStringSet(
								dataSourceIndividualPKJSONObject.optJSONArray(
									"individualPKs")));
					}
					else {
						bqDataSourceUsers.put(
							dataSourceId,
							_objectMapper.convertValue(
								dataSourceIndividualPKJSONObject,
								BQDataSourceUser.class));
					}
				}
			}

			Individual individual = _objectMapper.convertValue(
				jsonObject, Individual.class);

			individual.setBQDataSourceUsers(
				new HashSet<>(bqDataSourceUsers.values()));

			// TODO Add individual

		}
	}

	private void _setUpSegments() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/individual_segments.json",
					this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Segment segment = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Segment.class);

			segment.setIsNew(Boolean.TRUE);

			_segmentRepository.save(segment);
		}
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

}