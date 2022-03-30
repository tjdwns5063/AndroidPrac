package com.example.recyclerprac.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract val myDatabaseDAO: MyDatabaseDAO

    companion object {
        @Volatile
// 변수를 휘발성으로 만듭니다. 모든 스레드가 동일한 값을 참조하게 합니다.
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            //synchronized 블럭은 이 구역에 오직 하나의 스레드만이 접근할 수 있음을 보장합니다. 여러 스레드가 동시에 이 구역에 접근해서 데이터 베이스가 여러개 생기는것을 막아줍니다.
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "sleep_history_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}