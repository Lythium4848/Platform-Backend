package Definitions

import kotlinx.serialization.Serializable

@Serializable
class StationBoard(
	val generatedAt: String,
	val locationName: String,
	val crs: String,
	val filterLocationName: String,
	val filtercrs: String,
	val filterType: String,
	val nrccMessages: List<NrccMessage>,
	val platformAvailable: Boolean? = null,
	val areServicesAvailable: Boolean? = null,
	val trainServices: List<ServiceItem>,
	val busServices: List<ServiceItem>,
	val ferryServices: List<ServiceItem>,
	val fault: FaultData? = null,
) {}

