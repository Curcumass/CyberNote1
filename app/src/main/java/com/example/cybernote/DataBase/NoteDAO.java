package com.example.cybernote.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cybernote.Models.Notes;

import java.util.List;



@Dao
public interface NoteDAO {
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    @Query("select * from note order by id desc")
    List<Notes> getAll();

    @Query("select * from note where pinned='true'")
    List<Notes> getAllPinned();

    @Query("update note set title =:TITLE, notes = :NOTES where ID=:id")
    void update (int id, String TITLE, String NOTES);

    @Delete
    void delete(Notes notes);

    @Query("update note set pinned = :pin where ID =:id")
    void pin(int id, boolean pin);
}

