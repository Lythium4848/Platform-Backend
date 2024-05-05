package Definitions

import kotlinx.serialization.Serializable

@Serializable
class FormationData(
	val loadingCategory: LoadingCategory? = null,
	val coaches: List<CoachData>
)