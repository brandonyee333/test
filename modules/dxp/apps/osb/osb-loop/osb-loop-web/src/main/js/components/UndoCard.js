import Component, {Config} from 'metal-jsx';

import Icon from './Icon';

class UndoCard extends Component {
	render() {
		const {message, onClose, onUndo} = this.props;

		const langUndo = Liferay.Language.get('undo');

		return (
			<div class="undo-card-container">
				<div class="undo-card-content">
					{message}

					<span
						class="undo"
						data-tooltip
						onClick={onUndo}
						title={langUndo}
					>
						{langUndo}{'.'}
					</span>
				</div>

				<span
					class="dismiss"
					data-tooltip
					onClick={onClose}
					title={Liferay.Language.get('close')}
				>
					<Icon name="cancel" />
				</span>
			</div>
		);
	}
}

UndoCard.PROPS = {
	message: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	onClose: Config.func(),
	onUndo: Config.func()
};

export default UndoCard;