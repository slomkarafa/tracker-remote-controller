package org.kristi.trackerremote.steering

import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Test


class CircleToDriveTest {

    @Test
    fun hasProperResults() {
        val expected = arrayOf(
            arrayOf(0, 25, 0, 25),
            arrayOf(40, 56, 13, 56),
            arrayOf(80, 43, 35, 43),
            arrayOf(120, 41, 41, 20),
            arrayOf(160, 65, 65, 3),
            arrayOf(200, 26, -26, -1),
            arrayOf(240, 93, -93, -46),
            arrayOf(280, 8, -6, -8),
            arrayOf(320, 65, -15, -65)
        )
        expected.forEach {
            val result = circleToDrives(it[0], it[1])
            Log.d("WSS_ex_left", it[2].toString())
            Log.d("WSS_res_left", result.left.toString())
            Log.d("WSS_ex_right", it[3].toString())
            Log.d("WSS_res_right", result.right.toString())
            assertTrue(it[2] == result.left && it[3] == result.right)
        }

    }
}

