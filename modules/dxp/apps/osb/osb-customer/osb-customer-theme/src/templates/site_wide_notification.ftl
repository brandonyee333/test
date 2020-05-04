<div class="alert alert-danger alert-dismissible alert-fluid hide site-wide-notification" id="siteWideNotification" role="alert">
	<div>
		<svg class="lexicon-icon lexicon-icon-exclamation-full" viewBox="0 0 512 512">
			<use xlink:href="#exclamation-full" />
		</svg>

		<div class="notification-content">
			<div class="semi-bold">
				<@liferay.language key="security-alerts" />
			</div>

			<div>
				LSV-412:
				<@liferay.language key="registered-user-rce-using-json-deserialization" />

				<a class="semi-bold" href="https://help.liferay.com/hc/articles/360020526952">
					<@liferay.language key="view-details" /></a>
			</div>

			<div>
				LSV-545:
				<@liferay.language key="unauthenticated-remote-code-execution-via-jsonws" />

				<a class="semi-bold" href="https://help.liferay.com/hc/articles/360035141111">
					<@liferay.language key="view-details" /></a>
			</div>
		</div>
	</div>

	<button aria-label="Close" class="close" id="closeNotification" type="button">
		<svg aria-hidden="true" class="lexicon-icon lexicon-icon-times" viewBox="0 0 512 512">
			<use xlink:href="#times" />
		</svg>
	</button>
</div>