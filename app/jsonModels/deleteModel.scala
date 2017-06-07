package jsonModels

import play.api.libs.json.Json

case class deleteJson(code: String)
object deleteJson {
  implicit def jsonWrites = Json.writes[deleteJson]
  implicit def jsonReads = Json.reads[deleteJson]
}


case class deleteReturnJson(status:Int, message:String)
object deleteReturnJson {
  implicit def jsonWrites = Json.writes[deleteReturnJson]
  implicit def jsonReads = Json.reads[deleteReturnJson]
}
