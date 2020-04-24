import TinyLinkPreview from '../TinyLinkPreview';

describe(
	'TinyLinkPreview',
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
			'should render',
			() => {
				component = new TinyLinkPreview(
					{
						linkData: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render an image',
			() => {
				component = new TinyLinkPreview(
					{
						linkData: {
							imageURL: 'localhost/test.jpeg'
						}
					}
				);

				expect(component.element.querySelector('.link-image')).toBeTruthy();
			}
		);
	}
);