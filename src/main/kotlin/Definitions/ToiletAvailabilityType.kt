package Definitions

import Enums.ToiletAvailability
import kotlinx.serialization.Serializable

@Serializable
class ToiletAvailabilityType(
	val status: ToiletAvailability,
	val reason: String,
) {}