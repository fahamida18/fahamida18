package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // DB INFO
    private static final String DB_NAME = "ExpenseManager.db";
    private static final int DB_VERSION = 2; // 🔥 upgraded version

    // Constructor
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // =========================
    // CREATE TABLES
    // =========================
    @Override
    public void onCreate(SQLiteDatabase db) {

        // USERS
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT, " +
                        "email TEXT, " +
                        "password TEXT)"
        );

        // GROUPS
        db.execSQL(
                "CREATE TABLE groups (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_name TEXT)"
        );

        // MEMBERS
        db.execSQL(
                "CREATE TABLE members (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_id INTEGER, " +
                        "member_name TEXT)"
        );

        // MESSAGES
        db.execSQL(
                "CREATE TABLE messages (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_id INTEGER, " +
                        "message TEXT)"
        );

        // 💰 PAYMENTS (NEW)
        db.execSQL(
                "CREATE TABLE payments (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_id INTEGER, " +
                        "member_name TEXT, " +
                        "amount REAL)"
        );

        // ⚖ PENALTY RECORD (NEW OPTIONAL)
        db.execSQL(
                "CREATE TABLE penalty (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_id INTEGER, " +
                        "member_name TEXT, " +
                        "penalty_amount REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS groups");
        db.execSQL("DROP TABLE IF EXISTS members");
        db.execSQL("DROP TABLE IF EXISTS messages");
        db.execSQL("DROP TABLE IF EXISTS payments");
        db.execSQL("DROP TABLE IF EXISTS penalty");

        onCreate(db);
    }

    // =========================
    // LOGIN / SIGNUP
    // =========================
    public void register(String username, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        db.insert("users", null, cv);
        db.close();
    }

    public int login(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password}
        );

        int result = c.moveToFirst() ? 1 : 0;

        c.close();
        db.close();

        return result;
    }

    // =========================
    // GROUP SYSTEM
    // =========================
    public long createGroup(String groupName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("group_name", groupName);

        return db.insert("groups", null, cv);
    }

    public void addMember(long groupId, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("group_id", groupId);
        cv.put("member_name", name);

        db.insert("members", null, cv);
    }

    public void addMessage(long groupId, String message) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("group_id", groupId);
        cv.put("message", message);

        db.insert("messages", null, cv);
    }

    public Cursor getMembers(long groupId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM members WHERE group_id=?",
                new String[]{String.valueOf(groupId)}
        );
    }

    public Cursor getMessages(long groupId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM messages WHERE group_id=?",
                new String[]{String.valueOf(groupId)}
        );
    }

    // =========================
    // 💰 PAYMENT SYSTEM (NEW)
    // =========================
    public void addPayment(long groupId, String name, double amount) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("group_id", groupId);
        cv.put("member_name", name);
        cv.put("amount", amount);

        db.insert("payments", null, cv);
        db.close();
    }

    public Cursor getPayments(long groupId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM payments WHERE group_id=?",
                new String[]{String.valueOf(groupId)}
        );
    }

    // =========================
    // ⚖ PENALTY SYSTEM (OPTIONAL)
    // =========================
    public void addPenalty(long groupId, String name, double penalty) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("group_id", groupId);
        cv.put("member_name", name);
        cv.put("penalty_amount", penalty);

        db.insert("penalty", null, cv);
        db.close();
    }

    public Cursor getPenalty(long groupId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM penalty WHERE group_id=?",
                new String[]{String.valueOf(groupId)}
        );
    }
}