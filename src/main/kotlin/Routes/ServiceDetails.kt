package Routes

import dev.lythium.App.ldbws
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serviceDetails() {
	route("/servicedetails") {
		route("/{serviceID}") {
			get {
				val serviceID = call.parameters["serviceID"] ?: return@get call.respondText(
					"serviceID not supplied. Please specify a serviceID",
					status = HttpStatusCode.BadRequest
				)

				val data = ldbws.getServiceDetails(serviceID)
				call.respond(data)
			}
		}
	}
}