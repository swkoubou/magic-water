package controllers

import javax.inject.{Inject, Singleton}

import databaseModels.WatersModel
import jsonModels._
import play.api.db.Database
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller, Result}

/**
  * Created by mox on 2017/05/28.
  */
@Singleton
class UpdateController @Inject()(db: Database) extends Controller {
    val water: WatersModel = new WatersModel(db)

    //updateTime.jsonの処理
    def updateTime() = Action { implicit request =>
        def handle: Result = {
            //Jsonかどうか
            request.body.asJson match {
                case Some(postJsonRaw) => {
                    var postJson: updateTimeJson = null
                    //入力データのパース・エラーチェック
                    val isError: Boolean = try {
                        postJson = Json.fromJson[updateTimeJson](postJsonRaw).get
                        false
                    } catch {
                        case _ => true
                    }
                    //エラーフラグ立っていればエラがーとして返す
                    if (isError) return BadRequest(Json.toJson(updateTimeReturnJson(400, "不正なリクエストです。", -1)))

                    if (postJson.code != "") {
                        //codeが正しくPOSTされていた場合
                        if (water.isExistId(postJson.code)) {
                            water.updateTime(postJson.code, postJson.unixTime)
                            Ok(Json.toJson(updateTimeReturnJson(400, "", postJson.unixTime)))
                        }else{
                            BadRequest(Json.toJson(updateTimeReturnJson(400, "登録されていないコードです。", -1)))
                        }
                    } else {
                        //codeが正しくPOSTされていなかった場合
                        BadRequest(Json.toJson(updateTimeReturnJson(400, "不正なリクエストです。", -1)))
                    }
                }
                case None => BadRequest(Json.toJson(updateTimeReturnJson(400, "Unknown Request", -1)))
            }
        }

        handle
    }



    //updateStatus.jsonの処理
    def updateStatus() = Action { implicit request =>
        def handle: Result = {
            //Jsonかどうか
            request.body.asJson match {
                case Some(postJsonRaw) => {
                    var postJson: updateStatusJson = null
                    //入力データのパース・エラーチェック
                    val isError: Boolean = try {
                        postJson = Json.fromJson[updateStatusJson](postJsonRaw).get
                        false
                    } catch {
                        case _ => true
                    }
                    //エラーフラグ立っていればエラがーとして返す
                    if (isError) return BadRequest(Json.toJson(updateStatusReturnJson(400, "不正なリクエストです。", -1)))

                    if (postJson.code != "") {
                        //codeが正しくPOSTされていた場合
                        if (water.isExistId(postJson.code)) {
                            water.updateStatus(postJson.code, postJson.statusCode)
                            Ok(Json.toJson(updateStatusReturnJson(400, "", postJson.statusCode)))
                        }else{
                            BadRequest(Json.toJson(updateStatusReturnJson(400, "登録されていないコードです。", -1)))
                        }
                    } else {
                        //codeが正しくPOSTされていなかった場合
                        BadRequest(Json.toJson(updateStatusReturnJson(400, "不正なリクエストです。", -1)))
                    }
                }
                case None => BadRequest(Json.toJson(updateStatusReturnJson(400, "Unknown Request", -1)))
            }
        }

        handle
    }
}
