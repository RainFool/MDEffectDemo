package com.rainfool.md.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameFriendDao extends BaseDao<GameFriend> {

    @Query("Delete from gameFriend")
    void deleteAll();

    @Query("select* from gameFriend")
    List<GameFriend> getAllGameFriendInfo();

    @Query("select *from gameFriend where type_key=:scene")
    List<GameFriend> getGameFriendInfo(String scene);

    @Query("select *from gameFriend where mkey=:key")
    GameFriend getGameFriend(String key);
    @Query("delete from gameFriend where type_key=:scene")
    void deleteGameFriend(String scene);
}
