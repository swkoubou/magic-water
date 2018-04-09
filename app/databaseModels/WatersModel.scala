package databaseModels

import javax.inject._

import play.api.db._
import anorm._
import anorm.SqlParser._
import jsonModels.WaterData

case class WaterDbData(id:String, name:String, status:Option[Int], time:Option[Int], category:Option[Int])
@Singleton
class WatersModel(db: Database) {
    //Id用パーサー
    val waterIdParser = str("id")
    val waterIdMapper = waterIdParser.map {
        case id => Map("id" -> id)
    }

    //データ取得用パーサー
    val waterDataParser = str("id") ~ str("name") ~ get[Option[Int]]("status") ~ get[Option[Int]]("time") ~ get[Option[Int]]("category")
    val waterDataMapper = waterDataParser.map {
        case id~name~status~time~category => WaterDbData(id, name, status, time, category)
    }

    def isExistId(id: String): Boolean = {
        db.withConnection { implicit connect =>
            val idData = SQL("SELECT `id` FROM `waters` WHERE `id` = {Id};")
              .on("Id" -> id).as(waterIdMapper.*)

            return idData.nonEmpty
        }
    }

    def addId(id: String, name: String, category: Option[Int]) = {
        var categoryId = -1
        category match {
            case Some(categoryIdTemp) => categoryId = categoryIdTemp
            case None => categoryId = -1
        }

        db.withTransaction{implicit connect =>
            SQL("INSERT INTO `waters`(`id`, `name`, `status`, `category`) values({Id}, {Name}, 0, {Category});")
              .on("Id" -> id, "Name" -> name, "Category" -> categoryId).executeInsert()
        }
    }

    def updateTime(id: String, unixTime: Int) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `time` = {Time} WHERE `id` = {Id};")
              .on("Time" -> unixTime, "Id" -> id).executeUpdate()
        }
    }

    def updateStatus(id: String, statusCode: Int) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `status` = {Status} WHERE `id` = {Id};")
              .on("Status" -> statusCode, "Id" -> id).executeUpdate()
        }
    }

    def updateName(id: String, name: String) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `name` = {Name} WHERE `id` = {Id};")
              .on("Name" -> name, "Id" -> id).executeUpdate()
        }
    }

    def updateCategory(id: String, categoryCode: Int) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `category` = {Category} WHERE `id` = {Id};")
              .on("Category" -> categoryCode, "Id" -> id).executeUpdate()
        }
    }

    def delete(id: String) = {
        db.withTransaction{implicit connect =>
            SQL("DELETE FROM `waters` WHERE `id` = {id};")
              .on("id" -> id).executeUpdate()
        }
    }

    def getAll(): List[WaterData] = {
        db.withConnection{implicit connect =>
            val data = SQL("SELECT * FROM `waters` WHERE 1;")
              .as(waterDataMapper.*)

            var returnData:List[WaterData] = List[WaterData]()
            for(value <- data){
                returnData :+= new WaterData(
                    value.id,
                    value.name,
                    value.status.getOrElse(-1),
                    value.time.getOrElse(-1),
                    value.category.getOrElse(-1)
                )
            }

            return returnData
        }
    }

    def getFromId(id: String): WaterData = {
        db.withConnection{implicit connect =>
            val data = SQL("SELECT * FROM `waters` WHERE `id` = {Id};")
              .on("Id" -> id).as(waterDataMapper.*)

            var returnData:List[WaterData] = List[WaterData]()
            for(value <- data){
                returnData :+= new WaterData(
                    value.id,
                    value.name,
                    value.status.getOrElse(-1),
                    value.time.getOrElse(-1),
                    value.category.getOrElse(-1)
                )
            }

            if(returnData.length < 1) return null
            else return returnData(0)
        }
    }
}