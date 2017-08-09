/* global google */

import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';

import GoogleMap from './GoogleMap';
import sendRequest from '../lib/request';

class GoogleMapWrapper extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleMapsReady',
			'handleOnClick',
			'handleUpdateValue'
		);
	}

	findAddress(address) {
		if (address && this.state.mapsReady) {
			Liferay.Watson.mapComponent.geocoder.geocode(
				{
					address
				},
				(results, status) => {
					if (status === google.maps.GeocoderStatus.OK) {
						const location = results[0].geometry.location;

						const latLng = {
							lat: location.lat(),
							lng: location.lng()
						};

						this.refocusMap(latLng, 15);

						this.placeMarker(latLng);
					}
				}
			);
		}
	}

	findStreet(latLng) {
		if (latLng && this.state.mapsReady) {
			Liferay.Watson.mapComponent.geocoder.geocode(
				{latLng},
				(results, status) => {
					if (status === google.maps.GeocoderStatus.OK) {
						const addressResult = results[0].address_components;

						this.populateAddressFields(addressResult);

						addressResult.forEach(
							addressComponent => {
								if (addressComponent.types === 'route') {
									this.props.onChange(addressComponent.long_name, 'street');
								}
							}
						);
					}
				}
			);
		}
	}

	focusMap(address, postalCode, provinceWatsonListTypeId, subDistrictWatsonListTypeId) {
		if (address) {
			this.findAddress(address);

			if (subDistrictWatsonListTypeId) {
				this.setState(
					{
						postalCode,
						provinceWatsonListTypeId,
						subDistrictWatsonListTypeId
					}
				);
			}
			else if (postalCode) {
				this.setState(
					{
						postalCode,
						provinceWatsonListTypeId
					}
				);
			}
			else if (provinceWatsonListTypeId) {
				this.setState(
					{
						provinceWatsonListTypeId
					}
				);
			}

			if (Liferay.Watson.mapComponent.marker) {
				this.handleUpdateValue(
					Liferay.Watson.mapComponent.marker.getPosition().lat(),
					Liferay.Watson.mapComponent.marker.getPosition().lng()
				);
			}
		}
	}

	handleMapsReady() {
		this.setState({mapsReady: true});
	}

	handleOnClick(event) {
		const {disabled = false} = this.props;

		if (!disabled) {
			const {lat: latitude, lng: longitude} = event;

			const latLng = {
				lat: latitude,
				lng: longitude
			};

			this.placeMarker(latLng);

			this.findStreet(latLng);

			this.handleUpdateValue(latitude, longitude);
		}
	}

	handleUpdateValue(latitude, longitude) {
		latitude = parseFloat(latitude);
		longitude = parseFloat(longitude);

		this.setState(
			{
				latitude,
				longitude
			}
		);

		this.props.onChange(latitude, 'latitude');

		this.props.onChange(longitude, 'longitude');
	}

	placeMarker(latLng) {
		let placeMarkerStatus = false;

		if (this.state.mapsReady) {
			if (Liferay.Watson.mapComponent.marker) {
				Liferay.Watson.mapComponent.marker.setMap(null);
			}

			Liferay.Watson.mapComponent.marker = new google.maps.Marker(
				{
					icon: `${Liferay.ThemeDisplay.getPathThemeImages()}/marker.svg`,
					map: Liferay.Watson.mapComponent.map,
					position: latLng
				}
			);

			this.setState({markerPosition: latLng});

			placeMarkerStatus = true;
		}
		else {
			this.setState({mapsWereNotReady: latLng});
		}

		return placeMarkerStatus;
	}

	populateAddressFields(addressResults) {
		if (addressResults) {
			let country = '';
			let district = '';
			let googleMapsPostalCode = '';
			let province = '';
			let region = '';
			let subdistrict = '';

			addressResults.forEach(
				addressComponent => {
					const {long_name: longName, short_name: shortName, types} = addressComponent;

					if (types.includes('country')) {
						country = shortName;
					}

					if (types.includes('postal_code')) {
						googleMapsPostalCode = longName;
					}

					if (types.includes('administrative_area_level_1')) {
						province = longName.replace('Chang Wat ', '');

						if (province.includes('Krung Thep Maha Nakhon')) {
							province = 'Bangkok';
						}
					}

					if (types.includes('administrative_area_level_2') || types.includes('locality')) {
						district = longName.replace('Amphoe ', '');
					}

					if (types.includes('sublocality') || types.includes('sublocality_level_1')) {
						subdistrict = longName.replace('Tambon ', '');
					}

					if (!subdistrict && types.includes('locality')) {
						subdistrict = longName.replace('Tambon ', '');
					}

					if (province === district) {
						district = subdistrict;
					}

					region = province;
				}
			);

			const language = WatsonConstants.currentLanguageId;

			const requestValues = {country, district, language, province, subdistrict};

			sendRequest(
				{
					controller: 'list_type',
					controllerMethod: 'syncListTypes.json',
					data: requestValues
				}
			).then(
				response => {
					if (response && response.data) {
						const {onChange} = this.props;
						const {postalCode} = this.state;

						const {countryId, districtWatsonListTypeId, provinceWatsonListTypeId, subDistrictWatsonListTypeId} = response.data;

						onChange(countryId, 'countryId');

						if (region) {
							onChange(region, 'region');
						}

						onChange(provinceWatsonListTypeId, 'provinceWatsonListTypeId');

						onChange(districtWatsonListTypeId, 'districtWatsonListTypeId');

						onChange(subDistrictWatsonListTypeId, 'subDistrictWatsonListTypeId');

						if (googleMapsPostalCode && googleMapsPostalCode !== postalCode) {
							onChange(googleMapsPostalCode, 'postalCode');

							this.setState(
								{
									postalCode: googleMapsPostalCode
								}
							);
						}

						this.setState(
							{
								districtWatsonListTypeId,
								provinceWatsonListTypeId,
								subDistrictWatsonListTypeId
							}
						);
					}
				}
			);
		}
	}

	refocusMap(latLng, zoomLevel) {
		this.setState(
			{
				center: latLng,
				zoomLevel
			}
		);
	}

	refocusMapWatsonData(provinceLabel, provinceWatsonListTypeId, postalCode, subdistrictLabel, subDistrictWatsonListTypeId) {
		const {state} = this;

		if (subdistrictLabel && state.subDistrictWatsonListTypeId !== subDistrictWatsonListTypeId) {
			this.focusMap(`${provinceLabel} ${postalCode} ${subdistrictLabel}`, postalCode, provinceWatsonListTypeId, subDistrictWatsonListTypeId);
		}
		else if (postalCode && state.postalCode !== postalCode) {
			this.focusMap(`${provinceLabel} ${postalCode}`, postalCode);
		}
		else if (provinceLabel && state.provinceWatsonListTypeId !== provinceWatsonListTypeId) {
			this.focusMap(provinceLabel, null, provinceWatsonListTypeId);
		}
	}

	render() {
		const {center, zoomLevel} = this.state;

		return (
			<div class="map-wrapper" >
				<GoogleMap
					center={center}
					onClick={this.handleOnClick}
					onReady={this.handleMapsReady}
					zoom={zoomLevel}
				/>
			</div>
		);
	}

	rendered() {
		const {mapsReady, mapsWereNotReady} = this.state;

		if (mapsReady && mapsWereNotReady) {
			const markerWasSet = this.placeMarker(mapsWereNotReady);

			if (markerWasSet) {
				this.setState({mapsWereNotReady: null});
			}
		}
	}

	syncFormData(newState) {
		const {props, state} = this;

		const {fieldConfig, subdistrictOptions} = props;

		const {postalCode, provinceWatsonListTypeId, subDistrictWatsonListTypeId} = newState;
		let {latitude, longitude} = newState;

		latitude = parseFloat(latitude);
		longitude = parseFloat(longitude);

		let shouldRefocusMapData = true;

		if (latitude && longitude) {
			if (state.latitude !== latitude || state.longitude !== longitude) {
				shouldRefocusMapData = false;

				const latLng = {
					lat: latitude,
					lng: longitude
				};

				this.placeMarker(latLng);

				this.refocusMap(latLng, 15);

				if (provinceWatsonListTypeId && postalCode && subDistrictWatsonListTypeId) {
					this.setState(
						{
							latitude,
							longitude,
							postalCode,
							provinceWatsonListTypeId,
							subDistrictWatsonListTypeId
						}
					);
				}
				else {
					this.setState(
						{
							latitude,
							longitude
						}
					);
				}
			}
		}

		if (shouldRefocusMapData) {
			const {label: provinceLabel} = provinceWatsonListTypeId > 0 ? fieldConfig.provinceWatsonListTypeId.options[provinceWatsonListTypeId] : '';

			let subdistrictLabel = '';

			if (subdistrictOptions && subDistrictWatsonListTypeId) {
				const subdistrict = subdistrictOptions[subDistrictWatsonListTypeId];

				if (subdistrict) {
					subdistrictLabel = subdistrict.label;
				}
			}

			this.refocusMapWatsonData(provinceLabel, provinceWatsonListTypeId, postalCode, subdistrictLabel, subDistrictWatsonListTypeId);
		}
	}
}

