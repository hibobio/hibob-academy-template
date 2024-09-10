package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import javassist.CtMethod.ConstParameter.integer
import java.io.Serial

class PetsTable(tableName : String = "pets") : JooqTable(tableName) {
    val id = createIntField("id")
    val name = createVarcharField("name")
    val type = createVarcharField("type")


//    id SERIAL primary key ,
//    name varchar(255),
//    type varchar(255),
//    company_id integer,
//    date_of_arrival date default current_date

    companion object{
        val instance = PetsTable()
    }
}

class OwnersTable(tableName : String = "owners") : JooqTable(tableName) {
    companion object{
        val instance = OwnersTable()
    }
}