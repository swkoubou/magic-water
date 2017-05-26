package jsonModels

import play.api.libs.json.Json

case class addJson(code: String)
object addJson {
	implicit def jsonWrites = Json.writes[addJson]
	implicit def jsonReads = Json.reads[addJson]
}