import Definitions.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.reflect.typeOf

enum class APIType {
	ArrDep,
	Arr,
	Dep,
	FastestDep,
	NextDep,
	ServiceDetails
}

@Serializable
sealed class ApiResponse

@Serializable
data class SuccessResponseStationBoard(val data: StationBoardWithDetails) : ApiResponse()

@Serializable
data class SuccessResponseDeparturesBoard(val data: DeparturesBoardWithDetails) : ApiResponse()

@Serializable
data class SuccessResponseServiceDetails(val data: ServiceDetails) : ApiResponse()

@Serializable
data class ErrorResponse(val error: Fault) : ApiResponse()

class LDBWS(
	private val apiKeyArrDep: String,
	private val apiKeyArr: String,
	private val apiKeyDep: String,
	private val apiKeyFastestDep: String,
	private val apiKeyNextDep: String,
	private val apiKeyServiceDetails: String
) {
	private fun getHttpUrlBuilder(API: APIType, type: String, crs: String): HttpUrl.Builder {
		val urlBuilder = HttpUrl.Builder()
			.scheme("https")
			.host("api1.raildata.org.uk")

		when (API) {
			APIType.ArrDep -> {
				// https://raildata.org.uk/dashboard/dataProduct/P-7c866984-8a7b-4272-a6f8-00b4aaf821fa/specification
				return urlBuilder.addPathSegments("1010-live-arrival-and-departure-boards-arr-and-dep/LDBWS/api/20220120/${type}/${crs}")
			}

			APIType.Arr -> {
				// https://raildata.org.uk/dashboard/dataProduct/P-d904019d-1b74-4605-a592-9514883de16f/specification
				return urlBuilder.addPathSegments("1010-live-arrival-board-arr/LDBWS/api/20220120/${type}/${crs}")
			}

			APIType.Dep -> {
				// https://raildata.org.uk/dataProduct/P-9a01dd96-7211-4912-bcbb-c1b5d2e35609/specification
				return urlBuilder.addPathSegments("1010-live-departure-board-dep/LDBWS/api/20220120/${type}/${crs}")
			}

			APIType.FastestDep -> {
				// https://raildata.org.uk/dashboard/dataProduct/P-4600b51c-29c6-4cab-b5b0-333c32cf5d3c/overview
				return urlBuilder.addPathSegments("1010-live-fastest-departures/LDBWS/api/20220120/${type}/${crs}")
			}

			APIType.NextDep -> {
				// https://raildata.org.uk/dashboard/dataProduct/P-6226bbd0-5ca7-4d94-ba28-1504c30dc068/specification
				return urlBuilder.addPathSegments("1010-live-next-departure-board/LDBWS/api/20220120/${type}/${crs}")
			}

			APIType.ServiceDetails -> {
				// https://raildata.org.uk/dashboard/dataProduct/P-a9e90d13-3a13-4eda-b34b-5afeeffc3fec/specification
				return urlBuilder.addPathSegments("1010-service-details/LDBWS/api/20220120/${type}/${crs}")
			}
		}
	}


	fun getArrivalBoard(
		numRows: Int = 10,
		crs: String,
		filterCrs: String? = null,
		filterType: String? = "from",
		timeOffset: Int? = 0,
		timeWindow: Int = 120
	): ApiResponse {
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.Arr, "GetArrBoardWithDetails", crs)
		urlBuilder
			.addQueryParameter("numRows", numRows.toString())
			.addQueryParameter("crs", crs)
			.addQueryParameter("filterCrs", filterCrs)
			.addQueryParameter("filterType", filterType)
			.addQueryParameter("timeOffset", timeOffset.toString())
			.addQueryParameter("timeWindow", timeWindow.toString())

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyArr)
			.build()

		val response = client.newCall(request).execute()

		val responseBody = response.body?.string()

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)

		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<StationBoardWithDetails>(jsonElement)
			return SuccessResponseStationBoard(jsonFormatted)
		}
	}


	fun getArrivalDepartureBoard(
		numRows: Int = 10,
		crs: String,
		filterCrs: String? = null,
		filterType: String? = "from",
		timeOffset: Int? = 0,
		timeWindow: Int = 120
	): ApiResponse {
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.ArrDep, "GetArrDepBoardWithDetails", crs)
		urlBuilder
			.addQueryParameter("numRows", numRows.toString())
			.addQueryParameter("crs", crs)
			.addQueryParameter("filterCrs", filterCrs)
			.addQueryParameter("filterType", filterType)
			.addQueryParameter("timeOffset", timeOffset.toString())
			.addQueryParameter("timeWindow", timeWindow.toString())

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyArrDep)
			.build()

		val response = client.newCall(request).execute()
		val responseBody = response.body?.string()
		response.close()

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)

		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<StationBoardWithDetails>(jsonElement)
			return SuccessResponseStationBoard(jsonFormatted)
		}
	}

	fun getDepartureBoard(
		numRows: Int = 10,
		crs: String,
		filterCrs: String? = null,
		filterType: String? = "to",
		timeOffset: Int? = 0,
		timeWindow: Int = 120
	): ApiResponse {
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.Dep, "GetDepBoardWithDetails", crs)
		urlBuilder
			.addQueryParameter("numRows", numRows.toString())
			.addQueryParameter("crs", crs)
			.addQueryParameter("filterCrs", filterCrs)
			.addQueryParameter("filterType", filterType)
			.addQueryParameter("timeOffset", timeOffset.toString())
			.addQueryParameter("timeWindow", timeWindow.toString())

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyDep)
			.build()

		val response = client.newCall(request).execute()
		val responseBody = response.body?.string()
		response.close()

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)

		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<StationBoardWithDetails>(jsonElement)
			return SuccessResponseStationBoard(jsonFormatted)
		}
	}

	fun getFastestDepartures(
		crs: String,
		filterList: List<String>,
		timeOffset: Int? = 0,
		timeWindow: Int = 120
	): ApiResponse { // Currently errors with "Unable to route the message to a Target Endpoint". Potentially an issue with the API itself - Need to check with RDM
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.FastestDep, "GetFastestDeparturesWithDetails", crs)
		urlBuilder
			.addQueryParameter("crs", crs)
			.addQueryParameter("filterList", filterList.joinToString(","))
			.addQueryParameter("timeOffset", timeOffset.toString())
			.addQueryParameter("timeWindow", timeWindow.toString())

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyFastestDep)
			.build()

		val response = client.newCall(request).execute()
		val responseBody = response.body?.string()
		response.close()

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)

		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<DeparturesBoardWithDetails>(jsonElement)
			return SuccessResponseDeparturesBoard(jsonFormatted)
		}
	}

	fun getNextDepartures(
		crs: String,
		filterList: List<String>,
		timeOffset: Int? = 0,
		timeWindow: Int = 120
	): ApiResponse { // Currently errors with "Unable to route the message to a Target Endpoint". Potentially an issue with the API itself - Need to check with RDM
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.NextDep, "GetNextDeparturesWithDetails", crs)
		urlBuilder
			.addQueryParameter("crs", crs)
			.addQueryParameter("filterList", filterList.joinToString(","))
			.addQueryParameter("timeOffset", timeOffset.toString())
			.addQueryParameter("timeWindow", timeWindow.toString())

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyNextDep)
			.build()

		val response = client.newCall(request).execute()
		val responseBody = response.body?.string()
		response.close()

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)

		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<DeparturesBoardWithDetails>(jsonElement)
			return SuccessResponseDeparturesBoard(jsonFormatted)
		}
	}

	fun getServiceDetails(
		serviceID: String,
	): ApiResponse {
		val client = OkHttpClient()

		val urlBuilder = getHttpUrlBuilder(APIType.ServiceDetails, "GetServiceDetails", serviceID)
		urlBuilder
			.addQueryParameter("serviceID", serviceID)

		val url = urlBuilder.build()

		val request = Request.Builder()
			.url(url)
			.get()
			.addHeader("x-apikey", apiKeyServiceDetails)
			.build()

		val response = client.newCall(request).execute()
		val responseBody = response.body?.string()
		response.close()

		if (responseBody == "null") {
			return ErrorResponse(Fault(FaultData("Service not found", FaultDetail("404"))))
		}

		val json = Json { ignoreUnknownKeys = true }
		val jsonElement = json.parseToJsonElement(responseBody!!)


		if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey("Message"))) {
			val jsonFormatted = json.decodeFromJsonElement<Fault>(jsonElement)
			return ErrorResponse(jsonFormatted)
		} else {
			val jsonFormatted = json.decodeFromJsonElement<ServiceDetails>(jsonElement)
			return SuccessResponseServiceDetails(jsonFormatted)
		}
	}
}