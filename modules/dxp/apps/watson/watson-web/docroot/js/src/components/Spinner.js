import JSXComponent, {Config} from 'metal-jsx';

class Spinner extends JSXComponent {
	render() {
		const {size} = this.props;

		return (
			<div class="spinner-container">
				<div class={`spinner ${size}`} />
			</div>
		);
	}
}

Spinner.PROPS = {
	size: Config.string().value('small')
};

export default Spinner;