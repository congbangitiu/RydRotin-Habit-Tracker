package com.project.rydrotin.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HabitsDatabase_Impl extends HabitsDatabase {
  private volatile HabitsDAO _habitsDAO;

  private volatile HistoryDAO _historyDAO;

  private volatile MoodDAO _moodDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `habits_table` (`username` TEXT NOT NULL DEFAULT 'user', `name` TEXT NOT NULL, `countPerDay` INTEGER NOT NULL, `habitId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `history_table` (`username` TEXT NOT NULL DEFAULT 'user', `habitId` INTEGER NOT NULL, `habitName` TEXT NOT NULL, `countDone` INTEGER NOT NULL, `countPerDay` INTEGER NOT NULL, `date` TEXT NOT NULL, `message` TEXT NOT NULL DEFAULT '', `historyId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `mood_table` (`username` TEXT NOT NULL DEFAULT 'user', `value` INTEGER NOT NULL, `message` TEXT NOT NULL, `date` TEXT NOT NULL, `moodId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e2dc5529173e5abdf54cf202ff7e837f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `habits_table`");
        _db.execSQL("DROP TABLE IF EXISTS `history_table`");
        _db.execSQL("DROP TABLE IF EXISTS `mood_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsHabitsTable = new HashMap<String, TableInfo.Column>(4);
        _columnsHabitsTable.put("username", new TableInfo.Column("username", "TEXT", true, 0, "'user'", TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitsTable.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitsTable.put("countPerDay", new TableInfo.Column("countPerDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitsTable.put("habitId", new TableInfo.Column("habitId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHabitsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHabitsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHabitsTable = new TableInfo("habits_table", _columnsHabitsTable, _foreignKeysHabitsTable, _indicesHabitsTable);
        final TableInfo _existingHabitsTable = TableInfo.read(_db, "habits_table");
        if (! _infoHabitsTable.equals(_existingHabitsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "habits_table(com.project.rydrotin.local.entities.Habits).\n"
                  + " Expected:\n" + _infoHabitsTable + "\n"
                  + " Found:\n" + _existingHabitsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsHistoryTable = new HashMap<String, TableInfo.Column>(8);
        _columnsHistoryTable.put("username", new TableInfo.Column("username", "TEXT", true, 0, "'user'", TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("habitId", new TableInfo.Column("habitId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("habitName", new TableInfo.Column("habitName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("countDone", new TableInfo.Column("countDone", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("countPerDay", new TableInfo.Column("countPerDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("message", new TableInfo.Column("message", "TEXT", true, 0, "''", TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoryTable.put("historyId", new TableInfo.Column("historyId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistoryTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHistoryTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHistoryTable = new TableInfo("history_table", _columnsHistoryTable, _foreignKeysHistoryTable, _indicesHistoryTable);
        final TableInfo _existingHistoryTable = TableInfo.read(_db, "history_table");
        if (! _infoHistoryTable.equals(_existingHistoryTable)) {
          return new RoomOpenHelper.ValidationResult(false, "history_table(com.project.rydrotin.local.entities.History).\n"
                  + " Expected:\n" + _infoHistoryTable + "\n"
                  + " Found:\n" + _existingHistoryTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMoodTable = new HashMap<String, TableInfo.Column>(5);
        _columnsMoodTable.put("username", new TableInfo.Column("username", "TEXT", true, 0, "'user'", TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodTable.put("value", new TableInfo.Column("value", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodTable.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodTable.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodTable.put("moodId", new TableInfo.Column("moodId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMoodTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMoodTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMoodTable = new TableInfo("mood_table", _columnsMoodTable, _foreignKeysMoodTable, _indicesMoodTable);
        final TableInfo _existingMoodTable = TableInfo.read(_db, "mood_table");
        if (! _infoMoodTable.equals(_existingMoodTable)) {
          return new RoomOpenHelper.ValidationResult(false, "mood_table(com.project.rydrotin.local.entities.Mood).\n"
                  + " Expected:\n" + _infoMoodTable + "\n"
                  + " Found:\n" + _existingMoodTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e2dc5529173e5abdf54cf202ff7e837f", "3c0c3f8ca153394cafa2fe6a86e07c59");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "habits_table","history_table","mood_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `habits_table`");
      _db.execSQL("DELETE FROM `history_table`");
      _db.execSQL("DELETE FROM `mood_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HabitsDAO.class, HabitsDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(HistoryDAO.class, HistoryDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(MoodDAO.class, MoodDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList(new HabitsDatabase_AutoMigration_3_4_Impl());
  }

  @Override
  public HabitsDAO habitsDAO() {
    if (_habitsDAO != null) {
      return _habitsDAO;
    } else {
      synchronized(this) {
        if(_habitsDAO == null) {
          _habitsDAO = new HabitsDAO_Impl(this);
        }
        return _habitsDAO;
      }
    }
  }

  @Override
  public HistoryDAO historyDAO() {
    if (_historyDAO != null) {
      return _historyDAO;
    } else {
      synchronized(this) {
        if(_historyDAO == null) {
          _historyDAO = new HistoryDAO_Impl(this);
        }
        return _historyDAO;
      }
    }
  }

  @Override
  public MoodDAO moodDAO() {
    if (_moodDAO != null) {
      return _moodDAO;
    } else {
      synchronized(this) {
        if(_moodDAO == null) {
          _moodDAO = new MoodDAO_Impl(this);
        }
        return _moodDAO;
      }
    }
  }
}
