const express = require('express');
const uuid = require('uuid/v4');

const app = express();

app.use(express.json());

app.get('/projects/:id/services', function (req, res) {
	res.send([{
		createdAt: new Date().toISOString(),
		deployGroupUid: uuid(),
		groupUid: uuid(),
		health: "GOOD",
		id: req.params.id,
		imageHint: "",
		loadBalancer: {
			targetPort: 8080
		},
		projectId: "123",
		ready: true,
		serviceId: 123
	}]);
});
app.get('/status', function (req, res) {
	res.send('ok');
});
app.post('/projects/:id/build', function (req, res) {
	res.send([{
		branch: '7.0.x',
		buildGroupUid: uuid(),
		createdAt: new Date().toISOString(),
		groupUid: uuid(),
		image: 'image',
		projectId: req.params.id,
		projectUid: uuid(),
		serviceId: 123,
		status: 'READY'
	}]);
});

app.all('/projects/*', function (req, res) {
	res.send({
		buildGroupUidCounter: '987654321',
		cluster: req.body.cluster || 'ac-us',
		createdAt: new Date().toISOString(),
		health: 'GOOD',
		id: '321',
		loadBalancerIp: '127.0.0.1',
		ownerId: '654321',
		projectId: req.body.projectId || uuid(),
		status: 'READY'
	});
});

app.listen(3000);