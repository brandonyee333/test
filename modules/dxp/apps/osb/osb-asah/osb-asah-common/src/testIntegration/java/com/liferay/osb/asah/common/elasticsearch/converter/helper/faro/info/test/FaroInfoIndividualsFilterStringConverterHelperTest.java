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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.test;

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.BQFieldMappingRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Disabled
@RepositoryResource(
	repositoryClass = BQOrganizationRepository.class,
	resourcePath = "osbasahfaroinfo/organizations.json"
)
@RepositoryResource(
	repositoryClass = BQEventRepository.class,
	resourcePath = "osbasahfaroinfo/events.json"
)
@RepositoryResource(
	repositoryClass = BQFieldMappingRepository.class,
	resourcePath = "osbasahfaroinfo/field_mappings.json"
)
@RepositoryResource(
	repositoryClass = BQIndividualRepository.class,
	resourcePath = "osbasahfaroinfo/individuals.json"
)
@RepositoryResource(
	repositoryClass = BQSessionRepository.class,
	resourcePath = "osbasahcerebroinfo/user_sessions.json"
)
public class FaroInfoIndividualsFilterStringConverterHelperTest
	extends BaseFaroInfoFilterStringConverterHelperTestCase {

	@Override
	public FilterStringConverterHelper getFilterStringConverterHelper() {
		return _faroInfoIndividualsFilterStringConverterHelper;
	}

	@Disabled
	@Test
	public void testActivitiesFilterBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountAtLeastOne() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			"346468603851271125", "346468605699756892");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountAtMostOne() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=1)",
			"346468605699756892", "346468608880878498", "346468609906122549",
			"346468614337714393", "346468617541446459", "346468620407090009",
			"346468629688998133", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468650664446754", "346468651775874817", "346468652526171339",
			"346468659407593919", "346468660260465644", "346468664541254353",
			"346468669276672682", "346468670578686898", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqPositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=1)",
			"346468605699756892");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountEqZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=0)",
			"346468608880878498", "346468609906122549", "346468614337714393",
			"346468617541446459", "346468620407090009", "346468629688998133",
			"346468631552894612", "346468636854641829", "346468638249348979",
			"346468640520316300", "346468641321212439", "346468642072426931",
			"346468647886010657", "346468649722790279", "346468650664446754",
			"346468651775874817", "346468652526171339", "346468659407593919",
			"346468660260465644", "346468664541254353", "346468669276672682",
			"346468670578686898", "346468677661047218", "346468678682539385",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGeNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=-1)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468699875814972",
			"346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGePositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			"346468603851271125", "346468605699756892");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGeZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=0)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468699875814972",
			"346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=-1)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468699875814972",
			"346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtPositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=1)",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountGtZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=0)",
			"346468603851271125", "346468605699756892");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLeNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLePositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=2)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468699875814972",
			"346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLeZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=0)",
			"346468608880878498", "346468609906122549", "346468614337714393",
			"346468617541446459", "346468620407090009", "346468629688998133",
			"346468631552894612", "346468636854641829", "346468638249348979",
			"346468640520316300", "346468641321212439", "346468642072426931",
			"346468647886010657", "346468649722790279", "346468650664446754",
			"346468651775874817", "346468652526171339", "346468659407593919",
			"346468660260465644", "346468664541254353", "346468669276672682",
			"346468670578686898", "346468677661047218", "346468678682539385",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=-1)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtPositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=2)",
			"346468605699756892", "346468608880878498", "346468609906122549",
			"346468614337714393", "346468617541446459", "346468620407090009",
			"346468629688998133", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468650664446754", "346468651775874817", "346468652526171339",
			"346468659407593919", "346468660260465644", "346468664541254353",
			"346468669276672682", "346468670578686898", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountLtZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=0)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNeNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=-1)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468699875814972",
			"346468700681239480", "346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNePositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=1)",
			"346468603851271125", "346468608880878498", "346468609906122549",
			"346468614337714393", "346468617541446459", "346468620407090009",
			"346468629688998133", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468650664446754", "346468651775874817", "346468652526171339",
			"346468659407593919", "346468660260465644", "346468664541254353",
			"346468669276672682", "346468670578686898", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountNeZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=0)",
			"346468603851271125", "346468605699756892");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast7Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last7Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast24Hours() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last24Hours''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast28Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last28Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125", "346468608880878498");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast30Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last30Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125", "346468608880878498",
			"346468609906122549");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceLast90Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last90Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125", "346468608880878498",
			"346468609906122549", "346468614337714393");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountSinceYesterday() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountWithIndividual1() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			"1", null);
	}

	@Disabled
	@Test
	public void testActivitiesFilterByCountWithIndividual2() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994''', operator='ge', " +
					"value=1)",
			"346468614337714393", null);
	}

	@Disabled
	@Test
	public void testActivitiesFilterGtDay() throws Exception {
		testFilterString(
			"individuals", "activities.filter(filter='day gt ''2019-04-09''')",
			"346468699875814972", "346468603851271125", "346468608880878498",
			"346468609906122549", "346468614337714393");
	}

	@Disabled
	@Test
	public void testActivitiesFilterInBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"(activities.filter(filter='day le ''2019-03-31''') and " +
				"activities.filter(filter='day gt ''2019-03-01'''))",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterLtDay() throws Exception {
		testFilterString(
			"individuals", "activities.filter(filter='day lt ''2019-03-07''')",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testActivitiesFilterWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			"1", null);
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testFilterByUserId() throws Exception {
		testFilterString(
			"individuals", "userId eq '386700253520720569'",
			"346468603851271125");
	}

	@Test
	public void testFreestyle() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("sessions.filter(filter='context/browserName eq ''Chrome'' ");
		sb.append("and contains(context/city, ''French'') and ");
		sb.append("(context/region eq ''Indiana'') and (completeDate gt ''");
		sb.append("last90Days'')')");

		testFilterString("individuals", sb.toString(), "346468614337714393");
	}

	@RepositoryResource(
		repositoryClass = InterestRepository.class,
		resourcePath = "osbasahfaroinfo/interests.json"
	)
	@Test
	public void testInterestsFilter() throws Exception {
		testFilterString(
			"individuals",
			"interests.filter(filter='(((name eq ''abc'')) and (score eq " +
				"''true''))')");
		testFilterString(
			"individuals",
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
			"individuals",
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''true'')')",
			"346468699875814972", "346468700681239480");
		testFilterString(
			"individuals",
			"interests.filter(filter='(name eq ''abc'') and (score eq " +
				"''false'')')",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468677661047218", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468683127812925", "346468701457781206");
	}

	@RepositoryResource(
		repositoryClass = InterestRepository.class,
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
			"346468700681239480", null);
	}

	@Disabled
	@Test
	public void testOrganizationFilterGtModifiedDate() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(modifiedDate gt 1580256740750)'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterIdEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(id eq ''402139267512234420'')'))",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testOrganizationFilterIdNotEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(id ne ''402139267512234420'')'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterLtModifiedDate() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(modifiedDate lt 1580256740750)'))",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameContains() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(contains(name, ''child''))'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(name eq ''parentOrg'')'))",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameNotEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(name ne ''childOrg1'')'))",
			"346468603851271125", "346468608880878498", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathContains() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))",
			"346468605699756892", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(nameTreePath eq ''parentOrg > " +
				"childOrg1 > grandchild'')'))",
			"346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterNameTreePathNotContains()
		throws Exception {

		testFilterString(
			"individuals",
			"(organizations.filter(filter='(not contains(nameTreePath, " +
				"''childOrg1''))'))",
			"346468603851271125", "346468608880878498");
	}

	@Disabled
	@Test
	public void testOrganizationFilterParentIdEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(parentId eq " +
				"''402139267512234420'')'))",
			"346468605699756892", "346468608880878498");
	}

	@Disabled
	@Test
	public void testOrganizationFilterParentIdNe() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(parentId ne " +
				"''402139267512234420'')'))",
			"346468603851271125", "346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterTypeEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(type eq ''organization'')'))",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549");
	}

	@Disabled
	@Test
	public void testOrganizationFilterWithFilter() {
		try {
			IndividualIdThreadLocal.setIndividualId("1");

			// TODO

		}
		finally {
			IndividualIdThreadLocal.remove();
		}
	}

	@Test
	public void testSessionsFilterBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='between(completeDate, ''2090-08-01'', " +
				"''2090-10-01'')')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterBrowserNameContains() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='contains(context/browserName, " +
				"''Unknown'')')",
			"346468609906122549");
	}

	@Test
	public void testSessionsFilterBrowserNameEq() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='context/browserName eq ''Chrome''')",
			"346468603851271125", "346468614337714393", "346468608880878498");
	}

	@Test
	public void testSessionsFilterBrowserNameNotContains() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='not contains(context/browserName, " +
				"''Unknown'')')",
			"346468603851271125", "346468614337714393", "346468608880878498");
	}

	@Test
	public void testSessionsFilterDeviceTypeEq() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='(context/deviceType eq ''Desktop'')')",
			"346468603851271125", "346468609906122549", "346468614337714393",
			"346468608880878498");
	}

	@Test
	public void testSessionsFilterGeolocationEq() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='(context/city eq ''French Lick'') and " +
				"(context/country eq ''United States'') and (context/region " +
					"eq ''Indiana'')')",
			"346468614337714393");
	}

	@Test
	public void testSessionsFilterInBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='(completeDate gt ''2090-08-01'') and " +
				"(completeDate le ''2090-10-01'')')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterReferrerEq() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='context/referrer eq " +
				"''https://www.liferay.com/digital-experience-platform''')",
			"346468614337714393");
	}

	@Test
	public void testSessionsFilterReferrerEqEmpty() throws Exception {
		testFilterString(
			"individuals", "sessions.filter(filter='context/referrer eq ''''')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterReferrersEqNull() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='(context/referrers eq null)')",
			"346468609906122549");
	}

	@Test
	public void testSessionsFilterSinceCustomDate() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''2090-09-01''')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterSinceLast7Days() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''last7Days''')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterSinceLast24Hours() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''last24Hours''')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterSinceLast28Days() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''last28Days''')",
			"346468603851271125", "346468608880878498");
	}

	@Test
	public void testSessionsFilterSinceLast30Days() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''last30Days''')",
			"346468603851271125", "346468608880878498", "346468609906122549");
	}

	@Test
	public void testSessionsFilterSinceLast90Days() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''last90Days''')",
			"346468603851271125", "346468608880878498", "346468609906122549",
			"346468614337714393");
	}

	@Test
	public void testSessionsFilterSinceYesterday() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='completeDate gt ''yesterday''')",
			"346468603851271125");
	}

	@Test
	public void testSessionsFilterURLStartsWith() throws Exception {
		testFilterString(
			"individuals",
			"sessions.filter(filter='startsWith(context/url, " +
				"''https://customer.liferay.com/en/documentation'') and " +
					"between(completeDate, ''2090-09-11'', ''2090-09-12'')')",
			"346468603851271125");
	}

	@Disabled
	@Test
	public void testSessionsFilterWithIndividual1() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='context/city eq ''Tokyo''')",
			"346468603851271125", null);
	}

	@Disabled
	@Test
	public void testSessionsFilterWithIndividual2() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='context/city eq ''Budapest''')",
			"346468603851271125", null);
	}

	protected void testFilterStringWithIndividual(
		String filterString, String individualId, Object expectedQueryBuilder) {

		try {
			IndividualIdThreadLocal.setIndividualId(individualId);

			Assertions.assertEquals(expectedQueryBuilder, null);
		}
		finally {
			IndividualIdThreadLocal.remove();
		}
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

}