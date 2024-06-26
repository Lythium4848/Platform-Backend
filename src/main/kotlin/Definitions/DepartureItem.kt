package Definitions

import kotlinx.serialization.Serializable

@Serializable
class DepartureItem(
	val crs: String,
	val service: List<ServiceItem>
)