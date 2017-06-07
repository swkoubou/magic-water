package jsonModels

import play.api.libs.json.Json

/**
  * Created by mox on 2017/06/02.
  */
case class WaterData(id: String, name: String, status: Int, time: Int, category: Int)
object WaterData {
    implicit def jsonWrites = Json.writes[WaterData]
    implicit def jsonReads = Json.reads[WaterData]
}