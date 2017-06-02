package databaseModels

import javax.inject._

import play.api.db._
import anorm._
import anorm.SqlParser._
import jsonModels.WaterData

case class WaterDbData(id:String, status:Option[Int], time:Option[Int])
@Singleton
class WatersModel(db: Database) {
    //Id用パーサー
	val waterIdParser = str("id")
	val waterIdMapper = waterIdParser.map { 
		case id => Map("id" -> id)
	}

    //データ取得用パーサー
    val waterDataParser = str("id") ~ get[Option[Int]]("status") ~ get[Option[Int]]("time")
    val waterDataMapper = waterDataParser.map {
        case id~status~time => WaterDbData(id, status, time)
    }

	def isExistId(id: String): Boolean = {
		db.withConnection { implicit connect =>
			val idData = SQL("SELECT `id` FROM `waters` WHERE `id` = {Id};")
			    .on("Id" -> id).as(waterIdMapper.*)
			
			return idData.nonEmpty
		}
	}

    def addId(id: String) = {
        db.withTransaction{implicit connect =>
            SQL("INSERT INTO `waters`(`id`, `status`) values({Id}, 0);")
				.on("Id" -> id).executeInsert()
        }
    }

	def updateTime(id: String, unixTime: Int) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `time` = {Time} WHERE `id` = {Id};")
                .on("Time" -> unixTime, "Id" -> id).executeInsert()
        }
    }

    def updateStatus(id: String, statusCode: Int) = {
        db.withTransaction{implicit connect =>
            SQL("UPDATE `waters` SET `status` = {Status} WHERE `id` = {Id};")
                .on("Status" -> statusCode, "Id" -> id).executeInsert()
        }
    }

    def getAll(): List[WaterData] = {
        db.withConnection{implicit connect =>
            val data = SQL("SELECT * FROM `waters` WHERE 1;")
                .as(waterDataMapper.*)

            var returnData:List[WaterData] = List[WaterData]()
            for(value <- data){
                returnData :+= new WaterData(value.id, value.status.getOrElse(-1), value.time.getOrElse(-1))
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
                returnData :+= new WaterData(value.id, value.status.getOrElse(-1), value.time.getOrElse(-1))
            }

            if(returnData.length < 1) return null
            else return returnData(0)
        }
    }
}