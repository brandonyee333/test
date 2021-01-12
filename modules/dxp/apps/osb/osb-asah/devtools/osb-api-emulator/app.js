const express = require('express');

const app = express();

app.use(express.json());

app.get('/osb-portlet/api/jsonws/accountentry/get-corp-project-account-entry', function (req, res) {
	const corpProjectUuid = req.query.corpProjectUuid || "123456789";

	res.send({
		corpEntryName: "Mock Project",
		corpProjectUuid,
		dossieraAccountKey: "Mock Project",
		name: "Mock Project",
		offeringEntries: [
			{
				productEntryId: 110520636, /* BUSINESS */
				quantity: 1,
				status: 1, /* ACTIVE */
				startDate: "2021-01-01T00:00:00.000",
				supportEndDate: "2030-31-01T23:59:59.999",
			}
		]
	});
});
app.get('/status', function (req, res) {
	res.send('ok');
});

app.listen(3000);