function changePage(page) {
	var navItemChangeLog = document.getElementById("nav-item-change-log");
	var navItemAPIChanges = document.getElementById("nav-item-api-changes");
	var navItemUpgradeNotes = document.getElementById("nav-item-upgrade-notes");

	var pageChangeLog = document.getElementById("page-change-log");
	var pageAPIChanges = document.getElementById("page-api-changes");
	var pageUpgradeNotes = document.getElementById("page-upgrade-notes");

	setClassName(navItemChangeLog, "");
	setClassName(navItemAPIChanges, "");
	setClassName(navItemUpgradeNotes, "");

	pageChangeLog.style.display = "none";
	pageAPIChanges.style.display = "none";
	pageUpgradeNotes.style.display = "none";

	switch (page) {
		case "change-log":
			setClassName(navItemChangeLog, "selected");

			pageChangeLog.style.display = "block";

			break;
		case "api-changes":
			setClassName(navItemAPIChanges, "selected");

			pageAPIChanges.style.display = "block";

			break;
		case "upgrade-notes":
			setClassName(navItemUpgradeNotes, "selected");

			pageUpgradeNotes.style.display = "block";

			break;
	}

	hideTicketViewer();
}

function setClassName(element, className) {
	if (element != null) {
		element.className = className;
	}
}