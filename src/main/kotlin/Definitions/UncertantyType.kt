package Definitions

import Enums.UncertantyType
import kotlinx.serialization.Serializable

@Serializable
class UncertaintyType(
	val status: UncertantyType,
	val reason: String,
) {}