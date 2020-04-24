import Component from 'metal-jsx';

import LoopConstants from '../lib/loop-constants';

class ErrorPage extends Component {
	render() {
		return (
			<div class="error-page">
				<div class="message">
					<div class="title">{404}</div>

					{Liferay.Language.get('we-cant-seem-to-find-what-you-are-looking-for')}

					<div class="custom-message">
						{`${Liferay.Language.get('looking-to-play-a-game-instead')} `}

						<a href="https://loop.liferay.com/web/guest/name-game">{Liferay.Language.get('play-here')}</a>
					</div>

					<div class="footer">
						<a href={LoopConstants.urls.home}>{Liferay.Language.get('back-to-loop')}</a>
					</div>
				</div>

				<img src="/images/error.svg" />
			</div>
		);
	}
}

export default ErrorPage;