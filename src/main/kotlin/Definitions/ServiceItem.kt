package Definitions

class ServiceItem(
	val origin: List<ServiceLocation>, // TODO: List of ServiceLocation's giving original origins of the service
	val destination: List<ServiceLocation>, // TODO: List of ServiceLocation's giving final destination of the service
	val currentOrigins: List<ServiceLocation>? = null, // TODO: List of ServiceLocation's giving live/current origins of the service which is not starting at cancelled origins.
	val currentDestinations: List<ServiceLocation>? = null, // TOOD: List of ServiceLocation's giving live/current destinations of the service which is not ending at cancelled destinations.
	val sta: String? = null, // Scheduled time of arrival at the destination
	val eta: String? = null, // Estimated time of arrival at the destination
	val std: String? = null, // Scheduled time of departure from the origin
	val etd: String? = null, // Estimated time of departure from the origin
	val platform: String? = null, // Platform number at the origin
	val operator: String, // Operator of the service
	val operatorCode: String, // Operator code of the service
	val isCircularRoute: Boolean, // Indicates if the service is a circular route - Will the service circle back to the origin on its journey
	val isCancelled: Boolean, // Indicates if the service has been cancelled
	val filterLocationCancelled: Boolean?, // Indicates if the filter location has been cancelled.
	val serviceType: String, // Type of service (train, bus, ferry)
	val length: Int? = 0, // The train length
	val detachFront: Boolean? = null, // Indicates if the front of the train will detach at a certain location
	val isReverseFormation: Boolean? = null, // Indicates if the service is operating in reverse formation
	val cancelReason: String? = null, // The reason for the service being cancelled
	val delayReason: String? = null, // The reason for the service being delayed
	val serviceID: String, // Unique ID of the service relative to the Station Board on which it is displayed
	val adhocAlerts: List<String>? = null, // List of AdhocAlerts
	val uncertantyType: UncertaintyType? = null, // The uncertainty type of the service
	val affectedBy: List<String>? = null, // The NRE incident number
) {}