import Component, {Config} from 'metal-jsx';

import InlineButton from './InlineButton';

class OverlayErrorMessage extends Component {
	render() {
		const {message, onHide} = this.props;

		return (
			<div class="overlay-error-message-container">
				{message}{' - '}

				<InlineButton onClick={onHide}>
					{Liferay.Language.get('dismiss')}
				</InlineButton>
			</div>
		);
	}
}

OverlayErrorMessage.PROPS = {
	message: Config.string(),
	onHide: Config.func()
};

export default OverlayErrorMessage;