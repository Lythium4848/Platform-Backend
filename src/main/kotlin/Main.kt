package dev.lythium

import LDBWS
import Routes.*
import dev.lythium.App.ldbws
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds


object App {
	val dotenv = Dotenv.load();
	val ldbws = LDBWS(
		apiKeyArrDep = dotenv.get("API_KEY_ARR_DEP"),
		apiKeyArr = dotenv.get("API_KEY_ARR"),
		apiKeyDep = dotenv.get("API_KEY_DEP"),
		apiKeyFastestDep = dotenv.get("API_KEY_FASTEST_DEP"),
		apiKeyNextDep = dotenv.get("API_KEY_NEXT_DEP"),
		apiKeyServiceDetails = dotenv.get("API_KEY_SERVICE_DETAILS")
	)
}

fun main(args: Array<String>) {
	val port = App.dotenv["PORT"]?.toInt() ?: 8080
	println("Starting server on port $port")
	System.setProperty("ktor.deployment.port", port.toString())

	return io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
	install(ContentNegotiation) {
		json(Json {
			prettyPrint = true
		})
	}

	install(RateLimit) {
		global {
			rateLimiter(limit = 20, refillPeriod = 60.seconds)
		}
	}

	install(Resources)

	install(CORS) {
		anyHost()
		allowHeader(HttpHeaders.ContentType)
	}

	routing {
		get("*") {
			call.respondText("404 Not Found", status = HttpStatusCode.NotFound)
		}

		arrival()
		arrivalDeparture()
		departure()
		fastestDeparture()
		nextDeparture()
		serviceDetails()
	}
}