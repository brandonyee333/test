import dom from 'metal-dom';

import ImageSorter from '../ImageSorter';

const IMAGE_ARRAY = [
	{
		name: 'one',
		url: '/loop-portlet/images/cover_images/cover_image_1_web.jpg'
	},
	{
		name: 'two',
		url: '/loop-portlet/images/cover_images/cover_image_2_web.jpg'
	}
];

describe(
	'ImageSorter',
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
				component = new ImageSorter(
					{
						images: IMAGE_ARRAY
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render any number of images',
			() => {
				component = new ImageSorter(
					{
						images: IMAGE_ARRAY
					}
				);

				expect(component.element.querySelectorAll('.sortable-image-container').length).toBe(2);
			}
		);

		it(
			'should call onChange when removing an image',
			() => {
				const spy = jest.fn();

				component = new ImageSorter(
					{
						images: IMAGE_ARRAY,
						onChange: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.cancel.post'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call onChange when handleReorder is called',
			() => {
				const spy = jest.fn();

				component = new ImageSorter(
					{
						images: IMAGE_ARRAY,
						onChange: spy
					}
				);

				component.handleReorder_(0, 1);

				expect(spy).toBeCalled();
			}
		);

		it(
			'renders image from imageURL_web',
			() => {
				component = new ImageSorter(
					{
						images: [
							{
								imageURL_web: '/loop-portlet/images/cover_images/cover_image_3_web.jpg',
								name: 'one'
							}
						]
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders image from response',
			() => {
				component = new ImageSorter(
					{
						images: [
							{
								name: 'one',
								response: '{"data": {"imageURL_web": "/loop-portlet/images/cover_images/cover_image_4_web.jpg"}}'
							}
						]
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);