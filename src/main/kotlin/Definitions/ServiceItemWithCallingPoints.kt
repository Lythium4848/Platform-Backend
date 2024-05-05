package Definitions

class ServiceItemWithCallingPoints(
	val origin: List<ServiceLocation>,
	val destination: List<ServiceLocation>,
	val currentOrigins: List<ServiceLocation>? = null,
	val currentDestinations: List<ServiceLocation>? = null,
	val sta: String? = null,
	val eta: String? = null,
	val std: String? = null,
	val etd: String? = null,
	val platform: String? = null,
	val operator: String,
	val operatorCode: String,
	val isCircularRoute: Boolean,
	val isCancelled: Boolean,
	val filterLocationCancelled: Boolean?,
	val serviceType: String,
	val length: Int? = 0,
	val detachFront: Boolean? = null,
	val isReverseFormation: Boolean? = null,
	val cancelReason: String? = null,
	val delayReason: String? = null,
	val serviceID: String,
	val adhocAlerts: List<String>? = null,
	val previousCallingPoints: List<CallingPoint>? = null, // List of ServiceLocation's giving previous calling points of the service
	val subsequentCallingPoints: List<CallingPoint>? = null, // List of ServiceLocation's giving subsequent calling points of the service
	val uncertantyType: UncertaintyType? = null,
	val affectedBy: List<String>? = null,
) {}