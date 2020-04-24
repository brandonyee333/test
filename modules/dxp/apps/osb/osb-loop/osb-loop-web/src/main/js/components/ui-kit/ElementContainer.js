import Component, {Config} from 'metal-jsx';

class ElementContainer extends Component {
	render() {
		const {children, header} = this.props;

		return (
			<div>
				{header &&
					<h1 class="element-header-2">{header}</h1>
				}

				<div {...this.otherProps()} class="element-container">
					{children}
				</div>
			</div>
		);
	};
}

ElementContainer.PROPS = {
	header: Config.string()
};

export default ElementContainer;