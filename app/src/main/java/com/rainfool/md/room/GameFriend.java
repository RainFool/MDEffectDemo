package com.rainfool.md.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gameFriend")
public class GameFriend {

    @PrimaryKey
    @NonNull
    public String mkey;//uuid+scene对应的key
    public String uuid;
    public String gameName;
    public String sub_uuid;
    public String type_key;//大区id 对应的key
    public String scene;//用户信息拉取的需要场景的key
    public String groupName;
    public String cache_version;
    public int is_black_user;
    public int is_game_friend;
    public int is_fans;
    public int is_focus;
    public boolean onlineState;

}
