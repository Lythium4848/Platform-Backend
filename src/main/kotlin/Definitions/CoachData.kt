package Definitions

import kotlinx.serialization.Serializable

@Serializable
class CoachData(
	val coachClass: String,
	val loading: Int? = null,
	val loadingSpecified: Boolean,
	val number: String,
	val toilet: List<ToiletAvailabilityType>? = null
)