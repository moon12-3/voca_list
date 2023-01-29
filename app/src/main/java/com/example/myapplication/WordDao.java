package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Query("SELECT * FROM word WHERE id In (:wordIds)")
    List<Word> loadAllByIds(int[] wordIds);

    @Insert
    void insertAll(Word... words);

    @Delete
    void delete(Word word);

}
