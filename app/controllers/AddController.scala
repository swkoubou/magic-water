package controllers

import javax.inject._

import play.api.mvc._
import jsonModels._
import databaseModels._
import models.ResultCode
import play.api.libs.json._
import play.api.db._

@Singleton
class AddController @Inject()(db: Database) extends Controller {
    val water: WatersModel = new WatersModel(db)
    val resultRequest: ResultCode = new ResultCode()

    //addの処理
    def add() = Action { implicit request =>
        def handle: Result = {
            //Jsonかどうか
            request.body.asJson match {
                case Some(postJsonRaw) => {
                    var postJson: addJson = null
                    //入力データのパース・エラーチェック
                    val isError: Boolean = try {
                        postJson = Json.fromJson[addJson](postJsonRaw).get
                        false
                    } catch {
                        case _ => true
                    }
                    //エラーフラグ立っていればエラがーとして返す
                    if (isError) return BadRequest(Json.toJson(addReturnJson(400, "不正なリクエストです。")))

                    if (postJson.code != "") {
                        //codeが正しくPOSTされていた場合
                        if (!water.isExistId(postJson.code)) {
                            water.addId(postJson.code, postJson.name, postJson.category)
                            Ok(Json.toJson(addReturnJson(100, "")))
                        } else BadRequest(Json.toJson(addReturnJson(401, "すでに登録されているコードです。")))
                    } else {
                        //codeが正しくPOSTされていなかった場合
                        BadRequest(Json.toJson(addReturnJson(400, "不正なリクエストです。")))
                    }
                }
                case None => BadRequest(Json.toJson(addReturnJson(400, "Unknown Request")))
            }
        }

        handle
    }
}