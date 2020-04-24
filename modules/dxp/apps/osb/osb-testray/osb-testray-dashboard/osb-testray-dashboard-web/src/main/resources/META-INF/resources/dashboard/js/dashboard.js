YUI(
	{
		comboBase: 'https://cdn.lfrs.sl/alloyui.com/combo/combo.php?'
	}
).use(
	'async-queue',
	'aui-base',
	'aui-io-request',
	'aui-url',
	'promise',
	function(A) {
		var Lang = A.Lang;

		var gitHubAvatars = new Map();

		var serverNodes = A.all();

		var totalComputerCount = 0;

		var totalQueueCount = 0;

		var totalUtilizationCount = 0;

		var CSS_ERROR = 'error';

		var INT_CENTURION_COMPUTERS = 51;

		var INT_ENVIRONMENTS = 10;

		var INT_FUNCTIONAL_TESTS = 1;

		var INT_POLL_INTERVAL_SECONDS = 300;

		var INT_SERVERS = 23;

		var PULL_REQUESTS_DISPLAY_AMOUNT = 11;

		var STR_BLANK = '';

		var STR_ENVIRONMENT = 'environment';

		var STR_FUNCTIONAL_CE = 'functionalCE';

		var STR_FUNCTIONAL_EE = 'functionalEE';

		var STR_PORTAL_SMOKE_BUILD = 'latest';

		var STR_PORTAL_SMOKE_CASE = 'PortalSmoke#Smoke';

		var STR_PORTAL_SMOKE_PROJECT = 'Liferay Portal 7.0';

		var STR_PORTAL_SMOKE_ROUTINE = 'CE Development Acceptance (master)';

		var STR_SERVER = 'server';

		var TPL_ALERT = '<div class="dashboard-alert status-failed">' +
				'<span class="dashboard-alert-icon">' +
					'<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>' +
				'</span>' +
				'<span class="dashboard-alert-content status-label status-label-failed">' +
					'<span class="title">{title}</span>' +
					'<span class="description">{description}</span>' +
					'<span class="execution-date">{executionDate}</span>' +
				'</span>' +
			'</div>';

		var TPL_ENVIRONMENT = '<div class="environment status-{statusCssClass} {cssClass}">' +
				'<div class="data-row message status-label status-label-{statusCssClass}">' +
					'<span class="execution-date">{executionDate}</span>' +
					'<span class="environment-data">{environmentData}</span>' +
				'</div>' +
			'</div>';

		var TPL_ERROR_TEXT = '<div class="error-text">{text}</div>';

		var TPL_FUNCTIONAL = '<div class="functional">' +
				'<div class="status-bar-container">' +
					'<div class="status-bar status-bar-passed" style="width: {widthPassed}%;"></div>' +
					'<div class="status-bar status-bar-failed" style="width: {widthFailed}%;"></div>' +
					'<div class="status-bar status-bar-blocked" style="width: {widthBlocked}%;"></div>' +
					'<div class="status-bar status-bar-test-fix" style="width: {widthTestFix}%;"></div>' +
					'<div class="status-bar status-bar-untested {cssClassError}" style="width: {widthUntested}%;" title="{info}"></div>' +
				'</div>' +
				'<div class="data-row message">' +
					'<span class="data-column-main">' +
						'<span class="functional-test-name">{functionalTestName}</span>' +
					'</span>' +
					'<span class="data-column-main">' +
						'<span class="functional-execution-date">{executionDate}</span>' +
					'</span>' +
					'<span class="data-column">' +
						'<span class="status-count status-count-passed">{passed}</span>' +
						'<span class="status-count-label">Passed</span>' +
					'</span>' +
					'<span class="data-column">' +
						'<span class="status-count status-count-failed">{failed}</span>' +
						'<span class="status-count-label">Failed</span>' +
					'</span>' +
					'<span class="data-column">' +
						'<span class="status-count status-count-blocked">{blocked}</span>' +
						'<span class="status-count-label">Blocked</span>' +
					'</span>' +
					'<span class="data-column">' +
						'<span class="status-count status-count-test-fix">{testFix}</span>' +
						'<span class="status-count-label">Test Fix</span>' +
					'</span>' +
				'</div>' +
			'</div>';

		var TPL_FUNCTIONAL_CE = Lang.sub(
			TPL_FUNCTIONAL,
			{
				functionalTestName: 'CE Development Acceptance (master)'
			}
		);

		var TPL_FUNCTIONAL_EE = Lang.sub(
			TPL_FUNCTIONAL,
			{
				functionalTestName: 'EE Development Acceptance (7.1.x-private)'
			}
		);

		var TPL_PULL_REQUEST_AVATAR = '<span class="pull-request-{type}">{content}</span>';

		var TPL_PULL_REQUEST_ITEM = '<div class="pull-request-item"></div>';

		var TPL_SERVER = '<div class="centurion data-row">' +
				'<div class="data-column in-queue"></div>' +
				'<div class="data-column data-column-server-bar server">' +
					'<div class="free-space status-bar status-bar-{status}" id="server_{id}" style="width: {percentUsed}%; height: 100%"></div>' +
				'</div>' +
				'<div class="centurion-id data-column">{id}</div>' +
			'</div>';

		var URL_ENVIRONMENT = '/web/guest/home/-/testray/runs/latestRunResults.json?testrayRoutineId=270585528&jenkinsJobKey={index}';

		var URL_FUNCTIONAL_CE = '/web/guest/home/-/testray/builds/latestBuildResults.json?testrayRoutineId=84094986';

		var URL_FUNCTIONAL_EE = '/web/guest/home/-/testray/builds/latestBuildResults.json?testrayRoutineId=188675318';

		var URL_GITHUB_USER = 'https://api.github.com/users/{username}';

		var URL_PORTAL_SMOKE = '/web/guest/home/-/testray/case_results/index.json?testrayProjectName=' +
			STR_PORTAL_SMOKE_PROJECT +
			'&testrayRoutineName=' +
			STR_PORTAL_SMOKE_ROUTINE +
			'&testrayBuildName=' +
			STR_PORTAL_SMOKE_BUILD +
			'&filterName=' +
			STR_PORTAL_SMOKE_CASE;

		var URL_PULL_REQUESTS = '/o/testray-gateway-servlet?apiName=github&server=lrdcom-vm-16';

		var URL_QUEUE = '/o/testray-gateway-servlet?apiName=queue&server=test-1-{index}';

		var URL_RATE_LIMIT = 'https://api.github.com/rate_limit';

		var URL_SERVER = '/o/testray-gateway-servlet?apiName=serverStatus&server=test-1-{index}';

		var OBJ_ENVIRONMENT = {
			length: INT_ENVIRONMENTS,
			nodes: A.all(),
			string: STR_ENVIRONMENT,
			template: TPL_ENVIRONMENT,
			url: URL_ENVIRONMENT
		};

		var OBJ_FUNCTIONAL_CE = {
			length: INT_FUNCTIONAL_TESTS,
			nodes: A.all(),
			string: STR_FUNCTIONAL_CE,
			template: TPL_FUNCTIONAL_CE,
			url: URL_FUNCTIONAL_CE
		};

		var OBJ_FUNCTIONAL_EE = {
			length: INT_FUNCTIONAL_TESTS,
			nodes: A.all(),
			string: STR_FUNCTIONAL_EE,
			template: TPL_FUNCTIONAL_EE,
			url: URL_FUNCTIONAL_EE
		};

		var OBJ_QUEUE = {
			length: INT_SERVERS,
			nodes: serverNodes,
			url: URL_QUEUE
		};

		var OBJ_SERVER = {
			length: INT_SERVERS,
			nodes: serverNodes,
			string: STR_SERVER,
			template: TPL_SERVER,
			url: URL_SERVER
		};

		function buildHTML(data) {
			var buffer = [];

			var config = data.config;
			var testObj = data.testObj;

			var testName = testObj.string;

			for (var i = 1; i <= testObj.length; i++) {
				config.id = i;

				buffer.push(
					'<div class="' + testName + '-container" id="' + testName + i + '">' +
					renderHTML(
						{
							config: config,
							node: STR_BLANK,
							testType: testObj
						}
					) +
					'</div>'
				);
			}

			var testWrapper = A.one('#' + testName + 'Wrapper');

			var retVal = A.all();

			if (testWrapper) {
				testWrapper.append(buffer.join(STR_BLANK));

				retVal = testWrapper.all('.' + testName + '-container');
			}

			return retVal;
		}

		function buildUrl(data) {
			var config = data.config;

			for (var i = 1; i <= config.length; i++) {
				var url = Lang.sub(
					config.url,
					{
						index: i
					}
				);

				getJSON(
					{
						errorMethod: data.errorMethod,
						index: i,
						methodName: data.methodName,
						testObj: config,
						url: url
					}
				);
			}
		}

		function environmentErr(event, index, response, args) {
			return;
		}

		function getJSON(data) {
			A.io.request(
				data.url,
				{
					arguments: {
						testObj: data.testObj,
						urlID: data.index
					},
					dataType: 'json',
					headers: {
						'Accept': 'application/json'
					},
					on: {
						failure: data.errorMethod,
						success: data.methodName
					}
				}
			);
		}

		function getServerConfig(config) {
			var percentUsed = config.percentUsed;

			var status = 'mid';

			if (percentUsed > 49) {
				status = 'high';
			}
			else if (percentUsed < 15) {
				status = 'low';
			}

			config.status = status;

			return config;
		}

		function handleEnvironmentData(event, index, response, args) {
			var responseData = this.get('responseData');

			var data = responseData.data;

			if (data) {
				var results = data.testrayBuildResults || data.results;

				if (results) {
					var blocked = results.blocked || 0;
					var failed = results.failed || 0;
					var passed = results.passed || 0;

					var testFix = results['test-fix'] || 0;

					var totalTests = blocked + failed + passed + testFix;

					var widthBlocked = (blocked / totalTests) * 100;
					var widthFailed = (failed / totalTests) * 100;
					var widthPassed = (passed / totalTests) * 100;
					var widthTestFix = (testFix / totalTests) * 100;

					var adjustedTotalTests = widthBlocked + widthFailed + widthPassed + widthTestFix;

					var untestedWidth = 100 - adjustedTotalTests;

					var testObj = args.testObj;

					var statusCssClass = 'incomplete';

					// Ordered from least to most important status.

					if (passed > 0) {
						statusCssClass = 'passed';
					}
					else if (blocked > 0) {
						statusCssClass = 'blocked';
					}
					else if (testFix > 0) {
						statusCssClass = 'test-fix';
					}
					else if (failed > 0) {
						statusCssClass = 'failed';
					}

					var node = testObj.nodes.item(args.urlID - 1);

					var executionDate = data.executionDate;

					var testrayRunResults = data.testrayRunResults;

					if (testrayRunResults && testrayRunResults.length) {
						executionDate = testrayRunResults[0].executionDate;
					}

					renderHTML(
						{
							config: {
								blocked: blocked,
								cssClass: STR_BLANK,
								cssClassError: STR_BLANK,
								environmentData: data.environment,
								executionDate: executionDate ? moment(executionDate).fromNow() : STR_BLANK,
								failed: failed,
								info: STR_BLANK,
								passed: passed,
								statusCssClass: statusCssClass,
								testFix: testFix,
								untested: STR_BLANK,
								widthBlocked: widthBlocked,
								widthFailed: widthFailed,
								widthPassed: widthPassed,
								widthTestFix: widthTestFix,
								widthUntested: untestedWidth
							},
							node: node,
							testType: testObj
						}
					);

					node.show();
				}
				else {
					environmentErr(event, index, STR_BLANK, args);
				}
			}
			else {
				environmentErr(event, index, response, args);
			}
		}

		function handleServerData(event, index, response, args) {
			var responseData = this.get('responseData');

			var computersUsed = 0;

			var testObj = args.testObj;
			var urlID = args.urlID;

			var node = serverNodes.item(urlID - 1);

			if (responseData) {
				if (testObj.string == STR_SERVER) {
					var computer = responseData.computer;

					for (var j = 0; j < computer.length; j++) {
						if (!computer[j].idle) {
							computersUsed++;
						}
					}

					var percentUsed = Math.round(computersUsed / INT_CENTURION_COMPUTERS * 100);

					totalUtilizationCount += computersUsed;
					totalComputerCount += INT_CENTURION_COMPUTERS;

					var totalUtilizationPercent = Math.round(totalUtilizationCount / totalComputerCount * 100) || 0;

					var totalUtilizationPercentNode = A.one('#totalUtilizationPercent');

					if (totalUtilizationPercentNode) {
						totalUtilizationPercentNode.text(totalUtilizationPercent);
					}

					renderHTML(
						{
							config: {
								cssClassError: STR_BLANK,
								id: urlID,
								info: STR_BLANK,
								message: percentUsed + '%',
								percentUsed: percentUsed
							},
							node: node,
							testType: testObj
						}
					);
				}
				else if (node) {
					var queueItems = responseData.items;

					var queueNode = node.one('.in-queue');

					if (queueItems && queueNode) {
						var queueLength = queueItems.length;

						queueNode.text(queueLength);

						if (queueLength === 0) {
							queueNode.addClass('empty');
						}

						totalQueueCount += queueLength;

						var totalQueueCountNode = A.one('#totalQueueCount');

						if (totalQueueCountNode) {
							totalQueueCountNode.text(totalQueueCount);
						}
					}
				}
			}
		}

		function init() {
			signedIn();

			var config = {
				cssClassError: STR_BLANK,
				info: STR_BLANK,
				message: STR_BLANK,
				percentUsed: 0
			};

			serverNodes = buildHTML(
				{
					config: config,
					testObj: OBJ_SERVER
				}
			);

			OBJ_QUEUE.nodes = serverNodes;

			OBJ_SERVER.nodes = serverNodes;

			config = {
				blocked: STR_BLANK,
				cssClass: 'hide',
				cssClassError: STR_BLANK,
				environmentData: STR_BLANK,
				executionDate: STR_BLANK,
				failed: STR_BLANK,
				info: STR_BLANK,
				message: STR_BLANK,
				passed: STR_BLANK,
				testFix: STR_BLANK,
				untested: 'Untested',
				widthBlocked: 0,
				widthFailed: 0,
				widthPassed: 0,
				widthTestFix: 0,
				widthUntested: 100
			};

			OBJ_ENVIRONMENT.nodes = buildHTML(
				{
					config: config,
					testObj: OBJ_ENVIRONMENT
				}
			);

			OBJ_FUNCTIONAL_CE.nodes = buildHTML(
				{
					config: config,
					testObj: OBJ_FUNCTIONAL_CE
				}
			);

			OBJ_FUNCTIONAL_EE.nodes = buildHTML(
				{
					config: config,
					testObj: OBJ_FUNCTIONAL_EE
				}
			);

			update();
		}

		function signedIn() {
			A.DOM.creators.html = function(html, doc) {
				var el = document.createElement('html');

				el.innerHTML = html;

				return el;
			};

			A.io.request(
				'/web/guest/home/-/testray',
				{
					dataType: 'HTML',
					on: {
						success: function() {
							var data = this.get('responseData');

							if (data) {
								var hasSignedIn = data.hasClass('signed-in');

								if (hasSignedIn.pop()) {
									var navbar = A.one('.navbar');

									if (navbar) {
										navbar.remove();
									}
								}
							}
						}
					},
					selector: 'body'
				}
			);
		}

		function renderHTML(data) {
			var config = data.config;
			var node = data.node;
			var testType = data.testType;

			if (testType.string == STR_SERVER) {
				config = getServerConfig(config);
			}

			var html = Lang.sub(testType.template, config);

			if (node) {
				node.html(html);
			}

			return html;
		}

		function request(config) {
			return A.Promise(
				function(resolve, reject) {
					A.io.request(
						config.url,
						{
							data: config.params,
							dataType: 'json',
							method: 'GET',
							on: {
								failure: function() {
									reject(
										{
											message: 'Unable to retrieve data.'
										}
									);
								},
								success: function() {
									var queue = config.queue;

									if (queue) {
										queue.pause();
									}

									var responseData = this.get('responseData');

									resolve(responseData);

									if (queue) {
										queue.run();
									}
								}
							}
						}
					);
				}
			);
		}

		function requestGithubAvatar(data) {
			var itemNode = data.itemNode;
			var queue = data.queue;
			var username = data.username;

			queue.pause();

			if (gitHubAvatars.has(username)) {
				var avatarUrl = gitHubAvatars.get(username);

				setGithubAvatar(
					{
						avatarUrl: avatarUrl,
						itemNode: itemNode
					}
				);

				queue.run();
			}
			else {
				var requestUrl = Lang.sub(
					URL_GITHUB_USER,
					{
						username: username
					}
				);

				request(
					{
						url: URL_RATE_LIMIT
					}
				).then(
					function(response) {
						var remainingRequests = response.resources.core.remaining;

						if (remainingRequests > 0) {
							request(
								{
									queue: queue,
									url: requestUrl
								}
							).then(
								function(response) {
									var avatarUrl = response.avatar_url;

									setGithubAvatar(
										{
											avatarUrl: avatarUrl,
											itemNode: itemNode
										}
									);

									gitHubAvatars.set(username, avatarUrl);
								}
							);
						}
						else {
							queue.run();
						}
					}
				);
			}
		}

		function serverErr(event, index, response, args) {
			var testObj = args.testObj;

			if (testObj.string == STR_SERVER) {
				renderHTML(
					{
						config: {
							cssClassError: CSS_ERROR,
							id: args.urlID,
							info: '',
							message: '<span class="glyphicon glyphicon-flash"></span>',
							percentUsed: 0
						},
						node: testObj.nodes.item(args.urlID - 1),
						testType: testObj
					}
				);
			}
		}

		function setGithubAvatar(data) {
			var itemNode = data.itemNode;

			if (itemNode) {
				itemNode.setStyle('background-image', 'url(' + data.avatarUrl + ')');
			}
		}

		function update() {
			totalComputerCount = 0;
			totalQueueCount = 0;
			totalUtilizationCount = 0;

			buildUrl(
				{
					config: OBJ_ENVIRONMENT,
					errorMethod: environmentErr,
					methodName: handleEnvironmentData
				}
			);

			buildUrl(
				{
					config: OBJ_FUNCTIONAL_CE,
					errorMethod: environmentErr,
					methodName: handleEnvironmentData
				}
			);

			buildUrl(
				{
					config: OBJ_FUNCTIONAL_EE,
					errorMethod: environmentErr,
					methodName: handleEnvironmentData
				}
			);

			buildUrl(
				{
					config: OBJ_SERVER,
					errorMethod: serverErr,
					methodName: handleServerData
				}
			);

			buildUrl(
				{
					config: OBJ_QUEUE,
					errorMethod: serverErr,
					methodName: handleServerData
				}
			);

			updatePullRequests();

			updatePortalSmoke();

			setTimeout(update, INT_POLL_INTERVAL_SECONDS * 1000);
		}

		function updatePortalSmoke() {
			var dashboardAlertWrapper = A.one('#dashboardAlertWrapper');

			if (dashboardAlertWrapper) {
				request(
					{
						url: URL_PORTAL_SMOKE
					}
				).then(
					function(response) {
						var data = response.data;

						var failedResult = STR_BLANK;

						if (data && data.length) {
							failedResult = data.find(
								function(result) {
									return result.status === 3;
								}
							);
						}

						var dashboardAlertWrapperContent = STR_BLANK;

						if (failedResult !== STR_BLANK) {
							var alertHTML = Lang.sub(
								TPL_ALERT,
								{
									description: STR_PORTAL_SMOKE_PROJECT + ' / ' + STR_PORTAL_SMOKE_ROUTINE,
									executionDate: failedResult.executionDate ? moment(failedResult.executionDate).fromNow() : STR_BLANK,
									title: STR_PORTAL_SMOKE_CASE
								}
							);

							var dashboardAlertNode = A.Node.create(alertHTML);

							dashboardAlertWrapperContent = dashboardAlertNode;
						}

						dashboardAlertWrapper.setHTML(dashboardAlertWrapperContent);
					}
				).catch(
					function(response) {
						dashboardAlertWrapper.setHTML(STR_BLANK);
					}
				);
			}
		}

		function updatePullRequests() {
			request(
				{
					url: URL_PULL_REQUESTS
				}
			).then(
				function(response) {
					var pullRequests = response.testPullRequestQueryString;

					var pullRequestsLimited = pullRequests.slice(0, PULL_REQUESTS_DISPLAY_AMOUNT);

					var pullRequestsParsed = [];

					pullRequestsLimited.forEach(
						function(item) {
							var url = new A.Url('?' + item);

							var parameters = url.getParameters();

							pullRequestsParsed.push(parameters);
						}
					);

					var pullRequestContainerNode = A.one('#pullRequestTestWrapper');

					pullRequestContainerNode.empty();

					var queue = new A.AsyncQueue();

					pullRequestsParsed.forEach(
						function(item) {
							if (pullRequestContainerNode) {
								var pullRequestItemNode = A.Node.create(TPL_PULL_REQUEST_ITEM);

								var receiverHTML = Lang.sub(
									TPL_PULL_REQUEST_AVATAR,
									{
										content: STR_BLANK,
										type: 'receiver'
									}
								);

								var receiverNode = A.Node.create(receiverHTML);

								var senderHTML = Lang.sub(
									TPL_PULL_REQUEST_AVATAR,
									{
										content: STR_BLANK,
										type: 'sender'
									}
								);

								var senderNode = A.Node.create(senderHTML);

								pullRequestItemNode.append(senderNode);
								pullRequestItemNode.append(receiverNode);

								pullRequestContainerNode.append(pullRequestItemNode);

								queue.add(
									{
										args: {
											itemNode: senderNode,
											queue: queue,
											username: item.GITHUB_SENDER_USERNAME
										},
										fn: requestGithubAvatar
									}
								);

								queue.add(
									{
										args: {
											itemNode: receiverNode,
											queue: queue,
											username: item.GITHUB_RECEIVER_USERNAME
										},
										fn: requestGithubAvatar
									}
								);
							}
						}
					);

					var pullRequestItemNode = A.Node.create(TPL_PULL_REQUEST_ITEM);

					var hiddenAmount = pullRequests.length - PULL_REQUESTS_DISPLAY_AMOUNT;

					if (hiddenAmount > 0) {
						var hiddenAmountHTML = Lang.sub(
							TPL_PULL_REQUEST_AVATAR,
							{
								content: '+' + hiddenAmount,
								type: 'hidden-amount'
							}
						);

						var hiddenAmountNode = A.Node.create(hiddenAmountHTML);

						pullRequestItemNode.append(hiddenAmountNode);

						pullRequestContainerNode.append(pullRequestItemNode);
					}

					queue.run();
				}
			).catch(
				function(response) {
					var pullRequestContainerNode = A.one('#pullRequestTestWrapper');

					var errorElement = Lang.sub(
						TPL_ERROR_TEXT,
						{
							text: response.message
						}
					);

					pullRequestContainerNode.setHTML(errorElement);
				}
			);
		}

		init();
	}
);