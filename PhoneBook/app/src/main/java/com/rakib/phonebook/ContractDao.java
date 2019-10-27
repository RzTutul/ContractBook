package com.rakib.phonebook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContractDao {

    @Insert
    long AddNewContract(ContractPojo contractPojo);
    @Update
    int UpadteContract(ContractPojo contractPojo);
    @Delete
    int DeleteContract(ContractPojo contractPojo);


    @Query("select * from Contract_List order by name asc")
    List<ContractPojo> getAllContract();

    @Query("select * from Contract_List where id like:cid")
    ContractPojo getTodoID(int cid);


}
