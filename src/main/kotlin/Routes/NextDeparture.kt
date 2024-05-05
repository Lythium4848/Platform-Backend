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

				if (crs.length != 3) {
					return@get call.respondText("CRS must be 3 characters long.", status = HttpStatusCode.BadRequest)
				}

				val data = ldbws.getNextDepartures(crs, List(1) { "BAN" }, 0, 120)
				call.respond(data)
			}
		}
	}
}