package com.rainfool.md.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {GameFriend.class}, version = 15, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private String DB_NAME = "database.db";
    private static volatile AppDataBase appDataBase;

    /***
     *  根据登录的uuid的不同创建不同的数据库
     * @param context
     * @param uuid
     * @return
     */
    public static AppDataBase getInstance(Context context, String uuid) {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                if (appDataBase == null) {
                    appDataBase = Room.databaseBuilder(context, AppDataBase.class, String
                            .format("database_%s.db", uuid))
                            .addMigrations(migration)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDataBase;
    }

    /***
     * 清除app 数据库
     */
    public static void clearAppDataBase() {
        if (appDataBase != null && appDataBase.isOpen()) {
            appDataBase.close();
        }
        appDataBase = null;
    }

    public abstract GameFriendDao getGameFriendDao();

    private static Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //database 可以执行不同的sql语句以完成不同版本的数据库表的修改问题
            //database.execSQL();

        }
    };
}
