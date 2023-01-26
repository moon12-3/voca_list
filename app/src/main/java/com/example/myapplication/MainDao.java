package com.example.myapplication;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(WordModel model);

    @Delete
    void delete(WordModel model);

    @Delete
    void reset(List<WordModel> models);

    @Query("UPDATE table_name SET (text, mean) = (:sText, :sMean) WHERE ID = :sID")
    void update(int sID, String sText);

    @Query("SELECT * FROM table_name")
    default List<WordModel> getAll() {
        return null;
    }
}
