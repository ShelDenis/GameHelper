package com.example.gamehelper
import kotlin.random.Random

fun get_uniq_num(u: Int, used_nums: List<Int>): Int {
    while (true) {
        var k = Random.nextInt(0, u)
        if (!used_nums.contains(k))
            return k
    }
}