import Component, {Config} from 'metal-jsx';

import Content from './Content';
import Image from './Image';

class LinkPreview extends Component {
	render() {
		const {linkData} = this.props;

		return (
			<div class="link-preview-container">
				{linkData && linkData.imageURL &&
					<Image linkData={linkData} />
				}

				<Content linkData={linkData} />
			</div>
		);
	}
}

LinkPreview.PROPS = {
	linkData: Config.object()
};

LinkPreview.Content = Content;
LinkPreview.Image = Image;

export default LinkPreview;