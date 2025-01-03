package com.project.rydrotin.local;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.lang.Override;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
class HabitsDatabase_AutoMigration_3_4_Impl extends Migration {
  public HabitsDatabase_AutoMigration_3_4_Impl() {
    super(3, 4);
  }

  @Override
  public void migrate(@NonNull SupportSQLiteDatabase database) {
    database.execSQL("ALTER TABLE `history_table` ADD COLUMN `message` TEXT NOT NULL DEFAULT ''");
    database.execSQL("ALTER TABLE `history_table` ADD COLUMN `username` TEXT NOT NULL DEFAULT 'user'");
    database.execSQL("ALTER TABLE `mood_table` ADD COLUMN `username` TEXT NOT NULL DEFAULT 'user'");
    database.execSQL("ALTER TABLE `habits_table` ADD COLUMN `username` TEXT NOT NULL DEFAULT 'user'");
  }
}
