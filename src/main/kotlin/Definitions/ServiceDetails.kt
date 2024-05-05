package Definitions

class ServiceDetails(
	val generatedAt: String,
	val rsid: String? = null,
	val serviceType: String,
	val locationName: String,
	val crs: String,
	val operator: String,
	val operatorCode: String,
	val isCancelled: Boolean,
	val cancelReason: String? = null,
	val delayReason: String? = null,
	val detachFront: Boolean,
	val diversionReason: String? = null,
	val divertedVia: String? = null,
	val overdueMessage: String? = null,
	val length: Int? = 0,
	val platform: Int? = 0,
	val sta: String? = null,
	val eta: String? = null,
	val ata: String? = null,
	val std: String? = null,
	val atd: String? = null,
	val adhocAlerts: List<String>? = null,
	val previousCallingPoints: List<CallingPoint>? = null, // List of ServiceLocation's giving previous calling points of the service
	val subsequentCallingPoints: List<CallingPoint>? = null, // List of ServiceLocation's giving subsequent calling points of the service
	val formation: List<CallingPoint>? = null, // List of ServiceLocation's giving formation of the service
) {}