GoogleMapWrapper.PROPS = {
	disabled: Config.bool().value(false),
	fieldConfig: Config.object().value({}),
	formData: Config.object().value({})
};

GoogleMapWrapper.STATE = {
	center: Config.object().value(
		{
			lat: 16.04581345,
			lng: 101.27197265
		}
	),
	defaultMapOptions: Config.object().value(
		{
			mapTypeControl: true,
			mapTypeIds: [
				'ROADMAP', 'HYBRID', 'SATELLITE'
			],
			styles: [
				{
					elementType: 'all',
					featureType: 'all',
					stylers: [
						{
							visibility: 'on'
						}
					]
				}
			]
		}
	),
	latitude: Config.value(null),
	longitude: Config.value(null),
	mapsReady: Config.bool().value(false),
	mapsWereNotReady: Config.value(null),
	markerPosition: Config.value(null),
	postalCode: Config.value(null),
	provinceWatsonListTypeId: Config.value(null),
	subDistrictWatsonListTypeId: Config.value(null),
	zoomLevel: Config.number().value(7)
};

function mapStateToProps(state, props) {
	const {fieldConfig = {}, formData = {}} = props;

	const {listTypeValue, parentInputId} = fieldConfig.subDistrictWatsonListTypeId;

	const parentCurrentValue = formData[parentInputId];

	let subdistrictOptions = {};

	if (parentCurrentValue && listTypeValue) {
		subdistrictOptions = state.getIn(['list_types', 'data', parentCurrentValue, listTypeValue]) || {};
	}

	return {
		subdistrictOptions
	};
}

export default connect(mapStateToProps)(GoogleMapWrapper);