import Component, {Config} from 'metal-jsx';

class HotKey extends Component {
	render() {
		const {definition, keys} = this.props.shortcut;

		return (
			<li class="hot-key-container" tabindex="0">
				<span class="definition">{definition}</span>

				<span class="keys-wrapper">
					{
						keys.split(' ').map(
							(key, i) => <kbd class="key" key={i}>{key}</kbd>
						)
					}
				</span>
			</li>
		);
	}
}

HotKey.PROPS = {
	shortcut: Config.shapeOf(
		{
			definition: Config.string(),
			keys: Config.string()
		}
	)
};

export default HotKey;