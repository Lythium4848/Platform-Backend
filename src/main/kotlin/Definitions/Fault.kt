package Definitions

import kotlinx.serialization.Serializable

@Serializable
class FaultDetail(
	val errorcode: String
)

@Serializable
class FaultData(
	val faultstring: String,
	val detail: FaultDetail
)

@Serializable
class Fault(
	val fault: FaultData? = null,
	val Message: String? = null
)