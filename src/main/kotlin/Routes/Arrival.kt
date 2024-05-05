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

				if (crs.length != 3) {
					return@get call.respondText("CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
				}

				val data = ldbws.getArrivalBoard(10, crs)

				if (data is ErrorResponse) {
					call.respond(HttpStatusCode.InternalServerError, data)
				} else {
					call.respond(data)
				}
			}
		}
	}
}