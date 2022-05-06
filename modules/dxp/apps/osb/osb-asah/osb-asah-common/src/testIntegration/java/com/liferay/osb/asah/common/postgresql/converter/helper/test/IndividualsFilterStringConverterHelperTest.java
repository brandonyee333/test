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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.converter.FilterStringToConditionConverter;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

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
import org.springframework.data.domain.PageRequest;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class IndividualsFilterStringConverterHelperTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		for (String collectionName : _COLLECTION_NAMES) {
			_faroInfoElasticsearchInvoker.add(
				collectionName,
				new JSONArray(
					TestExecutionListenerUtil.replaceVariables(
						ResourceUtil.readResourceToString(
							"dependencies/osbasahfaroinfo/" +
								StringUtils.replace(collectionName, "-", "_") +
									".json",
							this))));
		}

		_cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/osbasahcerebroinfo/user_sessions.json",
						this))));

		_setUpDataSources();

		_setUpFieldMappings();

		_setUpAccounts();
		_setUpIndividuals();
		_setUpFields();
		_setUpOrganizations();

		_setUpSegments();
		_setUpBQMemberships();
		_setUpBQMembershipChanges();
	}

	@Test
	public void testAccountsFilter() {
		testFilterString(
			"accounts.filter(filter='not startsWith(" +
				"organization/shippingPostalCode/value, ''9'')')",
			346468605699756892L, 346468608880878498L, 346468609906122549L,
			346468614337714393L, 346468617541446459L, 346468620407090009L,
			346468629688998133L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468640520316300L, 346468641321212439L,
			346468642072426931L, 346468647886010657L, 346468649722790279L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468660260465644L, 346468664541254353L, 346468669276672682L,
			346468670578686898L, 346468677661047218L, 346468678682539385L,
			346468679569640045L, 346468680492094349L, 346468683127812925L,
			346468699875814972L, 346468700681239480L, 346468701457781206L);
	}

	@Test
	public void testAccountsFilterByCountEqNegative() {
		testFilterString(
			"accounts.filterByCount(filter='contains(" +
				"organization/billingStreet/value, ''Avenue'')', " +
					"operator='eq', value=-8)");
	}

	@Test
	public void testAccountsFilterByCountEqPositive() {
		testFilterString(
			"accounts.filterByCount(filter='not startsWith(" +
				"organization/description/value, ''Beauty'')', " +
					"operator='eq', value=2)",
			346468638249348979L, 346468636854641829L, 346468677661047218L,
			346468641321212439L, 346468660260465644L, 346468617541446459L,
			346468620407090009L, 346468652526171339L, 346468651775874817L,
			346468609906122549L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountEqZero() {
		testFilterString(
			"accounts.filterByCount(filter='" +
				"organization/numberOfEmployees/value lt 20', operator='eq', " +
					"value=0)",
			346468603851271125L, 346468605699756892L, 346468614337714393L,
			346468617541446459L);
	}

	@Test
	public void testAccountsFilterByCountGeNegative() {
		testFilterString(
			"accounts.filterByCount(filter='organization/fax/value ne null', " +
				"operator='ge', value=-4)",
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

	@Test
	public void testAccountsFilterByCountGePositive() {
		testFilterString(
			"accounts.filterByCount(filter='organization/industry/value ne " +
				"''Technology''', operator='ge', value=2)",
			346468636854641829L, 346468640520316300L, 346468642072426931L,
			346468649722790279L, 346468678682539385L, 346468680492094349L,
			346468699875814972L, 346468701457781206L);
	}

	@Test
	public void testAccountsFilterByCountGeZero() {
		testFilterString(
			"accounts.filterByCount(filter='id eq null', operator='ge', " +
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

	@Test
	public void testAccountsFilterByCountGtBigDecimal() {
		testFilterString(
			"activities.filterByCount(filter='(activityKey eq " +
				"''Blog#blogViewed#509847305915450432'' and day gt " +
					"''last24Hours'')',operator='ge',value=1e+26)");
		testFilterString(
			"activities.filterByCount(filter='(activityKey eq " +
				"''Form#formViewed#511687236573013375'' and day gt " +
					"''last24Hours'')',operator='ge',value=10000000000000000)");
	}

	@Test
	public void testAccountsFilterByCountGtNegative() {
		testFilterString(
			"accounts.filterByCount(filter='organization/phone/value eq " +
				"null', operator='gt', value=-4)",
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

	@Test
	public void testAccountsFilterByCountGtPositive() {
		testFilterString(
			"accounts.filterByCount(filter='not contains(" +
				"organization/description/value, ''LESA'')', operator='gt', " +
					"value=2)",
			346468701457781206L, 346468640520316300L, 346468678682539385L,
			346468629688998133L, 346468683127812925L, 346468670578686898L,
			346468649722790279L, 346468669276672682L, 346468664541254353L,
			346468679569640045L, 346468647886010657L, 346468642072426931L,
			346468699875814972L, 346468680492094349L, 346468700681239480L,
			346468659407593919L);
	}

	@Test
	public void testAccountsFilterByCountGtZero() {
		testFilterString(
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value ne ''USD''', " +
					"operator='gt', value=0)",
			346468701457781206L, 346468640520316300L, 346468605699756892L,
			346468678682539385L, 346468629688998133L, 346468609906122549L,
			346468683127812925L, 346468670578686898L, 346468649722790279L,
			346468664541254353L, 346468638249348979L, 346468636854641829L,
			346468679569640045L, 346468677661047218L, 346468641321212439L,
			346468647886010657L, 346468642072426931L, 346468699875814972L,
			346468631552894612L, 346468680492094349L, 346468617541446459L,
			346468700681239480L, 346468659407593919L, 346468651775874817L);
	}

	@Test
	public void testAccountsFilterByCountLeNegative() {
		testFilterString(
			"accounts.filterByCount(filter='" +
				"organization/shippingCountry/value eq ''United States''', " +
					"operator='le', value=-1)");
	}

	@Test
	public void testAccountsFilterByCountLePositive() {
		testFilterString(
			"accounts.filterByCount(filter='endsWith(" +
				"organization/website/value, ''.org/'')', operator='le'," +
					"value=2)",
			346468701457781206L, 346468649722790279L, 346468699875814972L,
			346468680492094349L, 346468700681239480L, 346468650664446754L,
			346468678682539385L, 346468608880878498L, 346468629688998133L,
			346468638249348979L, 346468669276672682L, 346468677661047218L,
			346468647886010657L, 346468659407593919L, 346468614337714393L,
			346468683127812925L, 346468679569640045L, 346468664541254353L,
			346468617541446459L, 346468640520316300L, 346468605699756892L,
			346468642072426931L, 346468652526171339L, 346468609906122549L,
			346468670578686898L, 346468641321212439L, 346468631552894612L,
			346468660260465644L, 346468603851271125L, 346468620407090009L,
			346468636854641829L, 346468651775874817L);
	}

	@Test
	public void testAccountsFilterByCountLeZero() {
		testFilterString(
			"accounts.filterByCount(filter='organization/annualRevenue/value " +
				"gt 1000000', operator='le', value=0)",
			346468603851271125L, 346468614337714393L, 346468620407090009L,
			346468608880878498L, 346468683127812925L, 346468669276672682L,
			346468638249348979L, 346468679569640045L, 346468677661047218L,
			346468641321212439L, 346468647886010657L, 346468631552894612L,
			346468660260465644L, 346468700681239480L, 346468652526171339L,
			346468650664446754L);
	}

	@Test
	public void testAccountsFilterByCountLtNegative() {
		testFilterString(
			"accounts.filterByCount(filter='organization/ownership/value eq " +
				"''Private''', operator='lt', value=-3)");
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountLtPositive() {
		testFilterString(
			"accounts.filterByCount(filter='organization/shippingState/value " +
				"ne ''California''', operator='lt', value=2)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468631552894612L, 346468636854641829L, 346468650664446754L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468660260465644L, 346468664541254353L, 346468677661047218L,
			346468678682539385L);
	}

	@Test
	public void testAccountsFilterByCountLtZero() {
		testFilterString(
			"accounts.filterByCount(filter='id ne null', operator='lt', " +
				"value=0)");
	}

	@Test
	public void testAccountsFilterByCountNeNegative() {
		testFilterString(
			"accounts.filterByCount(filter='organization/industry/value eq " +
				"''Technology''', operator='ne', value=-23)",
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

	@Test
	public void testAccountsFilterByCountNePositive() {
		testFilterString(
			"accounts.filterByCount(filter='organization/yearStarted/value " +
				"ge 2000', operator='ne', value=2)",
			346468603851271125L, 346468640520316300L, 346468614337714393L,
			346468605699756892L, 346468678682539385L, 346468608880878498L,
			346468609906122549L, 346468683127812925L, 346468664541254353L,
			346468638249348979L, 346468636854641829L, 346468679569640045L,
			346468677661047218L, 346468641321212439L, 346468642072426931L,
			346468699875814972L, 346468631552894612L, 346468660260465644L,
			346468680492094349L, 346468617541446459L, 346468659407593919L,
			346468652526171339L, 346468651775874817L, 346468650664446754L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountNestedAnd() {
		testFilterString(
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value eq ''USD'' and " +
					"organization/yearStarted/value ge 2000', operator='gt', " +
						"value=1)",
			346468651775874817L, 346468659407593919L, 346468664541254353L,
			346468670578686898L, 346468678682539385L, 346468680492094349L,
			346468699875814972L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountNestedOr() {
		testFilterString(
			"accounts.filterByCount(filter='organization/accountType/value " +
				"eq ''Customer'' or organization/annualRevenue/value gt " +
					"10000000', operator='le', value=1)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468614337714393L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468641321212439L, 346468650664446754L,
			346468677661047218L);
	}

	@Test
	public void testAccountsFilterByCountNeZero() {
		testFilterString(
			"accounts.filterByCount(filter='organization/yearStarted/value " +
				"lt 2000', operator='ne', value=0)",
			346468605699756892L, 346468608880878498L, 346468609906122549L,
			346468617541446459L, 346468620407090009L, 346468629688998133L,
			346468636854641829L, 346468638249348979L, 346468640520316300L,
			346468642072426931L, 346468647886010657L, 346468649722790279L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468664541254353L, 346468669276672682L, 346468670578686898L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468699875814972L, 346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountUnnestedAnd() {
		testFilterString(
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value eq ''USD''', operator='" +
					"gt', value=1) and accounts.filterByCount(filter='" +
						"organization/yearStarted/value ge 2000', " +
							"operator='gt', value=1)",
			346468640520316300L, 346468649722790279L, 346468651775874817L,
			346468659407593919L, 346468664541254353L, 346468670578686898L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468699875814972L, 346468700681239480L, 346468701457781206L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountUnnestedOr() {
		testFilterString(
			"accounts.filterByCount(filter='organization/accountType/value " +
				"eq ''Customer''', operator='le', value=1) or " +
					"accounts.filterByCount(filter='" +
						"organization/annualRevenue/value gt 10000000', " +
							"operator='le', value=1)",
			346468603851271125L, 346468605699756892L, 346468608880878498L,
			346468609906122549L, 346468614337714393L, 346468617541446459L,
			346468620407090009L, 346468631552894612L, 346468636854641829L,
			346468638249348979L, 346468640520316300L, 346468641321212439L,
			346468642072426931L, 346468647886010657L, 346468650664446754L,
			346468651775874817L, 346468652526171339L, 346468659407593919L,
			346468660260465644L, 346468669276672682L, 346468677661047218L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468683127812925L, 346468700681239480L);
	}

	@Disabled
	@Test
	public void testAccountsFilterByCountWithApostrophe() {
		testFilterString(
			"accounts.filterByCount(filter='organization/accountName/value " +
				"ne ''The World''''s Foremost Chess Club''', operator='gt', " +
					"value=2)",
			346468640520316300L, 346468649722790279L, 346468659407593919L,
			346468670578686898L, 346468678682539385L, 346468679569640045L,
			346468680492094349L, 346468699875814972L, 346468700681239480L,
			346468701457781206L);
	}

	@Test
	public void testAccountsFilterByCountWithIndividual() {
		testFilterStringWithIndividual(
			"accounts.filterByCount(filter='organization/yearStarted/value " +
				"lt 2000', operator='ne', value=0)",
			1L,
			DSL.and(
				DSL.or(
					DSL.and(
						DSL.field(
							"datasourceindividual.datasourceid"
						).eq(
							346306699042460013L
						),
						DSL.field(
							DSL.cast(
								DSL.array(
									DSL.field(
										"datasourceindividual.accountpks")),
								String[].class)
						).contains(
							DSL.cast(
								DSL.array(
									"bef7a4a0-7128-4e37-b58d-a3fdff2e4213"),
								String[].class)
						)),
					DSL.and(
						DSL.field(
							"datasourceindividual.datasourceid"
						).eq(
							346306699042460013L
						),
						DSL.field(
							DSL.cast(
								DSL.array(
									DSL.field(
										"datasourceindividual.accountpks")),
								String[].class)
						).contains(
							DSL.cast(
								DSL.array(
									"7dc0bc87-2d53-40d5-8137-2db03470adda"),
								String[].class)
						))),
				DSL.field(
					"id"
				).eq(
					1
				)));
	}

	@Test
	public void testAccountsFilterByCountWithInvalidOperatorThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class, "Unknown operator: is",
			"accounts.filterByCount(filter='organization/ownership/value eq " +
				"''Private''', operator='is', value=3)");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleFiltersThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for filter",
			"accounts.filterByCount(filter='" +
				"organization/billingPostalCode/value ne null', " +
					"operator='gt', value=1, filter='" +
						"organization/shippingPostalCode/value eq null')");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleOperatorsThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for operator",
			"accounts.filterByCount(filter='" +
				"organization/billingCountry/value ne ''United States''', " +
					"operator='ge', value=3, operator='eq')");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleValuesThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for value",
			"accounts.filterByCount(filter='" +
				"organization/shippingState/value eq ''California''', " +
					"operator='gt', value=0, value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithNonnumericValueThrowsException() {
		_testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='startsWith(" +
				"organization/billingStreet/value, ''1'')', operator='eq', " +
					"value='Avenue')");
	}

	@Test
	public void testAccountsFilterByCountWithOperatorButNoValueThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"The arguments operator and value must either both be set or " +
				"both be null",
			"accounts.filterByCount(filter='contains(" +
				"organization/shippingStreet/value, ''6'')', operator='gt')");
	}

	@Test
	public void testAccountsFilterByCountWithUnparseableFilterThrowsException() {
		_testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='" +
				"(organization/phone/value exists', operator='eq', value=2)");
	}

	@Test
	public void testAccountsFilterByCountWithUnquotedFilterThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected organization/numberOfEmployees/value gt 100 to be " +
				"fully surrounded by single quotes",
			"accounts.filterByCount(filter=" +
				"organization/numberOfEmployees/value gt 100, operator='le', " +
					"value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithUnquotedOperatorThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected ne to be fully surrounded by single quotes",
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value ne ''USD''', " +
					"operator=ne, value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithValueButNoOperatorThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"The arguments operator and value must either both be set or " +
				"both be null",
			"accounts.filterByCount(filter='contains(" +
				"organization/phone/value, ''0'')', value=0)");
	}

	@Disabled
	@Test
	public void testAccountsFilterNestedAnd() {
		testFilterString(
			"accounts.filter(filter='endsWith(organization/website/value, " +
				"''.com/'') and organization/currencyIsoCode/value eq " +
					"''USD''')",
			346468605699756892L, 346468609906122549L, 346468617541446459L,
			346468629688998133L, 346468636854641829L, 346468640520316300L,
			346468642072426931L, 346468649722790279L, 346468651775874817L,
			346468659407593919L, 346468664541254353L, 346468670578686898L,
			346468678682539385L, 346468680492094349L, 346468699875814972L,
			346468701457781206L);
	}

	@Disabled
	@Test
	public void testAccountsFilterUnnestedAnd() {
		testFilterString(
			"accounts.filter(filter='endsWith(organization/website/value, " +
				"''.com/'')') and accounts.filter(filter=" +
					"'organization/currencyIsoCode/value eq ''USD''')",
			346468605699756892L, 346468609906122549L, 346468617541446459L,
			346468629688998133L, 346468636854641829L, 346468638249348979L,
			346468640520316300L, 346468642072426931L, 346468647886010657L,
			346468649722790279L, 346468651775874817L, 346468659407593919L,
			346468664541254353L, 346468670578686898L, 346468677661047218L,
			346468678682539385L, 346468679569640045L, 346468680492094349L,
			346468683127812925L, 346468699875814972L, 346468700681239480L,
			346468701457781206L);
	}

	@Test
	public void testAccountsFilterWithApostrophe() {
		testFilterString(
			"accounts.filter(filter='organization/accountName/value eq ''The " +
				"World''''s Foremost Chess Club''')",
			346468701457781206L, 346468609906122549L, 346468670578686898L,
			346468649722790279L, 346468664541254353L, 346468699875814972L,
			346468680492094349L, 346468617541446459L, 346468659407593919L,
			346468640520316300L, 346468605699756892L, 346468678682539385L,
			346468629688998133L, 346468636854641829L, 346468642072426931L,
			346468651775874817L);
	}

	@Test
	public void testAccountsFilterWithMultipleFiltersThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for filter",
			"accounts.filter(filter='organization/accountType/value eq " +
				"''Customer''', filter='startsWith(" +
					"organization/accountName/value, ''The'')')");
	}

	@Test
	public void testAccountsFilterWithUnparseableFilterThrowsException() {
		_testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='organization/phone/value eq " +
				"1-877-543-3729')");
	}

	@Test
	public void testAccountsFilterWithUnquotedFilterThrowsException() {
		_testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected organization/yearStarted/value lt 2000 to be fully " +
				"surrounded by single quotes",
			"accounts.filter(filter=organization/yearStarted/value lt 2000)");
	}

	@Disabled
	@Test
	public void testActivitiesFilterBetweenDay() {
		testFilterString(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			346468603851271125L);
	}

	@Test
	public void testActivitiesFilterByCountAtLeastOne() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			346468603851271125L, 346468605699756892L);
	}

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

	@Test
	public void testActivitiesFilterByCountEqNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=-1)");
	}

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

	@Test
	public void testActivitiesFilterByCountGePositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			346468603851271125L, 346468605699756892L);
	}

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

	@Test
	public void testActivitiesFilterByCountGtPositive() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=1)",
			346468603851271125L);
	}

	@Test
	public void testActivitiesFilterByCountGtZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=0)",
			346468603851271125L, 346468605699756892L);
	}

	@Test
	public void testActivitiesFilterByCountLeNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=-1)");
	}

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

	@Test
	public void testActivitiesFilterByCountLtNegative() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=-1)");
	}

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

	@Test
	public void testActivitiesFilterByCountLtZero() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=0)");
	}

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

	@Test
	public void testActivitiesFilterByCountSinceLast7Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last7Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Test
	public void testActivitiesFilterByCountSinceLast24Hours() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last24Hours''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Test
	public void testActivitiesFilterByCountSinceLast28Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last28Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L);
	}

	@Test
	public void testActivitiesFilterByCountSinceLast30Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last30Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L,
			346468609906122549L);
	}

	@Test
	public void testActivitiesFilterByCountSinceLast90Days() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last90Days''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L, 346468608880878498L,
			346468609906122549L, 346468614337714393L);
	}

	@Test
	public void testActivitiesFilterByCountSinceYesterday() {
		testFilterString(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			346468699875814972L, 346468603851271125L);
	}

	@Test
	public void testActivitiesFilterByCountWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994''', operator='eq', " +
					"value=1)",
			346468699875814972L,
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

	@Test
	public void testActivitiesFilterWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			346468603851271125L,
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
			346468700681239480L,
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

	@Test
	public void testOrganizationFilterIdEq() {
		testFilterString(
			"(organizations.filter(filter='(id eq ''402139267512234420'')'))",
			346468603851271125L);
	}

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

	@Test
	public void testOrganizationFilterNameContains() {
		testFilterString(
			"(organizations.filter(filter='(contains(name, ''child''))'))",
			346468605699756892L, 346468608880878498L, 346468609906122549L);
	}

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

	@Test
	public void testOrganizationFilterNameTreePathContains() {
		testFilterString(
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))",
			346468605699756892L, 346468609906122549L);
	}

	@Test
	public void testOrganizationFilterNameTreePathEq() {
		testFilterString(
			"(organizations.filter(filter='(nameTreePath eq ''parentOrg > " +
				"childOrg1 > grandchild'')'))",
			346468609906122549L);
	}

	@Test
	public void testOrganizationFilterNameTreePathNotContains() {
		testFilterString(
			"(organizations.filter(filter='(not contains(nameTreePath, " +
				"''childOrg1''))'))",
			346468603851271125L, 346468608880878498L);
	}

	@Test
	public void testOrganizationFilterParentIdEq() {
		testFilterString(
			"(organizations.filter(filter='(parentId eq " +
				"''402139267512234420'')'))",
			346468605699756892L, 346468608880878498L);
	}

	@Test
	public void testOrganizationFilterParentIdNe() {
		testFilterString(
			"(organizations.filter(filter='(parentId ne " +
				"''402139267512234420'')'))",
			346468603851271125L, 346468609906122549L);
	}

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
			346468614337714393L,
			DSL.field(
				"individual.id"
			).in(
				346468614337714393L
			));
	}

	@Test
	public void testSessionsFilterWithIndividual2() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='(context/country eq ''United States'')')",
			346468603851271125L, DSL.noCondition());
	}

	protected void testFilterString(
		String filterString, Long... expectedIndividualIds) {

		List<Individual> individuals = _individualRepository.searchIndividuals(
			new FilterHelper(
				_faroInfoIndividualsFilterStringConverterHelper, filterString,
				_individualsFilterStringConverterHelper),
			PageRequest.of(0, 100));

		Stream<Individual> stream = individuals.stream();

		List<Long> ids = stream.map(
			Individual::getId
		).collect(
			Collectors.toList()
		);

		MatcherAssert.assertThat(
			expectedIndividualIds,
			Matchers.arrayContainingInAnyOrder(ids.toArray(new Long[0])));
	}

	protected void testFilterStringWithIndividual(
		String filterString, Long individualId, Condition expectedCondition) {

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

	private void _setUpAccounts() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/accounts.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Account account = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Account.class);

			account.setIsNew(Boolean.TRUE);

			_accountRepository.save(account);
		}
	}

	private void _setUpBQMembershipChanges() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/individuals.json", this)));

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

	private void _setUpFieldMappings() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/field_mappings.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			FieldMapping fieldMapping = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), FieldMapping.class);

			fieldMapping.setIsNew(Boolean.TRUE);

			_fieldMappingRepository.save(fieldMapping);
		}
	}

	private void _setUpFields() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/fields.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Field field = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Field.class);

			field.setIsNew(Boolean.TRUE);

			_fieldRepository.save(field);
		}
	}

	private void _setUpIndividuals() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/individuals.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Map<Long, DataSourceIndividual> dataSourceIndividuals =
				new HashMap<>();

			JSONArray dataSourceAccountPKsJSONArray = jsonObject.optJSONArray(
				"dataSourceAccountPKs");

			if (dataSourceAccountPKsJSONArray != null) {
				for (int j = 0; j < dataSourceAccountPKsJSONArray.length();
					 j++) {

					JSONObject dataSourceAccountPKJSONObject =
						dataSourceAccountPKsJSONArray.getJSONObject(j);

					dataSourceIndividuals.put(
						dataSourceAccountPKJSONObject.getLong("dataSourceId"),
						_objectMapper.convertValue(
							dataSourceAccountPKJSONObject,
							DataSourceIndividual.class));
				}
			}

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

					if (dataSourceIndividuals.containsKey(dataSourceId)) {
						DataSourceIndividual dataSourceIndividual =
							dataSourceIndividuals.get(dataSourceId);

						dataSourceIndividual.setIndividualPKs(
							JSONUtil.toStringSet(
								dataSourceIndividualPKJSONObject.optJSONArray(
									"individualPKs")));
					}
					else {
						dataSourceIndividuals.put(
							dataSourceId,
							_objectMapper.convertValue(
								dataSourceIndividualPKJSONObject,
								DataSourceIndividual.class));
					}
				}
			}

			Individual individual = _objectMapper.convertValue(
				jsonObject, Individual.class);

			individual.setDataSourceIndividuals(
				new HashSet<>(dataSourceIndividuals.values()));
			individual.setIsNew(Boolean.TRUE);

			_individualRepository.save(individual);
		}
	}

	private void _setUpOrganizations() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/osbasahfaroinfo/organizations.json", this)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Organization organization = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Organization.class);

			organization.setIsNew(Boolean.TRUE);

			_organizationRepository.save(organization);
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

	private void _testFilterStringThrowsException(
		Class<? extends Throwable> expectedExceptionClass,
		String expectedMessage, String filterString) {

		try {
			FilterStringToConditionConverter.convert(
				filterString, _individualsFilterStringConverterHelper);

			Assertions.fail();
		}
		catch (Exception exception) {
			if (expectedExceptionClass == null) {
				return;
			}

			Throwable throwable = exception;

			while (throwable.getCause() != null) {
				throwable = throwable.getCause();
			}

			Class<?> clazz = throwable.getClass();

			Assertions.assertEquals(
				clazz, expectedExceptionClass,
				"Expected innermost throwable to be of type " +
					expectedExceptionClass.getName() + ", but was " +
						clazz.getName());

			if (expectedMessage == null) {
				return;
			}

			Assertions.assertEquals(expectedMessage, throwable.getMessage());
		}
	}

	private static final String[] _COLLECTION_NAMES = {
		"activities", "interests"
	};

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationRepository _organizationRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}