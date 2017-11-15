import JSXComponent from 'metal-jsx';
import sub from 'string-sub';

class LandingPage extends JSXComponent {
	render() {
		return (
			<div class="page-container landing-page">
				<div class="welcome-landing-text">
					{sub(Liferay.Language.get('welcome-to-watson-x'), Liferay.ThemeDisplay.getUserName())}
				</div>

				<div class="arrow-container">
					<div class="arrow bounce" />

					<span class="landing-text">{Liferay.Language.get('please-select-a-method-on-the-left')}</span>
				</div>
			</div>
		);
	}
}

export default LandingPage;