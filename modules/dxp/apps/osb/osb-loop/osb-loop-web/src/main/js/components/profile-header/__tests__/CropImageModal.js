import CropImageModal from '../CropImageModal';

describe(
	'CropImageModal',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new CropImageModal(
					{
						imageData: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);