package controllers

import javax.inject.Inject

import play.api.mvc._
import databaseModels.WatersModel
import jsonModels.WaterData
import play.api.db.Database
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by mox on 2017/06/02.
  */
class GetDataController @Inject()(db: Database) extends Controller {
    val water: WatersModel = new WatersModel(db)

    //getAll.jsonの処理
    def getAll() = Action { implicit request =>
        def handle: Result = {
            val waterData = water.getAll()
            Ok(Json.toJson(waterData))
        }

        handle
    }
}
