import Component, {Config} from 'metal-jsx';

import ElementContainer from './ElementContainer';
import ImageSorter from '../ImageSorter';
import Kit from './Kit';

const IMAGE_ONE = {
	completed: true,
	name: 'one',
	url: '/loop-portlet/images/cover_images/cover_image_1_web.jpg'
};

class ImageSorterKit extends Component {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleChange_(newArray) {
		this.state.images_ = newArray;
	}

	render() {
		return (
			<Kit header="ImageSorter">
				<ElementContainer header="uploading">
					<ImageSorter
						images={[
							{
								...IMAGE_ONE,
								progress: 64
							}
						]}
					/>
				</ElementContainer>

				<ElementContainer header="upload Complete">
					<ImageSorter
						images={[IMAGE_ONE]}
					/>
				</ElementContainer>

				<ElementContainer header="sortable and removable images">
					<ImageSorter
						images={this.state.images_}
						onChange={this.handleChange_}
					/>
				</ElementContainer>
			</Kit>
		);
	};
}

ImageSorterKit.STATE = {
	images_: Config.value(
		[
			IMAGE_ONE,
			{
				completed: true,
				name: 'two',
				url: '/loop-portlet/images/cover_images/cover_image_2_web.jpg'
			},
			{
				completed: true,
				name: 'three',
				url: '/loop-portlet/images/cover_images/cover_image_3_web.jpg'
			}
		]
	)
};

export default ImageSorterKit;