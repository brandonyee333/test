import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

class Toggle extends Component {
	render() {
		const {checked, label, onChange} = this.props;

		const classes = getCN(
			'toggle-container',
			{checked}
		);

		return (
			<div class={classes}>
				<input
					{...this.otherProps()}
					checked={checked ? 'checked' : null}
					class="toggle-input"
					onChange={onChange}
					type="checkbox"
				/>

				{label &&
					<span class="toggle-label">{label}</span>
				}

				<div class="toggle" data-tooltip title={checked ? Liferay.Language.get('turn-off') : Liferay.Language.get('turn-on')}>
					<div class="toggle-slide" />

					<div class="toggle-dot" />
				</div>
			</div>
		);
	}
}

Toggle.PROPS = {
	checked: Config.bool(),
	label: Config.string(),
	onChange: Config.func()
};

export default Toggle;