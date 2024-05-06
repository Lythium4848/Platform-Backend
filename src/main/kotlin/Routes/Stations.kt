package Routes

import Stations
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.stations() {
	route("/stations") {
		get {
			val data = Stations.getStations()
			call.respond(data)
		}

		get("/{crs}") {
			val crs = call.parameters["crs"] ?: return@get call.respondText(
				"CRS not supplied. Please specify a CRS",
				status = HttpStatusCode.BadRequest
			)

			val data = Stations.getStation(crs)

			data ?: return@get call.respondText(
				"Station not found",
				status = HttpStatusCode.NotFound
			)

			call.respond(data)
		}
	}
}