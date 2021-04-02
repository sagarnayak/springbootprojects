package com.sagar.unittesting

class Business {

    fun calculateSum(listOfNumbers: ArrayList<Int>): Int {
        var result = 0
        listOfNumbers.forEach {
            result += it
        }
        return result
    }

    lateinit var dataService: DataService

    fun calculateSumUsingWayTwo(): Int {
        var result = 0
        dataService.getNumbersToAdd().forEach {
            result += it
        }
        return result
    }
}