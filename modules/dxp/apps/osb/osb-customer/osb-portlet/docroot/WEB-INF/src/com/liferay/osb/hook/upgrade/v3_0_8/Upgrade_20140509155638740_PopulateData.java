/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.SupportTeamConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140509155638740_PopulateData extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140509155638740L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		populateHolidayCalendars();
		populateSupportRegions();
		populateSupportLabor();
	}

	protected void populateAccountEntries_SupportRegions(
			long supportRegionId, int i)
		throws Exception {

		String[][] supportResponseNames = {
			{"Americas English"}, {"Europe English"}, {"India English"},
			{"Asia English", "Australia English"}, {"Europe Spanish"},
			{"Americas Portuguese"}
		};

		for (String supportResponseName : supportResponseNames[i]) {
			StringBundler sb = new StringBundler(11);

			sb.append("insert ignore into OSB_AccountEntries_SupportRegions ");
			sb.append("(accountEntryId, supportRegionId) select distinct ");
			sb.append("OSB_AccountEntry.accountEntryId, '");
			sb.append(supportRegionId);
			sb.append("' from OSB_AccountEntry inner join ");
			sb.append("OSB_SupportResponse on ");
			sb.append("OSB_SupportResponse.supportResponseId = ");
			sb.append("OSB_AccountEntry.highestSupportResponseId where ");
			sb.append("OSB_SupportResponse.name like '");
			sb.append(supportResponseName);
			sb.append("%'");

			runSQL(sb.toString());
		}
	}

	protected void populateHolidayCalendarRel(long holidayCalendarId, int i)
		throws Exception {

		String[] nameComparisons = {
			"like 'Support-US%' or OSB_SupportTeam.name like 'Platinum " +
				"Critical US%'",
			"like 'Support-HU%' or OSB_SupportTeam.name = 'Platinum Critical " +
				"Hungary'",
			"like 'Support-CN%' or OSB_SupportTeam.name = 'Platinum Critical " +
				"China'",
			"like 'Support-India%' or OSB_SupportTeam.name = 'Platinum " +
				"Critical India'",
			"like 'Support-ES%' or OSB_SupportTeam.name = 'Platinum Critical " +
				"Spain'",
			"like 'Support-BR%' or OSB_SupportTeam.name = 'Platinum Critical " +
				"Brazil'",
			"= 'Support-AU-Lvl1-Jonas'"
		};

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(7);

			sb.append("select distinct User_.userId as userId from User_ ");
			sb.append("inner join OSB_SupportWorker on ");
			sb.append("OSB_SupportWorker.userId = User_.userId inner join ");
			sb.append("OSB_SupportTeam on OSB_SupportTeam.supportTeamId = ");
			sb.append("OSB_SupportWorker.supportTeamId where ");
			sb.append("OSB_SupportTeam.name ");
			sb.append(nameComparisons[i]);

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				sb = new StringBundler(9);

				sb.append("insert ignore into OSB_HolidayCalendarRel ");
				sb.append("(holidayCalendarRelId, userId, holidayCalendarId) ");
				sb.append("values (");
				sb.append(increment());
				sb.append(", ");
				sb.append(rs.getLong("userId"));
				sb.append(", ");
				sb.append(holidayCalendarId);
				sb.append(")");

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void populateHolidayCalendars() throws Exception {
		if (hasRows("OSB_HolidayCalendar")) {
			return;
		}

		String[] holidayCalendarNames = {
			"US Holidays", "Hungarian Holidays", "Chinese Holidays",
			"Indian Holidays", "Spanish Holidays", "Brazilian Holidays",
			"Australian Holidays"
		};

		for (int i = 0; i < holidayCalendarNames.length; i++) {
			long holidayCalendarId = increment();

			StringBundler sb = new StringBundler(6);

			sb.append("insert into OSB_HolidayCalendar (holidayCalendarId, ");
			sb.append("name, description) values (");
			sb.append(holidayCalendarId);
			sb.append(", '");
			sb.append(holidayCalendarNames[i]);
			sb.append("', '')");

			runSQL(sb.toString());

			populateHolidayEntries(holidayCalendarId, i);
			populateHolidayCalendarRel(holidayCalendarId, i);
		}
	}

	protected void populateHolidayEntries(long holidayCalendarId, int i)
		throws Exception {

		String[][] holidayEntrySQL = {
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Memorial Day', '', '2014-05-26 00:00:00', '2014-05-26 " +
					"23:59:59', false",
				"'Independence Day', '', '2014-07-04 00:00:00', '2014-07-04 " +
					"23:59:59', true",
				"'Labor Day', '', '2014-09-01 00:00:00', '2014-09-01 " +
					"23:59:59', false",
				"'Thanksgiving Holiday', '', '2014-11-27 00:00:00', " +
					"'2014-11-28 23:59:59', false"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Revolution Holiday', '', '2014-03-15 00:00:00', " +
					"'2014-03-15 23:59:59', true",
				"'Labor Day', '', '2014-05-01 00:00:00', '2014-05-01 " +
					"23:59:59', true",
				"'Whit Monday', '', '2014-06-09 00:00:00', '2014-06-09 " +
					"23:59:59', false",
				"'National Holiday', '', '2014-08-20 00:00:00', '2014-08-20 " +
					"23:59:59', false",
				"'Heroes Day', '', '2014-10-23 00:00:00', '2014-10-23 " +
					"23:59:59', true",
				"'All Saints Day', '', '2014-11-01 00:00:00', '2014-11-01 " +
					"23:59:59', true",
				"'Christmas Holiday', '', '2014-12-24 00:00:00', '2014-12-25 " +
					"23:59:59', false"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Labor Day', '', '2014-05-01 00:00:00', '2014-05-03 " +
					"23:59:59', false",
				"'Youth Day', '', '2014-05-04 00:00:00', '2014-05-04 " +
					"23:59:59', true",
				"'Duanwu Festival', '', '2014-05-31 00:00:00', '2014-06-02 " +
					"23:59:59', false",
				"'Mid-Autumn Festival', '', '2014-09-06 00:00:00', " +
					"'2014-09-08 23:59:59', false",
				"'National Day', '', '2014-09-29 00:00:00', '2014-10-03 " +
					"23:59:59', false"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Makar Sankranti', '', '2014-01-14 00:00:00', '2014-01-14 " +
					"23:59:59', true",
				"'Labor Day', '', '2014-05-01 00:00:00', '2014-05-01 " +
					"23:59:59', true",
				"'Ramzan', '', '2014-08-15 00:00:00', '2014-08-15 23:59:59', " +
					"false",
				"'Ganesh Chaturthi', '', '2014-08-29 00:00:00', '2014-08-29 " +
					"23:59:59', false",
				"'Gandhi Jayanthi', '', '2014-10-02 00:00:00', '2014-10-02 " +
					"23:59:59', true",
				"'Vijayadashami', '', '2014-10-03 00:00:00', '2014-10-03 " +
					"23:59:59', false",
				"'Bakrid', '', '2014-10-04 00:00:00', '2014-10-05 23:59:59', " +
					"false",
				"'Karnataka Rajyosava', '', '2014-11-01 00:00:00', " +
					"'2014-11-01 23:59:59', true",
				"'Muharram', '', '2014-11-03 00:00:00', '2014-11-03 " +
					"23:59:59', false",
				"'Christmas Day', '', '2014-12-25 00:00:00', '2014-12-25 " +
					"23:59:59', true"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Labor Day', '', '2014-05-01 00:00:00', '2014-05-01 " +
					"23:59:59', true",
				"'Fiesta de la Comunidad de Madrid', '', '2014-05-02 " +
					"00:00:00', '2014-05-02 23:59:59', true",
				"'San Isidro', '', '2014-05-15 00:00:00', '2014-05-15 " +
					"23:59:59', true",
				"'Assumption Day', '', '2014-08-15 00:00:00', '2014-08-15 " +
					"23:59:59', true",
				"'Spanish National Holiday', '', '2014-10-12 00:00:00', " +
					"'2014-10-12 23:59:59', true",
				"'All Saints Day', '', '2014-11-01 00:00:00', '2014-11-01 " +
					"23:59:59', true",
				"'Nuestra Senora de la Almudena', '', '2014-11-09 00:00:00', " +
					"'2014-11-09 23:59:59', true",
				"'Christmas Holiday', '', '2014-12-25 00:00:00', '2014-12-25 " +
					"23:59:59', true"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'LaborDay', '', '2014-05-01 00:00:00', '2014-05-01 " +
					"23:59:59', true",
				"'Sao Joao', '', '2014-06-24 00:00:00', '2014-06-24 " +
					"23:59:59', true",
				"'Nossa Senhora do Carmo', '', '2014-07-16 00:00:00', " +
					"'2014-07-16 23:59:59', true",
				"'Independence Day', '', '2014-09-08 00:00:00', '2014-09-08 " +
					"23:59:59', false",
				"'Nossa Senhora da Aparecida', '', '2014-10-13 00:00:00', " +
					"'2014-10-13 23:59:59', false",
				"'All Souls Day', '', '2014-11-03 00:00:00', '2014-11-03 " +
					"23:59:59', false",
				"'Republic Day', '', '2014-11-14 00:00:00', '2014-11-14 " +
					"23:59:59', false"
			},
			{
				"'New Years Day', '', '2014-01-01 00:00:00', '2014-01-01 " +
					"23:59:59', true",
				"'Queens Birthday', '', '2014-06-09 00:00:00', '2014-06-09 " +
					"23:59:59', false",
				"'Bank Holiday', '', '2014-08-04 00:00:00', '2014-08-04 " +
					"23:59:59', false",
				"'Labour Day', '', '2014-10-06 00:00:00', '2014-10-06 " +
					"23:59:59', false",
				"'Christmas Day', '', '2014-12-25 00:00:00', '2014-12-25 " +
					"23:59:59', true",
				"'Boxing Day', '', '2014-12-26 00:00:00', '2014-12-26 " +
					"23:59:59', true"
			}
		};

		for (String holidayEntryData : holidayEntrySQL[i]) {
			long holidayEntryId = increment();

			StringBundler sb = new StringBundler(9);

			sb.append("insert into OSB_HolidayEntry (holidayEntryId, ");
			sb.append("holidayCalendarId, name, description, startDate, ");
			sb.append("endDate, repeatYearly) values (");
			sb.append(holidayEntryId);
			sb.append(", ");
			sb.append(holidayCalendarId);
			sb.append(", ");
			sb.append(holidayEntryData);
			sb.append(")");

			runSQL(sb.toString());
		}
	}

	protected void populateSupportLabor() throws Exception {
		if (hasRows("OSB_SupportLabor")) {
			return;
		}

		String[] supportLaborData = {
			"'US Hours', 'America/Los_Angeles', 0, 0, 420, 1080, 420, 1080, " +
				"420, 1080, 420, 1080, 420, 1080, 0, 0",
			"'Spain Hours', 'Europe/Lisbon', 0, 0, 540, 1080, 540, 1080, " +
				"540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'Hungary Hours', 'Europe/Istanbul', 0, 0, 540, 1080, 540, 1080, " +
				"540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'Brazil Hours', 'America/Sao_Paulo', 0, 0, 540, 1080, 540, " +
				"1080, 540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'China Hours', 'Asia/Shanghai', 0, 0, 540, 1080, 540, 1080, " +
				"540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'India Hours', 'Asia/Calcutta', 0, 0, 540, 1080, 540, 1080, " +
				"540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'Australia Hours', 'Australia/Sydney', 0, 0, 540, 1080, 540, " +
				"1080, 540, 1080, 540, 1080, 540, 1080, 0, 0",
			"'Platinum Critical US Global Hours', 'America/Los_Angeles', " +
				"540, 1080, 420, 1080, 420, 1080, 420, 1080, 420, 1080, 420, " +
					"1439, 420, 1439",
			"'Platinum Critical Hungary Global Hours', 'Europe/Istanbul', " +
				"540, 1080, 540, 960, 540, 960, 540, 960, 540, 960, 540, " +
					"960, 540, 1080",
			"'Platinum Critical China Global Hours', 'Asia/Shanghai', 630, " +
				"990, 630, 990, 630, 990, 630, 990, 630, 990, 0, 0, 0, 0"
		};

		for (int i = 0; i < supportLaborData.length; i++) {
			long supportLaborId = increment();

			StringBundler sb = new StringBundler(9);

			sb.append("insert into OSB_SupportLabor (supportLaborId, name, ");
			sb.append("timeZoneId, sunOpen, sunClose, monOpen, monClose, ");
			sb.append("tueOpen, tueClose, wedOpen, wedClose, thuOpen, ");
			sb.append("thuClose, friOpen, friClose, satOpen, satClose) ");
			sb.append("values (");
			sb.append(supportLaborId);
			sb.append(", ");
			sb.append(supportLaborData[i]);
			sb.append(")");

			runSQL(sb.toString());

			updateSupportTeams(supportLaborId, i);
		}
	}

	protected void populateSupportRegions() throws Exception {
		if (hasRows("OSB_SupportRegion")) {
			return;
		}

		String[] supportRegionNames =
			{"US", "Hungary", "India", "China", "Spain", "Brazil"};

		for (int i = 0; i < supportRegionNames.length; i++) {
			long supportRegionId = increment();

			StringBundler sb = new StringBundler(8);

			sb.append("insert ignore into OSB_SupportRegion (");
			sb.append("supportRegionId, userId, userName, createDate, ");
			sb.append("modifiedDate, name, description) values (");
			sb.append(supportRegionId);
			sb.append(", 5, '', '2014-02-28 22:40:25', '2014-02-28 22:40:25'");
			sb.append(", '");
			sb.append(supportRegionNames[i]);
			sb.append("', '')");

			runSQL(sb.toString());

			populateAccountEntries_SupportRegions(supportRegionId, i);
			populateSupportTeams(supportRegionId, i);
			populateSupportTeams_SupportRegions(supportRegionId, i);
		}
	}

	protected void populateSupportTeams(long supportRegionId, int i)
		throws Exception {

		String[][] supportTeamNames = {
			{"US West", "US East"}, {"Hungary"}, {"India"}, {"China"},
			{"Spain"}, {"Brazil"}
		};

		for (int j = 0; j < supportTeamNames[i].length; j++) {
			long supportTeamId = increment();

			StringBundler sb = new StringBundler(11);

			sb.append("insert ignore into OSB_SupportTeam (supportTeamId, ");
			sb.append("userId, userName, createDate, modifiedDate, ");
			sb.append("parentSupportTeamId, name, description, assignedWork, ");
			sb.append("maxWork, type_) values (");
			sb.append(supportTeamId);
			sb.append(", 2, '', '2014-03-19 00:00:00', '2014-03-19 00:00:00'");
			sb.append(", 0, 'Platinum Critical ");
			sb.append(supportTeamNames[i][j]);
			sb.append("', '', 0, 0, ");
			sb.append(SupportTeamConstants.TYPE_PLATINUM_CRITICAL);
			sb.append(")");

			runSQL(sb.toString());

			if (j > 0) {
				populateSupportWorkers(supportTeamId, 6);
			}
			else {
				populateSupportWorkers(supportTeamId, i);
			}

			populateSupportTeams_SupportRegions(supportRegionId, supportTeamId);
		}
	}

	protected void populateSupportTeams_SupportRegions(
			long supportRegionId, int i)
		throws Exception {

		String[][] regions =
			{{"US", "er"}, {"HU"}, {"In"}, {"CN", "AU"}, {"ES"}, {"BR"}};

		for (String region : regions[i]) {
			StringBundler sb = new StringBundler(7);

			sb.append("insert ignore into OSB_SupportTeams_SupportRegions (");
			sb.append("supportRegionId, supportTeamId) select supportTeamId, ");
			sb.append("'");
			sb.append(supportRegionId);
			sb.append("' from OSB_SupportTeam where substring(name, 9, 2) = '");
			sb.append(region);
			sb.append("'");

			runSQL(sb.toString());
		}
	}

	protected void populateSupportTeams_SupportRegions(
			long supportRegionId, long supportTeamId)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("insert ignore into OSB_SupportTeams_SupportRegions (");
		sb.append("supportRegionId, supportTeamId) values (");
		sb.append(supportRegionId);
		sb.append(", ");
		sb.append(supportTeamId);
		sb.append(")");

		runSQL(sb.toString());
	}

	protected void populateSupportWorkers(long supportTeamId, int j)
		throws Exception {

		long[][] userIds = {
			{10454309}, {14215850, 9395096, 12638110}, {11369600, 28919080},
			{6641537, 13219869, 26943406},
			{8356637, 33019948, 15796870, 15188402, 390865, 9889122, 6802360},
			{12899830, 14499078, 857045}, {15926289}
		};

		for (long userId : userIds[j]) {
			long supportWorkerId = increment();

			StringBundler sb = new StringBundler(10);

			sb.append("insert ignore into OSB_SupportWorker (");
			sb.append("supportWorkerId, userId, supportTeamId, assignedWork, ");
			sb.append("maxWork, escalationLevel, role, notifications, ");
			sb.append("supportLaborId) values (");
			sb.append(supportWorkerId);
			sb.append(", ");
			sb.append(userId);
			sb.append(", ");
			sb.append(supportTeamId);
			sb.append(", 0, 10, 31001, 0, 1, 0)");

			runSQL(sb.toString());
		}
	}

	protected void updateSupportTeams(long supportLaborId, int i)
		throws Exception {

		String[] nameComparisons = {
			"like 'Support-US%'",
			"like 'Support-ES%' or name = 'Platinum Critical Spain'",
			"like 'Support-HU%'",
			"like 'Support-BR%' or name = 'Platinum Critical Brazil'",
			"like 'Support-CN%' or name = 'Support-AUI-Lvl1'",
			"like 'Support-In%' or name = 'Platinum Critical India'",
			"= 'Support-AUI-Lvl1-Jonas'", "= 'Platinum Critical US'",
			"= 'Platinum Critical Hungary'", "= 'Platinum Critical China'"
		};

		StringBundler sb = new StringBundler(4);

		sb.append("update OSB_SupportTeam set supportLaborId = ");
		sb.append(supportLaborId);
		sb.append(" where name ");
		sb.append(nameComparisons[i]);

		runSQL(sb.toString());
	}

}