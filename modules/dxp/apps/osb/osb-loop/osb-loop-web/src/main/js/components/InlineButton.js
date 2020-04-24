import Component, {Config} from 'metal-jsx';

class InlineButton extends Component {
	render() {
		const {children, onClick, type} = this.props;

		return (
			<button
				{...this.otherProps()}
				class="inline-button-container"
				onClick={onClick}
				type={type}
			>
				{children}
			</button>
		);
	}
}

InlineButton.PROPS = {
	onClick: Config.func(),
	type: Config.oneOf(['button', 'menu', 'reset', 'submit']).value('button')
};

export default InlineButton;