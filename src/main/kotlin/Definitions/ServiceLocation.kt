package Definitions

class ServiceLocation(
	val locationName: String,
	val crs: String,
	val via: String?,
	val futureChangeTo: String?,
	val assocIsCancelled: Boolean?,
)