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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import java.util.Collections;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@ElasticsearchIndex(
	name = "organizations", resourcePath = "organizations.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "user-sessions", resourcePath = "user_sessions.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
)
public class FaroInfoIndividualsFilterStringConverterHelperTest
	extends BaseFaroInfoFilterStringConverterHelperTestCase {

	@Override
	public FilterStringConverterHelper getFilterStringConverterHelper() {
		return _faroInfoIndividualsFilterStringConverterHelper;
	}

	@Test
	public void testAccountsFilter() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filter(filter='not startsWith(" +
				"organization/shippingPostalCode/value, ''9'')')",
			"346468608880878498", "346468609906122549", "346468614337714393",
			"346468617541446459", "346468620407090009", "346468629688998133",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountEqNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='contains(" +
				"organization/billingStreet/value, ''Avenue'')', " +
					"operator='eq', value=-8)");
	}

	@Test
	public void testAccountsFilterByCountEqPositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='not startsWith(" +
				"organization/description/value, ''Beauty'')', " +
					"operator='eq', value=2)",
			"346468609906122549", "346468617541446459", "346468620407090009",
			"346468640520316300", "346468642072426931", "346468647886010657",
			"346468651775874817", "346468652526171339", "346468660260465644",
			"346468678682539385", "346468679569640045", "346468683127812925");
	}

	@Test
	public void testAccountsFilterByCountEqZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='" +
				"organization/numberOfEmployees/value lt 20', operator='eq', " +
					"value=0)",
			"346468603851271125", "346468605699756892", "346468614337714393",
			"346468617541446459");
	}

	@Test
	public void testAccountsFilterByCountGeNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/fax/value ne null', " +
				"operator='ge', value=-4)",
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

	@Test
	public void testAccountsFilterByCountGePositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/industry/value ne " +
				"''Technology''', operator='ge', value=2)",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468660260465644", "346468664541254353",
			"346468669276672682", "346468670578686898", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountGeZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='id eq null', operator='ge', " +
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

	@Test
	public void testAccountsFilterByCountGtBigDecimal() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='(activityKey eq " +
				"''Blog#blogViewed#509847305915450432'' and day gt " +
					"''last24Hours'')',operator='ge',value=1e+26)");
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='(activityKey eq " +
				"''Form#formViewed#511687236573013375'' and day gt " +
					"''last24Hours'')',operator='ge',value=10000000000000000)");
	}

	@Test
	public void testAccountsFilterByCountGtNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/phone/value eq " +
				"null', operator='gt', value=-4)",
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

	@Test
	public void testAccountsFilterByCountGtPositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='not contains(" +
				"organization/description/value, ''LESA'')', operator='gt', " +
					"value=2)",
			"346468642072426931", "346468649722790279", "346468664541254353",
			"346468670578686898", "346468678682539385", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountGtZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value ne ''USD''', " +
					"operator='gt', value=0)",
			"346468614337714393", "346468617541446459", "346468620407090009",
			"346468629688998133", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468660260465644", "346468664541254353", "346468669276672682",
			"346468670578686898", "346468677661047218", "346468678682539385",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountLeNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='" +
				"organization/shippingCountry/value eq ''United States''', " +
					"operator='le', value=-1)");
	}

	@Test
	public void testAccountsFilterByCountLePositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='endsWith(" +
				"organization/website/value, ''.org/'')', operator='le'," +
					"value=2)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468636854641829", "346468638249348979", "346468640520316300",
			"346468641321212439", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468650664446754", "346468651775874817",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468677661047218", "346468678682539385",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972");
	}

	@Test
	public void testAccountsFilterByCountLeZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/annualRevenue/value " +
				"gt 1000000', operator='le', value=0)",
			"346468603851271125", "346468608880878498", "346468631552894612",
			"346468638249348979", "346468650664446754", "346468652526171339",
			"346468677661047218", "346468679569640045");
	}

	@Test
	public void testAccountsFilterByCountLtNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/ownership/value eq " +
				"''Private''', operator='lt', value=-3)");
	}

	@Test
	public void testAccountsFilterByCountLtPositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/shippingState/value " +
				"ne ''California''', operator='lt', value=2)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468631552894612", "346468636854641829", "346468650664446754",
			"346468651775874817", "346468652526171339", "346468659407593919",
			"346468660260465644", "346468664541254353", "346468677661047218",
			"346468678682539385");
	}

	@Test
	public void testAccountsFilterByCountLtZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='id ne null', operator='lt', " +
				"value=0)");
	}

	@Test
	public void testAccountsFilterByCountNeNegative() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/industry/value eq " +
				"''Technology''', operator='ne', value=-23)",
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

	@Test
	public void testAccountsFilterByCountNePositive() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/yearStarted/value " +
				"ge 2000', operator='ne', value=2)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468629688998133", "346468631552894612",
			"346468638249348979", "346468641321212439", "346468647886010657",
			"346468650664446754", "346468652526171339", "346468660260465644",
			"346468669276672682", "346468678682539385", "346468680492094349",
			"346468699875814972", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountNestedAnd() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value eq ''USD'' and " +
					"organization/yearStarted/value ge 2000', operator='gt', " +
						"value=1)",
			"346468651775874817", "346468659407593919", "346468664541254353",
			"346468670578686898", "346468678682539385", "346468680492094349",
			"346468699875814972", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountNestedOr() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/accountType/value " +
				"eq ''Customer'' or organization/annualRevenue/value gt " +
					"10000000', operator='le', value=1)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468614337714393", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468641321212439", "346468650664446754",
			"346468677661047218");
	}

	@Test
	public void testAccountsFilterByCountNeZero() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/yearStarted/value " +
				"lt 2000', operator='ne', value=0)",
			"346468608880878498", "346468609906122549", "346468614337714393",
			"346468617541446459", "346468620407090009", "346468629688998133",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468649722790279",
			"346468652526171339", "346468659407593919", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468679569640045", "346468680492094349", "346468683127812925",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountUnnestedAnd() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value eq ''USD''', operator='" +
					"gt', value=1) and accounts.filterByCount(filter='" +
						"organization/yearStarted/value ge 2000', " +
							"operator='gt', value=1)",
			"346468640520316300", "346468649722790279", "346468651775874817",
			"346468659407593919", "346468664541254353", "346468670578686898",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468699875814972", "346468700681239480", "346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountUnnestedOr() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/accountType/value " +
				"eq ''Customer''', operator='le', value=1) or " +
					"accounts.filterByCount(filter='" +
						"organization/annualRevenue/value gt 10000000', " +
							"operator='le', value=1)",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549", "346468614337714393", "346468617541446459",
			"346468620407090009", "346468631552894612", "346468636854641829",
			"346468638249348979", "346468640520316300", "346468641321212439",
			"346468642072426931", "346468647886010657", "346468650664446754",
			"346468651775874817", "346468652526171339", "346468659407593919",
			"346468660260465644", "346468669276672682", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468700681239480");
	}

	@Test
	public void testAccountsFilterByCountWithApostrophe() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filterByCount(filter='organization/accountName/value " +
				"ne ''The World''''s Foremost Chess Club''', operator='gt', " +
					"value=2)",
			"346468640520316300", "346468649722790279", "346468659407593919",
			"346468670578686898", "346468678682539385", "346468679569640045",
			"346468680492094349", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterByCountWithIndividual() {
		testFilterStringWithIndividual(
			"accounts.filterByCount(filter='organization/yearStarted" +
				"/value lt 2000', operator='ne', value=0)",
			1L,
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("id", 1L)
			).should(
				QueryBuilders.nestedQuery(
					"dataSourceAccountPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"dataSourceAccountPKs.accountPKs",
							"7dc0bc87-2d53-40d5-8137-2db03470adda")
					).filter(
						QueryBuilders.termQuery(
							"dataSourceAccountPKs.dataSourceId",
							"346306699042460013")
					),
					ScoreMode.None)
			).should(
				QueryBuilders.nestedQuery(
					"dataSourceAccountPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"dataSourceAccountPKs.accountPKs",
							"e15f3b2f-7cad-4772-b844-0d96d2f27528")
					).filter(
						QueryBuilders.termQuery(
							"dataSourceAccountPKs.dataSourceId",
							"346306699042460013")
					),
					ScoreMode.None)
			));
	}

	@Test
	public void testAccountsFilterByCountWithInvalidOperatorThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class, "Unknown operator: is",
			"accounts.filterByCount(filter='organization/ownership/value eq " +
				"''Private''', operator='is', value=3)");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleFiltersThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for filter",
			"accounts.filterByCount(filter='" +
				"organization/billingPostalCode/value ne null', " +
					"operator='gt', value=1, filter='" +
						"organization/shippingPostalCode/value eq null')");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleOperatorsThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for operator",
			"accounts.filterByCount(filter='" +
				"organization/billingCountry/value ne ''United States''', " +
					"operator='ge', value=3, operator='eq')");
	}

	@Test
	public void testAccountsFilterByCountWithMultipleValuesThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for value",
			"accounts.filterByCount(filter='" +
				"organization/shippingState/value eq ''California''', " +
					"operator='gt', value=0, value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithNonnumericValueThrowsException() {
		testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='startsWith(" +
				"organization/billingStreet/value, ''1'')', operator='eq', " +
					"value='Avenue')");
	}

	@Test
	public void testAccountsFilterByCountWithOperatorButNoValueThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"The arguments operator and value must either both be set or " +
				"both be null",
			"accounts.filterByCount(filter='contains(" +
				"organization/shippingStreet/value, ''6'')', operator='gt')");
	}

	@Test
	public void testAccountsFilterByCountWithUnparseableFilterThrowsException() {
		testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='" +
				"(organization/phone/value exists', operator='eq', value=2)");
	}

	@Test
	public void testAccountsFilterByCountWithUnquotedFilterThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected organization/numberOfEmployees/value gt 100 to be " +
				"fully surrounded by single quotes",
			"accounts.filterByCount(filter=" +
				"organization/numberOfEmployees/value gt 100, operator='le', " +
					"value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithUnquotedOperatorThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected ne to be fully surrounded by single quotes",
			"accounts.filterByCount(filter='" +
				"organization/currencyIsoCode/value ne ''USD''', " +
					"operator=ne, value=1)");
	}

	@Test
	public void testAccountsFilterByCountWithValueButNoOperatorThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"The arguments operator and value must either both be set or " +
				"both be null",
			"accounts.filterByCount(filter='contains(" +
				"organization/phone/value, ''0'')', value=0)");
	}

	@Test
	public void testAccountsFilterForIndividual() {
		try {
			IndividualIdThreadLocal.setIndividualId(1L);

			QueryBuilder queryBuilder =
				FilterStringToQueryBuilderConverter.convert(
					"accounts.filter(filter='not startsWith(" +
						"organization/shippingPostalCode/value, ''9'')')",
					_faroInfoIndividualsFilterStringConverterHelper);

			Assertions.assertEquals(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("id", 1L)
				).should(
					QueryBuilders.nestedQuery(
						"dataSourceAccountPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termsQuery(
								"dataSourceAccountPKs.accountPKs",
								"7dc0bc87-2d53-40d5-8137-2db03470adda")
						).filter(
							QueryBuilders.termQuery(
								"dataSourceAccountPKs.dataSourceId",
								"346306699042460013")
						),
						ScoreMode.None)
				).should(
					QueryBuilders.nestedQuery(
						"dataSourceAccountPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termsQuery(
								"dataSourceAccountPKs.accountPKs",
								"e15f3b2f-7cad-4772-b844-0d96d2f27528")
						).filter(
							QueryBuilders.termQuery(
								"dataSourceAccountPKs.dataSourceId",
								"346306699042460013")
						),
						ScoreMode.None)
				),
				queryBuilder);
		}
		finally {
			IndividualIdThreadLocal.remove();
		}
	}

	@Test
	public void testAccountsFilterNestedAnd() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filter(filter='endsWith(organization/website/value, " +
				"''.com/'') and organization/currencyIsoCode/value eq " +
					"''USD''')",
			"346468605699756892", "346468609906122549", "346468617541446459",
			"346468629688998133", "346468636854641829", "346468640520316300",
			"346468642072426931", "346468649722790279", "346468651775874817",
			"346468659407593919", "346468664541254353", "346468670578686898",
			"346468678682539385", "346468680492094349", "346468699875814972",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterUnnestedAnd() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filter(filter='endsWith(organization/website/value, " +
				"''.com/'')') and accounts.filter(filter=" +
					"'organization/currencyIsoCode/value eq ''USD''')",
			"346468605699756892", "346468609906122549", "346468617541446459",
			"346468629688998133", "346468636854641829", "346468638249348979",
			"346468640520316300", "346468642072426931", "346468647886010657",
			"346468649722790279", "346468651775874817", "346468659407593919",
			"346468664541254353", "346468670578686898", "346468677661047218",
			"346468678682539385", "346468679569640045", "346468680492094349",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterWithApostrophe() throws Exception {
		testFilterString(
			"individuals",
			"accounts.filter(filter='organization/accountName/value eq ''The " +
				"World''''s Foremost Chess Club''')",
			"346468614337714393", "346468617541446459", "346468620407090009",
			"346468629688998133", "346468641321212439", "346468642072426931",
			"346468647886010657", "346468649722790279", "346468660260465644",
			"346468664541254353", "346468669276672682", "346468670578686898",
			"346468683127812925", "346468699875814972", "346468700681239480",
			"346468701457781206");
	}

	@Test
	public void testAccountsFilterWithMultipleFiltersThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Multiple values passed in for filter",
			"accounts.filter(filter='organization/accountType/value eq " +
				"''Customer''', filter='startsWith(" +
					"organization/accountName/value, ''The'')')");
	}

	@Test
	public void testAccountsFilterWithUnparseableFilterThrowsException() {
		testFilterStringThrowsException(
			null, null,
			"accounts.filterByCount(filter='organization/phone/value eq " +
				"1-877-543-3729')");
	}

	@Test
	public void testAccountsFilterWithUnquotedFilterThrowsException() {
		testFilterStringThrowsException(
			IllegalArgumentException.class,
			"Expected organization/yearStarted/value lt 2000 to be fully " +
				"surrounded by single quotes",
			"accounts.filter(filter=organization/yearStarted/value lt 2000)");
	}

	@Test
	public void testActivitiesFilterBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			"346468603851271125");
	}

	@Test
	public void testActivitiesFilterByCountAtLeastOne() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			"346468603851271125", "346468605699756892");
	}

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

	@Test
	public void testActivitiesFilterByCountEqNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=-1)");
	}

	@Test
	public void testActivitiesFilterByCountEqPositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='eq', " +
					"value=1)",
			"346468605699756892");
	}

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

	@Test
	public void testActivitiesFilterByCountGePositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ge', " +
					"value=1)",
			"346468603851271125", "346468605699756892");
	}

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

	@Test
	public void testActivitiesFilterByCountGtPositive() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=1)",
			"346468603851271125");
	}

	@Test
	public void testActivitiesFilterByCountGtZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='gt', " +
					"value=0)",
			"346468603851271125", "346468605699756892");
	}

	@Test
	public void testActivitiesFilterByCountLeNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='le', " +
					"value=-1)");
	}

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

	@Test
	public void testActivitiesFilterByCountLtNegative() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=-1)");
	}

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

	@Test
	public void testActivitiesFilterByCountLtZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='lt', " +
					"value=0)");
	}

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

	@Test
	public void testActivitiesFilterByCountNeZero() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#348853654381438580''', operator='ne', " +
					"value=0)",
			"346468603851271125", "346468605699756892");
	}

	@Test
	public void testActivitiesFilterByCountSinceLast7Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last7Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Test
	public void testActivitiesFilterByCountSinceLast24Hours() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last24Hours''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Test
	public void testActivitiesFilterByCountSinceLast28Days() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''last28Days''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125", "346468608880878498");
	}

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

	@Test
	public void testActivitiesFilterByCountSinceYesterday() throws Exception {
		testFilterString(
			"individuals",
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			"346468699875814972", "346468603851271125");
	}

	@Test
	public void testActivitiesFilterByCountWithIndividual1() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994'' and day gt " +
					"''yesterday''', operator='eq', value=1)",
			1L, BoolQueryBuilderUtil.mustNot(QueryBuilders.matchAllQuery()));
	}

	@Test
	public void testActivitiesFilterByCountWithIndividual2() {
		testFilterStringWithIndividual(
			"activities.filterByCount(filter='activityKey eq " +
				"''Page#pageViewed#357731107452100994''', operator='ge', " +
					"value=1)",
			346468614337714393L,
			QueryBuilders.termsQuery(
				"id", Collections.singleton("346468614337714393")));
	}

	@Test
	public void testActivitiesFilterGtDay() throws Exception {
		testFilterString(
			"individuals", "activities.filter(filter='day gt ''2019-04-09''')",
			"346468699875814972", "346468603851271125", "346468608880878498",
			"346468609906122549", "346468614337714393");
	}

	@Test
	public void testActivitiesFilterInBetweenDay() throws Exception {
		testFilterString(
			"individuals",
			"(activities.filter(filter='day le ''2019-03-31''') and " +
				"activities.filter(filter='day gt ''2019-03-01'''))",
			"346468603851271125");
	}

	@Test
	public void testActivitiesFilterLtDay() throws Exception {
		testFilterString(
			"individuals", "activities.filter(filter='day lt ''2019-03-07''')",
			"346468603851271125");
	}

	@Test
	public void testActivitiesFilterWithIndividual() {
		testFilterStringWithIndividual(
			"activities.filter(filter='between(day, ''2019-04-11'', " +
				"''2019-04-12'')')",
			1L, QueryBuilders.matchAllQuery());
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
	public void testFreestyle1() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("(((accounts.filterByCount(filter='((");
		sb.append("organization/shippingState/value ne ''California'') or ");
		sb.append("not endsWith(organization/website/value, ''.com/''))', ");
		sb.append("operator='ge', value=3) and (accounts.filter(filter=");
		sb.append("'((startsWith(organization/shippingCity/value, ");
		sb.append("''Diamond'')))')))) or (demographics/industry/value eq ");
		sb.append("'Real Estate investment Trusts' and ");
		sb.append("demographics/doNotCall/value ne false))");

		testFilterString(
			"individuals", sb.toString(), "346468620407090009",
			"346468649722790279", "346468670578686898", "346468680492094349",
			"346468699875814972", "346468701457781206");
	}

	@Test
	public void testFreestyle2() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("(accounts.filter(filter='organization/billingState/value ");
		sb.append("eq ''Texas''') and (demographics/additionalName/value eq ");
		sb.append("null)) and ((accounts.filterByCount(filter='((");
		sb.append("organization/ownership/value eq ''Private''))', ");
		sb.append("operator='eq', value=2) and demographics/postalCode/value ");
		sb.append("eq null) or ((accounts.filterByCount(");
		sb.append("filter='organization/annualRevenue/value gt 100', ");
		sb.append("operator='ne', value=2)) and not endsWith(");
		sb.append("demographics/country/value, 'a')))");

		testFilterString(
			"individuals", sb.toString(), "346468629688998133",
			"346468640520316300", "346468647886010657", "346468669276672682",
			"346468670578686898", "346468679569640045", "346468700681239480");
	}

	@Test
	public void testFreestyle3() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("sessions.filter(filter='context/browserName eq ''Chrome'' ");
		sb.append("and contains(context/city, ''French'') and ");
		sb.append("(context/region eq ''Indiana'') and (completeDate gt ''");
		sb.append("last90Days'')')");

		testFilterString("individuals", sb.toString(), "346468614337714393");
	}

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
			QueryBuilders.termsQuery("id", "346468700681239480"));
	}

	@Test
	public void testOrganizationFilterGtModifiedDate() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(modifiedDate gt 1580256740750)'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterIdEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(id eq ''402139267512234420'')'))",
			"346468603851271125");
	}

	@Test
	public void testOrganizationFilterIdNotEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(id ne ''402139267512234420'')'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterLtModifiedDate() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(modifiedDate lt 1580256740750)'))",
			"346468603851271125");
	}

	@Test
	public void testOrganizationFilterNameContains() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(contains(name, ''child''))'))",
			"346468605699756892", "346468608880878498", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterNameEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(name eq ''parentOrg'')'))",
			"346468603851271125");
	}

	@Test
	public void testOrganizationFilterNameNotEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(name ne ''childOrg1'')'))",
			"346468603851271125", "346468608880878498", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterNameTreePathContains() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(contains(nameTreePath, " +
				"''childOrg1''))'))",
			"346468605699756892", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterNameTreePathEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(nameTreePath eq ''parentOrg > " +
				"childOrg1 > grandchild'')'))",
			"346468609906122549");
	}

	@Test
	public void testOrganizationFilterNameTreePathNotContains()
		throws Exception {

		testFilterString(
			"individuals",
			"(organizations.filter(filter='(not contains(nameTreePath, " +
				"''childOrg1''))'))",
			"346468603851271125", "346468608880878498");
	}

	@Test
	public void testOrganizationFilterParentIdEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(parentId eq " +
				"''402139267512234420'')'))",
			"346468605699756892", "346468608880878498");
	}

	@Test
	public void testOrganizationFilterParentIdNe() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(parentId ne " +
				"''402139267512234420'')'))",
			"346468603851271125", "346468609906122549");
	}

	@Test
	public void testOrganizationFilterTypeEq() throws Exception {
		testFilterString(
			"individuals",
			"(organizations.filter(filter='(type eq ''organization'')'))",
			"346468603851271125", "346468605699756892", "346468608880878498",
			"346468609906122549");
	}

	@Test
	public void testOrganizationFilterWithFilter() {
		try {
			IndividualIdThreadLocal.setIndividualId(1L);

			QueryBuilder queryBuilder =
				FilterStringToQueryBuilderConverter.convert(
					"(organizations.filter(filter='(parentId ne " +
						"''402139267512234420'')'))",
					_faroInfoIndividualsFilterStringConverterHelper);

			Assertions.assertEquals(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("id", 1L)
				).should(
					QueryBuilders.termQuery(
						"organizationIds", "402139267512234420")
				).should(
					QueryBuilders.termQuery(
						"organizationIds", "402139268847589065")
				),
				queryBuilder);
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

	@Test
	public void testSessionsFilterWithIndividual1() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='context/city eq ''Tokyo''')",
			346468603851271125L,
			QueryBuilders.termsQuery(
				"id", Collections.singleton(346468603851271125L)));
	}

	@Test
	public void testSessionsFilterWithIndividual2() {
		testFilterStringWithIndividual(
			"sessions.filter(filter='context/city eq ''Budapest''')",
			346468603851271125L,
			QueryBuilders.termsQuery("id", Collections.emptySet()));
	}

	protected void testFilterStringWithIndividual(
		String filterString, Long individualId,
		QueryBuilder expectedQueryBuilder) {

		try {
			IndividualIdThreadLocal.setIndividualId(individualId);

			Assertions.assertEquals(
				expectedQueryBuilder,
				FilterStringToQueryBuilderConverter.convert(
					filterString,
					_faroInfoIndividualsFilterStringConverterHelper));
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