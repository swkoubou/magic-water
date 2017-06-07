package controllers

import javax.inject.Inject

import play.api.mvc._
import databaseModels.WatersModel
import jsonModels._
import play.api.db.Database
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by uryoya on 17/06/05.
  */
class DeleteController @Inject()(db: Database) extends Controller {
  val water: WatersModel = new WatersModel(db)

  // deleteの処理
  def delete() = Action { implicit request =>
    def handle: Result = {
      // Jsonかどうか
      request.body.asJson match {
        case Some(postJsonRaw) => {
          var postJson: deleteJson = null
          //入力データのパースエラーチェック
          val isError: Boolean = try {
            postJson = Json.fromJson[deleteJson](postJsonRaw).get
            false
          } catch {
            case _ => true
          }
          //エラーフラグが立っていればエラーとして返す
          if (isError) return BadRequest(Json.toJson(deleteReturnJson(400, "不正なリクエストです。")))

          if (postJson.code != "") {
            //codeが正しくPOSTされていた場合
            if (water.isExistId(postJson.code)) {
              water.delete(postJson.code)
              Ok(Json.toJson(deleteReturnJson(100, "success")))
            } else {
              BadRequest(Json.toJson(deleteReturnJson(400, "Unknown code")))
            }
          } else {
            //codeが正しくPOSTされていなかった場合
            BadRequest(Json.toJson(deleteReturnJson(400, "不正なリクエストです。")))
          }
        }
        case None => BadRequest(Json.toJson(deleteReturnJson(400, "Unknown Request")))
      }
    }

    handle
  }
}
