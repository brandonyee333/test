import Component, {Config} from 'metal-jsx';

class RadioGroup extends Component {
	render() {
		const {
			checked,
			children,
			component,
			name,
			onChange
		} = this.props;

		return (
			<div class="radio-group-container">
				{
					children.map(
						(child, i) => {
							const {props} = child;

							child.props = {
								...props,
								checked: checked === props.value,
								name,
								onChange
							};

							let retVal = child;

							if (component) {
								const Wrapper = component;

								retVal = (
									<Wrapper key={i}>
										{child}
									</Wrapper>
								);
							}

							return retVal;
						}
					)
				}
			</div>
		);
	}
}

RadioGroup.PROPS = {
	checked: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	component: Config.func(),
	name: Config.string(),
	onChange: Config.func()
};

export default RadioGroup;