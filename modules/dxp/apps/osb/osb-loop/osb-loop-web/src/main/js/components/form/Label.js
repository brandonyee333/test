import Component, {Config} from 'metal-jsx';

class Label extends Component {
	render() {
		const {required, text} = this.props;

		const requiredLabel = required ? ` (${Liferay.Language.get('required')})` : '';

		return (
			<label {...this.otherProps()} class="label-container">
				{`${text}${requiredLabel}`}
			</label>
		);
	}
}

Label.PROPS = {
	required: Config.bool().value(false),
	text: Config.string().required(),
	valid: Config.bool().value(true)
};

export default Label;