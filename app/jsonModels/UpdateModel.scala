package jsonModels

import play.api.libs.json.Json

case class updateTimeJson(code: String, unixTime: Int)
object updateTimeJson {
    implicit def jsonWrites = Json.writes[updateTimeJson]
    implicit def jsonReads = Json.reads[updateTimeJson]
}

case class updateStatusJson(code: String, statusCode: Int)
object updateStatusJson {
    implicit def jsonWrites = Json.writes[updateStatusJson]
    implicit def jsonReads = Json.reads[updateStatusJson]
}

case class updateNameJson(code: String, name: String)
object updateNameJson{
    implicit def jsonWrites = Json.writes[updateNameJson]
    implicit def jsonReads = Json.reads[updateNameJson]
}

case class updateCategoryJson(code: String, categoryCode: Int)
object updateCategoryJson{
    implicit def jsonWrites = Json.writes[updateCategoryJson]
    implicit def jsonReads = Json.reads[updateCategoryJson]
}

case class updateTimeReturnJson(status: Int, message: String, unixTime: Int)
object updateTimeReturnJson {
    implicit def jsonWrites = Json.writes[updateTimeReturnJson]
    implicit def jsonReads = Json.reads[updateTimeReturnJson]
}

case class updateStatusReturnJson(status: Int, message: String, statusCode: Int)
object updateStatusReturnJson {
    implicit def jsonWrites = Json.writes[updateStatusReturnJson]
    implicit def jsonReads = Json.reads[updateStatusReturnJson]
}

case class updateNameReturnJson(status: Int, message: String, name: String)
object updateNameReturnJson {
    implicit def jsonWrites = Json.writes[updateNameReturnJson]
    implicit def jsonReads = Json.reads[updateNameReturnJson]
}

case class updateCategoryReturnJson(status: Int, message: String, categoryCode: Int)
object updateCategoryReturnJson {
    implicit def jsonWrites = Json.writes[updateCategoryReturnJson]
    implicit def jsonReads = Json.reads[updateCategoryReturnJson]
}