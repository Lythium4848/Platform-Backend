package Definitions

import kotlinx.serialization.Serializable

@Serializable
class DepartureItemWithCallingPoints(
	val crs: String,
	val service: List<ServiceItemWithCallingPoints>
)