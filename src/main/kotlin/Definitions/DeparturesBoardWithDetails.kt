package Definitions

import kotlinx.serialization.Serializable

@Serializable
class DeparturesBoardWithDetails(
	val generatedAt: String,
	val locationName: String,
	val crs: String,
	val filterLocationName: String? = null,
	val filtercrs: String? = null,
	val filterType: String? = null,
	val nrccMessages: List<NrccMessage>? = null,
	val platformAvailable: Boolean? = null,
	val areServicesAvailable: Boolean = true,
	val departures: List<DepartureItemWithCallingPoints>? = null,
)