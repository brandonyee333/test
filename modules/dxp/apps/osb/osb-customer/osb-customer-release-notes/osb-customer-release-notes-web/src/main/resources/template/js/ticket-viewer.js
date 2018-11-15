function hideTicketViewer() {
	var ticketViewer = document.getElementById("ticket-viewer");

	ticketViewer.style.display = "none";
}

function loadTicket(jiraIssueId) {
	for (var i = 0; i < jiraIssues.length; i++) {
		if (jiraIssues[i].jiraIssueId == jiraIssueId) {
			var ticketViewerJIRAIssueComponents = document.getElementById(
				"ticket-viewer-jira-issue-components");

			ticketViewerJIRAIssueComponents.innerHTML =
				jiraIssues[i].components;

			var ticketViewerJIRAIssueDescription = document.getElementById(
				"ticket-viewer-jira-issue-description");

			ticketViewerJIRAIssueDescription.innerHTML =
				jiraIssues[i].description;

			var isRelatedTo = "";

			for (var j = 0; j < jiraIssues[i].isRelatedTo.length; j++) {
				isRelatedTo += '<a href="https://issues.liferay.com/browse/' + jiraIssues[i].isRelatedTo[j] + '">' + jiraIssues[i].isRelatedTo[j] + '</a> ';
			}

			var ticketViewerJIRAIssueIsRelatedTo = document.getElementById(
				"ticket-viewer-jira-issue-is-related-to");

			ticketViewerJIRAIssueIsRelatedTo.innerHTML = isRelatedTo;

			var ticketViewerJIRAIssueKey = document.getElementById(
				"ticket-viewer-jira-issue-key");

			ticketViewerJIRAIssueKey.innerHTML = jiraIssues[i].key;

			var ticketViewerJIRAIssuePriority = document.getElementById(
				"ticket-viewer-jira-issue-priority");

			if (jiraIssues[i].priority == 2) {
				ticketViewerJIRAIssuePriority.innerHTML = "Critical";
			}
			else if (jiraIssues[i].priority == 3) {
				ticketViewerJIRAIssuePriority.innerHTML = "Major";
			}
			else if (jiraIssues[i].priority == 4) {
				ticketViewerJIRAIssuePriority.innerHTML = "Minor";
			}
			else if (jiraIssues[i].priority == 5) {
				ticketViewerJIRAIssuePriority.innerHTML = "Trival";
			}

			var ticketViewerJIRAIssueSummary = document.getElementById(
				"ticket-viewer-jira-issue-summary");

			ticketViewerJIRAIssueSummary.innerHTML = jiraIssues[i].summary;

			var ticketViewerJIRAIssueType = document.getElementById(
				"ticket-viewer-jira-issue-type");

			if (jiraIssues[i].type == 1) {
				ticketViewerJIRAIssueType.innerHTML = "Bug";
			}
			else if (jiraIssues[i].type == 2) {
				ticketViewerJIRAIssueType.innerHTML = "New Feature";
			}
			else if (jiraIssues[i].type == 4) {
				ticketViewerJIRAIssueType.innerHTML = "Improvement";
			}
		}
	}
}

function showTicketViewer() {
	var scrollX;
	var scrollY;

	if (self.pageYOffset) {
		scrollX = self.pageXOffset;
		scrollY = self.pageYOffset;
	}
	else if (document.body) {
		scrollX = document.body.scrollLeft;
		scrollY = document.body.scrollTop;
	}
	else if (document.documentElement &&
			document.documentElement.scrollTop) {

		scrollX = document.documentElement.scrollLeft;
		scrollY = document.documentElement.scrollTop;
	}

	var centerX;
	var centerY;

	if (self.innerHeight) {
		centerX = window.innerWidth;
		centerY = window.innerHeight;
	}
	else if (document.body) {
		centerX = document.body.clientWidth;
		centerY = document.body.clientHeight;
	}
	else if (document.documentElement &&
		document.documentElement.clientHeight) {

		centerX = document.documentElement.clientWidth;
		centerY = document.documentElement.clientHeight;
	}

	var ticketViewer = document.getElementById("ticket-viewer");

	ticketViewer.style.display = "block";
	ticketViewer.style.left = (scrollX + ((centerX - 800) / 2)) + "px";
	ticketViewer.style.top = (scrollY + ((centerY - 500) / 2)) + "px";

	return false;
}

function viewTicket(jiraIssueId) {
	loadTicket(jiraIssueId);
	showTicketViewer();

	return false;
}