package Routes

import ErrorResponse
import dev.lythium.App.ldbws
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.arrival() {
	route("/arrival") {
		route("{crs}") {
			get {
				val crs = call.parameters["crs"] ?: return@get call.respondText(
					"CRS not supplied. Please specify a CRS",
					status = HttpStatusCode.BadRequest
				)

				val numRows = call.parameters["numRows"]?.toIntOrNull() ?: return@get call.respondText(
					"numRows not supplied.",
					status = HttpStatusCode.BadRequest
				)

				val filterCRS = call.parameters["filterCRS"] ?: ""
				val filterType = call.parameters["filterType"] ?: ""
				val timeOffset = call.parameters["timeOffset"]?.toIntOrNull() ?: 0
				val timeWindow = call.parameters["timeWindow"]?.toIntOrNull() ?: 120

				if (crs.length != 3) {
					return@get call.respondText("CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
				}

				if (Stations.getStation(crs = crs) == null) {
					return@get call.respondText("Invalid CRS code.", status = HttpStatusCode.BadRequest)
				}

				val data = ldbws.getArrivalBoard(numRows, crs, filterCRS, filterType, timeOffset, timeWindow)

				if (data is ErrorResponse) {
					call.respond(HttpStatusCode.InternalServerError, data)
				} else {
					call.respond(data)
				}
			}
		}
	}
}