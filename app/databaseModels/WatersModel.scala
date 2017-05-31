package databaseModels

import javax.inject._
import play.api.db._
import anorm._
import anorm.SqlParser._

@Singleton
class WatersModel(db: Database) {
	val waterIdParser = str("id")
	val waterIdMapper = waterIdParser.map { 
		case id => Map("id" -> id)
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
}