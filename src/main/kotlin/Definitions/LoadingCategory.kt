package Definitions

import kotlinx.serialization.Serializable

@Serializable
class LoadingCategory(
	val code: String,
	val color: String,
	val image: String
) {}