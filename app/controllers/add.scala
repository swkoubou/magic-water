package controllers

import javax.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import play.api.i18n.Messages.Implicits._
import play.api.Play._
import jsonModels._
import play.api.libs.json._

class AddController() extends Controller {

	def add() = Action { implicit request =>
		request.body.asJson match {
			case Some(postJsonRaw) =>{
				val postJson: addJson = Json.fromJson[addJson](postJsonRaw).get
				Ok(postJson.code)
			}
			case None => BadRequest("bad req") 
		}

	}
}