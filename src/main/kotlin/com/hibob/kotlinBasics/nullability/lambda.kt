package com.hibob.kotlinBasics.nullability

import java.time.LocalDate

/*
1. Make the Runner.init function more readable using Lambda with Receiver function usage
2. Initiate the variable movie in line 11 with the function createGoodMovie()
3. Implement pretty print using buildString function
4. Make SpidermanMovieProduceActions nullable (if not so yet) and make the relevant adjustments
*/

fun loMain() {
    val movie = createGoodMovie()
    val runner = Runner(movie)
    val success = runner.init()

    printSuccessMessage(success)
    println("Pretty print: ${movie.prettyPrint()}")
//    println("Json: ${movie.toJson()}")
}

fun printSuccessMessage(success: Boolean) {
    if (success) {
        println("The movie was successful")
    } else {
        println("The movie failed")
    }
}

interface SpidermanMovieProduceActions {
    fun signTobeyMaguire()
    fun signAndrew()
    fun signTom()
    fun getVillains()
    fun isThereLockdown(): Boolean
    fun publish(): Boolean

    val title: String
    val airDate: LocalDate
    val imdbRank: Double
}

class SpidermanNoWayHome() : SpidermanMovieProduceActions {

    override val title: String = "Spiderman - No Way Home"
    override val airDate: LocalDate = LocalDate.of(2021, 12, 16)
    override val imdbRank: Double = 9.6

    fun prettyPrint(): String {
        return buildString {
            appendLine("Title: $title")
            appendLine("Air Date: $airDate")
            appendLine("IMDB Rank: $imdbRank")
        }
    }

    override fun signTobeyMaguire() {
        println("Tobey maguire signed !")
    }

    override fun signAndrew() {
        println("Andrew signed !")
    }

    override fun signTom() {
        println("Tom signed !")
    }

    override fun getVillains() {
        println("Getting Villains")
    }

    override fun isThereLockdown(): Boolean = false

    override fun publish(): Boolean = true

//    fun toJson(): JsonNode {
//        /* implement the following json structure:
//                {
//                 "title" : title,
//                 "airDate": 2021-12-16,
//                 "imdbRank": 9.6
//                }
//
//        Note: In kotlin we receive the default object serializer "for free"
//        and we will not have to implement it from here
//        But, knowing how to write jsons in kotlin is still very important!
//        the common use cases: S2S clients implementation, tests and more */
//
//        return JsonBuilderObject().json {
//            "notImplemented" - true
//        }.asTree()
//    }

}

fun buildString(actions: StringBuilder.() -> Unit): String {
    val builder = StringBuilder()
    builder.actions()
    return builder.toString()
}

class Runner(private val movieProducer: SpidermanMovieProduceActions?) {
    fun init(): Boolean {
        movieProducer?.run{
            if (!isThereLockdown()) {
                signTobeyMaguire()
                signAndrew()
                signTom()
                getVillains()
                return publish()
            }
        }
        return false
    }
}

fun createGoodMovie(): SpidermanNoWayHome {
    return SpidermanNoWayHome()
}

