#
# Copyright (c) 2000-present Liferay, Inc. All rights reserved.
#
# The contents of this file are subject to the terms of the Liferay Enterprise
# Subscription License ("License"). You may not use this file except in
# compliance with the License. You can obtain a copy of the License by
# contacting Liferay, Inc. See the License for the specific language governing
# permissions and limitations under the License, including but not limited to
# distribution rights of the Software.
#

class CPDefinitionField(object):

	META_DESCRIPTION = 'metaDescription'

	META_KEYWORDS = 'metaKeywords'

	META_TITLE = 'metaTitle'

	PRODUCT_TYPE_NAME = 'productTypeName'

	SPECIFICATION_OPTIONS_IDS = 'specificationOptionsIds'

	SPECIFICATION_OPTIONS_NAMES = 'specificationOptionsNames'

class Field(object):

	ASSET_CATEGORY_IDS = 'assetCategoryIds'

	COMPANY_ID = 'companyId'

	DESCRIPTION = 'description'

	ENTRY_CLASS_NAME = 'entryClassName'

	ENTRY_CLASS_PK = 'entryClassPK'

	GROUP_ID = 'groupId'

	NAME = 'name'

	STATUS = 'status'

class ProductContentRecommendationConstants(object):

	PRODUCT_CONTENT_RECOMMENDATION_SCORE_FUNCTION = \
           'product.content.recommendation.score.function'

class ProductInteractionRecommendationConstants(object):

	PRODUCT_INTERACTION_RECOMMENDATION_SCORE_FUNCTION = \
           'product.interaction.recommendation.score.function'

	PRODUCT_INTERACTION_RECOMMENDATION_ENABLE =\
           'product.interaction.recommendation.enable'

class UserInteractionRecommendationConstants(object):

	USER_INTERACTION_RECOMMENDATION_ALS_CHECKPOINT_INTERVAL = \
           'user.interaction.recommendation.als.checkpoint.interval'

	USER_INTERACTION_RECOMMENDATION_CATALOG_COVERAGE = \
           'user.interaction.recommendation.catalog.coverage'

	USER_INTERACTION_RECOMMENDATION_TRAIN_SPLIT_RATIO = \
           'user.interaction.recommendation.train.split.ratio'

	USER_INTERACTION_RECOMMENDATION_TUNING_ALPHA = \
           'user.interaction.recommendation.tuning.alpha'

	USER_INTERACTION_RECOMMENDATION_TUNING_CROSS_VALIDATOR_NUM_FOLDS = \
           'user.interaction.recommendation.tuning.cross.validator.num.folds'

	USER_INTERACTION_RECOMMENDATION_TUNING_CROSS_VALIDATOR_PARALLELISM = \
           'user.interaction.recommendatin.tuning.cross.validator.parallelism'

	USER_INTERACTION_RECOMMENDATION_TUNING_RANK = \
           'user.interaction.recommendation.tuning.rank'

	USER_INTERACTION_RECOMMENDATION_TUNING_REGULARIZATION_PARAMETER = \
           'user.interaction.recommendation.tuning.regularization.parameter'

	USER_INTERACTION_RECOMMENDATION_TUNING_MAX_ITERATION = \
           'user.interaction.recommendation.tuning.max.iteration'
