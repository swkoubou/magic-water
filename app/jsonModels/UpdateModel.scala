package jsonModels

import play.api.libs.json.Json

case class updateTimeJson(code: String, unixTime: Int)
object updateTimeJson {
    implicit def jsonWrites = Json.writes[updateTimeJson]
    implicit def jsonReads = Json.reads[updateTimeJson]
}

case class updateStatusJson(code: String)
object updateStatusJson {
    implicit def jsonWrites = Json.writes[updateStatusJson]
    implicit def jsonReads = Json.reads[updateStatusJson]
}

case class updateReturnJson(status: Int, message: String, unixTime: Int)
object updateReturnJson {
    implicit def jsonWrites = Json.writes[updateReturnJson]
    implicit def jsonReads = Json.reads[updateReturnJson]
}
