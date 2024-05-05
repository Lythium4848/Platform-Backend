package Definitions

class CoachData(
	val coachClass: String,
	val loading: Int,
	val loadingSpecified: Boolean,
	val number: String,
	val toilet: List<ToiletAvailabilityType>
)