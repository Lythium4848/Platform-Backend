package Routes

import Stations
import dev.lythium.App.ldbws
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.departure() {
	route("/departure") {
		route("{crs}") {
			get {
				val crs = call.parameters["crs"] ?: return@get call.respondText(
					"CRS not supplied. Please specify a CRS",
					status = HttpStatusCode.BadRequest
				)

				val filterCRS = call.parameters["filterCRS"] ?: ""
				val filterType = call.parameters["filterType"] ?: "to"
				val timeOffset = call.parameters["timeOffset"]?.toIntOrNull() ?: 0
				val timeWindow = call.parameters["timeWindow"]?.toIntOrNull() ?: 120


				if (crs.length != 3) {
					return@get call.respondText("CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
				}

				if (Stations.getStation(crs = crs) == null) {
					return@get call.respondText("Invalid CRS code.", status = HttpStatusCode.BadRequest)
				}

				val data = ldbws.getDepartureBoard(10, crs, filterCRS, filterType, timeOffset, timeWindow)
				call.respond(data)
			}

			route("/to") {
				get("{crs2}") {
					val fromCRS = call.parameters["crs"] ?: return@get call.respondText(
						"From CRS not supplied. Please specify a From CRS",
						status = HttpStatusCode.BadRequest
					)

					val toCRS = call.parameters["crs2"] ?: return@get call.respondText(
						"To CRS not supplied. Please specify a To CRS",
						status = HttpStatusCode.BadRequest
					)

					val timeOffset = call.parameters["timeOffset"]?.toIntOrNull() ?: 0
					val timeWindow = call.parameters["timeWindow"]?.toIntOrNull() ?: 120

					fromCRS.length != 3 && return@get call.respondText("From CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
					toCRS.length != 3 && return@get call.respondText("To CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)

					Stations.getStation(crs = fromCRS) == null && return@get call.respondText("Invalid From CRS code.", status = HttpStatusCode.BadRequest)
					Stations.getStation(crs = toCRS) == null && return@get call.respondText("Invalid To CRS code.", status = HttpStatusCode.BadRequest)

					val data = ldbws.getDepartureBoard(10, fromCRS, toCRS, "to", timeOffset, timeWindow)
					call.respond(data)
				}
			}
		}
	}
}