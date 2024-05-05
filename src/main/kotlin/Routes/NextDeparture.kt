package Routes

import dev.lythium.App.ldbws
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.nextDeparture() {
	route("/nextdeparture") {
		route("{crs}") {
			get {
				val crs = call.parameters["crs"] ?: return@get call.respondText(
					"CRS not supplied. Please specify a CRS",
					status = HttpStatusCode.BadRequest
				)

				val filterList = call.parameters["filterList"]?.split(",") ?: return@get call.respondText(
					"Filter list not supplied. Please specify a filter list",
					status = HttpStatusCode.BadRequest
				)

				val timeOffset = call.parameters["timeOffset"]?.toIntOrNull() ?: 0
				val timeWindow = call.parameters["timeWindow"]?.toIntOrNull() ?: 120

				if (crs.length != 3) {
					return@get call.respondText("CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
				}

				if (CRSCodes.get(crs).isValid == false) {
					return@get call.respondText("Invalid CRS code.", status = HttpStatusCode.BadRequest)
				}

				val data = ldbws.getNextDepartures(crs, filterList, timeOffset, timeWindow)
				call.respond(data)
			}
		}
	}
}