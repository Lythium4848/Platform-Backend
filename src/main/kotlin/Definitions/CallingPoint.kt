package Definitions

import Enums.UncertantyType

class CallingPoint(
	val locationName: String,
	val crs: String,
	val st: String,
	val et: String,
	val at: String,
	val isCancelled: Boolean,
	val length: Int,
	val detachFront: Boolean,
	val adhocAlerts: List<String>? = null,
	val uncertanty: UncertantyType,
	val affectedBy: String? = null,

)