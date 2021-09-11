package <%= serverPackageName %>.data.tables

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.text

val Database.example get() = this.sequenceOf(Examples)

interface Example : Entity<Example> {
    companion object : Entity.Factory<Example>()

    var id: String
}

object Examples : Table<Example>("example") {
    val id = text("id").primaryKey().bindTo { it.id }
}