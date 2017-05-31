package jsonModels

import play.api.libs.json.Json

case class addJson(code: String)
object addJson {
	implicit def jsonWrites = Json.writes[addJson]
	implicit def jsonReads = Json.reads[addJson]
}


case class addReturnJson(status:Int, message:String)
object addReturnJson {
	implicit def jsonWrites = Json.writes[addReturnJson]
	implicit def jsonReads = Json.reads[addReturnJson]
}
