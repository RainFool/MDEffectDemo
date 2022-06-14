package com.rainfool.md.room

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_room_test.*
import java.util.*
import java.util.concurrent.Executors


class RoomTestActivity : Activity() {

    companion object {
        private const val TAG = "RoomTestActivity"
    }

    private lateinit var dao: GameFriendDao
    private var curKey = 0
    private val queue = LinkedList<GameFriend>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_test)
        dao = AppDataBase.getInstance(this, "877743207").gameFriendDao
        val service = Executors.newFixedThreadPool(8)
        Thread(Runnable {
            while (curKey < 1000) {
                Log.i(TAG, "run task insert $curKey")
                val gameFriend = GameFriend().apply {
                    mkey = curKey.toString()
                }
                dao.insert(gameFriend)
                queue.add(gameFriend)
                Thread.sleep(2)
                curKey++
            }
            while (true) {
                service.submit(Task())
                Thread.sleep(1)
            }
        }).start()
        room_test_btn_delete.setOnClickListener {
            Log.i(TAG, "btn click delete")
//            val gameFriend = queue.removeAt(0)
//            dao.delete(gameFriend)
            dao.deleteAll()
        }
    }

    inner class Task : Runnable {
        override fun run() {
            val list = dao.allGameFriendInfo
            Log.i(TAG, "run task list size:${list.size}")
        }
    }
}