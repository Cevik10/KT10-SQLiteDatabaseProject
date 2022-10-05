package com.hakancevik.sqlitedatabaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {

            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INT) ")

            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Frank Sinatra',67)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Engel H.',54)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)")


            // Update
            myDatabase.execSQL("UPDATE musicians SET age = 57 WHERE name = 'James'")
            myDatabase.execSQL("UPDATE musicians SET name = 'Engel Humperdinck ' WHERE id = 2")

            // Delete
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Frank Sinatra'")


            // Filtering
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name = 'Frank Sinatra'", null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = 2", null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'", null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'J%'", null)

            val idIx = cursor.getColumnIndex("id")
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            while (cursor.moveToNext()) {
                Log.d("system.out", "Id: ${cursor.getInt(idIx)}")
                Log.d("system.out", "Name: ${cursor.getString(nameIx)}")
                Log.d("system.out", "Age: ${cursor.getInt(ageIx)}")
            }

            cursor.close()


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}